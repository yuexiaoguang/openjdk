#include "ObjectList.h"
#include "awtmsg.h"

///////////////////////////////////////////////////////////////////////////
// AwtObject list -- track all created widgets for cleanup and debugging

AwtObjectList theAwtObjectList;

AwtObjectList::AwtObjectList()
{
    m_head = NULL;
}

void AwtObjectList::Add(AwtObject* obj)
{
    CriticalSection::Lock l(m_lock);

    /* Verify that the object is not already in the list. */
    DASSERT(LookUp(obj) == NULL);

    AwtObjectListItem* item = new AwtObjectListItem(obj);
    item->next = m_head;
    m_head = item;
}

BOOL AwtObjectList::Remove(AwtObject* obj)
{
    CriticalSection::Lock l(m_lock);

    AwtObjectListItem* item = m_head;
    AwtObjectListItem* lastItem = NULL;

    while (item != NULL) {
        if (item->obj == obj) {
            if (lastItem == NULL) {
                m_head = item->next;
            } else {
                lastItem->next = item->next;
            }
            DASSERT(item != NULL);
            delete item;
            return TRUE;
        }
        lastItem = item;
        item = item->next;
    }

    return FALSE;

//    DASSERT(FALSE);  // should never get here...
                      // even if it does it shouldn't be fatal.
}

#ifdef DEBUG
AwtObject* AwtObjectList::LookUp(AwtObject* obj)
{
    CriticalSection::Lock l(m_lock);

    AwtObjectListItem* item = m_head;

    while (item != NULL) {
        if (item->obj == obj) {
            return obj;
        }
        item = item->next;
    }
    return NULL;
}
#endif /* DEBUG */

void AwtObjectList::Cleanup()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    CHECK_IS_TOOLKIT_THREAD()

    CriticalSection::Lock l(theAwtObjectList.m_lock);

    CriticalSection &syncCS = AwtToolkit::GetInstance().GetSyncCS();
    BOOL entered = syncCS.TryEnter();
    if (entered) {
        AwtObjectListItem* item = theAwtObjectList.m_head;
        while (item != NULL) {
            // AwtObject::Dispose() method will call AwtObjectList::Remove(),
            // which will delete the item structure.
            AwtObjectListItem* next = item->next;
            // destructor for item->obj will be called from item->obj->Dispose() method
            item->obj->Dispose();
            item = next;
        }
        theAwtObjectList.m_head = NULL;
        syncCS.Leave();
    } else {
        AwtToolkit::GetInstance().PostMessage(WM_AWT_OBJECTLISTCLEANUP, NULL, NULL);
    }
}

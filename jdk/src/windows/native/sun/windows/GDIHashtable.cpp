#include "GDIHashtable.h"
#include "awt_GDIObject.h"

GDIHashtable::BatchDestructionManager GDIHashtable::manager;

/*
 * The order of monitor entrance is BatchDestructionManager->List->Hashtable.
 * GDIHashtable::put() and GDIHashtable::release() are designed to be called
 * only when we are synchronized on the BatchDestructionManager lock.
 */

void* GDIHashtable::put(void* key, void* value) {
    manager.decrementCounter();
    return Hashtable::put(key, value);
}

void GDIHashtable::release(void* key) {
    if (!manager.isBatchingEnabled()) {
        void* value = remove(key);
        DASSERT(value != NULL);
        m_deleteProc(value);
    }
}

void GDIHashtable::flush() {

    CriticalSection::Lock l(lock);

    for (int i = capacity; i-- > 0;) {
        HashtableEntry* prev = NULL;
        for (HashtableEntry* e = table[i] ; e != NULL ; ) {
            AwtGDIObject* pGDIObject = (AwtGDIObject*)e->value;
            if (pGDIObject->GetRefCount() <= 0) {
                if (prev != NULL) {
                    prev->next = e->next;
                } else {
                    table[i] = e->next;
                }
                count--;
                HashtableEntry* next = e->next;
                if (m_deleteProc) {
                    (*m_deleteProc)(e->value);
                }
                delete e;
                e = next;
            } else {
                prev = e;
                e = e->next;
            }
        }
    }
}

void GDIHashtable::List::flushAll() {

    CriticalSection::Lock l(m_listLock);

    for (ListEntry* e = m_pHead; e != NULL; e = e->next) {
        e->table->flush();
    }
}

void GDIHashtable::List::add(GDIHashtable* table) {

    CriticalSection::Lock l(m_listLock);

    ListEntry* e = new ListEntry;
    e->table = table;
    e->next = m_pHead;
    m_pHead = e;
}

void GDIHashtable::List::remove(GDIHashtable* table) {

    CriticalSection::Lock l(m_listLock);

    ListEntry* prev = NULL;
    for (ListEntry* e = m_pHead; e != NULL; prev = e, e = e->next) {
        if (e->table == table) {
            if (prev != NULL) {
                prev->next = e->next;
            } else {
                m_pHead = e->next;
            }
            delete e;
            return;
        }
    }
}

void GDIHashtable::List::clear() {

    CriticalSection::Lock l(m_listLock);

    ListEntry* e = m_pHead;
    m_pHead = NULL;
    while (e != NULL) {
        ListEntry* next = e->next;
        delete e;
        e = next;
    }
}

GDIHashtable::BatchDestructionManager::BatchDestructionManager(UINT nFirstThreshold,
                                                               UINT nSecondThreshold,
                                                               UINT nDestroyPeriod) :
  m_nFirstThreshold(nFirstThreshold),
  m_nSecondThreshold(nSecondThreshold),
  m_nDestroyPeriod(nDestroyPeriod),
  m_nCounter(0),
  m_bBatchingEnabled(TRUE)
{
}

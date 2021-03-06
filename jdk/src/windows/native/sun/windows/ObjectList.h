#ifndef OBJECTLIST_H
#define OBJECTLIST_H

#include "awt.h"
#include "awt_Toolkit.h"
#include "awt_Object.h"

class AwtObjectListItem {
public:
    INLINE AwtObjectListItem(AwtObject* c) {
        obj = c;
        next = NULL;
    }

    AwtObject* obj;
    AwtObjectListItem* next;
};

class AwtObjectList {
public:
    AwtObjectList();

    void Add(AwtObject* obj);
    BOOL Remove(AwtObject* obj);
#ifdef DEBUG
    /* Used for sanity checks only. */
    AwtObject* LookUp(AwtObject* obj);
#endif /* DEBUG */
    static void Cleanup(void);

    AwtObjectListItem* m_head;
    CriticalSection    m_lock;
};

extern AwtObjectList theAwtObjectList;

#endif // OBJECTLIST_H

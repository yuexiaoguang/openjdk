#ifndef AWT_NEW_H
#define AWT_NEW_H

#include "awt.h"


// This class is used for establishing and implementing an operator new/
// malloc out of memory handler. The handler attempts to correct the
// out of memory condition by initiating a Java GC.
class NewHandler {
public:
    static void init();

private:
    // Don't construct instances of this class.
    NewHandler();

    static int handler(size_t);

};

#endif /* AWT_NEW_H */

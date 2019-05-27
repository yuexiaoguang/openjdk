package sun.misc;

/** @deprecated */
@Deprecated
public interface VMNotification {

    // when the vm switches allocation states, we get notified
    // (possible semantics: if the state changes while in this
    // notification, don't recursively notify).
    // oldState and newState may be the same if we are just releasing
    // suspended threads.
    void newAllocState(int oldState, int newState,
                       boolean threadsSuspended);
}

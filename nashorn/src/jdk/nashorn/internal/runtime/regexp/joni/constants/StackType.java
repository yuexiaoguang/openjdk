package jdk.nashorn.internal.runtime.regexp.joni.constants;

public interface StackType {
    /** stack **/
    final int INVALID_STACK_INDEX           = -1;

    /* stack type */
    /* used by normal-POP */
    final int ALT                           = 0x0001;
    final int LOOK_BEHIND_NOT               = 0x0002;
    final int POS_NOT                       = 0x0003;
    /* handled by normal-POP */
    final int MEM_START                     = 0x0100;
    final int MEM_END                       = 0x8200;
    final int REPEAT_INC                    = 0x0300;
    final int STATE_CHECK_MARK              = 0x1000;
    /* avoided by normal-POP */
    final int NULL_CHECK_START              = 0x3000;
    final int NULL_CHECK_END                = 0x5000;  /* for recursive call */
    final int MEM_END_MARK                  = 0x8400;
    final int POS                           = 0x0500;  /* used when POP-POS */
    final int STOP_BT                       = 0x0600;  /* mark for "(?>...)" */
    final int REPEAT                        = 0x0700;
    final int CALL_FRAME                    = 0x0800;
    final int RETURN                        = 0x0900;
    final int VOID                          = 0x0a00;  /* for fill a blank */

    /* stack type check mask */
    final int MASK_POP_USED                 = 0x00ff;
    final int MASK_TO_VOID_TARGET           = 0x10ff;
    final int MASK_MEM_END_OR_MARK          = 0x8000;  /* MEM_END or MEM_END_MARK */
}

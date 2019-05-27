
    # Possibilities:
    # -- membar
    # -- CAS (SP + BIAS, G0, G0)
    # -- wr %g0, %asi

    .globl SpinPause
    .type   SpinPause,@function
    .align  32
SpinPause:      
    retl
    mov %g0, %o0

   .globl _Copy_conjoint_jlongs_atomic
   .type   _Copy_conjoint_jlongs_atomic,@function
_Copy_conjoint_jlongs_atomic:
        cmp     %o0, %o1
	bleu    4f
	sll     %o2, 3, %o4
        ba      2f
   1:
	subcc   %o4, 8, %o4
	std     %o2, [%o1]
	add     %o0, 8, %o0
	add     %o1, 8, %o1
   2:
	bge,a   1b
	ldd     [%o0], %o2
	ba      5f
        nop
   3:
	std     %o2, [%o1+%o4]
   4:
	subcc   %o4, 8, %o4
	bge,a   3b
	ldd     [%o0+%o4], %o2
   5:      
	retl
	nop


    .globl _flush_reg_windows
    .align 32
 _flush_reg_windows:
        ta 0x03
        retl
        mov     %fp, %o0


Obj_Files += linux_arm.o

ifneq ($(EXT_LIBS_PATH),)
  LIBS += $(EXT_LIBS_PATH)/sflt_glibc.a 
endif

CFLAGS += -DVM_LITTLE_ENDIAN

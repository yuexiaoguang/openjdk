
!include local.make

!ifdef ADLC_ONLY
SUBDIRS=generated
!else
SUBDIRS=generated $(BUILD_FLAVOR)
!endif

!include $(WorkSpace)/make/windows/makefiles/shared.make

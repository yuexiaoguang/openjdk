
DEFAULTACTIONS=clean post_update create

default:: $(SUBDIRS)

!ifndef DIR
DIR=.
!endif

!ifndef CXX
CXX=cl.exe
!endif


!ifdef SUBDIRS
# \ is used below because $(MAKE) is nmake here, which expects Windows paths
$(SUBDIRS): FORCE
	@if not exist $@ mkdir $@
	@if not exist $@/local.make echo # Empty > $@/local.make
	@echo nmake $(ACTION) in $(DIR)/$@
	cd $@ && $(MAKE) -NOLOGO -f $(WorkSpace)\make\windows\makefiles\$@.make $(ACTION) DIR=$(DIR)\$@ BUILD_FLAVOR=$(BUILD_FLAVOR)
!endif

# Creates the needed directory
create::
!if "$(DIR)" != "."
	@echo mkdir $(DIR)
!endif

# Epilog to update for generating derived files
post_update::

# Removes scrap files
clean:: FORCE
	-@rm -f *.OLD *.publish

# Remove all scrap files and all generated files
pure:: clean
	-@rm -f *.OLD *.publish

$(DEFAULTACTIONS) $(ACTIONS)::
!ifdef SUBDIRS
	@$(MAKE) -nologo ACTION=$@ DIR=$(DIR)
!endif

FORCE:



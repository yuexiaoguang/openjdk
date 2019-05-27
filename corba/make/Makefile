# Locate this Makefile
ifeq ($(filter /%, $(lastword $(MAKEFILE_LIST))), )
  makefile_path := $(CURDIR)/$(lastword $(MAKEFILE_LIST))
else
  makefile_path := $(lastword $(MAKEFILE_LIST))
endif
repo_dir := $(patsubst %/make/Makefile, %, $(makefile_path))

# What is the name of this subsystem (langtools, corba, etc)?
subsystem_name := $(notdir $(repo_dir))

# Try to locate top-level makefile
top_level_makefile := $(repo_dir)/../Makefile
ifneq ($(wildcard $(top_level_makefile)), )
  $(info Will run $(subsystem_name) target on top-level Makefile)
  $(info WARNING: This is a non-recommended way of building!)
  $(info ===================================================)
else
  $(info Cannot locate top-level Makefile. Is this repo not checked out as part of a complete forest?)
  $(error Build from top-level Makefile instead)
endif

all:
	@$(MAKE) -f $(top_level_makefile) $(subsystem_name)

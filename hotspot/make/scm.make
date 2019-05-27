
# Prune out all known SCM (Source Code Management) directories
# so they will not appear on -I PATHs, when copying directory trees,
# packaging up .jar files, etc.  This applies to all workspaces.
#
SCM_DIRS = -name .hg -o -name .svn -o -name CVS -o -name RCS -o -name SCCS -o -name Codemgr_wsdata -o -name deleted_files

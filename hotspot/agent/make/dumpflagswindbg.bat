@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.FlagDumper %1 %2

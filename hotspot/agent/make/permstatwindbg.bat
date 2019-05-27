@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.PermStat %1 %2

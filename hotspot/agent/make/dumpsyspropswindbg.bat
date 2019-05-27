@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.SysPropsDumper %1 %2

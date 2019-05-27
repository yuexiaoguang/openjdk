@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.StackTrace %1 %2

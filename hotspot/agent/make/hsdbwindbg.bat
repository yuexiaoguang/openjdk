@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.HSDB %1 %2

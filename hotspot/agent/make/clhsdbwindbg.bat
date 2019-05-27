@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.CLHSDB %1 %2

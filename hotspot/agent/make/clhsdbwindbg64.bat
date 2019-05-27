@echo off

call saenv64.bat

%SA_JAVA_CMD% sun.jvm.hotspot.CLHSDB %1 %2

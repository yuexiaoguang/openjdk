@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.ObjectHistogram %1 %2

@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.PStack %1 %2

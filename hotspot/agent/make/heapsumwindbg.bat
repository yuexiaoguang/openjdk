@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.HeapSummary %1 %2

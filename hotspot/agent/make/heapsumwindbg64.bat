@echo off

call saenv64.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.HeapSummary %1 %2

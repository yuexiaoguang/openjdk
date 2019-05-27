@echo off

call saenv64.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.HeapDumper %1 %2 %3 %4

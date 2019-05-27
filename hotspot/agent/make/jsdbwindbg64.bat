@echo off

call saenv64.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.soql.JSDB %1 %2

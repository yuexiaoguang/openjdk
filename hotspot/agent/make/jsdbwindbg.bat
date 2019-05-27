@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.soql.JSDB %1 %2

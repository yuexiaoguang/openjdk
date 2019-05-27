@echo off

call saenv.bat

%SA_JAVA_CMD% sun.jvm.hotspot.tools.soql.SOQL %1 %2

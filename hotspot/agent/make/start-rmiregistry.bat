@echo off

REM check for .\sa.jar, if it does not exist
REM assume that we are in build configuration.

if not exist .\sa.jar goto IN_BUILD_CONF
set CLASSPATH=.\sa.jar
goto EXEC_CMD

:IN_BUILD_CONF
set CLASSPATH=..\build\classes

:EXEC_CMD
start rmiregistry -J-Xbootclasspath/p:%CLASSPATH%

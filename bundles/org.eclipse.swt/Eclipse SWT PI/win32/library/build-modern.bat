@echo off

if %PROCESSOR_ARCHITECTURE%==x86 goto x86_error

rem Defaults for Java and Windows SDK
if "x.%JAVA_HOME%"=="x." for /d %%i in ("%ProgramFiles%\Java\jdk1.6.0_*") do set JAVA_HOME=%%i
for /d %%i in ("%JAVA_HOME%") do set JAVA_HOME=%%~si
if "x.%MSSDK%"=="x." for /d %%i in ("%ProgramFiles%\Microsoft SDKs\Windows\v7*") do set MSSDK=%%i
if "x.%OUTPUT_DIR%"=="x." set OUTPUT_DIR=..\..\..\..\..\eclipse.platform.swt.binaries\bundles\org.eclipse.swt.win32.win32.x86_64

rem Only support x86_64
set CFLAGS=%CFLAGS% -DJNI64

rem Patch for broken build
set CFLAGS=%CFLAGS% -D_BP_PAINTPARAMS=_BP_PAINTPARAMS -D_DTTOPTS=_DTTOPTS -wd4100

echo.
call "%MSSDK%\Bin\SetEnv" /Release /x64 /Vista

echo.
echo JAVA_HOME  = %JAVA_HOME%
echo MSSDK      = %MSSDK%
echo CFLAGS     = %CFLAGS%
echo OUTPUT_DIR = %OUTPUT_DIR%

echo.
echo Building ...

nmake -f make_win32.mak AWT_LIBS="%JAVA_HOME%\lib\jawt.lib" %1 %2 %3 %4 %5 %6 %7 %8 %9

goto done

:x86_error

echo x86 is not supported. Please run from an AMD64 command prompt window.

:done

pause
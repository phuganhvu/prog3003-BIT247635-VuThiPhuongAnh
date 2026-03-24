@echo off
echo Compiling and running Exercise 1...
cd /d "%~dp0"
cd ..
javac Exercise1\Main.java
if %ERRORLEVEL% equ 0 (
    java Main
) else (
    echo Compilation failed!
)
pause

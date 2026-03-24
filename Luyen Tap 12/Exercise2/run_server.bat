@echo off
echo Starting Chat Server...
cd /d "%~dp0"
mvn exec:java
pause

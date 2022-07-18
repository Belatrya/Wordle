@echo off

call mvn clean package >nul

java -jar target\wordle-0.1.0.jar

pause

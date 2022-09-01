@echo off

if exist "%CATALINA_HOME%\bin\catalina.bat" (
	call mvn clean package >nul

	copy .\target\wordle-0.1.0.war %CATALINA_HOME%\webapps >Nul
	
	cd /d %CATALINA_HOME%\bin\
	
	call catalina.bat run
	
) else (
	echo The CATALINA_HOME environment variable is not defined correctly
	echo This environment variable is needed to run this program
)

pause

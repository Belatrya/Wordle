@echo off

set CATALINA_HOME=%1

if exist "%CATALINA_HOME%\bin\catalina.bat" (
	call mvn clean package >nul

	copy .\target\wordle-0.1.0.war %CATALINA_HOME%\webapps >Nul
	
	cd /d %CATALINA_HOME%\bin\
	
	call catalina.bat run
	
) else (
	echo The Tomcat directory is not defined correctly, please specify it as the first parameter for the "run.bat"
)

pause

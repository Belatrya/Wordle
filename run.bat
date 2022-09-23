@echo off

if exist "%POSTGRES_DB%" (

    cd /d %POSTGRES_DB%
	
	@echo creating the 'wordle' db if it doesn't exist, please enter the password for the 'postgres' user 
	
	createdb -h localhost -p 5432 -U postgres wordle >nul
	
    ) else (
    echo The POSTGRES_DB environment variable is not defined correctly
    echo This environment variable is needed to run this program
)

if exist "%CATALINA_HOME%\bin\catalina.bat" (

	cd /d %~dp0
	
    call mvn clean package 

    copy .\target\wordle-0.1.0.war %CATALINA_HOME%\webapps >Nul
    
    cd /d %CATALINA_HOME%\bin\
    
    call catalina.bat run
	
    ) else (
    echo The CATALINA_HOME environment variable is not defined correctly
    echo This environment variable is needed to run this program
)
pause

@echo off

IF EXIST .\out\production (
    del  .\out\production /q
    )

javac -d .\out\production\Wordle -sourcepath src\main\java -classpath src\main\resources\spring\* src\main\java\Main.java

IF NOT EXIST .\out\resources (
	md .\out\resources\dictionary
	md .\out\resources\spring
    )

copy .\src\main\resources\dictionary .\out\resources\dictionary >NUL
copy .\src\main\resources\spring .\out\resources\spring >NUL
copy .\src\main\resources\application.properties .\out\resources\application.properties >NUL

java -cp .\out\production\Wordle;.\out\resources\spring\*;.\out\resources Main
pause
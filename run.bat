@echo off

IF EXIST .\out\production (
    del  .\out\production /q
    )

javac -d .\out\production\Wordle -sourcepath src\main\java -classpath src\main\resources\spring\* src\main\java\Main.java

robocopy .\src\main\resources .\out\resources  /E >Nul

java -cp .\out\production\Wordle;.\out\resources\spring\*;.\out\resources Main
pause
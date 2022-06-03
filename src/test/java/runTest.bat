@echo off
javac -d .\..\..\..\out\test\Wordle -classpath .;.\..\..\..\out\production\Wordle -sourcepath java TestMain.java

java -classpath .\..\..\..\out\test\Wordle;.\..\..\..\out\production\Wordle TestMain
pause
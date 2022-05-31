@echo off
javac -d .\out\production\Wordle -sourcepath src\main\java src\main\java\Main.java
java -cp .\out\production\Wordle Main
pause 
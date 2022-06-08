@echo off
javac -d .\out\production\Wordle -sourcepath src\main\java src\main\java\Main.java

IF NOT EXIST .\out\resources (
	md .\out\resources
	)
copy .\src\main\resources\dictionary\russian5letterwords.txt .\out\resources >NUL
copy .\src\main\resources\dictionary\hiddenWords.txt .\out\resources >NUL

java -cp .\out\production\Wordle Main
pause
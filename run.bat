@echo off
javac -d .\out\production\Wordle -sourcepath src\main\java -classpath src\main\resources\spring\spring-context-5.2.9.RELEASE.jar;src\main\resources\spring\spring-beans-5.2.9.RELEASE.jar;src\main\resources\spring\spring-core-5.2.9.RELEASE.jar;src\main\resources\spring\spring-aop-5.2.9.RELEASE.jar;src\main\resources\spring\spring-expression-5.2.9.RELEASE.jar;src\main\resources\spring\spring-jcl-5.2.9.RELEASE.jar; src\main\java\Main.java

IF NOT EXIST .\out\resources (
	md .\out\resources
	)
copy .\src\main\resources\dictionary\russian5letterwords.txt .\out\resources >NUL
copy .\src\main\resources\dictionary\hiddenWords.txt .\out\resources >NUL

java -cp .\out\production\Wordle Main
pause
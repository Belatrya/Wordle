@echo off
javac -d out/production/Wordle -sourcepath src src/main/java/base/Main.java
java -cp out/production/Wordle main.java.base.Main
pause 
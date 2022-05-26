@echo off
javac -d out/production/Wordle -sourcepath src src/base/Main.java
java -cp out/production/Wordle base.Main
pause 
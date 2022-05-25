@echo off
rem chcp 65001
javac -d out/production/Wordle -sourcepath src src/base/Game.java
java -cp out/production/Wordle base.Game
pause 
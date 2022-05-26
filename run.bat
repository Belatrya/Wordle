@echo off
javac -d out/production/Wordle -sourcepath src src/base/Game.java
java -cp out/production/Wordle base.Game
pause 
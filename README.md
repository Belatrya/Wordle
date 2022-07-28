# Wordle
Wordle console game

Welcome to the game Wordle!
There is the hidden word from 5 letters, you will have 6 rounds to guess it.
Every round you will need to type 1 existing word.
If your word doesn't equal to the hidden, there will be description for every letter:
 - WRONG - letter doesn't exist in the hidden word
 - NOT REALLY - letter exists but not on the right place
 - CORRECT - letter exists and is on the right place
 
Let's start and good luck!

How to start playing:
1) You will need to set up the Apache Tomcat (https://tomcat.apache.org/) version 9 or later.
2) Run the "run.bat" file with the argument - an absolute path to installed Tomcat (i.e. run.bat C:\tomcat\apache-tomcat-9.0.64\apache-tomcat-9.0.64). If there is no "bin" directory on the specified path, you will see an error.
3) Open your browser on the page http://localhost:8080/wordle-0.1.0/wordle.
4) Enjoy the game!
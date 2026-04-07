@echo off
echo Compiling the Poker Hand Evaluator...
if not exist bin mkdir bin
javac -d bin src\main\java\com\poker\*.java

echo Running the Simulator...
echo ===================================
java -cp bin com.poker.Main
echo ===================================
pause

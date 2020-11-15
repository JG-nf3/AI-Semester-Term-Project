# AI-Semester-Term-Project

## By James Granata & Quinn Kleinfelter

This project is the term project for EECS 4740 Artificial Intelligence taught by Dr. Serpen at the University of Toledo 
in Fall 2020.
We implement the board game Conga, as well as a random agent and minimax with alpha-beta pruning agent to play the game.

## Usage
To run this project you have a few options, for development we used IntelliJ IDEA Ultimate 2020.2.3's debugging tools,
 though building an executable JAR, such as through IntelliJ, or running the executable JAR provided with this README
 in the University of Toledo OneDrive will work.

If you are running this program from the executable JAR provided on the University of Toledo OneDrive folder, you will
notice that our program is modified to run a single game with a 200 ms delay between each move. This is to ensure that
you can properly see each move being made by both agents. Our program will also provide various statistics about the game
after it completes in the command line interface you ran it from.

## Running the Project from a provided JAR
1. Download the folder on the University of Toledo OneDrive, containing this README file and an executable "Conga.jar".
2. Move the folder to the desired location on your computer.
3. Open a command prompt in the desired location, or navigate to the desired location in a command prompt.
4. Run `java -jar Conga.jar`
5. Observe the game between the Random Agent (Player 1) and the AI Agent (Player 2)
6. Once the game is complete, observe the output in the command prompt showing the statistics about the game that was played.
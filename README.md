# Snake
***** EXECUTION *****

The execution is java SnakeGame. Once in the game, press "p" to play. To 
move the snake, use the "W", "A", "S", "D" keys to move up, left, down, 
or right. Try to get the highest score possible by eating food. Sometimes, 
a differently colored fruit may spawn, which will add two points to your 
score. Avoid the walls, the obstacle, and the snake's own body or the game 
will end. To try out the hard mode, press "h". This causes the snake to 
move faster, so it will be harder to manipulate it. To go back to normal 
mode, press "n". When the game ends, press "r" to replay, and you will be 
guided back to the start page, where it will display your highest score.

***** CODE *****

Board.java - A class representing the board in which the Snake game 
takes place. Keeps track of the game's Snake and receives the player's 
input to control the snake.

Snake.java - A class that represents the snake in Snake Game, where it 
can update its own position based on which direction the user inputs. 
The snake moves forward by moving the tail to the front of its head. It 
checks if it has collided with itself if the x and y coordinates of 
the head equals that of its body.

Segment.java - A class that acts as a node and builds the snake.

SnakeGame.java - Represents the Snake game, where the player maneuvers a snake 
to grow larger by eating food and avoiding obstacles. The game runs until 
the snake collides with the wall, itself, or an obstacle. Player can choose 
to restart the game through user input.

***** FEATURES *****

Hard mode - switch to hard mode during game play by pressing "h" and 
switch back to normal mode by pressing "n"

Bonus food - There is a 40% chance that each food will be bonus, which 
adds two points to the score and is denoted by a magenta circle labeled 
+2

Start page - Before playing the game, the user is greeted with a start 
page. To proceed, the user should press "p" to play

Obstacle - In the game, there will be a random obstacle placed on the 
board that will cause the player to lose if hit


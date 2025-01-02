/*  Name: Yifan Li
*  PennKey: yifanali
*
*  Execution: java SnakeGame
*
*  Represents the Snake game, where the player manuevers a snake
*  to grow larger by eating food and avoiding obstacles. Runs
*  the game until the snake collides with the wall, itself, or an
*  obstacle. User can choose to restart the game.
*
*/

public class SnakeGame {
    
    public static void main(String[] args) {
        while (true) {
            // Instantiate a board
            Board board = new Board();
            // Draw title screen
            board.drawTitleScreen();
            // Game runs at 5 frames per second
            PennDraw.enableAnimation(5);
            // Advance PennDraw until p is pressed
            while (!PennDraw.hasNextKeyTyped() || 
                !(PennDraw.nextKeyTyped() == 'p')) {
                PennDraw.advance();
            }
            
            while (!board.gameOver()) {
                // Clear screen
                PennDraw.clear();
                // Update board and all of its components, such as
                // snake, background, food, and obstacles.
                board.update();
                // Draw updated board and components
                board.draw();
            }
            // Game is over when while loop is finished. The board will display
            // a game complete screen. PennDraw is advanced until r is 
            // pressed to restart the game.
            board.drawGameCompleteScreen();
            while (!PennDraw.hasNextKeyTyped() || 
                !(PennDraw.nextKeyTyped() == 'r')) {
                PennDraw.advance();
            }
        }
    }
}

/*  Name: Yifan Li
*  PennKey: yifanali
*  Execution: N/A, this class is meant to be used by other classes
*
*  A class representing the board in which the Snake
*  game takes place. Keeps track of the game's Snake and
*  receives the player's input to control the snake.
*
*/

public class Board {
    
    // Declare instance variables
    private final int gridSize = 12; // size of grid
    
    private Snake snake; // snake
    private String direction; // direction for snake
    
    private int score; // current score
    private static int highScore = 0; // highest score
    
    private Segment obstacle; // obstacle
    
    private Segment food; // food
    private boolean eaten; // if food is eaten
    private boolean bonus; // if food is bonus
    
    // Constructor
    public Board() {
        snake = new Snake(0, 0);
        direction = "";
        
        score = 0;
        
        obstacle = generateObstacle();
        
        food = generateFood();
        eaten = false;
        bonus = false;
        
        // Set canvas size and scale
        PennDraw.setCanvasSize(600, 700);
        PennDraw.setXscale(-1, gridSize + 1);
        PennDraw.setYscale(-2, gridSize + 1);
    }
    
    /**
    * 1. Randomly select obstacle x and y coordinates
    * 2. Check if selected coordinates overlap with any snake segment
    * 3. Reselect random obstacle coordinates if true
    * 4. Otherwise generate obstacle
    * Input: None 
    * Output: Segment
    */
    private Segment generateObstacle() {
        int obsX = (int) ((gridSize - 1) * Math.random() + 1);
        int obsY = (int) ((gridSize - 1) * Math.random() + 1);
        
        Segment curr = snake.getHead();
        while (curr != null) {
            if (curr.x() == obsX && curr.y() == obsY) {
                return generateObstacle();
            }
            curr = curr.next();
        }
        return new Segment(obsX, obsY);
    }
    
    /**
    * 1. Randomly select food x and y coordinates
    * 2. Check if selected coordinates overlap with any snake segment
    * 3. Reselect random food coordinates if true
    * 4. Otherwise generate food
    * Input: None 
    * Output: Segment
    */
    private Segment generateFood() {
        int foodX = (int) (gridSize * Math.random());
        int foodY = (int) (gridSize * Math.random());
        
        Segment curr = snake.getHead();
        while (curr != null) {
            if (curr.x() == foodX && curr.y() == foodY) {
                generateFood();
            }
            curr = curr.next();
        }
        if (foodX == obstacle.x() && foodY == obstacle.y()) {
            generateFood();
        }
        return new Segment(foodX, foodY);
    }
    
    /**
    * 1. Return true if snake collides with obstacle
    * 2. Otherwise return false
    * Input: None 
    * Output: boolean
    */
    private boolean collideObstacle() {
        if (snake.getHead().x() == obstacle.x() && 
            snake.getHead().y() == obstacle.y()) {
            return true;
        }
        return false;
    }
    
    /**
    * 1. Return true if snake head collides with wall
    * 2. Otherwise return false
    * Input: None
    * Output: boolean
    */
    private boolean collideWall() {
        if (snake.getHead().x() < 0 || snake.getHead().x() >= gridSize || 
            snake.getHead().y() < 0 || snake.getHead().y() >= gridSize) {
            return true;
        }
        return false;
    }
    
    /**
    * 1. Return true if snake collides with self, wall, or obstacle
    * 2. If score is higher than highestScore, set highestScore equal to score
    * 3. Otherwise return false
    * Input: None 
    * Output: boolean
    */
    public boolean gameOver() {
        if (snake.collideSelf() || collideWall() || collideObstacle()) {
            if (score > highScore) {
                highScore = score;
            }
            return true;
        }
        return false;
    }
    
    /**
    * 1. Update direction when a specific key is pressed
    * 2. Update speed of game when a specific key is pressed
    * 3. When snake eats food
    *      3.1. Update score depending on its bonus status
    *      3.2. Regenerate food
    * 4. Move the snake
    */
    public void update() {
        if (PennDraw.hasNextKeyTyped()) {
            char c = PennDraw.nextKeyTyped();
            if (c == 'w' && !direction.equals("down")) {
                direction = "up";
            }
            if (c == 's' && !direction.equals("up"))  {
                direction = "down";
            }
            if (c == 'd' && !direction.equals("left")) {
                direction = "right";
            }
            if (c == 'a' && !direction.equals("right")) {
                direction = "left";
            }
            if (c == 'h') {
                PennDraw.enableAnimation(10);
            }
            if (c == 'n') {
                PennDraw.enableAnimation(5);
            }
        }
        
        // check if food is eaten
        if (snake.getHead().x() == food.x() && snake.getHead().y() == food.y()) {
            if (bonus == true) {
                score += 2;
            } else {
                // add score
                score++;
            }
            
            // 0.4 chance for bonus food
            if (Math.random() < 0.4) {
                bonus = true;
            } else {
                bonus = false;
            }
            // regenerate food
            food = generateFood();
            // snake grow
            eaten = true;
        } else {
            eaten = false;
        }
        
        if (!direction.equals("")) {
            snake.move(direction, eaten); // move snake if direction pressed
        }
        
    }
    
    /**
    * 1. Draw background walls and grids
    * 2. Draw snake
    * 3. Draw obstacles
    * 4. Draw food and change color if bonus
    * 5. Draw caption underneath the game
    */
    public void draw() {
        // Clear the screen
        PennDraw.clear();
        //Draw background walls
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(0.016);
        PennDraw.line(0, 0, 0, gridSize); // vertical left
        PennDraw.line(0, 0, gridSize, 0); // horizontal bottom
        PennDraw.line(0, gridSize, gridSize, gridSize); // horizontal top
        PennDraw.line(gridSize, 0, gridSize, gridSize); // vertical right
        PennDraw.setPenRadius(0.005);
        // Draw background grids
        for (int i = 0; i < gridSize; i++) {  // cavas size / gridSize
            for (int j = 0; j < gridSize; j++) {
                PennDraw.setPenColor(PennDraw.GRAY);
                PennDraw.filledSquare(i + 0.5, j  + 0.5, 0.45);
            }
        }
        
        snake.draw(); // draw snake
        
        // draw obstacles
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledSquare(obstacle.x() + 0.5, obstacle.y() + 0.5, 0.4);
        
        if (bonus == false) {
            PennDraw.setPenColor(PennDraw.RED);
            // draw normal food
            PennDraw.filledCircle(food.x() + 0.5, food.y() + 0.5, 0.5);
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.text(food.x() + 0.5, food.y() + 0.5, "+1");
        } else {
            PennDraw.setPenColor(PennDraw.MAGENTA);
            // draw bonus food
            PennDraw.filledCircle(food.x() + 0.5, food.y() + 0.5, 0.5);
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.text(food.x() + 0.5, food.y() + 0.5, "+2");
        }
        
        // caption
        PennDraw.setPenColor(PennDraw.BLACK);
        // draw highest score
        PennDraw.text(gridSize / 2, -1, "Your highest score is " + highScore);
        // draw score
        PennDraw.text(gridSize / 2, -1.35, "Your current score is " + score); 
        PennDraw.text(gridSize / 2, 12.8, "press to switch modes:");
        PennDraw.text(gridSize / 2, 12.3, "H: hard, N: normal");
        
        PennDraw.advance();
    }
    
    /**
    * Draw game complete screen
    * Input: None
    * Output: None
    */
    public void drawGameCompleteScreen() {
        PennDraw.clear();
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(gridSize / 2, gridSize / 2, "YOUR FINAL SCORE IS: " + score);
        // highest score
        PennDraw.text(gridSize / 2, -1, "Your highest score is " + highScore);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(gridSize / 2, gridSize / 2 - 1, "press R to restart");
    }
    
    /**
    * Draw title screen
    * Draw highest score
    * Input: None 
    * Output: None
    */
    public void drawTitleScreen() {
        PennDraw.clear();
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(gridSize / 2, gridSize / 2, "WELCOME TO SNAKE GAME!!!!");
        PennDraw.text(gridSize / 2, -1, "Your highest score is " + highScore);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(gridSize / 2, gridSize / 2 - 1, "press P to play");
    }
}

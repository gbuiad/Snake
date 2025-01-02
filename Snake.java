/*  Name: Yifan Li
*  PennKey: yifanali
*  Execution: N/A, this class is meant to be used by other classes
*
*  A class that represents the snake in Snake Game.
*  Can update its own position based on which direction
*  the user inputs through wasd keys. Moves by moving the
*  tail to its own head, and then removing the tail. Checks
*  if it has collided with itself if the x and y coordinates
*  of the head equals that of its body.
*
*/

public class Snake {
    // Head and tail of the snake
    private Segment head, tail;
    
    // Initialize the snake's variables
    public Snake(int initX, int initY) {
        head = new Segment(initX, initY);
        tail = head;
    }
    
    /**
    * 1. Create new x and y coordinates
    * 2. Update how coordinates will move based on direction
    * 3. Add new head in the direction the snake is moving
    * 4. Set head equal to the new head
    * 5. Remove the tail if the snake didn't eat food
    * 
    * Input: String direction, boolean eaten
    * Output: None
    */
    public void move(String direction, boolean eaten) {
        int newX = head.x();
        int newY = head.y();
        
        if (direction.equals("up")) {
            newY++;
        }
        if (direction.equals("down")) {
            newY--;
        }
        if (direction.equals("left")) {
            newX--;
        }
        if (direction.equals("right")) {
            newX++;
        }
        
        // add new head
        Segment newHead = new Segment(newX, newY);
        newHead.setNext(head);
        head = newHead;
        
        // if snake doesn't eat, don't remove tail
        if (!eaten) {
            Segment curr = head;
            while (curr.next() != tail) {
                curr = curr.next();
            }
            curr.setNext(null);
            tail = curr;
        }
    }
    
    /**
    * return head segment
    * Inputs: None
    * Outputs: Segment
    */
    public Segment getHead() {
        return new Segment(head.x(), head.y());
    }
    
    /**
    * Return true if the snake collides with itself.
    * Otherwise return false.
    * Input: None
    * Output: boolean
    */
    public boolean collideSelf() {
        Segment curr = head.next();
        while (curr != null) {
            if (curr.x() == head.x() && curr.y() == head.y()) {
                return true;
            }
            curr = curr.next();
        }
        return false;
    }
    
    /**
    * 1. Draw blue head and yellow mouth
    * 2. Draw green body part
    */
    public void draw() {
        PennDraw.setPenColor(PennDraw.BLUE);
        Segment curr = head;
        PennDraw.filledSquare(curr.x() + 0.5, curr.y() + 0.5, 0.5);
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledCircle(curr.x() + 0.5, curr.y() + 0.5, 0.25);
        PennDraw.setPenColor(PennDraw.GREEN);
        while (curr.next() != null) {
            curr = curr.next();
            PennDraw.filledSquare(curr.x() + 0.5, curr.y() + 0.5, 0.5);
        }
    }
}

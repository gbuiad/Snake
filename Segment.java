/*  Name: Yifan Li
*  PennKey: yifanali
*  Execution: N/A, this class is meant to be used by other classes
*
*  Defines segment, which acts as a Node and builds the snake.
*/

public class Segment {
    private Segment next;
    private int x, y;
    
    // Constructor
    public Segment(int x, int y) {
        this.x = x;
        this.y = y;
        this.next = null;
    }
    
    // getters
    /**
     * Inputs: None
     * Outputs: Segment
     * Description: next segment of snake body
    */
    public Segment next() {
        return next;
    }
    
    /**
     * Inputs: None
     * Outputs: Integer
     * Description: get x coordinate for snake
    */
    public int x() {
        return x;
    }
    
    /**
     * Inputs: None
     * Outputs: Integer
     * Description: get y coordinate for snake
    */
    public int y() {
        return y;
    }
    
    // setters
    /**
     * Inputs: Segment
     * Outputs: None
     * Description: next segment of snake
    */
    public void setNext(Segment next) {
        this.next = next;
    }
    
    /**
     * Inputs: Integer 
     * Outputs: None
     * Description: x coordinate for snake part
    */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Inputs: Integer
     * Outputs: None
     * Description: y coordinate for snake part
    */
    public void setY(int y) {
        this.y = y;
    }
}

import java.util.*;
import javax.swing.JOptionPane;

/**
 * this class draws the initial web without bridges
 * @author Cardenas-Cardona
 * @version 1.0
 */
public class Hilo{
    private Linea thread;
    private boolean isVisible = false;

    /**
     * Constructor for objects of class Thread with the center
     */
    public Hilo(float x2, float y2){
        this.thread = new Linea(x2,y2);
    }
    
    /**
     * Constructor for objects of class Thread
     */
    public Hilo(float x1, float y1,float x2, float y2){
        this.thread = new Linea(x1,y1,x2,y2);
    }
    
    /**
     * Updates the coordinates of the thread endpoints with the new specified coordinates
     *
     * @param newx1 The new x coordinate of the first end of the line.
     * @param newy1 The new y coordinate of the first end of the line.
     * @param newx2 The new x coordinate of the second end of the line.
     * @param newy2 The new y coordinate of the second end of the line.
     */
    public void newPoints(float newx1, float newy1,float newx2, float newy2){
        this.thread.newPoints(newx1,newy1,newx2,newy2);
    }
    
    /**
     * Updates the coordinates of the thread endpoints with the new specified coordinates
     *
     * @param newx2 The new x coordinate of the first end of the line.
     * @param newy2 The new y coordinate of the first end of the line.
     */
    public void newPoints(float newx2, float newy2){
        this.thread.newPoints(newx2, newy2);
    }
    
    /**
     * get the x1 coordenate
     * 
     * @return the value of the x1 coordenate
     */
    public float getX1(){
        return thread.getX1();
    }
    
    /**
     * get the x2 coordenate
     * 
     * @return the value of the x2 coordenate
     */
    public float getX2(){
        return thread.getX2();
    }
    
    /**
     * get the y1 coordenate
     * 
     * @return the value of the y1 coordenate
     */
    public float getY1(){
        return thread.getY1();
    }
    
    /**
     * get the y2 coordenate
     * 
     * @return the value of the y2 coordenate
     */
    public float getY2(){
        return thread.getY2();
    }
    
    /**
     * Make this Line visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        if(!isVisible){
            thread.makeVisible();
            isVisible = true;
        }
    }
    
    /**
     * Make this Line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        if(isVisible){
            thread.makeInvisible();
            isVisible = false;
        }
    }
    
    /**
     * Change the size of a thread
     * @param newSize A new percentage value
     */
    public void changeSize(int newSize){
        float x2 = this.getX2()-Spiderweb.getPoscenterImage()[0] + (this.getX2()-Spiderweb.getPoscenterImage()[0]) * (float)newSize / 100;
        float y2 = -(this.getY2()-Spiderweb.getPoscenterImage()[1]) - (this.getY2()-Spiderweb.getPoscenterImage()[1]) * (float)newSize / 100;
        newPoints(getX1(),getY1(),x2 + Spiderweb.getPoscenterImage()[0],Spiderweb.getPoscenterImage()[1] - y2);
    }
    
}

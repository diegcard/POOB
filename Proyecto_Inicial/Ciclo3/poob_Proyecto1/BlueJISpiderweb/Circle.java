import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000)
 */

public class Circle {
    public static final double PI = 3.1416;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new circle at default position with default color.
     */
    public Circle() {
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }

    /**
     * Create a new circle at a default position with default color.
     *
     * @param xPos coordenate
     * @param yPos coordenate
     */
    public Circle(int xPos, int yPos) {
        diameter = 10;
        xPosition = xPos;
        yPosition = yPos;
        color = "black";
        isVisible = false;
    }

    /**
     * move the circle to new coordenates
     *
     * @param xPos coordenate
     * @param yPos coordenate
     */
    public void relocate(int xPos, int yPos) {
        erase();
        xPosition = xPos;
        yPosition = yPos;
        if (isVisible) {
            draw();
        }
    }

    /**
     * return the color of the circle
     *
     * @return the color
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * return the color of the circle
     *
     * @return the color
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * return the color of the circle
     *
     * @return the color
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * return the color of the circle
     *
     * @return the color
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Make this Circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Draw the circle with current specifications on screen.
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                    new Ellipse2D.Double(xPosition, yPosition,
                            diameter, diameter));
            canvas.wait(10);
        }
    }

    /**
     * Erase the circle on screen.
     */
    private void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Move the circle horizontally.
     *
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     *
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     *
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance) {
        int delta;

        if (distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for (int i = 0; i < distance; i++) {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     *
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance) {
        int delta;

        if (distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for (int i = 0; i < distance; i++) {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     *
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter) {
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color.
     *
     * @param newColor the new color. Valid colors are "red", "yellow", "blue", "green",
     *                 "magenta" and "black".
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }
}
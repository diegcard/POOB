
/**
 * This class is used to create a spot object. The spot object is used to represent a spot on the spider web.
 *
 * @author Cardenas-Cardona
 * @version 1.0 - 02/03/2024
 */
public class Spot {
    private final Circle spot;
    private boolean isVisible = false;
    private int Strand;

    /**
     * Constructor for objects of class Spot
     *
     * @param xPos x position
     * @param yPos y position
     */
    public Spot(int xPos, int yPos, int strand) {
        this.spot = new Circle(xPos, yPos);
        setStrand(strand);
    }

    /**
     * Make the spot visible
     */
    public void makeVisible() {
        if (!isVisible) {
            spot.makeVisible();
            isVisible = true;
        }
    }

    /**
     * Make the spot invisible
     */
    public void makeInvisible() {
        if (isVisible) {
            spot.makeInvisible();
            isVisible = false;
        }
    }

    /**
     * Set the strand of the spot
     *
     * @param newStrand the new strand
     */
    public void setStrand(int newStrand) {
        this.Strand = newStrand;
    }

    /**
     * Get the strand of the spot
     *
     * @return the strand
     */
    public int getStrand() {
        return Strand;
    }

    /**
     * This method is used to get the x position of the spot.
     *
     * @return int This returns the x position of the spot.
     */
    public int getxPosition() {
        return spot.getxPosition();
    }

    /**
     * This method is used to get the y position of the spot.
     *
     * @return int This returns the y position of the spot.
     */
    public int getyPosition() {
        return spot.getyPosition();
    }

    /**
     * This method is used to change the color of the spot.
     *
     * @param newColor The new color for the spot.
     */
    public void changeColor(String newColor) {
        spot.changeColor(newColor);
    }

    /**
     * This method is used to relocate the spot to a new position.
     *
     * @param xPos The new x position for the spot.
     * @param yPos The new y position for the spot.
     */
    public void relocate(int xPos, int yPos) {
        spot.relocate(xPos, yPos);
    }
}

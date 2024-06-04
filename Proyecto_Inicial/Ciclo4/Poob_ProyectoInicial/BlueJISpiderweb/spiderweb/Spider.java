
package spiderweb;
import shapes.*;
import java.util.*;

/**
 * Representa una araña con componentes specificness como cabeza, cuerpo y patas.
 * Permite mover, hacer visible e invisible la araña, así como cambiar su tamaño.
 * Además, inicializa y posiciona los componentes de la araña.
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class Spider {

    private final Circle head;
    private final Circle body;
    private final ArrayList<Linea> patas;
    private boolean isVisible = false;
    private String color;

    /**
     * Class spider contructor
     * Init the new Spider with its all components
     */
    public Spider() {
        this.head = new Circle();
        this.body = new Circle();
        this.patas = new ArrayList<>();
        initDrawSpider();
    }

    /**
     * get x coordenate
     *
     * @return x coordenate
     */
    public int getPosx() {
        return body.getxPosition() + body.getDiameter() / 2;
    }

    /**
     * get y coordenate
     *
     * @return y coordenate
     */
    public int getPosy() {
        return body.getyPosition() + body.getDiameter() / 2;
    }

    /**
     * Initializes the spider by reorganizing its legs and setting the color of its body and head to red.
     * This method also repositions the head of the spider.
     */
    private void initDrawSpider() {
        reorPatas();
        body.changeColor("red");
        head.changeColor("red");
        reordenarCabeza();
    }

    /**
     * Clears the current legs of the spider and reorganizes them.
     * This method calls the methods to reorganize the front and rear legs of the spider.
     */
    private void reorPatas() {
        patas.clear();
        reorPatasDelanteras();
        reorPatasTraseras();
    }

    /**
     * Reorganizes the front legs of the spider.
     * This method creates and positions the front legs of the spider based on the current position and size of the spider's body and head.
     * Each leg is represented by a Linea object and is added to the patas ArrayList.
     */
    private void reorPatasDelanteras() {
        Linea pata1 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() + body.getDiameter() - (float) head.getDiameter() / 6, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        patas.add(pata1);
        Linea pata2 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() + (float) head.getDiameter() / 6, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        patas.add(pata2);
        Linea pata3 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 6, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 20);
        patas.add(pata3);
        Linea pata4 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() - (float) head.getDiameter() / 6, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 20);
        patas.add(pata4);
        Linea pata31 = new Linea(body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 6, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 20,
                body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 10, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        patas.add(pata31);
        Linea pata41 = new Linea(body.getxPosition() - (float) head.getDiameter() / 6, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 20,
                body.getxPosition() - (float) head.getDiameter() / 10, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        patas.add(pata41);
    }

    /**
     * Reorganizes the rear legs of the spider.
     * This method creates and positions the rear legs of the spider based on the current position and size of the spider's body and head.
     * Each leg is represented by a Linea object and is added to the patas ArrayList.
     */
    private void reorPatasTraseras() {
        Linea pata5 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 4, body.getyPosition() + (float) body.getDiameter() / 2 - (float) head.getDiameter() / 8);
        Linea pata6 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() - (float) head.getDiameter() / 4, body.getyPosition() + (float) body.getDiameter() / 2 - (float) head.getDiameter() / 8);
        patas.add(pata5);
        patas.add(pata6);
        Linea pata51 = new Linea(body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 4, body.getyPosition() + (float) body.getDiameter() / 2 - (float) head.getDiameter() / 8,
                body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 2, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        Linea pata61 = new Linea(body.getxPosition() - (float) head.getDiameter() / 4, body.getyPosition() + (float) body.getDiameter() / 2 - (float) head.getDiameter() / 8,
                body.getxPosition() - (float) head.getDiameter() / 2, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        patas.add(pata51);
        patas.add(pata61);
        Linea pata7 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 2, body.getyPosition() + (float) body.getDiameter() / 4);
        Linea pata8 = new Linea((body.getxPosition() + (float) body.getDiameter() / 2), (body.getyPosition() + (float) body.getDiameter() / 2),
                body.getxPosition() - (float) head.getDiameter() / 2, body.getyPosition() + (float) body.getDiameter() / 4);
        patas.add(pata7);
        patas.add(pata8);
        Linea pata71 = new Linea(body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 2, body.getyPosition() + (float) body.getDiameter() / 4,
                body.getxPosition() + body.getDiameter() + (float) head.getDiameter() / 3, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        Linea pata81 = new Linea(body.getxPosition() - (float) head.getDiameter() / 2, body.getyPosition() + (float) body.getDiameter() / 4,
                body.getxPosition() - (float) head.getDiameter() / 3, body.getyPosition() + body.getDiameter() + (float) head.getDiameter() / 4);
        patas.add(pata71);
        patas.add(pata81);
    }

    /**
     * Reorganizes the head of the spider.
     * This method repositions the head of the spider based on the current position and size of the spider's body.
     */
    private void reordenarCabeza() {
        head.changeSize(body.getDiameter() / 3);
        head.moveHorizontal(body.getxPosition() + body.getDiameter() / 2 - head.getDiameter() / 2 - head.getxPosition());
        head.moveVertical(body.getyPosition() + body.getDiameter() - head.getDiameter() / 2 - head.getyPosition());
    }

    /**
     * Move the spider's legs to a new position.
     *
     * @param x The new x coordinate of the legs.
     * @param y The new y coordinate of the legs.
     */
    private void movePatas(int x, int y) {
        for (Linea pata : patas) {
            pata.moveHorizontal(x);
            pata.moveVertical(y);
        }
    }

    /**
     * Move the Spider to new place
     *
     * @param x coordenate x
     * @param y coordenate y
     */
    public void moveTo(int x, int y) {
        movePatas(x, y);
        body.moveHorizontal(x);
        body.moveVertical(y);
        head.moveHorizontal(x);
        head.moveVertical(y);
    }

    /**
     * make spider visible
     */
    public void makeVisible() {
        if (!isVisible) {
            patas.forEach(Linea::makeVisible);
            body.makeVisible();
            head.makeVisible();
            isVisible = true;
        }
    }

    /**
     * make spider invisible
     */
    public void makeInvisible() {
        if (isVisible) {
            patas.forEach(Linea::makeInvisible);
            body.makeInvisible();
            head.makeInvisible();
            isVisible = false;
        }
    }

    /**
     * Change Spider Size
     *
     * @param newSize new size
     */
    public void changeSize(int newSize) {
        body.changeSize((int) (body.getDiameter() + body.getDiameter() * ((float) newSize / 100)));
        head.changeSize((int) (head.getDiameter() + head.getDiameter() * ((float) newSize / 100)));
        patas.forEach(Linea::makeInvisible);
        reorPatas();
        if (isVisible) {
            patas.forEach(Linea::makeVisible);
        }
        body.changeColor("red");
        reordenarCabeza();
    }

    /**
     * Change spider's head color to indicate when the spider is sat
     */
    public void headChangeColor(String newColor) {
        head.changeColor(newColor);
    }
    
    /**
     * Change spider's body color 
     */
    public void bodyChangeColor(String newColor) {
        body.changeColor(newColor);
        this.color = newColor;
    }
    
    /**
     * gets spider's body color 
     */
    public String getColor() {
        return color;
    }
}

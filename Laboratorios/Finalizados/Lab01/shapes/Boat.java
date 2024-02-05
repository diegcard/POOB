import java.util.*;
/**
 * La clase Boat representa un objeto bote en un plano gráfico, con elementos como una base rectangular, lados triangulares,
 * un palo vertical y un velero. El bote puede ser orientado hacia la izquierda (pozIz) o hacia la derecha (posDe).
 * Tiene métodos para manipular su posición, visibilidad, tamaño y color.
 * 
 * @author Diego Cardenas - Sebastian Cardona
 * @version 1.0.0
 */
public class Boat{
    private Rectangle base;
    private Triangle ladoIzquierdo;
    private Triangle ladoDerecho;
    private Rectangle palo;
    private Triangle velero;
    public boolean pozIz = false;
    public boolean posDe = false;
    public String orilla;
    
    /**
     * Constructor por defecto de la clase Boat.
     * Inicializa las partes del bote y configura su representación gráfica.
     */
    public Boat(){
        this.base = new Rectangle();
        this.ladoIzquierdo = new Triangle();
        this.ladoDerecho = new Triangle();    
        this.velero = new Triangle();
        this.palo = new Rectangle();   
        initDraw();
    }
    
    /**
     * Método privado que inicializa la representación gráfica del bote.
     * Configura el tamaño, posición y colores de las partes del bote.
     */
    private void initDraw(){
        base.changeSize(40, 150);
        base.moveVertical(50);
        base.changeColor("black");
        ladoIzquierdo.moveVertical(50);
        ladoIzquierdo.changeSize(45, 60);
        ladoIzquierdo.moveHorizontal(-70);
        ladoIzquierdo.changeColor("white");
        ladoDerecho.moveVertical(50);
        ladoDerecho.changeSize(45, 60);
        ladoDerecho.moveHorizontal(79);
        ladoDerecho.changeColor("white");
        velero.changeSize(70, 50);
        velero.moveHorizontal(10);
        velero.moveVertical(-38);
        velero.changeColor("red");  
        palo.changeSize(60, 20);
        palo.moveVertical(30);
        palo.moveHorizontal(70);
        palo.changeColor("black");
    }
    
    /**
     * Hace visible el objeto.
     */
    public void makeVisible(){
        base.makeVisible();
        ladoIzquierdo.makeVisible();
        ladoDerecho.makeVisible();
        palo.makeVisible();
        velero.makeVisible();
    }  
    
    /**
     * Hace invisible el objeto bote.
     */
    public void makeInvisible(){
        base.makeInvisible();
        ladoIzquierdo.makeInvisible();
        ladoDerecho.makeInvisible();
        palo.makeInvisible();
        velero.makeInvisible();
    }
    
    /**
     * Método que no realiza rotación ya que el bote es simétrico.
     */
    public void rotate(){
        
    }
    
    /**
     * Mueve el objeto bote a una nueva posición especificada.
     *
     * @param newPosx La nueva posición horizontal (eje x) a la que se moverá el bote.
     * @param newPosy La nueva posición vertical (eje y) a la que se moverá el bote.
     */
    public void moteTo(int newPosx, int newPosy){        
        base.moveHorizontal(newPosx);
        base.moveVertical(newPosy);
        palo.moveVertical(newPosy);
        palo.moveHorizontal(newPosx);
        ladoIzquierdo.moveHorizontal(newPosx);
        ladoIzquierdo.moveVertical(newPosy);
        ladoDerecho.moveHorizontal(newPosx);
        ladoDerecho.moveVertical(newPosy);
        velero.moveHorizontal(newPosx);
        velero.moveVertical(newPosy);        
    }
    
    /**
     * Cambia el tamaño del bote.
     *
     * @param newSize El nuevo tamaño del bote.
     */
    public void changeSize(int newSize){
        //base.changeSize(_newHeight_, _newWidth_)
    }
    
    /**
     * Cambia el color del bote y de sus partes.
     *
     * @param newColor El nuevo color del bote.
     */
    public void changeColor(String newColor){
        base.changeColor(newColor);
        palo.changeColor(newColor);
        ladoDerecho.changeColor("white");
        ladoIzquierdo.changeColor("white");
        
    }
    
    /**
     * Obtiene la posición horizontal (eje x) actual del objeto bote.
     *
     * @return La posición horizontal actual del bote.
     */
    public int getxPosition(){
        return base.getxPosition();
    }

    /**
     * Obtiene la posición vertical (eje y) actual del objeto bote.
     *
     * @return La posición vertical actual del bote.
     */
    public int getyPosition(){
        return base.getyPosition();
    }
}

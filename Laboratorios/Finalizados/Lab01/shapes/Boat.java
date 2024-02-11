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
    private boolean posIz = false;
    private boolean posDe = false;
    private boolean posIzb = false;
    private boolean posDeb = false;
    private String orilla;
    private int rotate;
    
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
        ladoDerecho.moveHorizontal(80);
        ladoDerecho.changeColor("white");
        velero.changeSize(70, 50);
        velero.moveHorizontal(5);
        velero.moveVertical(-40);
        velero.changeColor("red");  
        palo.changeSize(60, 20);
        palo.moveVertical(30);
        palo.moveHorizontal(65);
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
     * Rotate 90 grades the boat
     */
    public void rotate(){
        base.rotate();
        ladoIzquierdo.rotate();
        ladoDerecho.rotate();
        palo.rotate();
        velero.rotate();
        if(rotate < 3){
            rotate ++;
        }else{
            rotate = 0;
        }
        reorganizar();
    }
    
    /**
     * Reorganizar el lado izquierdo y derecho del barco
     */
    private void reorganizarLadosBarco(){
        switch(rotate){
            case 0:
                ladoIzquierdo.moveVertical(base.getyPosition()-ladoIzquierdo.getyPosition());
                ladoIzquierdo.moveHorizontal(base.getxPosition()-ladoIzquierdo.getxPosition());
                ladoDerecho.moveVertical(base.getyPosition()-ladoDerecho.getyPosition());
                ladoDerecho.moveHorizontal(base.getxPosition()+base.getWidth()-ladoDerecho.getxPosition());
                break;
            case 1:
                ladoDerecho.moveVertical(base.getyPosition()+base.getHeight()-(ladoDerecho.getWidth()/2)-ladoDerecho.getyPosition());
                ladoDerecho.moveHorizontal(base.getxPosition()-ladoDerecho.getxPosition());          
                ladoIzquierdo.moveVertical(base.getyPosition()-(ladoIzquierdo.getWidth()/2)-ladoIzquierdo.getyPosition());
                ladoIzquierdo.moveHorizontal(base.getxPosition()-ladoIzquierdo.getxPosition()); 
                break;
            case 2:
                ladoDerecho.moveVertical(base.getyPosition()-ladoDerecho.getyPosition());
                ladoDerecho.moveHorizontal(base.getxPosition()+base.getWidth()-ladoDerecho.getxPosition());
                ladoIzquierdo.moveVertical(base.getyPosition()-ladoIzquierdo.getyPosition());
                ladoIzquierdo.moveHorizontal(base.getxPosition()-ladoIzquierdo.getxPosition()); 
                break;
            default:
                ladoDerecho.moveVertical(base.getyPosition()+base.getHeight()-(ladoDerecho.getWidth()/2)-ladoDerecho.getyPosition());
                ladoDerecho.moveHorizontal(base.getxPosition()+base.getWidth()-ladoDerecho.getxPosition());       
                ladoIzquierdo.moveVertical(base.getyPosition()-(ladoIzquierdo.getWidth()/2)-ladoIzquierdo.getyPosition());
                ladoIzquierdo.moveHorizontal(base.getxPosition()+base.getWidth()-ladoIzquierdo.getxPosition()); 
        }
    }
    
    /**
     * Reorganiza el palo y velero de un barco
     */
    private void reorganizaPaloVeleroDeBarco(){
        switch(rotate){
            case 0:
                palo.moveHorizontal(((base.getWidth()/2)+base.getxPosition()-(palo.getWidth()/2)-palo.getxPosition()));
                palo.moveVertical((base.getyPosition()+base.getHeight()-palo.getHeight()-palo.getyPosition()));
                velero.moveHorizontal(base.getxPosition()+(base.getWidth()/2)-velero.getxPosition());
                velero.moveVertical(palo.getyPosition()-velero.getHeight()-velero.getyPosition()); 
                break;
            case 1:
                palo.moveHorizontal(base.getxPosition()-palo.getxPosition());
                palo.moveVertical((base.getyPosition()+(base.getHeight()/2)-(palo.getHeight()/2)-palo.getyPosition()));
                velero.moveVertical(base.getyPosition()+(base.getHeight()/2)-(velero.getWidth()/2)-velero.getyPosition());
                velero.moveHorizontal(palo.getxPosition()+palo.getWidth()-velero.getxPosition());
                break;
            case 2:
                palo.moveHorizontal(base.getxPosition()+(base.getWidth()/2)-(palo.getWidth()/2)-palo.getxPosition());
                palo.moveVertical((base.getyPosition()-palo.getyPosition()));
                velero.moveVertical(palo.getyPosition()+palo.getHeight()-velero.getyPosition());
                velero.moveHorizontal(base.getxPosition()+(base.getWidth()/2)-velero.getxPosition()); 
                break;
            default:
                palo.moveHorizontal(base.getxPosition()-base.getWidth()-palo.getxPosition());
                palo.moveVertical((base.getyPosition()+(base.getHeight()/2)-(palo.getHeight()/2)-palo.getyPosition()));
                velero.moveVertical(base.getyPosition()+(base.getHeight()/2)-(velero.getWidth()/2)-velero.getyPosition());
                velero.moveHorizontal(palo.getxPosition()-velero.getxPosition());
        }
    }
    
    /**
     * Reorganiza todos los elementos del barco
     */
    private void reorganizar(){
        reorganizarLadosBarco();
        reorganizaPaloVeleroDeBarco();
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
        base.changeSize((int)(base.getHeight() + base.getHeight()*((float)newSize/100)),(int)(base.getWidth() + base.getWidth()*((float)newSize/100)));
        ladoDerecho.changeSize((int)(ladoDerecho.getHeight() + ladoDerecho.getHeight()*((float)newSize/100)), (int)(ladoDerecho.getWidth() + ladoDerecho.getWidth()*((float)newSize/100)));
        ladoIzquierdo.changeSize((int)(ladoIzquierdo.getHeight() + ladoIzquierdo.getHeight()*((float)newSize/100)),(int)(ladoIzquierdo.getWidth() + ladoIzquierdo.getWidth()*((float)newSize/100)));
        palo.changeSize((int)(palo.getHeight() + palo.getHeight()*((float)newSize/100)),(int)(palo.getWidth() + palo.getWidth()*((float)newSize/100)));
        velero.changeSize((int)(velero.getHeight() + velero.getHeight()*((float)newSize/100)),(int)(velero.getWidth() + velero.getWidth()*((float)newSize/100)));
        reorganizar();
    }
    
    /**
     * Cambia el color del bote y de sus partes.
     *
     * @param newColorPalo El nuevo color del palo del bote
     * @param newColorBase para el nuevo color de la base del bote
     * @param newColorVelero para el nuevo color del velero del bote
     * @param newColorLados para el color de los lados invisibles del bote
     */
    public void changeColor(String newColorPalo, String newColorBase, String newColorVelero, String newColorLados){
        base.changeColor(newColorBase);
        palo.changeColor(newColorPalo);
        velero.changeColor(newColorVelero);
        ladoDerecho.changeColor(newColorLados);
        ladoIzquierdo.changeColor(newColorLados);
        
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
    
    /**
     * Obtiene el nombre de la orilla actual del bote.
     * 
     * @return El nombre de la orilla actual del bote ("Izquierda" o "Derecha").
     */
    public String getOrilla(){
        return orilla;
    }
    
    /**
     * Establece el nombre de la orilla actual del bote.
     * 
     * @param orilla El nombre de la orilla a establecer ("Izquierda" o "Derecha").
     */
        public void setOrilla(String orilla){
            this.orilla = orilla;
    }
    
    /**
     * Obtiene el estado de la posición del bote en la orilla izquierda.
     * 
     * @return true si el bote está en la orilla izquierda, false en caso contrario.
     */
    public boolean getPosIz(){
        return posIz;
    }
    
    /**
     * Establece el estado de la posición del bote en la orilla izquierda.
     * 
     * @param posIz true si el bote está en la orilla izquierda, false si no.
     */
    public void setPosIz(boolean posIz){
        this.posIz = posIz;
    }
    
    /**
     * Obtiene el estado de la posición del bote en la orilla derecha.
     * 
     * @return true si el bote está en la orilla derecha, false en caso contrario.
     */
    public boolean getPosDe(){
        return posDe;
    }
    
    /**
     * Establece el estado de la posición del bote en la orilla derecha.
     * 
     * @param posDe true si el bote está en la orilla derecha, false si no.
     */
    public void setPosDe(boolean posDe){
        this.posDe = posDe;
    }
    
    /**
     * Obtiene el estado de la posición del bote en la orilla izquierda, para los caníbales.
     * 
     * @return true si el bote está en la orilla izquierda, false en caso contrario.
     */
    public boolean getPosIzb(){
        return posIzb;
    }
    
    /**
     * Establece el estado de la posición del bote en la orilla izquierda, para los caníbales.
     * 
     * @param posIzb true si el bote está en la orilla izquierda para los caníbales, false si no.
     */
    public void setPosIzb(boolean posIzb){
        this.posIzb = posIzb;
    }
    
    /**
     * Obtiene el estado de la posición del bote en la orilla derecha, para los caníbales.
     * 
     * @return true si el bote está en la orilla derecha, false en caso contrario.
     */
    public boolean getPosDeb(){
        return posDeb;
    }
    
    /**
     * Establece el estado de la posición del bote en la orilla derecha, para los caníbales.
     * 
     * @param posDeb true si el bote está en la orilla derecha para los caníbales, false si no.
     */
    public void setposDeb(boolean posDeb){
        this.posDeb = posDeb;
    }
}

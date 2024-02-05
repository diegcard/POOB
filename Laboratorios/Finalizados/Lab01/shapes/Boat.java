
import java.util.*;

/**
 * Write a description of class Boat here.
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
    
    public Boat(){
        this.base = new Rectangle();
        this.ladoIzquierdo = new Triangle();
        this.ladoDerecho = new Triangle();    
        this.velero = new Triangle();
        this.palo = new Rectangle();   
        initDraw();
    }
    
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
     * Hace el objeto visible
     */
    public void makeVisible(){
        base.makeVisible();
        ladoIzquierdo.makeVisible();
        ladoDerecho.makeVisible();
        palo.makeVisible();
        velero.makeVisible();
    }  
    
    /**
     * Hace el obejtos invisible
     */
    public void makeInvisible(){
        base.makeInvisible();
        ladoIzquierdo.makeInvisible();
        ladoDerecho.makeInvisible();
        palo.makeInvisible();
        velero.makeInvisible();
    }
    
    /**
     * Al ser simetrica no rota
     */
    public void rotate(){
        
    }
    
    /**
     * 
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
     * 
     */
    public void changeSize(int newSize){
        //base.changeSize(_newHeight_, _newWidth_)
    }
    
    /**
     * Cambia el color del bote 
     */
    public void changeColor(String newColor){
        base.changeColor(newColor);
        palo.changeColor(newColor);
        ladoDerecho.changeColor("white");
        ladoIzquierdo.changeColor("white");
        
    }
    
    //public ArrayList cordenatesBoat(){
        //ArrayList<Integer> listaCordenadas = new ArrayList<Integer>();
        //listaCordenadas.add(base.getxPosition());
        //listaCordenadas.add(base.getyPosition());
        //System.out.println(listaCordenadas.get(0));
        //System.out.println(listaCordenadas.get(1));
        //return listaCordenadas;
    //}
    
    public int getxPosition(){
        return base.getxPosition();
    }
    
    public int getyPosition(){
        return base.getyPosition();
    }
}

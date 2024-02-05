import java.util.*;
/**
 * Write a description of class pe1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Missionary{
    private Circle head;
    private Rectangle body;
    public String name;
    
    public Missionary(){
        //this.head = new Circle();
        this.body = new Rectangle();
        
    }
    
    private void initDraw(){
        body.changeSize(60, 20);
    }
    
    public void makeVisible(){
        body.makeVisible();
    }
    
    public void moteTo(int newPosx, int newPosy){    
        body.moveHorizontal(newPosx);
        body.moveVertical(newPosy);      
    }
    
    // public ArrayList cordenatesBoat(){
        // ArrayList<Integer> listaCordenadas = new ArrayList<Integer>();
        // listaCordenadas.add(Rectangle.getxPosition());
        // listaCordenadas.add(Rectangle.getyPosition());
        // System.out.println(listaCordenadas.get(0));
        // System.out.println(listaCordenadas.get(1));
        // return listaCordenadas;
    // }
    
    public int getxPosition(){
        return body.getxPosition();
    }
    
    public int getyPosition(){
        return body.getyPosition();
    }
}

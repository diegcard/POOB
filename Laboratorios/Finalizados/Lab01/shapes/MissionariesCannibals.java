import java.util.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Write a description of class MissionariesCannibals here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MissionariesCannibals{
    private Boat bote;
    private Missionary Misionero1;
    private Missionary Misionero2;
    private Missionary Misionero3;
    private Cannibal Canibal1;
    private Cannibal Canibal2;
    private Cannibal Canibal3;
    
    private ArrayList<Missionary> borde1 = new ArrayList<Missionary>();
    private ArrayList<Missionary> borde2 = new ArrayList<Missionary>();
    private ArrayList<Missionary> boteEnMomento = new ArrayList<Missionary>();
    
    /**
     * Metodo Constructor
     */
    public MissionariesCannibals(){
        this.bote = new Boat();
        this.Misionero1 = new Missionary();
        this.Misionero2 = new Missionary();
        this.Misionero3 = new Missionary();
        posicionesIniciales();
        makeVisible();
    }
    
    private void makeVisible(){
        bote.makeVisible();
        Misionero1.makeVisible();
        Misionero2.makeVisible();
        Misionero3.makeVisible();
    }
    
    private void posicionesIniciales(){
        bote.moteTo(60, 150);
        bote.orilla = "Izquierda";
        Misionero1.moteTo(0, 40);
        Misionero1.name = "Misionero";
        Misionero2.moteTo(0, 90);
        Misionero2.name = "Misionero";
        Misionero3.moteTo(0, 140);
        Misionero3.name = "Misionero";
        borde1.add(Misionero1);
        borde1.add(Misionero2);
        borde1.add(Misionero3);
    }
    
    private boolean defeat(){
        return true;
    }
    
    /**
     * Inserta un Missionero al bote
     */
    public void insertarMissionary(){
        if(boteEnMomento.size() == 2){
            JOptionPane.showMessageDialog(null, "No puede insertar mas, El barco esta lleno.");
        }
        if(bote.orilla.equals("Izquierda")){
            for(Missionary misioneros: borde1){
                if (borde1.size()!=0){
                   if(bote.pozIz == false){   
                       misioneros.moteTo(bote.getxPosition() - misioneros.getxPosition() + 20, bote.getyPosition() - misioneros.getyPosition() - 20);   
                       borde1.remove(0);
                       bote.pozIz = true;
                       boteEnMomento.add(misioneros);
                       break;
                   }else if(bote.posDe == false){
                       misioneros.moteTo(bote.getxPosition() - misioneros.getxPosition() + 100, bote.getyPosition() - misioneros.getyPosition() - 20); 
                       borde1.remove(0);
                       bote.posDe = true;
                       boteEnMomento.add(misioneros);
                       break;
                   }
                }
            }
        }else if(bote.orilla.equals("Derecha")){
            for(Missionary misioneros: borde2){
                if (borde2.size()!=0){
                   if(bote.pozIz == false){   
                       misioneros.moteTo(bote.getxPosition() - misioneros.getxPosition() + 20, bote.getyPosition() - misioneros.getyPosition() - 20);   
                       borde2.remove(0);
                       bote.pozIz = true;
                       boteEnMomento.add(misioneros);
                       break;
                   }else if(bote.posDe == false){
                       misioneros.moteTo(bote.getxPosition() - misioneros.getxPosition() + 100, bote.getyPosition() - misioneros.getyPosition() - 20); 
                       borde2.remove(0);
                       bote.posDe = true;
                       boteEnMomento.add(misioneros);
                       break;
                   }
                }
            }
        }
    }
    
    /**
     * Mueve el bote de una orilla a la otra
     */
    public void moverBote(){
        if(bote.orilla.equals("Izquierda")){
            bote.moteTo(400, 0);
            for(Missionary misioneros: boteEnMomento){
                misioneros.moteTo(400, 0);
            }
            bote.orilla = "Derecha";
        }else{
            bote.moteTo(-400, 0);
            for(Missionary misioneros: boteEnMomento){
                misioneros.moteTo(-400, 0);
            }
            bote.orilla = "Izquierda";
        }
    }
    
    /**
     * Descarga los missioneros en el bote a la orilla a la que se encuentre el bote
     */
    public void descargarMissionary(){
        if(boteEnMomento.size()>0){
            if(bote.orilla.equals("Izquierda")){
                int totaly = 10;
                for (Missionary misioneros: borde1){
                    if(misioneros.name.equals("Misionero")){
                        totaly+=60;
                    }
                }
                Missionary aDescargar = boteEnMomento.get(0);
                aDescargar.moteTo(bote.getxPosition() - aDescargar.getxPosition() - 100, -totaly);
                borde1.add(aDescargar);
                if(boteEnMomento.size() == 2){
                    bote.pozIz = false;
                }
                if(boteEnMomento.size() == 1 && bote.pozIz == true){
                    bote.pozIz = false;
                }
                if(boteEnMomento.size() == 1 && bote.pozIz == false && bote.posDe == true){
                    bote.posDe = false;
                }
                if(boteEnMomento.size() == 0){
                    
                }
                boteEnMomento.remove(0);
            }else if(bote.orilla.equals("Derecha")){
                int totaly = 10;
                for (Missionary misioneros: borde2){
                    if(misioneros.name.equals("Misionero")){
                        totaly+=50;
                    }
                }
                Missionary aDescargar = boteEnMomento.get(0);
                aDescargar.moteTo(bote.getxPosition() - aDescargar.getxPosition() + 150, -totaly);
                borde2.add(aDescargar);
                if(boteEnMomento.size() == 2){
                    bote.pozIz = false;
                }
                if(boteEnMomento.size() == 1 && bote.pozIz == true){
                    bote.pozIz = false;
                }
                if(boteEnMomento.size() == 1 && bote.pozIz == false && bote.posDe == true){
                    bote.posDe = false;
                }
                if(boteEnMomento.size() == 0){
                    
                }
                boteEnMomento.remove(0);
            }
        }
    }
    
    
}

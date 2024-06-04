package Dominio;

import java.util.LinkedList;

/**
 * Recorrido de la troncal de una estacion a otra
 */
public class Ruta {

    private String nombre;
    private LinkedList<Estacion> estaciones;

    /**
     * Constructor de clase Ruta
     * @param nombre de la ruta
     */
    public Ruta(String nombre){
        this.nombre = nombre;
    }

    /**
     * @return
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * @param estacion1
     * @param estacion2
     * @return
     */
    public int completarRuta(String estacion1, String estacion2){
        int contador = 0;
        for(int i=0;i<estaciones.size();i++){
            if(estacion1==estaciones.get(i).getNombre()){
                contador = i;
            }
            if(estacion2==estaciones.get(i).getNombre()){
                contador = i-contador;
            }
        }
        return contador;
    }
}

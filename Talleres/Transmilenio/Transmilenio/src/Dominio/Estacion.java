package Dominio;

/**
 * Parada de una troncal
 */
public class Estacion {

    private String nombre;
    private String nivelOcupacion;
    private String tiempoEspera;

    /**
     * Constructor de Estacion
     * @param nombre
     */
    public Estacion(String nombre){
        this.nombre = nombre;
    }


    /**
     * Obtener tiempo de espera
     */

     public getTiempoEspera(){
        return tiempoEspera;
     }
    /**
     *
     * @return
     */
    public String getNombre(){
        return nombre;
    }

}

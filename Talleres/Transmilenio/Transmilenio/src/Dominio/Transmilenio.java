package Dominio;

import java.util.*;

/**
 * Sistema de transmilenio
 * Opera con troncales y estaciones
 */
public class Transmilenio{

    private LinkedList<Estacion> estaciones;
    private HashMap<String,String[]> rutas;

    /**
     * Constructor del sistema de transmilenio
     */
    public Sistema(){
        estaciones = new LinkedList<Estacion>();
        rutas = new HashMap<String,String[]>();
    }

    /**
     * Se conoce el tiempo de espera de una estacion dado su nombre
     * @param nombre
     * @return
     * @throws SistemaExcepcion
     */
    public int tiempoEsperaEstacion(String nombre)throws SistemaExcepcion{
        for(int i=0;i<estaciones.size();i++){
            if(estaciones.get(i).getNombre()==nombre){
                return estaciones.get(i).getTiempoEspera();
            }
        }
        if(nombre==null)throw new SistemaExcepcion(SistemaExcepcion.NOMBRE_ESTACION);
        return -1;
    }

    /**
     * Obtener el nombre de las rutas del sistema ordenadas alfabéticamente
     * @return Lista de nombres de rutas ordenados alfabéticamente
     */
    public List<String> obtenerRutasOrdenadas() {
        List<String> nombresRutas = new ArrayList<>(rutas.keySet());
        Collections.sort(nombresRutas);
        return nombresRutas;
    }

    /**
     * El numero de paradas para ir de una estacion a otra de una misma ruta
     * @param nombreRuta
     * @param estacion1
     * @param estacion2
     * @return
     */
    public int paradas(String nombreRuta, String estacion1, String estacion2){
        int contador = 0;
        if(rutas.containsKey(nombreRuta)){
            String[] temp = rutas.get(nombreRuta);
            for(int i=0;i<temp.length;i++){
                if(estacion1==temp[i]){
                    contador=i;
                }
                if(estacion2==temp[i]){
                    contador=i-contador;
                }
            }
        }
        return contador;
    }

    /**
     * Nombre de las rutas que comparten mismas estaciones sin transbordos
     * @param estacion1
     * @param estacion2
     * @return
     */
    public LinkedList<String> rutasSinTransbordos(String estacion1, String estacion2){
        LinkedList<String> rutasVarias = new LinkedList<String>();
        for(String key:rutas.keySet()){
            String[] temp = rutas.get(key);
            for(int i=0;i<temp.length;i++){
                if(estacion1==temp[i]){
                    for(int j=0;j<temp.length;j++){
                        if(estacion2==temp[j]){
                            rutasVarias.add(key);
                        }
                    }
                }
            }
        }
        Collections.sort(rutasVarias);
        return rutasVarias;
    }


}

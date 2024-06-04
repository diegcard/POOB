package Dominio;

public class SistemaExcepcion extends Exception{

    public final static String NOMBRE_ESTACION = "No existe ese nombre de estación en el sistema";

    /**
     * Constructor de SistemaExcepcion
     * @param mensaje
     */
    public SistemaExcepcion(String mensaje){
        super(mensaje);
    }
}

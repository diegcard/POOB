import java.util.Date;

public class Pedido {
    private String idUsurio;
    private String tituloMaterial;
    private int codigoMaterial;

    public void reservar(Date fecha){
        //logica de reservar
        try {
            RecursoBiblioteca recurso = new RecursoBiblioteca();
            recurso.reservar(fecha);
        } catch (NotMaterialFoundException e) {
            //logica de enviar mensaje de error
        } catch (NotAveableMaterialExecption e){
            //logica de enviar mensaje de error
        }
        // logica
    }

    public void entregar(Date fecha){

    }

}

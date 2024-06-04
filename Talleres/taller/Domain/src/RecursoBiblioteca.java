import java.util.Date;

public class RecursoBiblioteca {
    protected String titulo;
    protected String tipo;
    protected String autor;
    protected String editorial;

public void reservar(Date fecha) throws NotMaterialFoundException, NotAveableMaterialExecption{
    //logica de reservar
    //mirar si titulo no está en recursos
    if (/* condición para verificar si el título no está en los recursos */true) {
        throw new NotMaterialFoundException("No se ha encontrado el material");
    }
    //mirar si el material está disponible
    else if (/* condición para verificar si el material no está disponible */true) {
        throw new NotAveableMaterialExecption("El material no está disponible");
    }
}
    public void entregar(Date fecha){

    }
}

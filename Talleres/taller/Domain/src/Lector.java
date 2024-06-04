import java.util.Date;
public class Lector {
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected int codigo;
    public Lector(String nombre, String direccion, String telefono, int codigo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigo = codigo;
    }
    public void reservar(Date date){
        try {
            //logica de reservar
            //verifica si el codigo esta bien:
            isValidCode(codigo);
        } catch (InvalidCodeExecption e) {
            //logica de enviar mensaje de error
        }
        //más logica
    }
    public void entregar(){}
    public boolean isValidCode(int code) throws InvalidCodeExecption{
        boolean isValid = false;
        if(1000000000 > code){
            return isValid= true;
        }
        throw new InvalidCodeExecption("El código no es válido");
    }
}

/**
 * La clase Missionary representa un objeto Missionary el cual es una representacion grafica de este personaje.
 * Los Misioneros tienen una representación gráfica con un cuerpo (rectángulo) y un nombre asociado.
 * 
 * @author Diego Cardenas - Sebastian Cardona
 * @version 1.0.0
 */
public class Missionary{
    private Rectangle body;
    public String name;
    
    /**
     * Constructor Misionero
     */
    public Missionary(){
        this.body = new Rectangle();
    }
    
    private void initDraw(){
        body.changeSize(60, 20);
    }
    
    /**
     * Hace el Missionero visible 
     */
    public void makeVisible(){
        body.makeVisible();
    }
    
    /**
     * Mueve el objeto a una nueva posición especificada.
     * 
     * @param newPosx La nueva posición horizontal (eje x) a la que se moverá el objeto.
     * @param newPosy La nueva posición vertical (eje y) a la que se moverá el objeto.
     */
    public void moteTo(int newPosx, int newPosy){    
        body.moveHorizontal(newPosx);
        body.moveVertical(newPosy);      
    }
    
    /**
     * Obtiene la posición horizontal (eje x) actual del objeto.
     *
     * @return La posición horizontal actual del objeto.
     */
    public int getxPosition(){
        return body.getxPosition();
    }
    
    /**
     * Obtiene la posición vertical (eje y) actual del objeto en el plano.
     *
     * @return La posición vertical actual del objeto.
     */
    public int getyPosition(){
        return body.getyPosition();
    }
}

import java.awt.geom.Line2D;
import javax.swing.JOptionPane;
/**
 * Clase que representa un objeto línea.
 * Una línea se define por dos puntos extremos en un plano.
 * Permite operaciones como cambiar posición, color y visibilidad.
 * También puede dibujarse y borrarse en una ventana gráfica.
 * 
 * @author  Sebastian Cardona - Diego Cardenas
 * @version 1.0
 */

public class Linea{
    private String color;
    private boolean isVisible;
    private float x1;
    private float x2;
    private float y1;
    private float y2;
    
    /**
     * Crea una nueva línea con un punto final estático.
     * La línea se crea desde el centro de la imagen hasta el punto final especificado.
     * 
     * @param x2 La coordenada x del punto final de la línea.
     * @param y2 La coordenada y del punto final de la línea.
     */
    public Linea(float x2, float y2){
        color = "black";
        isVisible = false;
        this.x1 = Spiderweb.getPoscenterImage()[0]; //initial value
        this.y1 = Spiderweb.getPoscenterImage()[1]; //initial value
        this.x2 = x1+x2;
        this.y2 = y1-y2;
    }
    
    /**
     * Constructor de la clase Linea que crea una línea con las coordenadas dadas.
     * 
     * @param x1 La coordenada x del primer extremo de la línea.
     * @param y1 La coordenada y del primer extremo de la línea.
     * @param x2 La coordenada x del segundo extremo de la línea.
     * @param y2 La coordenada y del segundo extremo de la línea.
     */
    public Linea(float x1, float y1,float x2, float y2){
        color = "black";
        isVisible = false;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    /**
     * Actualiza las coordenadas de los puntos extremos de la línea con las nuevas coordenadas especificadas,
     * borrando la línea actual y dibujando una nueva línea con las coordenadas actualizadas.
     *
     * @param newx1 La nueva coordenada x del primer extremo de la línea.
     * @param newy1 La nueva coordenada y del primer extremo de la línea.
     * @param newx2 La nueva coordenada x del segundo extremo de la línea.
     * @param newy2 La nueva coordenada y del segundo extremo de la línea.
     */
    public void newPoints(float newx1, float newy1,float newx2, float newy2){
        erase();
        this.x1 = newx1;
        this.y1 = newy1;
        this.x2 = newx2;
        this.y2 = newy2;
        draw();
    }
    
    /**
     * Actualiza las coordenadas de los puntos extremos finales de la línea con las nuevas coordenadas especificadas,
     * borrando la línea actual y dibujando una nueva línea con las coordenadas actualizadas.
     *
     * @param newx2 La nueva coordenada x del segundo extremo de la línea.
     * @param newy2 La nueva coordenada y del segundo extremo de la línea.
     */
    public void newPoints(float newx2, float newy2){
        erase();
        this.x2 = x1+newx2;
        this.y2 = y1-newy2;
        draw();
    }
    
    /**
     * Cambia la posición inicial de la línea a las coordenadas especificadas.
     *
     * @param x1 La nueva coordenada x del primer extremo de la línea.
     * @param y1 La nueva coordenada y del primer extremo de la línea.
     * @throws IllegalArgumentException Si las nuevas coordenadas son iguales a las coordenadas actuales del primer extremo de la línea.
     */
    public void changePositionInitial(float x1, float y1){
        if(this.x1 == x1 || this.y1 == y1){
            JOptionPane.showMessageDialog(null, "No se puede ingresar la misma coordenada de x y y ");
        }else{
            this.x1 = x1;
            this.y1 = y1;
        }
    }
    
    /**
     * Devuelve el valor de la coordenada x1.
     * 
     * @return el valor de la coordenada x1
     */
    public float getX1(){
        return x1;
    }
    
    /**
     * Devuelve el valor de la coordenada x2.
     * 
     * @return el valor de la coordenada x2
     */
    public float getX2(){
        return x2;
    }
    
    /**
     * Devuelve el valor de la coordenada y1.
     * 
     * @return el valor de la coordenada y1
     */
    public float getY1(){
        return y1;
    }
    
    /**
     * Devuelve el valor de la coordenada y2.
     * 
     * @return el valor de la coordenada y2
     */
    public float getY2(){
        return y2;
    }
    
    /**
     * Make this Line visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this Line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Cambia el color de la línea.
     * 
     * @param newcolor El nuevo color de la línea.
     */
    public void changeColor(String newcolor){
        color = newcolor;
        draw();
    }
    
    /**
     * Mueve la línea horizontalmente por la cantidad especificada.
     * 
     * @param distancia La distancia a mover la línea horizontalmente.
     */
    public void moveHorizontal( int distancia){
        erase();
        x1 += distancia;
        x2 += distancia;
        draw();
    }
    
    /**
     * Mueve la línea verticalmente por la cantidad especificada.
     * 
     * @param distancia La distancia a mover la línea verticalmente.
     */
    public void moveVertical( int distancia){
        erase();
        y1 += distancia;
        y2 += distancia;
        draw();
    }
    
    /**
     * Dibuja la línea con las especificaciones actuales en pantalla.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            Line2D line = new Line2D.Float(x1, y1, x2, y2);
            canvas.draw(this, color, line);
            canvas.wait(10);
        }
    }
    
    /**
     * Erase the line on screen.
     **/
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

import java.util.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Recreacion grafica del juego Misioneros y Canibales
 * 
 * @author Diego Cardenas - Sebastian Cardona
 * @version 1.1.0
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
    private ArrayList<Cannibal> borde1b = new ArrayList<Cannibal>();
    private ArrayList<Cannibal> borde2b = new ArrayList<Cannibal>();
    private ArrayList<Cannibal> boteEnMomentob = new ArrayList<Cannibal>();
    
    /**
     * Metodo Constructor.
     */
    public MissionariesCannibals(){
        this.bote = new Boat();
        this.Misionero1 = new Missionary();
        this.Misionero2 = new Missionary();
        this.Misionero3 = new Missionary();
        borde1.add(Misionero1);
        borde1.add(Misionero2);
        borde1.add(Misionero3);
        this.Canibal1 = new Cannibal();
        this.Canibal2 = new Cannibal();
        this.Canibal3 = new Cannibal();
        borde1b.add(Canibal1);
        borde1b.add(Canibal2);
        borde1b.add(Canibal3);
        posicionesIniciales();
        makeVisible();
    }
    
    /**
     * Hace visible todos los objetos de MissionariesCannibals.
     */
    private void makeVisible(){
        bote.makeVisible();
        Misionero1.makeVisible();
        Misionero2.makeVisible();
        Misionero3.makeVisible();
        Canibal1.makeVisible();
        Canibal2.makeVisible();
        Canibal3.makeVisible();
    }
    
    /**
     * Metodo que da las posiciones iniciales de cada uno de los objetos de MissionariesCannibals.
     */
    private void posicionesIniciales(){
        posicionesInicialesBoat();
        posicionesInicialesMissionary();
        posicionesInicialesCannibal();
    }
    
    /**
     * Metodo da la poscion inicial del boat.
     */
    private void posicionesInicialesBoat(){
        bote.moteTo(60, 250);
        bote.changeSize(70);
        bote.setOrilla("Izquierda");
    }
    
    /**
     * Metodo mueve a las posiciones iniciales de los misioneros.
    */
    private void posicionesInicialesMissionary(){
        int posInicialY = 20;
        for(Missionary misioneros: borde1){
            misioneros.moveTo(0, posInicialY);
            posInicialY+=70;
            misioneros.name = "Misionero";
        }
    }
    
    /**
     * Metodo mueve a las posiciones iniciales de los canibales.
    */
    private void posicionesInicialesCannibal(){
        int posInicialY = 290;
        for(Cannibal canibales: borde1b){
            canibales.moveTo(0, posInicialY);
            posInicialY+=70;
            canibales.name = "Canibal";
        }
    }
    
    /**
     * Metodo que verifica si la cantidad de canibales es mayor a la de los misioneros en la orilla Izquierda.
     * 
     * @return true si la cantidad de canibales es mayor a la de los misioneros.
     */
    private boolean verificarPerderOrillaIzquierda(){
        if(borde1.size() == 0){
            return false;
        }else{
            return borde1b.size() > borde1.size();
        }
    }
    
    /**
     * Metodo que verifica si la cantidad de canibales es mayor a la de los misioneros en la orilla Derecha.
     * 
     * @return true si la cantidad de canibales es mayor a la de los misioneros.
     */
    private boolean verificarPerderOrillaDerecha(){
        if(borde2.size() == 0){
            return false;
        }else{
            return borde2b.size() > borde2.size();
        }
    }
    
    /**
     * Metodo que verifica en alguna de las dos orilla se cumple la condicion de que hay mas canibales que misioneros.
     * 
     * @return true si la cantidad de canibales es mayor a la de los misioneros.
     */
    private boolean verificarCantidadMisionerosCannibalsoOrillas() {
        return verificarPerderOrillaIzquierda() || verificarPerderOrillaDerecha();
    }

    /**
     * Inserta un misionero en el bote si es posible.
     * 
     * Este método verifica si el bote tiene espacio disponible para insertar un misionero.
     * Si el bote está lleno, muestra un mensaje de error.
     * Si el bote está en la orilla izquierda, llama al método insertarMissionaryEnOrilla para intentar insertar un misionero en esa orilla.
     * Si el bote está en la orilla derecha, llama al método insertarMissionaryEnOrilla para intentar insertar un misionero en esa orilla.
     */
    public void insertarMissionary() {
        if (boteEnMomento.size() == 2 || boteEnMomentob.size() == 2 || (boteEnMomento.size() == 1 && boteEnMomentob.size() == 1)) {
            JOptionPane.showMessageDialog(null, "No puede insertar más, el barco está lleno.");
        } else if (bote.getOrilla().equals("Izquierda")) {
            insertarMissionaryEnOrilla(borde1);
        } else if (bote.getOrilla().equals("Derecha")) {
            insertarMissionaryEnOrilla(borde2);
        }
    }
    
    /**
     * Inserta un misionero en la orilla si es posible.
     * 
     * Este método intenta insertar un misionero en la orilla especificada si hay espacio disponible en el bote y el bote está en la orilla correcta.
     * Si el bote está en la orilla izquierda y hay espacio disponible, el misionero se mueve a la posición del bote y se añade al bote.
     * Si el bote está en la orilla derecha y hay espacio disponible, el misionero se mueve a la posición del bote y se añade al bote.
     * 
     * @param orilla La lista que representa la orilla donde se insertará el misionero.
     */
    private void insertarMissionaryEnOrilla(List<Missionary> orilla) {
        for (Missionary misioneros : orilla) {
            if (orilla.size() != 0) {
                if (!bote.getPosIz() && !bote.getPosIzb()) {
                    moverMissionary(misioneros, 30, 20);
                    orilla.remove(0);
                    bote.setPosIz(true);
                    boteEnMomento.add(misioneros);
                    break;
                } else if(!bote.getPosDe() && !bote.getPosDeb()) {
                    moverMissionary(misioneros, 185, 20);
                    orilla.remove(0);
                    bote.setPosDe(true);
                    boteEnMomento.add(misioneros);
                    break;
                }
            }
        }
    }
    
    /**
     * Mueve un misionero a una posición relativa al bote.
     * 
     * Este método mueve un misionero a una posición relativa al bote, especificada por las coordenadas (x, y).
     * 
     * @param misioneros El misionero que se moverá.
     * @param x La coordenada x relativa al bote a la que se moverá el misionero.
     * @param y La coordenada y relativa al bote a la que se moverá el misionero.
     */
    private void moverMissionary(Missionary misioneros, int x, int y) {
        misioneros.moveTo(bote.getxPosition() - misioneros.getxPosition() + x, bote.getyPosition() - misioneros.getyPosition() + y);
    }
    
    /**
     * Inserta un caníbal en el bote si es posible.
     * 
     * Este método verifica si el bote tiene espacio disponible para insertar un caníbal.
     * Si el bote está lleno, muestra un mensaje de error.
     * Si el bote está en la orilla izquierda, llama al método insertarCannibalEnOrilla para intentar insertar un caníbal en esa orilla.
     * Si el bote está en la orilla derecha, llama al método insertarCannibalEnOrilla para intentar insertar un caníbal en esa orilla.
     */
    public void insertarCannibal(){
        if (boteEnMomento.size() == 2 || boteEnMomentob.size() == 2 || (boteEnMomento.size() == 1 && boteEnMomentob.size() == 1)) {
            JOptionPane.showMessageDialog(null, "No puede insertar más, el barco está lleno.");
        } else if (bote.getOrilla().equals("Izquierda")) {
            insertarCannibalEnOrilla(borde1b);
        } else if (bote.getOrilla().equals("Derecha")) {
            insertarCannibalEnOrilla(borde2b);
        }
    }
    
    /**
     * Inserta un caníbal en la orilla si es posible.
     * 
     * Este método intenta insertar un caníbal en la orilla especificada si hay espacio disponible en el bote y el bote está en la orilla correcta.
     * Si el bote está en la orilla izquierda y hay espacio disponible, el caníbal se mueve a la posición del bote y se añade al bote.
     * Si el bote está en la orilla derecha y hay espacio disponible, el caníbal se mueve a la posición del bote y se añade al bote.
     * 
     * @param orilla La lista que representa la orilla donde se insertará el caníbal.
     */
    private void insertarCannibalEnOrilla(List<Cannibal> orilla){
        for(Cannibal canibales: orilla){
            if(orilla.size()!=0){
                if(!bote.getPosIzb() && !bote.getPosIz()){
                    moverCannibal(canibales, 30, 20);
                    orilla.remove(0);
                    bote.setPosIzb(true);
                    boteEnMomentob.add(canibales);
                    break;
                } else if(!bote.getPosDeb() && !bote.getPosDe()){
                    moverCannibal(canibales, 185, 20);
                    orilla.remove(0);
                    bote.setposDeb(true);
                    boteEnMomentob.add(canibales);
                    break;
                }
            }
        }
    }
    
    /**
     * Mueve un caníbal a una posición relativa al bote.
     * 
     * @param canibales El caníbal que se moverá.
     * @param x La coordenada x relativa al bote a la que se moverá el caníbal.
     * @param y La coordenada y relativa al bote a la que se moverá el caníbal.
     */
    private void moverCannibal(Cannibal canibales, int x, int y){
        canibales.moveTo(bote.getxPosition() - canibales.getxPosition() + x, bote.getyPosition() - canibales.getyPosition() + y);
    }
    
    /**
     * Mueve el bote de una orilla a la otra.
     * 
     * Este método verifica si el bote está vacío o si hay alguna restricción que impida el movimiento,
     * como la pérdida de misioneros en una orilla. Si el movimiento es válido, mueve el bote a la orilla
     * opuesta. Si el bote está en la orilla izquierda, llama al método para mover el bote hacia la derecha,
     * y si está en la orilla derecha, llama al método para mover el bote hacia la izquierda.
     */
    public void moverBote(){
        if(boteEnMomento.size() == 0 && boteEnMomentob.size() == 0){
            JOptionPane.showMessageDialog(null, "El bote esta vacio, debe de haber alguien para manejarlo");
        }else if(verificarCantidadMisionerosCannibalsoOrillas()){
            JOptionPane.showMessageDialog(null, "Los misioneros Perdieron");
        }else{
            int cantidadMover = 600;
            if(bote.getOrilla().equals("Izquierda")){
                moverBoteIzquierda(cantidadMover);
            }else{
                moverBoteDerecha(-cantidadMover);
            }
        }
    }

    /**
     * Mueve el bote de la orilla izquierda a la derecha.
     * 
     * Este método mueve el bote y a todos los pasajeros (misioneros y caníbales) que están en él hacia la orilla derecha.
     * Luego, actualiza la propiedad que indica la orilla actual del bote a "Derecha".
     * 
     * @param cantMover La cantidad de movimiento horizontal del bote.
     */
    private void moverBoteIzquierda(int cantMover){
        bote.moteTo(cantMover, 0);
        for(Missionary misioneros: boteEnMomento){
            misioneros.moveTo(cantMover, 0);
        }
        for(Cannibal canibales: boteEnMomentob){
            canibales.moveTo(cantMover, 0);
        }
        bote.setOrilla("Derecha");
    }
    
    /**
     * Mueve el bote de la orilla derecha a la izquierda.
     * 
     * Este método mueve el bote y a todos los pasajeros (misioneros y caníbales) que están en él hacia la orilla izquierda.
     * Luego, actualiza la propiedad que indica la orilla actual del bote a "Izquierda".
     * 
     * @param cantMover La cantidad de movimiento horizontal del bote.
     */
    private void moverBoteDerecha(int cantMover){
        bote.moteTo(cantMover, 0);
        for(Missionary misioneros: boteEnMomento){
            misioneros.moveTo(cantMover, 0);
        }
        for(Cannibal canibales: boteEnMomentob){
            canibales.moveTo(cantMover, 0);
        }
        bote.setOrilla("Izquierda");
    }
    
    /**
     * Descarga un misionero del bote si hay alguno presente.
     * 
     * Este método verifica si hay al menos un misionero en el bote. Si es así, determina la orilla actual del bote
     * y llama al método correspondiente para descargar el misionero en esa orilla.
     */
    public void descargarMissionary(){
        if(boteEnMomento.size()>0){
            if(bote.getOrilla().equals("Izquierda")){
                descargarMissionaryIzquierda();            
            }else if(bote.getOrilla().equals("Derecha")){
                descargarMissionaryDerecha();  
            }
        }
    }
    
    /**
     * Descarga un misionero en la orilla izquierda.
     * 
     * Este método calcula la posición y en la orilla izquierda donde se colocará el misionero,
     * lo mueve a esa posición y lo agrega a la lista de misioneros en la orilla izquierda.
     * Finalmente, actualiza el estado del bote si corresponde y elimina el misionero de la lista de misioneros en el bote.
     */
    private void descargarMissionaryIzquierda(){
        int totaly = 0;
        for (Missionary misioneros: borde1){
            if(misioneros.name.equals("Misionero")){
                totaly+=70;
            }
        }
        Missionary aDescargar = boteEnMomento.get(0);
        aDescargar.moveTo(bote.getxPosition() - aDescargar.getxPosition() - 60, -totaly);
        borde1.add(aDescargar);
        if(bote.getPosIz() && !bote.getPosIzb()){
            bote.setPosIz(false);
        }else if(bote.getPosDe() && !bote.getPosDeb()){
            bote.setPosDe(false);
        }
        boteEnMomento.remove(0);
    }
    
    /**
     * Descarga un misionero en la orilla derecha.
     * 
     * Este método calcula la posición y total en la orilla derecha donde se colocará el misionero,
     * lo mueve a esa posición y lo agrega a la lista de misioneros en la orilla derecha.
     * Finalmente, actualiza el estado del bote si corresponde y elimina el misionero de la lista de misioneros en el bote.
     */
    private void descargarMissionaryDerecha(){
        int totaly = 10;
        for (Missionary misioneros: borde2){
            if(misioneros.name.equals("Misionero")){
                totaly+=70;
            }
        }
        Missionary aDescargar = boteEnMomento.get(0);
        aDescargar.moveTo(bote.getxPosition() - aDescargar.getxPosition() + 390, -totaly);
        borde2.add(aDescargar);
        if(bote.getPosIz() && !bote.getPosIzb()){
            bote.setPosIz(false);
        }else if(bote.getPosDe() && !bote.getPosDeb()){
            bote.setPosDe(false);
        }
        boteEnMomento.remove(0);
    }
    
    /**
     * Descarga un caníbal del bote si hay alguno presente.
     * 
     * Este método verifica si hay al menos un caníbal en el bote. Si es así, determina la orilla actual del bote
     * y llama al método correspondiente para descargar el caníbal en esa orilla.
     */
    public void descargarCannibal(){
        if(boteEnMomentob.size()>0){
            if(bote.getOrilla().equals("Izquierda")){
                descargarCannibalIzquierda();            
            }else if(bote.getOrilla().equals("Derecha")){
                descargarCannibalDerecha();  
            }
        }
    }
    
    /**
     * Descarga un caníbal en la orilla izquierda.
     * 
     * Este método calcula la posición y total en la orilla izquierda donde se colocará el caníbal,
     * lo mueve a esa posición y lo agrega a la lista de caníbales en la orilla izquierda.
     * Finalmente, actualiza el estado del bote si corresponde y elimina el caníbal de la lista de caníbales en el bote.
     */
    private void descargarCannibalIzquierda(){
        int totaly = 50;
        for (Cannibal canibales: borde1b){
            if(canibales.name.equals("Canibal")){
                totaly+=70;
            }
        }
        Cannibal aDescargar = boteEnMomentob.get(0);
        aDescargar.moveTo(bote.getxPosition() - aDescargar.getxPosition() - 60, totaly);
        borde1b.add(aDescargar);
        if(bote.getPosIzb() && !bote.getPosIz()){
            bote.setPosIzb(false);
        }else if(bote.getPosDeb() && !bote.getPosDe()){
            bote.setposDeb(false);
        }
        boteEnMomentob.remove(0);
    }
    
    /**
     * Descarga un caníbal en la orilla derecha.
     * 
     * Este método calcula la posición y total en la orilla derecha donde se colocará el caníbal,
     * lo mueve a esa posición y lo agrega a la lista de caníbales en la orilla derecha.
     * Finalmente, actualiza el estado del bote si corresponde y elimina el caníbal de la lista de caníbales en el bote.
     */
    private void descargarCannibalDerecha(){
        int totaly = 100;
        for (Cannibal canibales: borde2b){
            if(canibales.name.equals("Canibal")){
                totaly+=70;
            }
        }
        Cannibal aDescargar = boteEnMomentob.get(0);
        aDescargar.moveTo(bote.getxPosition() - aDescargar.getxPosition() + 390, totaly);
        borde2b.add(aDescargar);
        if(bote.getPosIzb() && !bote.getPosIz()){
            bote.setPosIzb(false);
        }else if(bote.getPosDeb() && !bote.getPosDe()){
            bote.setposDeb(false);
        }
        boteEnMomentob.remove(0);
    }
    
    /**
     * Verifica si los misioneros ganan el juego.
     *
     * @return true si hay exactamente 3 misioneros en la orilla derecha, lo que indica que los misioneros ganan el juego;
     *         false en caso contrario.
     */
    private void verificarSiMisionerosGanan(){
        if(borde2.size() == 3){
            JOptionPane.showMessageDialog(null, "Los misioneros ganaron");
        }
    }
    
}

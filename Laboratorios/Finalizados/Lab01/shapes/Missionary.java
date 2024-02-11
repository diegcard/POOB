/**
 * La clase Missionary representa un objeto Missionary el cual es una representacion grafica de este personaje.
 * Los Misioneros tienen una representación gráfica con un cuerpo (rectángulo) y un nombre asociado.
 * 
 * @author Diego Cardenas - Sebastian Cardona
 * @version 1.0.0
 */
public class Missionary{
    private Rectangle body;
    private Circle head;
    private Circle eyeD;
    private Circle eyeI;
    private Rectangle mouth;
    private Rectangle armD;
    private Rectangle armI;
    private Rectangle leg;
    private int rotacion;
    
    /**
     * Constructor Misionero
     */
    public Missionary(){
        this.body = new Rectangle();
        this.head = new Circle();
        this.eyeD = new Circle();
        this.eyeI = new Circle();
        this.mouth = new Rectangle();
        this.armD = new Rectangle();
        this.armI = new Rectangle();
        this.leg = new Rectangle();
        initDraw();
    }
    
    /**
     * Realiza el dibujo inicial
     */
    private void initDraw(){
        rotacion = 0;
        body.moveVertical(60);
        body.changeSize(50, 40);
        body.changeColor("black");
        leg.changeSize(50,40);
        armD.changeSize(50,10);
        armI.changeSize(50,10);
        head.changeSize(40);
        head.changeColor("yellow");
        mouth.changeColor("white");
        mouth.changeSize(4,10);
        eyeD.changeColor("black");
        eyeD.changeSize(6);
        eyeI.changeColor("black");
        eyeI.changeSize(6);
        reorganizar();
    }
    
    /**
     * Hace visibles todas las partes del personaje.
     * Esto incluye el cuerpo, la cabeza, los ojos (derecho e izquierdo), la boca,
     * los brazos (derecho e izquierdo) y la pierna.
     */
    public void makeVisible(){
        body.makeVisible();
        head.makeVisible();
        eyeD.makeVisible();
        eyeI.makeVisible();
        mouth.makeVisible();
        armD.makeVisible();
        armI.makeVisible();
        leg.makeVisible();
    }
    
    /**
     * Oculta todas las partes del personaje.
     * Esto incluye el cuerpo, la cabeza, los ojos (derecho e izquierdo), la boca,
     * los brazos (derecho e izquierdo) y la pierna.
     */
    public void makeInvisible(){
        body.makeInvisible();
        head.makeInvisible();
        eyeD.makeInvisible();
        eyeI.makeInvisible();
        mouth.makeInvisible();
        armD.makeInvisible();
        armI.makeInvisible();
        leg.makeInvisible();
    }
    
    /**
     * Cambia el tamaño de todas las partes del personaje según el porcentaje especificado.
     * El tamaño de cada parte se ajusta en función del tamaño original y del porcentaje proporcionado.
     * 
     * @param newSize El porcentaje de cambio de tamaño a aplicar. Un valor positivo aumentará el tamaño y un valor negativo lo reducirá.
     */
    public void changeSize(int newSize){
        body.changeSize((int)(body.getHeight() + body.getHeight()*((float)newSize/100)),(int)(body.getWidth() + body.getWidth()*((float)newSize/100)));
        leg.changeSize((int)(leg.getHeight() + leg.getHeight()*((float)newSize/100)), (int)(leg.getWidth() + leg.getWidth()*((float)newSize/100)));
        head.changeSize((int)(head.getDiameter() + head.getDiameter()*((float)newSize/100)));
        armD.changeSize((int)(armD.getHeight() + armD.getHeight()*((float)newSize/100)),(int)(armD.getWidth() + armD.getWidth()*((float)newSize/100)));
        armI.changeSize((int)(armI.getHeight() + armI.getHeight()*((float)newSize/100)),(int)(armI.getWidth() + armI.getWidth()*((float)newSize/100)));
        mouth.changeSize((int)(mouth.getHeight() + mouth.getHeight()*((float)newSize/100)),(int)(mouth.getWidth() + mouth.getWidth()*((float)newSize/100)));
        eyeD.changeSize((int)(eyeD.getDiameter() + eyeD.getDiameter()*((float)newSize/100)));
        eyeI.changeSize((int)(eyeI.getDiameter() + eyeI.getDiameter()*((float)newSize/100)));
        reorganizar();
    }
    
    /**
     * Cambia el color de la ropa del misionero.
     *
     * @param newColorChaleco El nuevo color del chaleco
     * @param newColorPantalon para el nuevo color del pantalon
     * @param newColorMangas para el nuevo color de las mangas
     */
    
    public void changeColor(String newColorChaleco, String newColorPantalon, String newColorMangas){
        body.changeColor(newColorChaleco);
        leg.changeColor(newColorPantalon);
        armD.changeColor(newColorMangas);
        armI.changeColor(newColorMangas);
    }
    
    /**
     * Mueve el objeto a una nueva posición especificada.
     * 
     * @param newPosx La nueva posición horizontal (eje x) a la que se moverá el objeto.
     * @param newPosy La nueva posición vertical (eje y) a la que se moverá el objeto.
     */
    public void moveTo(int newPosx, int newPosy){    
        body.moveHorizontal(newPosx);
        body.moveVertical(newPosy);
        leg.moveHorizontal(newPosx);
        leg.moveVertical(newPosy);
        head.moveVertical(newPosy);
        head.moveHorizontal(newPosx);
        mouth.moveHorizontal(newPosx);
        mouth.moveVertical(newPosy);
        armD.moveHorizontal(newPosx);
        armD.moveVertical(newPosy);
        armI.moveHorizontal(newPosx);
        armI.moveVertical(newPosy);
        eyeD.moveHorizontal(newPosx);
        eyeD.moveVertical(newPosy);
        eyeI.moveHorizontal(newPosx);
        eyeI.moveVertical(newPosy);
    }
    
    /**
     * Obtiene la posición horizontal (eje x) actual del objeto.
     *
     * @return La posición horizontal actual del objeto.
     */
    public int getxPosition(){
        return leg.getxPosition();
    }
    
    /**
     * Obtiene la posición vertical (eje y) actual del objeto en el plano.
     *
     * @return La posición vertical actual del objeto.
     */
    public int getyPosition(){
        return leg.getyPosition()+leg.getHeight();
    }
    
    /**
     * rotate 90 grades the missionary
     */
    public void rotate(){
        body.rotate();
        mouth.rotate();
        armD.rotate();
        armI.rotate();
        leg.rotate();
        if(rotacion < 3){
            rotacion ++;
        }else{
            rotacion = 0;
        }
        reorganizar();
    }
    
    /**
     * reorganizar el brazo izquierdo y derecho del misionero
     */
    private void reorganizarBrazos(){
        switch(rotacion){
            case 0:
                armD.moveHorizontal(body.getxPosition()+body.getWidth()-armD.getxPosition());
                armD.moveVertical(body.getyPosition()-armD.getyPosition());
                armI.moveHorizontal(body.getxPosition()-armI.getWidth()-armI.getxPosition());
                armI.moveVertical(body.getyPosition()-armI.getyPosition());
                break;
            case 1:
                armD.moveHorizontal(body.getxPosition()+body.getWidth()-armD.getWidth()-armD.getxPosition());
                armD.moveVertical(body.getyPosition()+body.getHeight()-armD.getyPosition());
                armI.moveHorizontal(body.getxPosition()+body.getWidth()-armI.getWidth()-armI.getxPosition());
                armI.moveVertical(body.getyPosition()-armI.getHeight()-armI.getyPosition());
                break;
            case 2:
                armD.moveHorizontal(body.getxPosition()+body.getWidth()-armD.getxPosition());
                armD.moveVertical(body.getyPosition()+body.getHeight()-armD.getHeight()-armD.getyPosition());
                armI.moveHorizontal(body.getxPosition()-armI.getWidth()-armI.getxPosition());
                armI.moveVertical(body.getyPosition()+body.getHeight()-armI.getHeight()-armI.getyPosition());
                break;
            default:
                armD.moveHorizontal(body.getxPosition()-armD.getxPosition());
                armD.moveVertical(body.getyPosition()-armD.getHeight()-armD.getyPosition());
                armI.moveHorizontal(body.getxPosition()-armI.getxPosition());
                armI.moveVertical(body.getyPosition()+body.getHeight()-armI.getyPosition());
        }
    }
    
    /**
     * reorganizar cabeza del misionero
     */
    private void reorganizarCabeza(){
        switch(rotacion){
            case 0:
                head.moveHorizontal(body.getxPosition()-head.getxPosition());
                head.moveVertical(body.getyPosition()-head.getDiameter()-head.getyPosition());
                break;
            case 1:
                head.moveHorizontal(body.getxPosition()+body.getWidth()-head.getxPosition());
                head.moveVertical(body.getyPosition()-head.getyPosition());
                break;
            case 2:
                head.moveHorizontal(body.getxPosition()-head.getxPosition());
                head.moveVertical(body.getyPosition()+body.getHeight()-head.getyPosition());
                break;
            default:
                head.moveHorizontal(body.getxPosition()-head.getDiameter()-head.getxPosition());
                head.moveVertical(body.getyPosition()-head.getyPosition());
        }
    }
    
    /**
     * reorganizar la boca y piernas del misionero
     */
    private void reorganizarPiernasBoca(){
        switch(rotacion){
            case 0:
                leg.moveHorizontal(body.getxPosition()-leg.getxPosition());
                leg.moveVertical(body.getyPosition()+body.getHeight()-leg.getyPosition());
                mouth.moveVertical(body.getyPosition()-(head.getDiameter()/3)-mouth.getyPosition());
                mouth.moveHorizontal(body.getxPosition()+(body.getWidth()/2)-(mouth.getWidth()/2)-mouth.getxPosition());
                break;
            case 1:
                leg.moveHorizontal(body.getxPosition()-leg.getWidth()-leg.getxPosition());
                leg.moveVertical(body.getyPosition()-leg.getyPosition());
                mouth.moveVertical(body.getyPosition()+(body.getHeight()/2)-(mouth.getHeight()/2)-mouth.getyPosition());
                mouth.moveHorizontal(body.getxPosition()+body.getWidth()+(head.getDiameter()/3)-mouth.getxPosition());
                break;
            case 2:
                leg.moveHorizontal(body.getxPosition()-leg.getxPosition());
                leg.moveVertical(body.getyPosition()-leg.getHeight()-leg.getyPosition());
                mouth.moveVertical(body.getyPosition()+body.getHeight()+(head.getDiameter()/3)-mouth.getyPosition());
                mouth.moveHorizontal(body.getxPosition()+(body.getWidth()/2)-(mouth.getWidth()/2)-mouth.getxPosition());
                break;
            default:
                leg.moveHorizontal(body.getxPosition()+body.getWidth()-leg.getxPosition());
                leg.moveVertical(body.getyPosition()-leg.getyPosition());
                mouth.moveVertical(body.getyPosition()+(body.getHeight()/2)-(mouth.getHeight()/2)-mouth.getyPosition());
                mouth.moveHorizontal(body.getxPosition()-(head.getDiameter()/3)-mouth.getxPosition());
        }
    }
    
    /**
     * reorganizar ojos del misionero
     */
    private void reorganizarOjos(){
        switch(rotacion){
            case 0:
                eyeD.moveHorizontal(body.getxPosition()+3*(body.getWidth()/4)-(eyeD.getDiameter()/2)-eyeD.getxPosition());
                eyeD.moveVertical(head.getyPosition()+(head.getDiameter()/3)-eyeD.getyPosition());
                eyeI.moveHorizontal(body.getxPosition()+(body.getWidth()/4)-(eyeI.getDiameter()/2)-eyeI.getxPosition());
                eyeI.moveVertical(head.getyPosition()+(head.getDiameter()/3)-eyeI.getyPosition());
                break;
            case 1:
                eyeD.moveHorizontal(head.getxPosition()+2*(head.getDiameter()/3)-eyeD.getxPosition());
                eyeD.moveVertical(body.getyPosition()+3*(body.getHeight()/4)-(eyeD.getDiameter()/2)-eyeD.getyPosition());
                eyeI.moveHorizontal(head.getxPosition()+2*(head.getDiameter()/3)-eyeI.getxPosition());
                eyeI.moveVertical(body.getyPosition()+(body.getHeight()/4)-(eyeI.getDiameter()/2)-eyeI.getyPosition());
                break;
            case 2:
                eyeD.moveHorizontal(body.getxPosition()+(body.getWidth()/4)-(eyeD.getDiameter()/2)-eyeD.getxPosition());
                eyeD.moveVertical(head.getyPosition()+2*(head.getDiameter()/3)-eyeD.getyPosition());
                eyeI.moveHorizontal(body.getxPosition()+3*(body.getWidth()/4)-(eyeI.getDiameter()/2)-eyeI.getxPosition());
                eyeI.moveVertical(head.getyPosition()+2*(head.getDiameter()/3)-eyeI.getyPosition());
                break;
            default:
                eyeD.moveHorizontal(head.getxPosition()+(head.getDiameter()/3)-eyeD.getxPosition());
                eyeD.moveVertical(body.getyPosition()+(body.getHeight()/4)-(eyeD.getDiameter()/2)-eyeD.getyPosition());
                eyeI.moveHorizontal(head.getxPosition()+(head.getDiameter()/3)-eyeI.getxPosition());
                eyeI.moveVertical(body.getyPosition()+3*(body.getHeight()/4)-(eyeI.getDiameter()/2)-eyeI.getyPosition());
        }
    }
    
    /**
     * Reorganizar todo el cuerpo
     */
    private void reorganizar(){
        reorganizarBrazos();
        reorganizarCabeza();
        reorganizarPiernasBoca();
        reorganizarOjos();
    }
}

package test;
import domain.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * The test class GardenTets.
 *
 * @author  cardona-cardenas
 * @version 1.0.0
 */
public class GardenTets{
    
    public Garden garden;
    //ciclo 1
    @Before
    public void setUp() {
        garden = new Garden();
    }

    @Test
    public void testGetLength() {
        int length = garden.getLength();
        assertEquals(40, length);
    }
    
    @Test
    public void testGetThing() {
        // Assuming that someThings() sets an entity at position (10, 10)
        Thing thing = garden.getThing(10, 10);
        assertNotNull(thing);
    }
    
    @Test
    public void testSetThing() {
        Thing thing = new Flower(garden, 38, 5, "testAnt");
        garden.setThing(38, 5, thing);
        Thing retrievedThing = garden.getThing(38, 5);
        assertEquals(thing, retrievedThing);
    }
    
    @Test
    public void testSomeThings() {
        // Assuming someThings() adds at least one Cane and one Cell
        Thing thing1 = garden.getThing(20, 25);
        Thing thing2 = garden.getThing(15,20);

        assertNotNull(thing1);
        assertNotNull(thing2);
    }
    
    @Test
    public void shouldDeadFlowersAfterThreeActs(){
        Flower flower = new Flower(garden,29,2,"Bob");
        for (int i = 0;i<3;i++)flower.act();
        assertEquals(false,flower.isAlive());
    }
    
    @Test
    public void shouldAliveFlowersAfterFiveActs(){
        Flower flower = new Flower(garden,29,2,"Bob");
        for (int i = 0;i<5;i++)flower.act();
        assertEquals(true,flower.isAlive());
    }
    
    @Test
    public void shouldDeadFlowersAfterThreeTicTac(){
        Flower flower = new Flower(garden,29,2,"Bob");
        for (int i = 0;i<3;i++)garden.ticTac();
        assertEquals(false,flower.isAlive());
    }
    
    @Test
    public void shouldAliveFlowersAfterFiveTicTac(){
        Flower flower = new Flower(garden,29,2,"Bob");
        for (int i = 0;i<5;i++)garden.ticTac();
        assertEquals(true,flower.isAlive());
        garden.setThing(29, 2, null);
    }
    
    //ciclo 2
    @Test
    public void shouldCarnovorousEatAFlower(){
        Flower flower = new Flower(garden,29,2,"Bob");
        Flower carnovorous = new Carnivorous(garden,28,2,"Assasing");
        garden.ticTac();
        Thing thing = garden.getThing(29, 2);
        assertEquals(carnovorous,thing);
        garden.setThing(29, 2, null);
    }
    
    @Test
    public void shouldNotCarnovorousEatACell(){
        Cell cell = new Cell(garden,29,2);
        Flower carnovorous = new Carnivorous(garden,28,2,"Assasing");
        garden.ticTac();
        Thing thing = garden.getThing(29, 2);
        assertEquals(cell,thing);
        garden.setThing(29, 2, null);
    }
    
    // ciclo 3
    @Test
    public void shouldSandDisapearAfter100ticTacs(){
        Sand sand = new Sand(garden,29,2,"Garrix");
        for (int i = 0;i<=101;i++)garden.ticTac();
        Thing thing = garden.getThing(29, 2);
        assertNull(thing);
    }
    
    @Test
    public void shouldSandLostColor(){
        Sand sand = new Sand(garden,29,2,"Garrix");
        int colorSand1 = sand.getColor().getRed();
        for (int i = 0;i<=3;i++)garden.ticTac();
        int colorSand2 = sand.getColor().getRed();
        assertNotEquals(colorSand1,colorSand2);
    }
    
    //ciclo 4
    @Test
    public void shouldCaneWalkToTheNearestSand(){
        Sand sand = new Sand(garden,29,2,"Garrix");
        Flower cane = new Cane(garden,26,2,"Friend");        
        for (int i = 0;i<=2;i++)garden.ticTac();
        Thing thing = garden.getThing(28, 2);
        assertEquals(thing,cane);
        garden.setThing(28, 2, null);
    }
    
    @Test
    public void shouldCaneNourishTheNearestSand(){
        Sand sand = new Sand(garden,29,2,"Garrix");
        Flower cane = new Cane(garden,24,2,"Friend");
        for (int i = 0;i<=3;i++)garden.ticTac();
        int colorSand1 = sand.getColor().getRed();
        for (int i = 0;i<=8;i++)garden.ticTac();
        int colorSand2 = sand.getColor().getRed();
        assertNotEquals(colorSand1,colorSand2);
        garden.setThing(28, 2, null);
    }
    
    //ciclo 5
    @Test
    public void shouldAntIgnoreOtherFlowersThatAreNotCarnivorous(){
        Flower cane = new Cane(garden,35,11,"robert");
        Ant ant = new Ant(garden,34,11,"Carlitos");        
        garden.ticTac();
        Thing thing = garden.getThing(35, 11);
        assertNotEquals(thing,ant);
        for(int r = 34-1; r<=34+1;r++){
            for(int c = 11-1; c<=11+1;c++){
                garden.setThing(r, c, null);
            }
        }
    }
    
    @Test
    public void shouldAntEatCarnivorousFlowers(){
        Flower Carnivorous = new Carnivorous(garden,35,11,"robert");
        Ant ant = new Ant(garden,34,11,"Carlitos");        
        garden.ticTac();
        Thing thing = garden.getThing(35, 11);
        assertEquals(thing,ant);
        for(int r = 34-1; r<=34+1;r++){
            for(int c = 11-1; c<=11+1;c++){
                garden.setThing(r, c, null);
            }
        }
    }
    
    //ciclo 6
    @Test
    public void shouldCellCreateWith3CellsNeighbors(){
        Cell cell1 = new Cell(garden,34,11);
        Cell cell2 = new Cell(garden,35,12);
        Cell cell3 = new Cell(garden,35,10);
        garden.ticTac();
        Thing thing = garden.getThing(35, 11);
        assertNotNull(thing);
        for(int r = 35-1; r<=35+1;r++){
            for(int c = 11-1; c<=11+1;c++){
                garden.setThing(r, c, null);
            }
        }
    }
    
    @Test
    public void shouldConwayDeadWithOutCells(){
        Flower conway = new Conway(garden,35,11,"robert");
        garden.ticTac();
        boolean isAlive = conway.isAlive();      
        assertFalse(isAlive);
        garden.setThing(35,11, null);
    }
    
    @Test
    public void shouldConwayReviveWith3Cells(){
        Flower conway = new Conway(garden,35,11,"robert");
        garden.ticTac();
        boolean isDead = conway.isAlive();
        Cell cell1 = new Cell(garden,34,11);
        Cell cell2 = new Cell(garden,35,12);
        Cell cell3 = new Cell(garden,35,10);
        garden.ticTac();
        boolean isAlive = conway.isAlive();    
        assertNotEquals(isDead,isAlive);
        for(int r = 35-1; r<=35+1;r++){
            for(int c = 11-1; c<=11+1;c++){
                garden.setThing(r, c, null);
            }
        }
    }
    
    @Test
    public void shouldConwayDiedWithMoreThan3Cells(){
        Flower conway = new Conway(garden,35,11,"robert");
        Cell cell1 = new Cell(garden,34,11);
        Cell cell2 = new Cell(garden,35,12);
        Cell cell3 = new Cell(garden,35,10);
        garden.ticTac();
        boolean isAlive = conway.isAlive();
        Cell cell4 = new Cell(garden,36,11);
        garden.ticTac();
        boolean isDead = conway.isAlive();    
        assertNotEquals(isDead,isAlive);
        for(int r = 35-1; r<=35+1;r++){
            for(int c = 11-1; c<=11+1;c++){
                garden.setThing(r, c, null);
            }
        }
    }
    
    @Test
    public void shouldConwayDiedWithLessThan2Cells(){
        Flower conway = new Conway(garden,35,11,"robert");
        Cell cell1 = new Cell(garden,34,11);
        garden.ticTac();
        boolean isAlive = conway.isAlive();
        assertFalse(isAlive);
        for(int r = 35-1; r<=35+1;r++){
            for(int c = 11-1; c<=11+1;c++){
                garden.setThing(r, c, null);
            }
        }
    }
    
    @Test
    public void shouldConwayLiveWith2Cells(){
        Flower conway = new Conway(garden,35,11,"robert");
        Cell cell1 = new Cell(garden,34,11);
        Cell cell2 = new Cell(garden,35,12);
        garden.ticTac();
        boolean isAlive = conway.isAlive();
        assertTrue(isAlive);
        for(int r = 35-1; r<=35+1;r++){
            for(int c = 11-1; c<=11+1;c++){
                garden.setThing(r, c, null);
            }
        }
    }
}

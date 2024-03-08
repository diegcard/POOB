import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * The test class SpiderwebC2Test.
 *
 * @author  Diego Cardenas - Sebastian Cardona
 * @version 1.0 - 02/03/2024
 */
public class SpiderwebC2Test {
    //Las pruebas se van a hacer para un Spiderweb de 7 hilos y un radio de 500
    private Spiderweb spiderweb = new Spiderweb(7, 500);

    @BeforeEach
    public void before() {
        //Añadir Puntos
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        //Añadir Puentes
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        //sentar araña
        
    }

    @Test
    public void accordingCCShouldReturnTheRightNumbersOfStrands(){
        assertEquals(spiderweb.getStrands(),7);
    }
    
    @Test
    public void accordingCCShouldNotReturnFalseNumbersOfStrands(){
        assertNotEquals(spiderweb.getStrands(),100);
    }
    
    @Test
    public void accordingCCShouldReturnTheRightRadioOfTheWeb(){
        assertEquals(spiderweb.getRadio(),500);
    }
    
    @Test
    public void accordingCCShouldNotReturnFalseRadioOfTheWeb(){
        assertNotEquals(spiderweb.getRadio(),501);
    }
    
    @Test
    public void accordingCCShouldAddSpot() {
        spiderweb.addSpot("green", 3);
        assertTrue(spiderweb.ok());
        String[] valorDevolver = {"red", "green", "blue"};
        assertArrayEquals(spiderweb.spots(), valorDevolver);
    }

    @Test
    public void accordingCCShouldNotAddSpotWithSameColor() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("red", 1);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotAddSpotInNonExistingStrand() {
        spiderweb.addSpot("red", 11);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void accordingCCShouldReturnReachableSpots() {
        spiderweb.spiderSit(2);
        String[] valorDevolver = {"red"};
        assertArrayEquals(spiderweb.reachablesSpots(), valorDevolver);
        spiderweb.spiderSit(7);
        String[] valorDevolver2 = {};
        assertArrayEquals(spiderweb.reachablesSpots(), valorDevolver2);
    }

    @Test
    public void accordingCCShouldNotAllowReachableSpotsWhithoutSitting() {
        spiderweb.reachablesSpots();
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotReturnFalseReachableSpots() {
        spiderweb.spiderSit(2);
        String[] valorDevolver = {"green"};
        assertNotEquals(Arrays.asList(spiderweb.reachablesSpots()),Arrays.asList(valorDevolver));
    }
    
    @Test
    public void accordingCCShouldAddStrand() {
        spiderweb.addStrand();
        assertEquals(spiderweb.getStrands(), 8);
    }

    @Test
    public void accordingCCShouldNotAddTwoStrandsStrandAtTime() {
        spiderweb.addStrand();
        assertNotEquals(spiderweb.getStrands(), 9);
    }

    @Test
    public void accordingCCShouldEnlarge(){
        spiderweb.enlarge(10);
        assertEquals(spiderweb.getRadio(), 550);
    }

    @Test
    public void accordingCCShouldNotEnlargeFalseValue(){
        spiderweb.enlarge(10);
        assertNotEquals(spiderweb.getRadio(), 605);
    }

    @Test
    public void accordingCCShouldNotCutTheStrand(){
        spiderweb.enlarge(-10);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void accordingCCShouldAddBridge(){
        spiderweb.addBridge("red", 100, 1);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotAdddRepeatBridge(){
        spiderweb.addBridge("red", 200, 1);
        spiderweb.addBridge("red", 200, 1);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotAdddBridgeInNonExistingStrand(){
        spiderweb.addBridge("red", 200, 70);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void accordingCCShouldNotAdddBridgeWithDistanceLargerThanStrand(){
        spiderweb.addBridge("red", 1000, 2);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void accordingCCShouldRelocateBride(){
        spiderweb.addBridge("red", 200, 5);
        spiderweb.relocateBridge("red", 200);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotRelocateANotExistingBridge(){
        spiderweb.addBridge("red", 200, 1);
        spiderweb.relocateBridge("blue", 700);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotRelocateABrideWithDistanceLargerThanStrand(){
        spiderweb.addBridge("red", 200, 1);
        spiderweb.relocateBridge("blue", 1000);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void accordingCCShouldDelBride(){
        spiderweb.addBridge("red", 200, 1);
        spiderweb.delBridge("red");
        assertTrue(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotDelSameBrideTwice(){
        spiderweb.addBridge("red", 200, 1);
        spiderweb.delBridge("red");
        spiderweb.delBridge("red");
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldDelSpot(){
        spiderweb.delSpot("red");
        assertTrue(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotDelNotExistingSpot(){
        spiderweb.delSpot("green");
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldSpiderSit(){
        spiderweb.spiderSit(1);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotSpiderSitInANotExistingSpot(){
        spiderweb.spiderSit(8);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldSpiderWalk(){
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldNotSpiderWalkWithOutSitting(){
        spiderweb.spiderWalk(true);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void accordingCCShouldSpiderLastPath(){
        spiderweb.addBridge("red", 200, 2);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        int[]   valorDevolver = {2, 3, 1};
        assertArrayEquals(spiderweb.spiderLastPath(), valorDevolver);
    }

    @Test
    public void accordingCCShouldNotReturnFalseSpiderLastPath(){
        spiderweb.addBridge("red", 200, 2);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        int[]   valorDevolver = {3, 3, 1};
        assertNotEquals(Arrays.asList(spiderweb.spiderLastPath()),Arrays.asList(valorDevolver));
    }

    @Test
    public void accordingCCShouldReturnColorBridges(){
        String[] valorDevolver = {"green", "blue"};
        assertArrayEquals(spiderweb.bridges(), valorDevolver);
        spiderweb.addBridge("red", 200, 1);
        String[] valorDevolver2 = {"red", "green", "blue"};
        assertArrayEquals(spiderweb.bridges(), valorDevolver2);
    }

    @Test
    public void accordingCCShouldNotReturnFalseBridges(){
        String[] valorDevolver2 = {"green", "blue", "red"};
        assertNotEquals(Arrays.asList(spiderweb.bridges()), Arrays.asList(valorDevolver2));
    }

    @Test
    public void accordingCCShouldReturnBridgeLocation(){
        int[] valorDevolver = {1, 2};
        spiderweb.addBridge("red", 200, 1);
        assertArrayEquals(spiderweb.bridge("red"), valorDevolver);
        int[] valorDevolver2 = {4, 5};
        assertArrayEquals(spiderweb.bridge("blue"), valorDevolver2);
    }

    @Test
    public void accordingCCShouldNotReturnFalseBridgeLocation(){
        int[] valorDevolver = {1, 2};
        spiderweb.addBridge("red", 2, 1);
        assertArrayEquals(spiderweb.bridge("red"), valorDevolver);
        int[] valorDevolver2 = {4, 5};
        assertArrayEquals(spiderweb.bridge("blue"), valorDevolver2);
    }

    @Test
    public void accordingCCShouldReturnSpotsColor(){
        String[] valorDevolver = {"red", "blue"};
        assertArrayEquals(spiderweb.spots(), valorDevolver);
        spiderweb.addSpot("green", 1);
        String[] valorDevolver2 = {"red", "green", "blue"};
        assertArrayEquals(spiderweb.spots(), valorDevolver2);
    }

    @Test
    public void accordingCCShouldNotReturnFalseSpotsColor(){
        String[] valorDevolver = {"red"};
        assertNotEquals(Arrays.asList(spiderweb.spots()), Arrays.asList(valorDevolver));
    }

    @Test
    public void accordingCCShouldReturnSpotLocation(){
        int valorDevolver = 2;
        spiderweb.addSpot("green", 2);
        assertEquals(spiderweb.spot("green"), valorDevolver);
        int valorDevolver2 = 1;
        assertEquals(spiderweb.spot("red"), valorDevolver2);
    }

    @Test
    public void accordingCCShoulNotReturnFalseSpotLocation(){
        int valorDevolver = 3;
        spiderweb.addSpot("green", 2);
        assertNotEquals(spiderweb.spot("green"), valorDevolver);
        int valorDevolver2 = 8;
        assertNotEquals(spiderweb.spot("red"), valorDevolver2);
    }

    @Test
    public void accordingCCShouldShowUnusedBridge(){
        spiderweb.addBridge("red", 150, 1);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        String[] valorDevolver = {"blue"};
        assertArrayEquals(spiderweb.unusedBridges(), valorDevolver);
    }

    @Test
    public void accordingCCShoulNotShowIncorrectUnusedBridge(){
        spiderweb.addBridge("red", 150, 1);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        String[] valorDevolver = {"red", "green", "blue"};
        assertNotEquals(Arrays.asList(spiderweb.unusedBridges()), Arrays.asList(valorDevolver));
    }

}
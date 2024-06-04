import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The test class SpiderwebC2Test.
 *
 * @author Diego Cardenas - Sebastian Cardona
 * @version 1.0 - 02/03/2024
 */
public class SpiderwebC2Test {
    private final Spiderweb spiderweb = new Spiderweb(7, 500);

    @Test
    public void ShouldReturnTheRightNumbersOfStrands() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        assertEquals(spiderweb.getStrands(), 7);
    }

    @Test
    public void ShouldNotReturnFalseNumbersOfStrands() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        assertNotEquals(spiderweb.getStrands(), 100);
    }

    @Test
    public void ShouldReturnTheRightRadioOfTheWeb() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        assertEquals(spiderweb.getRadio(), 500);
    }

    @Test
    public void ShouldNotReturnFalseRadioOfTheWeb() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        assertNotEquals(spiderweb.getRadio(), 501);
    }

    @Test
    public void ShouldAddSpot() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addSpot("green", 3);
        assertTrue(spiderweb.ok());
        String[] valorDevolver = {"red", "green", "blue"};
        assertArrayEquals(spiderweb.spots(), valorDevolver);
    }

    @Test
    public void ShouldNotAddSpotWithSameColor() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("red", 1);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldNotAddSpotInNonExistingStrand() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addSpot("red", 11);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldReturnReachableSpots() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.spiderSit(2);
        String[] valorDevolver = {"red"};
        assertArrayEquals(spiderweb.reachablesSpots(), valorDevolver);
        spiderweb.spiderSit(7);
        String[] valorDevolver2 = {};
        assertArrayEquals(spiderweb.reachablesSpots(), valorDevolver2);
    }

    @Test
    public void ShouldNotAllowReachableSpotsWhithoutSitting() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.reachablesSpots();
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldNotReturnFalseReachableSpots() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.spiderSit(2);
        String[] valorDevolver = {"green"};
        assertNotEquals(Arrays.asList(spiderweb.reachablesSpots()), Arrays.asList(valorDevolver));
    }

    @Test
    public void ShouldAddStrand() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addStrand();
        assertEquals(spiderweb.getStrands(), 8);
    }

    @Test
    public void ShouldNotAddTwoStrandsStrandAtTime() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addStrand();
        assertNotEquals(spiderweb.getStrands(), 9);
    }

    @Test
    public void ShouldEnlarge() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.enlarge(10);
        assertEquals(spiderweb.getRadio(), 550);
    }

    @Test
    public void ShouldNotEnlargeFalseValue() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.enlarge(10);
        assertNotEquals(spiderweb.getRadio(), 605);
    }

    @Test
    public void ShouldNotCutTheStrand() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.enlarge(-10);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldAddBridge() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 100, 1);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void ShouldNotAdddRepeatBridge() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 1);
        spiderweb.addBridge("red", 200, 1);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldNotAdddBridgeInNonExistingStrand() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 70);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldNotAdddBridgeWithDistanceLargerThanStrand() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 1000, 2);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldRelocateBride() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 5);
        spiderweb.relocateBridge("red", 200);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void ShouldNotRelocateANotExistingBridge() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 1);
        spiderweb.relocateBridge("blue", 700);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldNotRelocateABrideWithDistanceLargerThanStrand() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 1);
        spiderweb.relocateBridge("blue", 1000);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldDelBride() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 1);
        spiderweb.delBridge("red");
        assertTrue(spiderweb.ok());
    }

    @Test
    public void ShouldNotDelSameBrideTwice() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 1);
        spiderweb.delBridge("red");
        spiderweb.delBridge("red");
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldDelSpot() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.delSpot("red");
        assertTrue(spiderweb.ok());
    }

    @Test
    public void ShouldNotDelNotExistingSpot() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.delSpot("green");
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldSpiderSit() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.spiderSit(1);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void ShouldNotSpiderSitInANotExistingSpot() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.spiderSit(8);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldSpiderWalk() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        assertTrue(spiderweb.ok());
    }

    @Test
    public void ShouldNotSpiderWalkWithOutSitting() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.spiderWalk(true);
        assertFalse(spiderweb.ok());
    }

    @Test
    public void ShouldSpiderLastPath() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 2);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        int[] valorDevolver = {2, 3, 1};
        assertArrayEquals(spiderweb.spiderLastPath(), valorDevolver);
    }

    @Test
    public void ShouldNotReturnFalseSpiderLastPath() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 200, 2);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        int[] valorDevolver = {3, 3, 1};
        assertNotEquals(Collections.singletonList(spiderweb.spiderLastPath()), List.of(valorDevolver));
    }

    @Test
    public void ShouldReturnColorBridges() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        String[] valorDevolver = {"green", "blue"};
        assertArrayEquals(spiderweb.bridges(), valorDevolver);
        spiderweb.addBridge("red", 200, 1);
        String[] valorDevolver2 = {"red", "green", "blue"};
        assertArrayEquals(spiderweb.bridges(), valorDevolver2);
    }

    @Test
    public void ShouldNotReturnFalseBridges() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        String[] valorDevolver2 = {"green", "blue", "red"};
        assertNotEquals(Arrays.asList(spiderweb.bridges()), Arrays.asList(valorDevolver2));
    }

    @Test
    public void ShouldReturnBridgeLocation() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        int[] valorDevolver = {1, 2};
        spiderweb.addBridge("red", 200, 1);
        assertArrayEquals(spiderweb.bridge("red"), valorDevolver);
        int[] valorDevolver2 = {4, 5};
        assertArrayEquals(spiderweb.bridge("blue"), valorDevolver2);
    }

    @Test
    public void ShouldNotReturnFalseBridgeLocation() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        int[] valorDevolver = {1, 2};
        spiderweb.addBridge("red", 2, 1);
        assertArrayEquals(spiderweb.bridge("red"), valorDevolver);
        int[] valorDevolver2 = {4, 5};
        assertArrayEquals(spiderweb.bridge("blue"), valorDevolver2);
    }

    @Test
    public void ShouldReturnSpotsColor() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        String[] valorDevolver = {"red", "blue"};
        assertArrayEquals(spiderweb.spots(), valorDevolver);
        spiderweb.addSpot("green", 1);
        String[] valorDevolver2 = {"red", "green", "blue"};
        assertArrayEquals(spiderweb.spots(), valorDevolver2);
    }

    @Test
    public void ShouldNotReturnFalseSpotsColor() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        String[] valorDevolver = {"red"};
        assertNotEquals(Arrays.asList(spiderweb.spots()), Arrays.asList(valorDevolver));
    }

    @Test
    public void ShouldReturnSpotLocation() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        int valorDevolver = 2;
        spiderweb.addSpot("green", 2);
        assertEquals(spiderweb.spot("green"), valorDevolver);
        int valorDevolver2 = 1;
        assertEquals(spiderweb.spot("red"), valorDevolver2);
    }

    @Test
    public void ShoulNotReturnFalseSpotLocation() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        int valorDevolver = 3;
        spiderweb.addSpot("green", 2);
        assertNotEquals(spiderweb.spot("green"), valorDevolver);
        int valorDevolver2 = 8;
        assertNotEquals(spiderweb.spot("red"), valorDevolver2);
    }

    @Test
    public void ShouldShowUnusedBridge() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 150, 1);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        String[] valorDevolver = {"blue"};
        assertArrayEquals(spiderweb.unusedBridges(), valorDevolver);
    }

    @Test
    public void ShoulNotShowIncorrectUnusedBridge() {
        spiderweb.addSpot("red", 1);
        spiderweb.addSpot("blue", 5);
        spiderweb.addBridge("green", 100, 1);
        spiderweb.addBridge("blue", 300, 4);
        spiderweb.addBridge("red", 150, 1);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        String[] valorDevolver = {"red", "green", "blue"};
        assertNotEquals(Arrays.asList(spiderweb.unusedBridges()), Arrays.asList(valorDevolver));
    }

}
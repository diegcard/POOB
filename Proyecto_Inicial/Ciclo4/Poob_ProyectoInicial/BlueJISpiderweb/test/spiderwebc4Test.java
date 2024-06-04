package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import spiderweb.*;

/**
 * The test class SpiderwebC4Test.
 * These tests are focused on the inherited classes and their functionalities
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0 - 02/03/2024
 */
public class spiderwebc4Test {
    
    private final Spiderweb spiderweb = new Spiderweb(7, 500);
    @Test
    public void ShouldAcceptInsertNormalBridges() {
        spiderweb.addBridge("normal","green", 100, 1);
        spiderweb.addBridge("normal","blue", 300, 4);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldNotAcceptInsertTwoBridgesInSamePlace() {
        spiderweb.addBridge("normal","green", 100, 1);
        spiderweb.addBridge("normal","blue", 100, 1);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void ShouldNotAcceptInsertTwoBridgesAdyacents() {
        spiderweb.addBridge("normal","green", 100, 2);
        spiderweb.addBridge("normal","blue", 100, 1);
        
        spiderweb.addBridge("normal","yellow", 100, 4);
        spiderweb.addBridge("normal","red", 100, 5);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void ShouldAcceptInsertFixedBridges() {
        spiderweb.addBridge("fixed","green", 100, 2);
        spiderweb.addBridge("normal","red", 100, 5);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldNotAcceptInsertUnknowBridgesTypes() {
        spiderweb.addBridge("skjdhkds","green", 100, 2);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void ShouldNotAllowDeleteFixedBridges() {
        spiderweb.addBridge("fixed","green", 100, 2);
        spiderweb.addBridge("normal","red", 100, 5);
        boolean moment1 = spiderweb.ok();
        spiderweb.delBridge("green");
        assertNotEquals(moment1,spiderweb.ok());
    }
    
    @Test
    public void ShouldAcceptInsertTransformerBridges() {
        spiderweb.addBridge("transformer","green", 100, 2);
        spiderweb.addBridge("transformer","red", 100, 5);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldTransformerBridgesConvertInSpotsWhenAreDeliting() {
        spiderweb.addBridge("transformer","green", 100, 2);
        spiderweb.delBridge("green");
        int spot = spiderweb.spot("green");
        assertEquals(2,spot);
    }
    
    @Test
    public void ShouldTransformerBridgesConvertInSpotsWhenAreDelitingWithTheSameColor() {
        spiderweb.addBridge("transformer","green", 100, 2);
        spiderweb.delBridge("green");
        String[] spot = spiderweb.spots();
        String[] valorDevolver1 = {"green"};
        assertArrayEquals(spot, valorDevolver1);
    }
    
    @Test
    public void ShouldNotAllowCreateASpotDelitingATransformerBridgesWhenInTheStrandAlreadyExistsASpot() {
        spiderweb.addSpot("blue",2);
        spiderweb.addBridge("transformer","green", 100, 2);
        spiderweb.delBridge("green");
        String[] spot = spiderweb.spots();
        String[] valorDevolver1 = {"blue"};
        assertArrayEquals(spot, valorDevolver1);
    }
    
    @Test
    public void ShouldNotAllowCreateASpotDelitingATransformerBridgesWhenInASpotWithTheSameColorAlreadyExists() {
        spiderweb.addSpot("green",4);
        spiderweb.addSpot("blue",1);
        spiderweb.addBridge("transformer","green", 100, 2);
        spiderweb.delBridge("green");
        int spot = spiderweb.spot("green");
        assertEquals(4,spot);
    }
    
    @Test
    public void ShouldAcceptInsertWeakBridges() {
        spiderweb.addBridge("weak","green", 100, 2);
        spiderweb.addBridge("weak","red", 100, 5);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldDeleteAWeakBridgesWhenTheSpiderWalkOverIt() {
        spiderweb.addBridge("weak","green", 100, 2);
        spiderweb.addBridge("weak","red", 100, 5);
        spiderweb.spiderSit(2);
        spiderweb.spiderWalk(true);
        String[] bridges = spiderweb.bridges();
        String[] valorDevolver1 = {"red"};
        assertArrayEquals(bridges, valorDevolver1);
    }
    
    @Test
    public void ShouldAcceptInsertMobileBridges() {
        spiderweb.addBridge("mobile","green", 100, 2);
        spiderweb.addBridge("mobile","red", 100, 5);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldAMobileBridgeMoveToTheNextStrandWhenASpiderWalkOver() {
        spiderweb.addBridge("mobile","green", 100, 2);
        spiderweb.spiderSit(3);
        spiderweb.spiderWalk(true);
        int[] bridge = spiderweb.bridge("green");
        int[] valorDevolver1 = {3,4};
        assertArrayEquals(bridge, valorDevolver1);
    }
    
    @Test
    public void ShouldNotAMobileBridgeMoveToTheNextStrandWhenASpiderWalkOverAndInTheNextStrandAreAnotherBridge() {
        spiderweb.addBridge("mobile","green", 100, 2);
        spiderweb.addBridge("mobile","red", 120, 3);
        spiderweb.spiderSit(2);
        spiderweb.spiderWalk(true);
        int[] bridge = spiderweb.bridge("green");
        int[] valorDevolver1 = {2,3};
        assertArrayEquals(bridge, valorDevolver1);
    }
    
    @Test
    public void ShouldNotAMobileBridgeMoveToTheNextStrandWhenASpiderWalkOverAndThereAreNotEnoughSpace() {
        spiderweb.addBridge("mobile","green", 490, 2);
        spiderweb.spiderSit(2);
        spiderweb.spiderWalk(true);
        int[] bridge = spiderweb.bridge("green");
        int[] valorDevolver1 = {2,3};
        assertArrayEquals(bridge, valorDevolver1);
    }
    
    @Test
    public void ShouldAcceptInsertNormalSpots() {
        spiderweb.addSpot("normal","green",1);
        spiderweb.addSpot("normal","blue", 4);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldNotAcceptInsertTwoSpotsInSamePlace() {
        spiderweb.addSpot("normal","green",1);
        spiderweb.addSpot("normal","blue", 1);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void ShouldNotAcceptInsertASpotInInvalidStrand() {
        spiderweb.addSpot("normal","green",-1);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void ShouldAcceptInsertBounciesSpots() {
        spiderweb.addSpot("bouncy","green",1);
        spiderweb.addSpot("bouncy","blue", 2);
        spiderweb.addSpot("bouncy","yellow",3);
        spiderweb.addSpot("bouncy","orange", 4);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldBounciesSpotsTranslatingSpider() {
        spiderweb.addSpot("bouncy","green",1);
        spiderweb.addSpot("bouncy","blue", 2);
        spiderweb.addSpot("bouncy","yellow",3);
        spiderweb.addSpot("bouncy","orange", 4);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        assertEquals(spiderweb.getCurrentStrand(),5);
    }
    
    @Test
    public void ShouldNotAcceptInsertUnknowSpotsTypes() {
        spiderweb.addSpot("ahag","green",1);
        assertFalse(spiderweb.ok());
    }
    
    @Test
    public void ShouldAcceptInsertKillerSpots() {
        spiderweb.addSpot("killer","green",1);
        spiderweb.addSpot("killer","blue", 2);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldKillerSpotsDeleteTheSpider() {
        spiderweb.addSpot("killer","green",1);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        assertNull(spiderweb.getSpider());
    }
    
    @Test
    public void ShouldSpiderSitAppearTheSpider() {
        spiderweb.addSpot("killer","green",1);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        spiderweb.spiderSit(2);
        assertNotNull(spiderweb.getSpider());
    }
    
    @Test
    public void ShouldAcceptInsertColorsSpots() {
        spiderweb.addSpot("color","green",1);
        spiderweb.addSpot("color","blue", 2);
        assertTrue(spiderweb.ok());
    }
    
    @Test
    public void ShouldChangeSpiderColorWhenHeArrivedToAColorSpot() {
        spiderweb.addSpot("color","green",1);
        spiderweb.spiderSit(1);
        spiderweb.spiderWalk(true);
        assertEquals(spiderweb.getSpiderColor(),"green");
    }
}

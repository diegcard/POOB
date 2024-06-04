import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The test class SpiderWebContestTest.
 *
 * @author Diego Cardenas - Sebastian Cardona
 * @version 1.0 - 17/03/2024
 */
public class SpiderWebContestTest{
    
    @Test
    public void ShouldReturnTheRightSolutionOfTheProblem() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(7,6,bridges);
        int [] solution = {2,1,1,1,0,1,2};
        assertArrayEquals(spiderwebcontest.solve(7,6,bridges),solution);
    }
    
    @Test
    public void ShouldReturnTheRightSolutionOfTheProblemWithOtherSpot() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(7,6,bridges);
        int [] solution = {1,1,2,2,2,1,0};
        assertArrayEquals(spiderwebcontest.solve(7,1,bridges),solution);
    }
    
    @Test
    public void ShouldReturnTheRightSolutionOfTheProblem2() {
        int[][] bridges = {{1,1},{2,2},{3,3},{4,4}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(4,2,bridges);
        int [] solution = {1,1,0,1};
        assertArrayEquals(spiderwebcontest.solve(4,2,bridges),solution);
    }
    
    @Test
    public void ShouldReturnTheRightSolutionOfTheProblem3() {
        int[][] bridges = {{5,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(5,1,bridges);
        int [] solution = {1,1,2,1,0};
        assertArrayEquals(spiderwebcontest.solve(5,1,bridges),solution);
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblem() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(7,6,bridges);
        int [] solution = {2,1,1,4,0,4,2};
        assertNotEquals(Arrays.asList(spiderwebcontest.solve(7,6,bridges)), Arrays.asList(solution));
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblemWithOtherSpot() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(7,6,bridges);
        int [] solution = {1,1,0,2,2,1,0};
        assertNotEquals(Arrays.asList(spiderwebcontest.solve(7,1,bridges)), Arrays.asList(solution));
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblem2() {
        int[][] bridges = {{1,1},{2,2},{3,3},{4,4}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(4,2,bridges);
        int [] solution = {0,0,0,1};
        assertNotEquals(Arrays.asList(spiderwebcontest.solve(4,2,bridges)), Arrays.asList(solution));
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblem3() {
        int[][] bridges = {{5,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(5,1,bridges);
        int [] solution = {1,1,2,1,10000};
        assertNotEquals(Arrays.asList(spiderwebcontest.solve(5,1,bridges)), Arrays.asList(solution));
    }
    
    @Test
    public void ShouldNotAcceptBridgeWithDistanceLargerThanRadio() {
        int[][] bridges = {{450,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(5,1,bridges);
        assertFalse(spiderwebcontest.isOk);
    }
    
    @Test
    public void ShouldSimulateTheProblem() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(7,6,bridges);
        spiderwebcontest.simulate(7,6,bridges);
        assertTrue(spiderwebcontest.isOk);
    }
    
    @Test
    public void ShouldSimulateTheProblemWithOtherSpot() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(7,6,bridges);
        spiderwebcontest.simulate(7,1,bridges);
        assertTrue(spiderwebcontest.isOk);
    }
    
    @Test
    public void ShouldSimulateTheProblem2() {
        int[][] bridges = {{1,1},{2,2},{3,3},{4,4}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(4,2,bridges);
        spiderwebcontest.simulate(4,2,bridges);
        assertTrue(spiderwebcontest.isOk);
    }
    
    @Test
    public void ShouldSimulateTheProblem3() {
        int[][] bridges = {{5,5}};
        SpiderWebContest spiderwebcontest = new SpiderWebContest(5,1,bridges);
        spiderwebcontest.simulate(5,1,bridges);
        assertTrue(spiderwebcontest.isOk);
    }
    
}

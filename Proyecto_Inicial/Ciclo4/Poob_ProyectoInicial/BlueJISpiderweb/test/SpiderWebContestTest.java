package test;
import static org.junit.jupiter.api.Assertions.*;
import spiderweb.*;
import org.junit.jupiter.api.Test;
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
        int [] solution = {2,1,1,1,0,1,2};
        assertArrayEquals(SpiderWebContest.solve(7,6,bridges),solution);
    }
    
    @Test
    public void ShouldReturnTheRightSolutionOfTheProblemWithOtherSpot() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        int [] solution = {1,1,2,2,2,1,0};
        assertArrayEquals(SpiderWebContest.solve(7,1,bridges),solution);
    }
    
    @Test
    public void ShouldReturnTheRightSolutionOfTheProblem2() {
        int[][] bridges = {{1,1},{2,2},{3,3},{4,4}};
        int [] solution = {1,1,0,1};
        assertArrayEquals(SpiderWebContest.solve(4,2,bridges),solution);
    }
    
    @Test
    public void ShouldReturnTheRightSolutionOfTheProblem3() {
        int[][] bridges = {{5,5}};
        int [] solution = {1,1,2,1,0};
        assertArrayEquals(SpiderWebContest.solve(5,1,bridges),solution);
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblem() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        int [] solution = {2,1,1,4,0,4,2};
        assertNotEquals(Collections.singletonList(SpiderWebContest.solve(7, 6, bridges)), List.of(solution));
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblemWithOtherSpot() {
        int[][] bridges = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        int [] solution = {1,1,0,2,2,1,0};
        assertNotEquals(Collections.singletonList(SpiderWebContest.solve(7, 1, bridges)), List.of(solution));
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblem2() {
        int[][] bridges = {{1,1},{2,2},{3,3},{4,4}};
        int [] solution = {0,0,0,1};
        assertNotEquals(Collections.singletonList(SpiderWebContest.solve(4, 2, bridges)), List.of(solution));
    }
    
    @Test
    public void ShouldNotReturnFalseSolutionOfTheProblem3() {
        int[][] bridges = {{5,5}};
        int [] solution = {1,1,2,1,10000};
        assertNotEquals(Collections.singletonList(SpiderWebContest.solve(5, 1, bridges)), List.of(solution));
    }
    

    
}

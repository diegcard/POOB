package test;
import domain.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ActivityTest{
   
    
    public ActivityTest(){
    }


    @Before
    public void setUp(){    
    }

    @After
    public void tearDown(){
    }
    

    @Test
    public void shouldCalculateTheTimeOfAComposedSecuencialActivity(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        try {
           assertEquals(45,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }    
    
    
     @Test
    public void shouldCalculateTheTimeOfAComposedSecuencialActivitylevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 20));
        Composed c2 = new Composed("IS-LOGICA", 100 , false );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(130,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldCalculateTheTimeOfAComposedParallelActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(20,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }  
    
    @Test
    public void shouldCalculateTheTimeOfAComposedParallelActivitylevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(24,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }   
    
    @Test
    public void shouldCalculateTheTimeOfAComposedMixActivitylevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(69,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfComposedIsEmpty(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }    
    }    
    
    
   @Test
    public void shouldThrowExceptionIfThereIsErrorInTime(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, -20));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInTimeLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, -20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }        
    }
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInTime1(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }    
    }     
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInTimeLevel3_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 50));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }        
    }
    
   @Test
    public void shouldThrowExceptionIfTimeIsNotKnown(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfTimeIsNotKnownLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, null));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
        }        
    }
    
    @Test
    public void shouldAttendTimeEmptyExceptionWithDefaultValue(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, null));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        
        assertEquals(30,c.time(60,40,30));  
    }
    
    @Test
    public void shouldAttendTimeErrorExceptionWithDefaultValue(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 21));
        c1.add(new Simple("AYPR", 10, -5));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        
        assertEquals(40,c.time(60,40,30));
    }
    
    @Test
    public void shouldAttendComposedEmptyExceptionWithDefaultValue(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        assertEquals(60,c.time(60,40,30));  
    }

    @Test
    public void shouldCalculateTheTimeOfSecuencialActivityWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(52,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfSecuencialActivityWithModalityALevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 80));
        Composed c2 = new Composed("IS-LOGICA", 100 , false );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
           assertEquals(60,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfSecuencialActivityWithModalityA1(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, 30));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(45,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfSecuencialSubActivityThatThrowsExceptionWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('A');
            assertEquals(69,time);
            assertEquals(17,c1.getTime());
            assertEquals(17,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfSecuencialSubActivityThatThrowsExceptionWithModalityM(){
        Composed c = new Composed("IS-BASICA", 100 , false);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('M');
            assertEquals(75,time);
            assertEquals(20,c1.getTime());
            assertEquals(20,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfParallelActivityWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(15,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfParallelActivityWithModalityALevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, null));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            assertEquals(20,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfParallelActivityWithModalityA1(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(15,c.time('A'));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfParallelSubActivityThatThrowsExceptionWithModalityA(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('A');
            assertEquals(20,time);
            assertEquals(17,c1.getTime());
            assertEquals(17,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldEstimateTheTimeOfParallelSubActivityThatThrowsExceptionWithModalityM(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        Activity c1 = new Simple("CVDS", 10, null);
        Activity c2 = new Simple("AYED", 10, 80);
        c.add(c2);
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        c.add(c1);
        try {
            int time = c.time('M');
            assertEquals(20,time);
            assertEquals(20,c1.getTime());
            assertEquals(20,c2.getTime());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfAModalityTimeIsImposibleToEstimate(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 40));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 30));
        try {
            int time=c.time('M');
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfAModalityTimeIsImposibleToEstimate1(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 30));
        try {
            int time=c.time('C');
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldShearchForAnActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        assertEquals("AYED",c.search("AYED").name());
    }

    @Test
    public void shouldShearchForAnActivityLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        assertEquals("MMIN",c.search("MMIN").name());
    }

    @Test
    public void shouldShearchForAnActivityLevel3_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        assertEquals("IS-BEGINING",c.search("IS-BEGINING").name());
    }

    @Test
    public void shouldShearchForAnActivityAndReturnNullIfDoesntExistsLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        assertNull(c.search("PEPE"));
    }

    @Test
    public void shouldCalculateTheTimeOfASubActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
            assertEquals(20,c.time("MBDA"));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheTimeOfASubActivityLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , true );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            assertEquals(24,c.time("IS-BEGINING"));
        } catch (ProjectException e){
            fail("Threw a exception");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldCalculateTheTimeOfASubActivityLevel3_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            assertEquals(54,c.time("IS-BEGINING"));
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfDoesntExistsASubActivityToCalculateTheTime(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("Guillermo");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.UNKNOWN,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfIsImposibleCalculateTheTimeOfASubAcivity(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 40));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("AYPR");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfIsImposibleCalculateTheTimeOfASubAcivity_1(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, null));
        c1.add(new Simple("INSI", 10, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("IS-BEGINING");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfIsImposibleCalculateTheTimeOfASubAcivity_2(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 10, 10));
        c1.add(new Simple("INSI", 10, 24));
        c1.add(new Composed("IS-BEGINING1", 100 , false ));
        Composed c2 = new Composed("IS-LOGICA", 100 , true );
        c2.add(new Simple("LCAT", 10, 5));
        c2.add(new Simple("MATD", 10, 15));
        c2.add(new Simple("TPRO", 10, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));//Se cambia a 15
        c.add(c2);
        try {
            int time=c.time("IS-BEGINING1");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.IMPOSIBLE,e.getMessage());
        }
    }

    @Test
    public void shouldCalculateTheCostOfAComposedSecuencialActivity(){
        Composed c = new Composed("IS-BASICA", 60 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 20, 20));
        c.add(new Simple("POOB", 30, 15));
        try {
           assertEquals(60,c.cost());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldCalculateTheCostOfAComposedParallelActivity(){
        Composed c = new Composed("IS-BASICA", 150 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 20, 20));
        c.add(new Simple("POOB", 30, 15));
        try {
            assertEquals(150,c.cost());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }
    @Test
    public void shouldCalculateTheCostOfAComposedSecuencialActivityLevel3(){
        Composed c1 = new Composed("IS-BEGINING", 100 , false );
        c1.add(new Simple("MMIN", 10, 20));
        c1.add(new Simple("AYPR", 20, 10));
        c1.add(new Simple("INSI", 30, 24));
        Composed c2 = new Composed("IS-LOGICA", 100 , false );
        c2.add(new Simple("LCAT", 40, 5));
        c2.add(new Simple("MATD", 50, 15));
        c2.add(new Simple("TPRO", 60, 15));
        c2.add(c1);
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 70, 10));
        c.add(new Simple("MBDA", 80, 20));
        c.add(new Simple("POOB", 90, 15));//Se cambia a 15
        c.add(c2);
        try {
            assertEquals(100,c.cost());
        } catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfComposedIsEmptyCost(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        try {
           int cost=c.cost();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfThereIsErrorInCost(){
        Composed c = new Composed("IS-BASICA", -100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 20, 20));
        c.add(new Simple("POOB", 30, 15));
        try {
           int cost=c.cost();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COST_ERROR,e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfThereIsEmptyCost(){
        Composed c = new Composed("IS-BASICA", null , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 20, 20));
        c.add(new Simple("POOB", 30, 15));
        try {
           int cost=c.cost();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COST_EMPTY,e.getMessage());
        }
    }

    @Test
    public void shouldAddAnActivitySimpleInTheProject(){
        Project p = new Project();
        try{
            p.add("aYED3","10","15","");
            assertEquals("7 actividades\n>aYED3. Costo:10.Tiempo:15\n",p.search("aYED3"));
        }catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldAddAnActivityComposedInTheProject(){
        Project p = new Project();
        try{
            p.add("AYED","10","15","");
            p.add("IS-BASICA","100","secuencial","AYED");
        }catch (ProjectException e){
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldAProjectListAllActivities() {
        Project p = new Project();
        try {
            p.add("AYED", "10", "15", "");
            p.add("IS-BASICA", "100", "secuencial", "AYED");
            assertEquals("8 actividades\n>Iterar 3 veces. Tipo Paralela.\n\tIterar. Costo:1000.Tiempo:10\n\tIterar. Costo:1000.Tiempo:10\n\tIterar. Costo:1000.Tiempo:10\n>Buscar datos. Costo:50.Tiempo:15\n>Limpiar datos. Costo:100.Tiempo:24\n>AYED. Costo:10.Tiempo:15\n>Iterar. Costo:1000.Tiempo:10\n>Evaluar datos. Costo:80.Tiempo:20\n>IS-BASICA. Tipo Secuencial.\n\tAYED. Costo:10.Tiempo:15\n>Preparar datos. Tipo Secuencial.\n\tBuscar datos. Costo:50.Tiempo:15\n\tEvaluar datos. Costo:80.Tiempo:20\n\tLimpiar datos. Costo:100.Tiempo:24\n", p.toString());
        } catch (ProjectException e) {
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldAProjectListAllActivities1(){
        Project p = new Project();
        try{
            p.add("AYED","1","15","");
            p.add("IS-BASICA","20","secuencial","AYED");
            assertEquals("8 actividades\n>Iterar 3 veces. Tipo Paralela.\n\tIterar. Costo:1000.Tiempo:10\n\tIterar. Costo:1000.Tiempo:10\n\tIterar. Costo:1000.Tiempo:10\n>Buscar datos. Costo:50.Tiempo:15\n>Limpiar datos. Costo:100.Tiempo:24\n>AYED. Costo:1.Tiempo:15\n>Iterar. Costo:1000.Tiempo:10\n>Evaluar datos. Costo:80.Tiempo:20\n>IS-BASICA. Tipo Secuencial.\n\tAYED. Costo:1.Tiempo:15\n>Preparar datos. Tipo Secuencial.\n\tBuscar datos. Costo:50.Tiempo:15\n\tEvaluar datos. Costo:80.Tiempo:20\n\tLimpiar datos. Costo:100.Tiempo:24\n",p.toString());
        }catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void testAddAndConsultActivities(){
        Project p = new Project();
        try{
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");

            //consultemos la actividad AYED
            Activity ayed = p.consult("AYED");
            assertEquals("AYED",ayed.name());

            //consultemos la actividad MBDA
            Activity mbda = p.consult("MBDA");
            assertEquals("MBDA",mbda.name());

            //consultemos la actividad POOB
            Activity poob = p.consult("POOB");
            assertEquals("POOB",poob.name());

            //consultemos la actividad IS-BASICA
            Activity isBasica = p.consult("IS-BASICA");
            assertEquals("IS-BASICA",isBasica.name());

            //calculemos el tiempo de la actividad IS-BASICA
            try {
                assertEquals(20,isBasica.time());
            } catch (ProjectException e){
                fail("Threw a exception");
            }
        }catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldTheProjectDontAcceptTwoActivitiesWithTheSameName(){
        Project p = new Project();
        try{
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");
            //agregar la clase compuesta con el mismo nombre
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");
            fail("Did not throw exception");
        }catch (ProjectException e){
            assertEquals(ProjectException.DUPLICATE_ACTIVITY,e.getMessage());
        }
    }
    
    @Test
    public void shouldTheProjectDontAcceptTwoActivitiesWithTheSameName1(){
        Project p = new Project();
        try {
            //Agregar  1 clases básicaa
            p.add("AYED","10","15","");
            //intentar agregar otra actividad con el mismo nombre
            p.add("AYED","20","20","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.DUPLICATE_ACTIVITY,e.getMessage());
        }
    }

    @Test
    public void shouldTheProjectDontAcceptaActivityWithCostOrTimeEmptyOrTypeTimeDiferentToSecuencialOrParallel(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");
            //agregar una clase simple con tiempo vacio
            p.add("INSI","100","","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
            //agregar una clase simple con costo vacio
            try {
                p.add("INSI","","15","");
                fail("Did not throw exception");
            } catch (ProjectException e1) {
                assertEquals(ProjectException.COST_EMPTY,e1.getMessage());
                //agregar una clase compuesta con tipo de tiempo diferente a secuencial o paralela
                try {
                    p.add("PRYE","100","pepe","AYED\nMBDA\nPOOB");
                    fail("Did not throw exception");
                } catch (ProjectException e2) {
                    assertEquals(ProjectException.COMPOSED_ERROR,e2.getMessage());
                }
            }
        }
    }

    @Test
    public void shouldDontAcceptProjectTimesAndCostsErrors(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");
            //agregar una clase simple con tiempo vacio
            p.add("INSI","100","-15","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
            //agregar una clase simple con costo negativo
            try {
                p.add("INSI","-100","Paralela","AYED\nMBDA\nPOOB");
                fail("Did not throw exception");
            } catch (ProjectException e1) {
                assertEquals(ProjectException.COST_ERROR,e1.getMessage());
            }
        }
    }

    @Test
    public void shouldDontAcceptProjectTimesAndCostsEmpty(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");
            //agregar una clase simple con tiempo vacio
            p.add("INSI","100","","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
            //agregar una clase simple con costo negativo
            try {
                p.add("PSOC","","Paralela","AYED\nMBDA\nPOOB");
                fail("Did not throw exception");
            } catch (ProjectException e1) {
                assertEquals(ProjectException.COST_EMPTY,e1.getMessage());
            }
        }
    }

    @Test
    public void shouldDontAcceptProjectNamesEmpty(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");
            //agregar una clase simple con tiempo vacio
            p.add("","100","15","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.DATA_INCOMPLETE,e.getMessage());
            //agregar una clase simple con costo negativo
            try {
                p.add("","150","Paralela","AYED\nMBDA\nPOOB");
                fail("Did not throw exception");
            } catch (ProjectException e1) {
                assertEquals(ProjectException.DATA_INCOMPLETE,e1.getMessage());
            }
        }
    }

    @Test
    public void shouldDontAcceptProjectActivitiesWithTypeDiferrentToSecuencialOrParallel(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","pipe","AYED\nMBDA\nPOOB");
            //agregar una clase simple con tiempo vacio
            p.add("ses","100","15","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_ERROR,e.getMessage());
            //agregar una clase simple con costo negativo
            try {
                p.add("frani","150","15","AYED\nMBDA\nPOOB");
                fail("Did not throw exception");
            } catch (ProjectException e1) {
                assertEquals(ProjectException.COMPOSED_ERROR,e1.getMessage());
            }
        }
    }

    @Test
    public void shouldDontAceptProjectComposedActivitiesWithOutSubActivities(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");

            //agregar una clase compuesta, pero sin subactividades
            p.add("IS-ss","100","Paralela","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }
    }

    @Test
    public void shouldDontAceptProjectSimpleActivitiesWithFalseNumberTime(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");

            //agregar una clase simple, pero sin un numero en tiempo
            p.add("IS-bg","100","pepgrillo","");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }
    }

    @Test
    public void shouldDontAceptProjectComposedOrSimpleActivitiesWithFalseNumberCost(){
        Project p = new Project();
        try {
            //Agregar  3 clases básicaa
            p.add("AYED","10","15","");
            p.add("MBDA","20","20","");
            p.add("POOB","30","15","");
            //Agregar 1 clases compuestas
            p.add("IS-BASICA","100","Paralela","AYED\nMBDA\nPOOB");

            //agregar una clase simple, pero sin correcto costo
            p.add("IS-BASICA1","abc","Secuencial","AYED\nMBDA\nPOOB");
            fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COST_ERROR,e.getMessage());
        }
    }

    @Test
    public void shouldProjectSearchForAnActivity(){
        Project p = new Project();
        try{
            p.add("POBCITo","110","20","");
            assertEquals("7 actividades\n>POBCITo. Costo:110.Tiempo:20\n",p.search("pob"));
        }catch (ProjectException e){
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldProjectThrowAnExeptionWhenSearchForAnNonExistingActivity(){
        Project p = new Project();
        try{
            assertEquals("7 actividades\n>aYED3. Costo:10.Tiempo:15\n",p.search("aYED3"));
            fail("Did not throw exception");
        }catch (Exception e){
            assertEquals(ProjectException.SEARCH_EMPTY,e.getMessage());
        }
    }
}
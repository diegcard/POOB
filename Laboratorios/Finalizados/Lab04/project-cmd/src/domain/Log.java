package domain;    

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

/**
 *  This class represents a log of the project that can be used
 *  to record exceptions
 *
 * @author POOB
 * @version ECI 2022
 */
public class Log{
    public static String name="Project";
    
    public static void record(Exception e){
        try{
            Logger logger=Logger.getLogger(name);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(name+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }
}
    

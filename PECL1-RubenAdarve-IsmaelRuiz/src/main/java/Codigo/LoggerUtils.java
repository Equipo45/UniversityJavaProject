/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codigo;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author ismir
 */
public class LoggerUtils {
    private Logger logger;
    private FileHandler fileh;  
    private File archivo ;
    private String logName = "evolucionCartas";
    
    //Podremos poner el loger que nosotros queramos, en este caso habra un log para cada clase
    public LoggerUtils(){
        logger = Logger.getLogger(logName);
        archivo = new File("src\\main\\java\\logs\\"+logName+".txt");
        if(archivo.exists()){
            try {
                System.out.println("Entra");
                fileh = new FileHandler("src\\main\\java\\logs\\"+logName+".txt");
                logger.addHandler(fileh);
                //Esto lo usamos para a la hora de escribir el log sea mas entendible
                SimpleFormatter formato = new SimpleFormatter();
                fileh.setFormatter(formato);
            } catch (IOException | SecurityException ex) {
                System.out.println(ex);
            }
        } else {
            try {
                boolean result = archivo.createNewFile();
                fileh = new FileHandler("src\\main\\java\\logs\\"+logName+".txt");
                logger.addHandler(fileh);
                SimpleFormatter formato = new SimpleFormatter();
                fileh.setFormatter(formato);
            } catch (IOException | SecurityException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public void log(String mensaje){
        logger.info(mensaje);
    }
    
}

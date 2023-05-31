/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codigo;

/**
 *
 * @author Usuario
 */
public class Persona extends Thread {

    private final String nombre;
    private final Buzon buzon;
    private final LoggerUtils logger;

    public Persona(String nombre, Buzon buzon, LoggerUtils logger) {
        this.nombre = nombre;
        this.buzon = buzon;
        this.logger = logger;
    }

    public void run() {

        long TiempoDejarCarta = 0;
        int i = 3;
        for (int x = 1; x < i; x++) {
            try {                
                sleep((long) 1000 + (int) ((25000 - 1000) * Math.random()));
                TiempoDejarCarta = (long) 400 + (int) ((800 - 400) * Math.random());
                String carta = nombre + "-C" + x;
                buzon.dejarCarta(carta);
                logger.log(nombre + " deja la carta " + carta + " en el buzon.");
                sleep(TiempoDejarCarta);
            } catch (InterruptedException ex) {
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codigo;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.JTextPane;

/**
 *
 * @author Usuario
 */
public class Furgoneta {

    private final ArrayList<String> furgo;
    private final String nombreFurgo;
    private final Semaphore ExclusionMutua = new Semaphore(1);
    private final Semaphore lleno = new Semaphore(1);
    private final LoggerUtils logger;
    private final JTextPane salidaFurgo;

    public Furgoneta(String nombre, LoggerUtils logger, JTextPane panel) {
        this.nombreFurgo = nombre;
        this.furgo = new ArrayList<>();
        this.logger = logger;
        this.salidaFurgo = panel;
    }

    public void DepositarFurgo(String carta, String nombre) throws InterruptedException {
        try {
            lleno.acquire();
            ExclusionMutua.acquire();
            furgo.add(carta);
            salidaFurgo.setText("" + furgo);
            logger.log("La carta/" + carta + "/ ha sido depositada por /" + nombre + "/ en " + nombreFurgo);
        } finally {
            ExclusionMutua.release();
        }
    }

    public void VueltaAlBuzon(String nombre) throws InterruptedException {
        try {
            ExclusionMutua.acquire();
            logger.log(nombre + " ha vuelto de la - " + nombreFurgo);
        } finally {
            ExclusionMutua.release();
            lleno.release();
        }
    }
}

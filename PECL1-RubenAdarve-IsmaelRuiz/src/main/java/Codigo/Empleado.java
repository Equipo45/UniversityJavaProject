/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codigo;

import javax.swing.JTextPane;

/**
 *
 * @author Usuario
 */
public class Empleado extends Thread {

    private final String nombre;
    private final Buzon buzon;
    private final Furgoneta furgoneta1;
    private final Furgoneta furgoneta2;
    private final LoggerUtils logger;
    private final JTextPane salidaEmpleado;
    private final Pausar pausa;

    public Empleado(String nombre, Buzon buzon, Furgoneta furgo1, Furgoneta furgo2, LoggerUtils logger, JTextPane panel, Pausar pausaEspecifica) {
        this.nombre = nombre;
        this.buzon = buzon;
        this.furgoneta1 = furgo1;
        this.furgoneta2 = furgo2;
        this.logger = logger;
        this.salidaEmpleado = panel;
        this.pausa = pausaEspecifica;
    }

    public void run() {
        long TiempoDejarCarta = 0;
        String salida;
        String carta;
        while (true) {
            pausa.mirar();
            TiempoDejarCarta = (long) 400 + (int) ((700 - 400) * Math.random());//primer tiempo random que tome en el primer sleep
            try {
                carta = buzon.cogerCarta();//coge la carta el repartidor
                sleep(TiempoDejarCarta);
                TiempoDejarCarta = (long) 400 + (int) ((700 - 400) * Math.random()); //segundo tiempo random para que coja en el otro sleep

                if (buzon.getFurgo(carta) == 1) {
                    salida = nombre + " coge la carta " + carta + " y va a depositarla en la furgoneta 1";
                    salidaEmpleado.setText(salida);
                    logger.log(nombre + " coge la carta " + carta + " y va a depositarla en la furgoneta 1");
                    furgoneta1.DepositarFurgo(carta, nombre);//la deposita en la furgoneta 1

                    salida = nombre + " depositando " + carta + " en la furgoneta 1";
                    salidaEmpleado.setText(salida);

                    sleep(TiempoDejarCarta);

                    salida = nombre + " volviendo de la furgoneta 1";
                    salidaEmpleado.setText(salida);
                    furgoneta1.VueltaAlBuzon(nombre);

                } else {
                    salida = nombre + " coge la carta " + carta + " y va a depositarla en la furgoneta 2";
                    salidaEmpleado.setText(salida);
                    furgoneta2.DepositarFurgo(carta, nombre);//la deposita en la furgoneta 2
                    salida = nombre + " depositando " + carta + " en la furgoneta 2";
                    salidaEmpleado.setText(salida);
                    logger.log(nombre + " coge la carta " + carta + " y va a depositarla en la furgoneta 2");
                    sleep(TiempoDejarCarta);

                    salida = nombre + " volviendo de la furgoneta 2";
                    salidaEmpleado.setText(salida);
                    furgoneta2.VueltaAlBuzon(nombre);
                }
                if (pausa.estaPausado()) {
                    salidaEmpleado.setText(" Esta pausado");
                }
                if (buzon.getCartasBuzon() == 0) {// si esta vacio el buzon entonces no tiene nada que repartir
                    salidaEmpleado.setText(nombre + " no tiene cartas que repartir");
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}

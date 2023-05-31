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
public class Buzon {

    private final ArrayList<String> buzon;
    private final Semaphore ExclusionMutua = new Semaphore(1);
    private final Semaphore vacio = new Semaphore(0); // numero de elementos
    private final Semaphore lleno;    // numero de huecos
    private final JTextPane salidaBuzon;
    private final JTextPane TamanoBuzon;

    public Buzon(int maximo, JTextPane panel, JTextPane tamanoBuzon) {
        this.buzon = new ArrayList<>(maximo);
        this.lleno = new Semaphore(maximo);
        this.salidaBuzon = panel;
        this.TamanoBuzon = tamanoBuzon;

    }

    public String cogerCarta() throws InterruptedException {
        try {
            vacio.acquire();
            ExclusionMutua.acquire();
            String carta = buzon.get(0);
            buzon.remove(0);
            if (buzon.size() == 0) {
                salidaBuzon.setText("");//para cuando se inicializa no salga vacio
            } else {
                salidaBuzon.setText("" + buzon);
            }
            TamanoBuzon.setText(String.valueOf(buzon.size()));
            return carta;
        } finally {
            ExclusionMutua.release();
            lleno.release();
        }
    }

    public void dejarCarta(String carta) throws InterruptedException {
        try {
            lleno.acquire();
            ExclusionMutua.acquire();
            buzon.add(carta);
            if (buzon.size() == 0) {
                salidaBuzon.setText("");//para cuando se inicializa no salga vacio esto : []
            } else {
                salidaBuzon.setText("" + buzon);
            }
            TamanoBuzon.setText(String.valueOf(buzon.size()));
        } finally {
            ExclusionMutua.release();
            vacio.release();
        }
    }

    public int getFurgo(String carta) {
//este metodo sirve para obtener el ultimo caracter de la carta
//que es a donde va a ir a la furgoneta segun ese numero
        String Identificador;
        Identificador = carta;
        int id;
        id = Integer.parseInt(Identificador.substring(Identificador.length() - 1));//elimino de la cadena todo y me quedo con el ultimo digito para saber que numero es
        return id;
    }

    public int getCartasBuzon() {
        return this.buzon.size();
    }
}

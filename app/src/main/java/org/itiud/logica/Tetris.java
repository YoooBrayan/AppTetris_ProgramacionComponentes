package org.itiud.logica;

import android.graphics.Color;

import org.itiud.controlador.ControlTouch;
import org.itiud.util.Constantes;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends Observable {
    public final static int FILAS = 20;
    public final static int COLUMNAS = 10;
    int matriz[][] = new int[20][10];
    private Ficha fichaActual;
    private ArrayList<Integer> filasAValidar;
    private Timer timer;

    public int[][] getMatriz() {
        return matriz;
    }

    public void fichaNueva() {
        //int n = (int)(Math.random() * 2);
        int n = 0;
        if (n == 0) {
            this.fichaActual = new I(this);
        } else if (n == 1) {
            this.fichaActual = new L(this);
        } else if (n == 2) {
            this.fichaActual = new J(this);
        }
    }

    public void iniciarJuego() {
        this.fichaNueva();
        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(this.fichaActual.getSegmentos());
        elemento.add(this.fichaActual.getColor());
        this.setChanged();
        this.notifyObservers(elemento);
        final TimerTask timerTask = new TimerTask() {
            public void run() {
                moverFicha(Constantes.ABAJO);
            }
        };
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void moverFicha(String movimiento) {
        int[][] segmentosAnteriores = new int[4][2];
        for (int i = 0; i < segmentosAnteriores.length; i++) {
            segmentosAnteriores[i][0] = this.fichaActual.getSegmentos()[i][0];
            segmentosAnteriores[i][1] = this.fichaActual.getSegmentos()[i][1];
        }
        boolean hecho = false;
        boolean fijado = false;
        if (movimiento == Constantes.IZQUIERDA) {
            if (this.fichaActual.izquierda()) {
                // Puede mover hacia la izquierda
                hecho = true;
            } else {
                // encontro limite
                fijado = true;
                fijar();
            }
        } else if (movimiento == Constantes.DERECHA) {
            if (this.fichaActual.derecha()) {
                // Puede mover hacia la derecha
                hecho = true;
            } else {
                // encontro limite
                fijado = true;
                fijar();
            }
        } else if (movimiento == Constantes.ABAJO) {
            if (this.fichaActual.abajo()) {
                // Puede seguir bajando
                hecho = true;
            } else {
                // encontro piso o limite
                fijado = true;
                fijar();
            }
        } else if (movimiento == Constantes.PISO) {
            this.fichaActual.piso(); //
            fijado = true;
            fijar();
        } else if (movimiento == Constantes.ARRIBA) {
            hecho = this.fichaActual.rotar();
        }
        if (hecho || fijado) {
            ArrayList<Object> elemento = new ArrayList<Object>();
            elemento.add(this.fichaActual.getSegmentos());
            elemento.add((!fijado) ? this.fichaActual.getColor() : Color.GRAY);
            elemento.add(segmentosAnteriores);
            this.setChanged();
            this.notifyObservers(elemento);
        }
        if (fijado) {
            this.validarEliminar();
            this.fichaNueva();
            if (!validarFinJuego()) {
                ArrayList<Object> elemento = new ArrayList<Object>();
                elemento.add(this.fichaActual.getSegmentos());
                elemento.add(this.fichaActual.getColor());
                this.setChanged();
                this.notifyObservers(elemento);
            } else {
                this.timer.cancel();
                ArrayList<Object> elemento = new ArrayList<Object>();
                elemento.add(null);
                elemento.add(null);
                elemento.add(null);
                elemento.add(null);
                elemento.add(true);
                this.setChanged();
                this.notifyObservers(elemento);
            }
        }
    }

    public void fijar() {
        this.filasAValidar = new ArrayList<Integer>();
        // toma la coordenadas de la ficha actual y actualiza la matriz con 1
        for (int i = 0; i < this.fichaActual.getSegmentos().length; i++) {
            this.matriz[this.fichaActual.getSegmentos()[i][0]][this.fichaActual.getSegmentos()[i][1]] = 1;
            Integer fila = new Integer(this.fichaActual.getSegmentos()[i][0]);
            if (!this.filasAValidar.contains(fila)) {
                this.filasAValidar.add(fila);
            }
        }
    }

    public boolean validarFila(int fila) {
        for (int i = 0; i < Tetris.COLUMNAS; i++) {
            if (this.matriz[fila][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void eliminarFila(int fila) {
        for (int i = fila - 1; i >= 0; i--) {
            for (int j = 0; j < Tetris.COLUMNAS; j++) {
                this.matriz[i + 1][j] = this.matriz[i][j];
                if (i == 0) {
                    this.matriz[i][j] = 0;
                }
            }
        }
    }

    public boolean validarFilas(ArrayList<Integer> filasAValidar) {
        boolean eliminaFilas = false;
        for (Integer fila : filasAValidar) {
            if (this.validarFila(fila.intValue())) {
                this.eliminarFila(fila.intValue());
                eliminaFilas = true;
            }
        }
        return eliminaFilas;
    }

    public void validarEliminar() {
        if (this.validarFilas(this.filasAValidar)) {
            ArrayList<Object> elemento = new ArrayList<Object>();
            elemento.add(null);
            elemento.add(null);
            elemento.add(null);
            elemento.add(this.matriz);
            this.setChanged();
            this.notifyObservers(elemento);
        }
    }

    private boolean validarFinJuego() {
        for (int i = 0; i < 4; i++) {
            if (this.matriz[this.fichaActual.getSegmentos()[i][0]][this.fichaActual.getSegmentos()[i][1]] == 1) {
                return true;
            }
        }
        return false;
    }
}
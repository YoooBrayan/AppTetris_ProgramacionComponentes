package org.itiud.logica;

public abstract class Ficha {
    protected Tetris tetris;
    protected int segmentos[][] = new int[4][2];
    protected int color;
    protected int orientacion = 1;

    public Ficha(Tetris tetris) {
        this.tetris = tetris;
    }

    public int[][] getSegmentos() {
        return segmentos;
    }

    public int getColor() {
        return color;
    }

    public abstract boolean rotar();

    public boolean izquierda() {
        for (int i = 0; i < this.segmentos.length; i++) {
            if (this.segmentos[i][1] == 0 ||
                    this.tetris.getMatriz()[this.segmentos[i][0]][this.segmentos[i][1] - 1] == 1) {
                return false;
            }
        }
        for (int i = 0; i < 4; i++) {
            this.segmentos[i][1]--;
        }
        return true;
    }

    public boolean derecha() {
        for (int i = 0; i < this.segmentos.length; i++) {
            if (this.segmentos[i][1] == Tetris.COLUMNAS - 1 ||
                    this.tetris.getMatriz()[this.segmentos[i][0]][this.segmentos[i][1] + 1] == 1) {
                return false;
            }
        }
        for (int i = 0; i < 4; i++) {
            this.segmentos[i][1]++;
        }
        return true;
    }

    public boolean abajo() {
        for (int i = 0; i < this.segmentos.length; i++) {
            if (this.segmentos[i][0] == Tetris.FILAS - 1 ||
                    this.tetris.getMatriz()[this.segmentos[i][0] + 1][this.segmentos[i][1]] == 1) {
                return false;
            }
        }
        for (int i = 0; i < 4; i++) {
            this.segmentos[i][0]++;
        }
        return true;
    }

    // evento que baja de un solo movimiento la ficha hasta encontrar el piso o limite
    public void piso() {
        boolean esPiso = false;
        do {
            for (int i = 0; i < this.segmentos.length; i++) {
                if (this.segmentos[i][0] == Tetris.FILAS - 1 || this.tetris.getMatriz()[this.segmentos[i][0] + 1][this.segmentos[i][1]] == 1) {
                    esPiso = true;
                }
            }
            if (!esPiso) {
                for (int i = 0; i < 4; i++) {
                    this.segmentos[i][0]++;
                }
            }
        } while (!esPiso);
    }
}

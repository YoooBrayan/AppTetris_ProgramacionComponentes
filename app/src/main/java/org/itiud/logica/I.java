package org.itiud.logica;

import android.graphics.Color;

public class I extends Ficha {

    public I(Tetris tetris){
        super(tetris);
        this.segmentos[0][0] = 0; // x
        this.segmentos[0][1] = 3; // y
        this.segmentos[1][0] = 0;
        this.segmentos[1][1] = 4;
        this.segmentos[3][0] = 0;
        this.segmentos[3][1] = 6;
        this.segmentos[2][0] = 0;
        this.segmentos[2][1] = 5;
        this.color = Color.CYAN;
    }

    @Override
    public boolean rotar() {
        if(this.orientacion == 0){ // parado
            // Validar Bordes Horizontales
            if(this.segmentos[0][1] > 0 && this.segmentos[0][1] < Tetris.COLUMNAS-2 && this.segmentos[0][0] != Tetris.FILAS-1 && this.segmentos[1][0] != 0){
                this.segmentos[0][0]+=1;
                this.segmentos[0][1]-=1;
                this.segmentos[2][0]-=1;
                this.segmentos[2][1]+=1;
                this.segmentos[3][0]-=2;
                this.segmentos[3][1]+=2;

                // validar Borde horizontal izquierdo
            }else if(this.segmentos[0][1] == 0){
                this.segmentos[0][0]+=1;
                this.segmentos[1][1]+=1;
                this.segmentos[2][0]-=1;
                this.segmentos[2][1]+=2;
                this.segmentos[3][0]-=2;
                this.segmentos[3][1]+=3;

                // validar borde horizontal derecho con ultima columna
            }else if(this.segmentos[0][1] == Tetris.COLUMNAS-1){
                this.segmentos[0][0]+=1;
                this.segmentos[0][1]-=3;
                this.segmentos[1][1]-=2;
                this.segmentos[2][0]-=1;
                this.segmentos[2][1]-=1;
                this.segmentos[3][0]-=2;

                // validar borde horizontal derecho con penultima columna
            }else if(this.segmentos[0][1] == Tetris.COLUMNAS-2){
                this.segmentos[0][0]+=1;
                this.segmentos[0][1]-=2;
                this.segmentos[1][1]-=1;
                this.segmentos[2][0]-=1;
                this.segmentos[3][0]-=2;
                this.segmentos[3][1]+=1;
            }else if(this.segmentos[0][0] == Tetris.FILAS-1){
                this.segmentos[0][0]-=1; // x
                this.segmentos[0][1]-=1; // y
                this.segmentos[2][0]+=1;
                this.segmentos[2][1]+=1;
                this.segmentos[3][0]+=2;
                this.segmentos[3][1]+=2;
            }else if(this.segmentos[1][0] == 0){
                this.segmentos[0][0]-=1; // x
                this.segmentos[0][1]-=1; // y
                this.segmentos[2][0]-=2;
                this.segmentos[2][1]+=1;
                this.segmentos[3][0]-=3;
                this.segmentos[3][1]+=2;
            }
            this.orientacion = 1;
        }else if(this.orientacion == 1){ // acostado
            // validar borde vertical abajo con penultima columna
            if(this.segmentos[0][0] == Tetris.FILAS-2){
                this.segmentos[0][0]+=1;
                this.segmentos[0][1]+=1;
                this.segmentos[2][0]-=1;
                this.segmentos[2][1]-=1;
                this.segmentos[3][0]-=2;
                this.segmentos[3][1]-=2;
                // validar borde vertical abajo con ultima columna
            }else if(this.segmentos[0][0] == Tetris.FILAS-1){
                this.segmentos[0][0]-=3; // x
                this.segmentos[0][1]+=1; // y
                this.segmentos[1][0]-=2; // x
                this.segmentos[2][0]-=1; // x
                this.segmentos[2][1]-=1; // y
                this.segmentos[3][1]-=2; // y
                // validar borde vertical arriba con primera columna
            }else if(this.segmentos[0][0] == 0){
                this.segmentos[0][1]+=1; // y
                this.segmentos[1][0]+=1; // x
                this.segmentos[2][0]+=2; // x
                this.segmentos[2][1]-=1; // y
                this.segmentos[3][0]+=3; // x
                this.segmentos[3][1]-=2; // y
            }else if(this.segmentos[0][0] < Tetris.FILAS-2){
                this.segmentos[0][0]-=1; // x
                this.segmentos[0][1]+=1; // y
                this.segmentos[2][0]+=1; // x
                this.segmentos[2][1]-=1; // y
                this.segmentos[3][0]+=2; // x
                this.segmentos[3][1]-=2; // y
            }
            this.orientacion = 0;
        }
        return true;
    }
}


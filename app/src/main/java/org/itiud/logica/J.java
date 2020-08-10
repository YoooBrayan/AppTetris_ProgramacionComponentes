package org.itiud.logica;

import android.graphics.Color;

public class J extends Ficha {

    public J(Tetris tetris){
        super(tetris);
        this.segmentos[0][0] = 0;
        this.segmentos[0][1] = 5;
        this.segmentos[1][0] = 1;
        this.segmentos[1][1] = 5;
        this.segmentos[2][0] = 2;
        this.segmentos[2][1] = 5;
        this.segmentos[3][0] = 2;
        this.segmentos[3][1] = 4;
        this.color = Color.RED;
    }

    @Override
    public boolean rotar() {

        return true;


    }



}

package org.itiud.logica;

import android.graphics.Color;

public class L extends Ficha {

    public L(Tetris tetris){
        super(tetris);
        this.segmentos[0][0] = 0;
        this.segmentos[0][1] = 4;
        this.segmentos[1][0] = 1;
        this.segmentos[1][1] = 4;
        this.segmentos[2][0] = 2;
        this.segmentos[2][1] = 4;
        this.segmentos[3][0] = 2;
        this.segmentos[3][1] = 5;
        this.color = Color.YELLOW;
    }

    @Override
    public boolean rotar() {
        if(this.orientacion == 1){
            this.segmentos[0][0]+=1;
            this.segmentos[0][1]+=1;
            this.segmentos[2][0]-=1;
            this.segmentos[2][1]-=1;
            //this.segmentos[3][0]-=0;
            this.segmentos[3][1]-=2;
            this.orientacion = 2;
        }else if(this.orientacion == 2){
            this.segmentos[0][0]+=1;
            this.segmentos[0][1]-=1;
            this.segmentos[2][0]-=1;
            this.segmentos[2][1]+=1;
            this.segmentos[3][0]-=2;
            //this.segmentos[3][1]-=2;
            this.orientacion = 3;
        }else if(this.orientacion == 3){
            this.segmentos[0][0]-=1;
            this.segmentos[0][1]-=1;
            this.segmentos[2][0]+=1;
            this.segmentos[2][1]+=1;
            //this.segmentos[3][0]-=2;
            this.segmentos[3][1]+=2;
            this.orientacion = 4;
        }else if(this.orientacion == 4){
            this.segmentos[0][0]-=1;
            this.segmentos[0][1]+=1;
            this.segmentos[2][0]+=1;
            this.segmentos[2][1]-=1;
            this.segmentos[3][0]+=2;
            //this.segmentos[3][1]+=2;
            this.orientacion = 1;
        }
        return true;
    }


}

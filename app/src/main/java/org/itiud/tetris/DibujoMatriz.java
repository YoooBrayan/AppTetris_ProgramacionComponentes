package org.itiud.tetris;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.itiud.logica.Tetris;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DibujoMatriz implements Observer {
    private GridLayout gridLayoutMatriz;
    private static TextView textViewMatriz[][];
    private LinearLayout linearLayoutTetris;
    private MainActivity mainActivity;

    public DibujoMatriz(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.textViewMatriz = new TextView[Tetris.FILAS][Tetris.COLUMNAS];
        this.gridLayoutMatriz = new GridLayout(this.mainActivity);
        this.gridLayoutMatriz.setColumnCount(10);
        this.gridLayoutMatriz.setRowCount(20);
        this.linearLayoutTetris = this.mainActivity.findViewById(R.id.linearLayoutTetris);
        Display display = this.mainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(2, 2, 2, 2);

        for (int i = 0; i < Tetris.FILAS; i++) {
            for (int j = 0; j < Tetris.COLUMNAS; j++) {
                this.textViewMatriz[i][j] = new TextView(this.mainActivity);
                this.textViewMatriz[i][j].setWidth((int) (width * 0.7 / 10));
                this.textViewMatriz[i][j].setHeight((int) (height * 0.38 / 10));
                this.textViewMatriz[i][j].setLayoutParams(params);
                this.textViewMatriz[i][j].setBackgroundColor(Color.BLACK);
                this.gridLayoutMatriz.addView(this.textViewMatriz[i][j]);
            }
        }
        this.linearLayoutTetris.addView(this.gridLayoutMatriz);
    }

    @Override
    public void update(Observable o, Object arg) {
        final ArrayList<Object> elemento = (ArrayList<Object>) arg;

        this.mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (elemento.get(0) != null) {
                    int segmentos[][] = (int[][]) elemento.get(0);
                    int color = (int) elemento.get(1);
                    if (elemento.size() > 2) {
                        int segmentosAnteriores[][] = (int[][]) elemento.get(2);
                        for (int i = 0; i < segmentosAnteriores.length; i++) {
                            textViewMatriz[segmentosAnteriores[i][0]][segmentosAnteriores[i][1]].setBackgroundColor(Color.BLACK);
                        }
                    }
                    for (int i = 0; i < segmentos.length; i++) {
                        textViewMatriz[segmentos[i][0]][segmentos[i][1]].setBackgroundColor(color);
                    }
                } else if (elemento.get(3) != null) {
                    int matriz[][] = (int[][]) elemento.get(3);
                    for (int i = 0; i < Tetris.FILAS; i++) {
                        for (int j = 0; j < Tetris.COLUMNAS; j++) {
                            textViewMatriz[i][j].setBackgroundColor((matriz[i][j] == 0) ? Color.BLACK : Color.GRAY);
                        }
                    }
                }else if(elemento.get(4) != null){
                    Toast.makeText(mainActivity.getApplicationContext(), "Perdió",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

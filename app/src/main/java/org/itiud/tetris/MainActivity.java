package org.itiud.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.itiud.controlador.ControlTouch;
import org.itiud.logica.Tetris;

public class MainActivity extends AppCompatActivity {
    private Tetris tetris;
    private DibujoMatriz dibujoMatriz;
    private ControlTouch controlTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dibujoMatriz = new DibujoMatriz(this);
        this.controlTouch = new ControlTouch(this);
        this.tetris = new Tetris();
        this.tetris.addObserver(this.dibujoMatriz);
        this.tetris.iniciarJuego();
    }

    public void moverFicha(String movimiento) {
        this.tetris.moverFicha(movimiento);
    }
}

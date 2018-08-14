package com.aprendiz.ragp.concentrese1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity {
    private int fondoJuego= R.drawable.signo;
    private int[] imagenesJuego={
            R.drawable.atleta,
            R.drawable.balon,
            R.drawable.banderacar,
            R.drawable.guante,
            R.drawable.medalla,
            R.drawable.natacion,
            R.drawable.silbato,
            R.drawable.trofeocham
    }, imagenesAleatorias, imagenesFondo;
    private List<Integer> imagenesSelect = new ArrayList<>();
    int nivel=4,modoJuego=1,salir,canselect, pos1=-1,pos2=-1, movimientos;
    int alto=0, ancho=0, inicioJugador;
    int [] segundos ={0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
    }
}

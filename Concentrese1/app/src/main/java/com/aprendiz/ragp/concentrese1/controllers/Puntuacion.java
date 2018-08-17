package com.aprendiz.ragp.concentrese1.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.concentrese1.R;
import com.aprendiz.ragp.concentrese1.models.GestorDB;
import com.aprendiz.ragp.concentrese1.models.Score;

import java.util.List;

public class Puntuacion extends AppCompatActivity implements View.OnClickListener{
    RadioButton btnFacil, btnMedio, btnDificl, btnTiempo, btnMovimientos;
    Button btnContinuar;
    TextView txt1, txt2, txt3, txt4,txt5;
    String dificultad="4", modo="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        inizialite();
        inputClick();
        inputData();
    }

    private void inputData() {
        GestorDB gestorDB = new GestorDB(this);
        List<Score> scores =gestorDB.selectData(modo,dificultad);
        if (scores.size()>0){
            txt1.setText(scores.get(0).getNombre()+" "+scores.get(0).getPunutacion());
        }else {
            Toast.makeText(this, "No hay puntuaciones disponibles", Toast.LENGTH_SHORT).show();
            txt1.setText("");
        }

        if (scores.size()>1){
            txt2.setText(scores.get(1).getNombre()+" "+scores.get(1).getPunutacion());
        }else {
            txt2.setText("");
        }



        if (scores.size()>2){
            txt3.setText(scores.get(2).getNombre()+" "+scores.get(2).getPunutacion());
        }else {
            txt3.setText("");
        }



        if (scores.size()>3){
            txt4.setText(scores.get(3).getNombre()+" "+scores.get(3).getPunutacion());
        }else {
            txt4.setText("");
        }

        if (scores.size()>4){
            txt5.setText(scores.get(4).getNombre()+" "+scores.get(4).getPunutacion());
        }else {
            txt5.setText("");
        }

    }


    private void inizialite() {
        btnFacil = findViewById(R.id.rbtnFacil);
        btnMedio = findViewById(R.id.rbtnMedio);
        btnDificl = findViewById(R.id.rbtnDificil);
        btnTiempo = findViewById(R.id.rbtnTiempos);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnMovimientos = findViewById(R.id.rbtnMovimientos);

        txt1 = findViewById(R.id.txtUno);
        txt2 = findViewById(R.id.txtDos);
        txt3 = findViewById(R.id.txtTres);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);

    }

    private void inputClick() {
        btnFacil.setOnClickListener(this);
        btnMedio.setOnClickListener(this);
        btnDificl.setOnClickListener(this);
        btnTiempo.setOnClickListener(this);
        btnMovimientos.setOnClickListener(this);
        btnContinuar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbtnFacil:
                dificultad="4";
                inputData();
                break;

            case R.id.rbtnMedio:
                dificultad="6";
                inputData();
                break;


            case R.id.rbtnDificil:
                dificultad="8";
                inputData();
                break;

            case R.id.rbtnTiempos:
                modo="1";
                inputData();
                break;

            case R.id.rbtnMovimientos:
                modo="2";
                inputData();
                break;

            case R.id.btnContinuar:
                finish();
                break;


        }
    }
}

package com.aprendiz.ragp.concentrese1.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aprendiz.ragp.concentrese1.R;

public class Configuracion extends AppCompatActivity {
    int modo;
    SharedPreferences juegoC;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        editor = juegoC.edit();
    }

    public void tiempo(View view) {
        editor.putInt("modo",1);
        editor.commit();
        Toast.makeText(this, "Se ha guardado en modo tiempo", Toast.LENGTH_SHORT).show();
    }

    public void movimientos(View view) {
        editor.putInt("modo",2);
        editor.commit();
        Toast.makeText(this, "Se ha guardado en modo movimientos", Toast.LENGTH_SHORT).show();
    }


    public void jugar(View view) {
        Intent intent = new Intent(Configuracion.this,Juego.class);
        startActivity(intent);
        finish();
    }
}

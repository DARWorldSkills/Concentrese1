package com.aprendiz.ragp.concentrese1;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MenuT extends AppCompatActivity {
    public static int modo = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_t);

    }

    public void jugar(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_dificultad);
        dialog.setCancelable(true);
        final RadioButton rbtnFacil = dialog.findViewById(R.id.rbtnFacil);
        final RadioButton rbtnMedio = dialog.findViewById(R.id.rbtnMedio);
        final RadioButton rbtnDificil = dialog.findViewById(R.id.rbtnDificil);
        Button btnAceptar= dialog.findViewById(R.id.btnAceptarD);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbtnFacil.isChecked()){
                    modo=4;
                }

                if (rbtnMedio.isChecked()){
                    modo=6;
                }


                if (rbtnDificil.isChecked()){
                    modo=8;
                }
            }
        });

        dialog.show();

    }

    public void puntuacion(View view) {
    }

    public void configuracion(View view) {
    }
}

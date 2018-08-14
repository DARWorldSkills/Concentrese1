package com.aprendiz.ragp.concentrese1;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    public static String jugador1,jugador2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CountDownTimer countDownTimer = new CountDownTimer(1000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(Splash.this);
                final ConstraintLayout constraintLayout = findViewById(R.id.contenedorSplash);
                dialog.setContentView(R.layout.item_inicio);
                final EditText txtJugador1=dialog.findViewById(R.id.txtJugador1);
                final EditText txtJugador2=dialog.findViewById(R.id.txtJugador2);
                Button btnAceptar = dialog.findViewById(R.id.btnAceptar);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jugador1=txtJugador1.getText().toString();
                        jugador2=txtJugador2.getText().toString();
                        if (jugador1.length()>0 && jugador2.length()>0){
                            Snackbar.make(constraintLayout,"Bienvenido a Concentrese1.",Snackbar.LENGTH_SHORT).show();
                            Intent intent = new Intent(Splash.this,MenuT.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Snackbar.make(constraintLayout,"Por favor ingrese los dos campos.",Snackbar.LENGTH_SHORT).show();
                        }



                    }
                });
                dialog.setCancelable(false);
                dialog.show();

            }
        }.start();

    }


}

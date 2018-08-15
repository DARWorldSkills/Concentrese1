package com.aprendiz.ragp.concentrese1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Resumen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
    }

    public void Compartir(View view) {


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.twitter.android");
        intent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(intent);

        }catch (Exception e){
            Toast.makeText(this, "No cuentas con está app", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void Face(View view) {


    }
}

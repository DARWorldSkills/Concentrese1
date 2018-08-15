package com.aprendiz.ragp.concentrese1.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.concentrese1.R;
import com.aprendiz.ragp.concentrese1.models.AdapterAR;
import com.aprendiz.ragp.concentrese1.models.AdapterJ;
import com.aprendiz.ragp.concentrese1.models.GestorDB;
import com.aprendiz.ragp.concentrese1.models.Score;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity {
    private int fondoJuego= R.drawable.d;
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
    int alto=0, ancho=0, inicioJugador, puntuacionJ1,puntuacionJ2;
    int [] segundos ={0,0};
    TextView txtNombreJ1, txtNombreJ2, txtPuntuacionJ1, txtPuntuacionJ2, txtTiempo;
    RecyclerView contenedorJuego;
    ProgressBar pTiempo;
    boolean bandera=true;
    boolean arreglo=true;
    ImageView imagen1, imagen2;
    View itemR1, itemR2;
    Animator animator,animator1,animator2;
    MediaPlayer endgame;
    MediaPlayer pipe;
    MediaPlayer up1;
    int item;
    int columnas;
    AdapterAR adapterAR;
    public  static boolean arreglo1=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        inputValues();
        obtenerMedidas();
        turnos();
        go_game();

        adapterAR = new AdapterAR(imagenesFondo,item,this);
        contenedorJuego.setAdapter(adapterAR);
        contenedorJuego.setLayoutManager(new GridLayoutManager(this,columnas,GridLayoutManager.VERTICAL,false));
        contenedorJuego.setHasFixedSize(true);
        adapterAR.setMlistener(new AdapterAR.OnItemClickListener() {
            @Override
            public void itemClick(int position, ImageView imagen, View itemView) {


                canselect++;
                ImageView item = imagen;

                if (arreglo) {
                    arreglo=false;
                    if (pos1 == position || pos2 == position) {
                        canselect--;

                    }
                    Log.e("Posiciooooooon:", Integer.toString(position));
                    if (canselect == 1) {
                        pos1 = position;
                        imagen1 = item;
                        itemR1 =itemView;
                        itemR1.setEnabled(false);
                        animator = ViewAnimationUtils.createCircularReveal(imagen1, 0, alto / 2, 0, ancho * 1.5f);
                        animator.setDuration(200);
                        animator.start();
                    }

                    if (canselect == 2) {
                        pos2 = position;
                        imagen2 = item;
                        itemR2 =itemView;
                        itemR2.setEnabled(false);
                        animator = ViewAnimationUtils.createCircularReveal(imagen2, 0, alto / 2, 0, ancho * 1.5f);
                        animator.setDuration(200);
                        animator.start();
                        arreglo1=false;
                    }

                    mostarImagen(position, item);

                }

            }
        });

    }

    private void mostarImagen(int position, ImageView item) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize=3;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imagenesAleatorias[position],op);
        item.setImageBitmap(bitmap);
        if (canselect ==2){
            movimientos++;
            if (modoJuego==2) {
                txtTiempo.setText("Movimientos: " + movimientos);
            }

            new ValidarJuego().execute();
        }
        arreglo=true;
    }

    public class ValidarJuego extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            contenedorJuego.setEnabled(false);
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (inicioJugador==1){
                txtNombreJ1.setTextColor(getColor(R.color.colorVerde));
                txtNombreJ2.setTextColor(getColor(R.color.colorGris));
                inicioJugador=2;

            }else {

                if (inicioJugador == 2) {
                    txtNombreJ2.setTextColor(getColor(R.color.colorVerde));
                    txtNombreJ1.setTextColor(getColor(R.color.colorGris));
                    inicioJugador = 1;
                }
            }


            if (imagenesAleatorias[pos1]==imagenesAleatorias[pos2]){
                animator1 = ViewAnimationUtils.createCircularReveal(imagen1,ancho/2,alto/2,alto/2,0);
                animator2 = ViewAnimationUtils.createCircularReveal(imagen2,ancho/2,alto/2,alto/2,0);
                animator1.setDuration(200);
                animator2.setDuration(200);

                up1.start();
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        itemR1 = null;
                        imagen1.setVisibility(View.INVISIBLE);
                        imagen1=null;
                    }
                });


                animator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        itemR2 = null;
                        imagen2.setVisibility(View.INVISIBLE);
                        imagen2=null;

                        pos1=-1;
                        pos2=-1;
                        contenedorJuego.setEnabled(true);
                        arreglo1=true;
                    }
                });
                animator1.start();animator2.start();

                if (inicioJugador==1){
                    puntuacionJ1+=100;
                    txtPuntuacionJ1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacionJ2.setText(Integer.toString(puntuacionJ2));

                }

                if (inicioJugador==2){
                    puntuacionJ2+=100;
                    txtPuntuacionJ1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacionJ2.setText(Integer.toString(puntuacionJ2));

                }
                salir--;
                if (salir==0){
                    up1.stop();
                    endgame.start();
                    bandera=false;
                    GestorDB gestorDB = new GestorDB(Juego.this);
                    Score score1 = new Score();
                    Score score2 = new Score();
                    score1.setNombre(Splash.jugador1);
                    score2.setNombre(Splash.jugador2);
                    score1.setPunutacion(puntuacionJ1);
                    score2.setPunutacion(puntuacionJ2);
                    score1.setDificultad(Integer.toString(nivel));
                    score1.setModo(Integer.toString(modoJuego));
                    score2.setDificultad(Integer.toString(nivel));
                    score2.setModo(Integer.toString(modoJuego));
                    gestorDB.inputData(score1);
                    gestorDB.inputData(score2);


                    Dialog dialog = new Dialog(Juego.this);
                    dialog.setContentView(R.layout.activity_resumen);
                    TextView txtNombre1 = dialog.findViewById(R.id.txtPuntajeRJ1);
                    TextView txtNombre2 = dialog.findViewById(R.id.txtPuntajeRJ2);
                    TextView txtp1 = dialog.findViewById(R.id.puntuacionR1);
                    TextView txtp2 = dialog.findViewById(R.id.puntuacionR2);
                    TextView txttiempo = dialog.findViewById(R.id.txtTiempoFinal);
                    TextView btnContinuar = dialog.findViewById(R.id.txtContinuar);
                    ImageButton btnTwi = dialog.findViewById(R.id.btnTwi);
                    ImageButton btnface = dialog.findViewById(R.id.btnFace);

                    txtNombre1.setText(Splash.jugador1);
                    txtNombre2.setText(Splash.jugador2);
                    txtp1.setText(Integer.toString(puntuacionJ1));
                    txtp2.setText(Integer.toString(puntuacionJ2));
                    if (modoJuego==1){
                        txttiempo.setText("Tiempo: "+segundos[0]);
                    }else {
                        txttiempo.setText("Movimientos: "+movimientos);
                    }

                    final String mensaje = Splash.jugador1+" puntaje:"+puntuacionJ1+"\n"+Splash.jugador2+" puntaje:"+puntuacionJ2+"\n"+ segundos[0];
                    btnContinuar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });

                    btnface.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                    btnTwi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.setPackage("com.twitter.android");
                            intent.putExtra(Intent.EXTRA_TEXT, mensaje);

                            try {
                                startActivity(intent);

                            }catch (Exception e){
                                Toast.makeText(Juego.this, "No cuentas con está app", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });

                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                }


            }else {
                animator1 = ViewAnimationUtils.createCircularReveal(imagen1,ancho/2,alto/2,alto,0);
                animator2 = ViewAnimationUtils.createCircularReveal(imagen2,ancho/2,alto/2,alto,0);
                animator1.setDuration(200);
                animator2.setDuration(200);
                itemR1.setEnabled(true);
                itemR2.setEnabled(true);
                pipe.start();
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        BitmapFactory.Options op = new BitmapFactory.Options();
                        op.inSampleSize=1;
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),fondoJuego,op);
                        imagen1.setImageBitmap(bitmap);
                        Animator animator= ViewAnimationUtils.createCircularReveal(imagen1,0,alto/2,2,ancho*1.5f);
                        animator.setDuration(200);
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);


                            }
                        });
                        animator.start();
                    }
                });

                animator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        BitmapFactory.Options op = new BitmapFactory.Options();
                        op.inSampleSize=1;
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),fondoJuego,op);
                        imagen2.setImageBitmap(bitmap);
                        Animator animator= ViewAnimationUtils.createCircularReveal(imagen2,0,alto/2,2,ancho*1.5f);
                        animator.setDuration(200);
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                pos1=-1;
                                pos2=-1;
                                contenedorJuego.setEnabled(true);
                                arreglo1=true;
                            }
                        });
                        animator.start();
                    }
                });

                animator1.start();animator2.start();

                if (inicioJugador==1){
                    puntuacionJ1-=1;
                    txtPuntuacionJ1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacionJ2.setText(Integer.toString(puntuacionJ2));

                }

                if (inicioJugador==2){
                    puntuacionJ2-=1;
                    txtPuntuacionJ1.setText(Integer.toString(puntuacionJ1));
                    txtPuntuacionJ2.setText(Integer.toString(puntuacionJ2));

                }


            }
            pos1=-1;
            pos2=-1;
            canselect=0;



        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void inizialite() {
        txtNombreJ1 = findViewById(R.id.txtNombreJ1);
        txtNombreJ2 = findViewById(R.id.txtNombreJ2);
        txtPuntuacionJ1 = findViewById(R.id.txtPuntuacionJ1);
        txtPuntuacionJ2 = findViewById(R.id.txtPuntuacionJ2);
        txtTiempo = findViewById(R.id.txtTiempo);
        contenedorJuego = findViewById(R.id.contenedorJuego);
        pTiempo = findViewById(R.id.progressBar);
        pipe = MediaPlayer.create(this,R.raw.smb_pipe);
        up1 = MediaPlayer.create(this,R.raw.smb_up);
        endgame = MediaPlayer.create(this,R.raw.smb_stage_clear);

    }

    private void inputValues() {
        SharedPreferences juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        nivel = MenuT.modo;
        modoJuego=juegoC.getInt("modo",1);
        txtNombreJ1.setText(Splash.jugador1);
        txtNombreJ2.setText(Splash.jugador2);
        pTiempo.setMax(60);
        salir=nivel;
        arreglo=true;

    }

    private void obtenerMedidas() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = (int) getResources().getDisplayMetrics().density;
        int dpH = metrics.heightPixels/density;
        int dpW = metrics.widthPixels/density;
        alto = dpH/3;
        contenedorJuego.setPadding(10,10,10,10);
        if (nivel==4){
            ancho= dpW/2;
            item=R.layout.item_1;
            columnas=2;
        }

        if (nivel==6){
            ancho= dpW/3;
            item=R.layout.item_2;
            columnas=3;
        }

        if (nivel==8){
            ancho= dpW/4;
            item=R.layout.item_3;
            columnas=4;
        }

    }

    private void turnos() {
        inicioJugador = (int) (Math.random() *2)+1;
        if (inicioJugador==1){
            txtNombreJ1.setTextColor(getColor(R.color.colorVerde));
            txtNombreJ2.setTextColor(getColor(R.color.colorGris));
            Snackbar.make(contenedorJuego,"Empieza Jugador 1",Snackbar.LENGTH_SHORT).show();
            txtPuntuacionJ1.setText(Integer.toString(puntuacionJ1));
            txtPuntuacionJ2.setText(Integer.toString(puntuacionJ2));
        }

        if (inicioJugador==2){
            txtNombreJ2.setTextColor(getColor(R.color.colorVerde));
            txtNombreJ1.setTextColor(getColor(R.color.colorGris));
            Snackbar.make(contenedorJuego,"Empieza Jugador 2",Snackbar.LENGTH_SHORT).show();
            txtPuntuacionJ1.setText(Integer.toString(puntuacionJ1));
            txtPuntuacionJ2.setText(Integer.toString(puntuacionJ2));
        }
    }

    private void go_game() {
        generarFondo();
        generarSelect();
        generarAleatorias();
        chronometer();
    }

    private void generarFondo() {
        imagenesFondo = new int [nivel*2];
        for (int i=0;i<imagenesFondo.length;i++){
            imagenesFondo[i] = fondoJuego;
        }
    }

    private void generarSelect() {
        imagenesSelect = new ArrayList<>();
        for (int i=0; i< nivel; i++) {
            int tmp = (int) (Math.random() * nivel);
            if (!imagenesSelect.contains(imagenesJuego[tmp])){
                imagenesSelect.add(imagenesJuego[tmp]);
            }else {
                i--;
            }
        }

    }

    private void generarAleatorias() {
        imagenesAleatorias = new int[nivel*2];
        for (int i=0; i<nivel;i++){
            int tmp =0;
            do {
                int valor = (int) (Math.random() * nivel*2);
                if (imagenesAleatorias[valor]==0){
                    imagenesAleatorias[valor]=imagenesSelect.get(i);
                    tmp++;
                }

            }while (tmp<2);
        }
    }

    private void chronometer() {
        if (modoJuego==1){
            txtTiempo.setText("Tiempo: "+segundos[0]);
        }else {
            txtTiempo.setText("Movimientos: "+movimientos);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            segundos[0]++;
                            segundos[1]++;
                            if (modoJuego==1){
                                txtTiempo.setText("Tiempo: "+segundos[0]);
                            }
                            if (segundos[1]>60){
                                segundos[1]=0;
                            }

                            pTiempo.setProgress(segundos[1]);
                        }
                    });

                }
            }
        });
        thread.start();
    }
}

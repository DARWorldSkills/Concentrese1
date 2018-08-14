package com.aprendiz.ragp.concentrese1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.ScriptC;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper {
    public GestorDB(Context context) {
        super(context, "concentrese1.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (NOMBRE TEXT, PUNTUACION INTEGER, MODO TEXT, DIFICULTAD TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inputData(Score score){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE",score.getNombre());
        values.put("PUNTUACION",score.getPunutacion());
        values.put("MODO",score.getModo());
        values.put("DIFICULTAD",score.getDificultad());
        db.insert("SCORE",null,values);
        db.close();
    }

    public List<Score> selectData(String modo, String dificultad){
        List<Score> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SCORE WHERE MODO = '"+modo+"' AND DIFICULTAD = '"+dificultad+"' ORDER BY PUNTUACION DESC;",null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score();
                score.setNombre(cursor.getString(0));
                score.setPunutacion(cursor.getInt(1));
                score.setModo(cursor.getString(2));
                score.setDificultad(cursor.getString(3));
                results.add(score);

            }while (cursor.moveToNext());
        }

        return results;
    }

}

package com.example.zohr.customizedlistview2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="favorisManager";

    private static final String TABLE_FAVORIS= "favoris";
    private static final String KEY_ID = "id";
    private static final String KEY_NOMARTIST = "nomArtist";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_IMAGEURI="imageUri";
    private static final String KEY_MUSICLIST="musicList";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL( "CREATE TABLE " +TABLE_FAVORIS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NOMARTIST + " TEXT, "
                + KEY_GENRE + " TEXT," +KEY_IMAGEURI+ " TEXT," +KEY_MUSICLIST+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_FAVORIS + ";");
        onCreate(db);
    }

}


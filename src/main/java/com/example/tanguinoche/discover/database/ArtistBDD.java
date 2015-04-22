package com.example.zohr.customizedlistview2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ArtistBDD {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="favorisManager";

    private static final String TABLE_FAVORIS = "favoris";
    private static final String KEY_ID = "id";
    private static final int NUM_KEY_ID= 0;
    private static final String KEY_NOMARTIST = "nomArtist";
    private static final int NUM_KEY_NOMARTIST= 1;
    private static final String KEY_GENRE = "genre";
    private static final int NUM_KEY_GENRE= 2;
    private static final String KEY_IMAGEURI="imageUri";
    private static final int NUM_KEY_IMAGEURI= 3;
    private static final String KEY_MUSICLIST="musicList";
    private static final int NUM_KEY_MUSICLIST= 4;

    private SQLiteDatabase bdd;

    private DataBaseHelper maBaseSQLite;

    public ArtistBDD(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new DataBaseHelper(context);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertArtist(Artist artist){

        open();
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        if (bdd.rawQuery("SELECT * FROM favoris WHERE KEY_ID=artist.get_id() ", null).getCount()==0) {

            //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
            values.put(KEY_ID, artist.get_id());
            values.put(KEY_NOMARTIST, artist.get_nomArtist());
            values.put(KEY_GENRE, artist.get_genre());
            values.put(KEY_IMAGEURI, String.valueOf(artist.get_imageUri()));
            values.put(KEY_MUSICLIST, String.valueOf(artist.get_musicList()));
            //on insère l'objet dans la BDD via le ContentValues
            close();
            return bdd.insert(TABLE_FAVORIS, null, values);
        }
        else return bdd.insert(TABLE_FAVORIS,null,null);

    }

    public void updateArtist(Artist artist){
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, artist.get_id());
        values.put(KEY_NOMARTIST, artist.get_nomArtist());
        values.put(KEY_GENRE, artist.get_genre());
        values.put(KEY_IMAGEURI, String.valueOf(artist.get_imageUri()));
        values.put(KEY_MUSICLIST, String.valueOf(artist.get_musicList()));
        close();

        bdd.update(TABLE_FAVORIS, values, KEY_ID + "= ?", new String[] { String.valueOf(artist.music_ID) });
    }


    public void removeArtistWithID(int id){
        open();
        bdd.delete(TABLE_FAVORIS, KEY_ID + " = " + id, null);
        close();
    }

    public Artist getArtistWithNomArtist(String nomArtist){
        //Récupère dans un Cursor les valeur correspondant à un artist contenu dans la BDD
        Cursor c = bdd.query(TABLE_FAVORIS, new String[] {KEY_ID, KEY_NOMARTIST, KEY_GENRE,KEY_IMAGEURI,KEY_MUSICLIST}, KEY_ID + " LIKE \"" + nomArtist +"\"", null, null, null, null);
        return cursorToArtist(c);
    }

    public Artist getArtistById(int id){
        open();
        String selectQuery =  "SELECT  " +
                KEY_ID + "," +
                KEY_NOMARTIST + "," +
                KEY_GENRE + "," +
                KEY_IMAGEURI +
                KEY_MUSICLIST +
                " FROM " + TABLE_FAVORIS
                + " WHERE " +
                KEY_ID + "=?";


        Cursor c = bdd.query(TABLE_FAVORIS, new String[] {KEY_ID, KEY_NOMARTIST, KEY_GENRE,KEY_IMAGEURI,KEY_MUSICLIST}, KEY_ID + " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToArtist(c);
    }




    //Cette méthode permet de convertir un cursor en un artist
    private static Artist cursorToArtist(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un artist
        Artist artist = new Artist();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        artist.set_id(c.getInt(NUM_KEY_ID));
        artist.set_nomArtist(c.getString(NUM_KEY_NOMARTIST));
        artist.set_genre(c.getString(NUM_KEY_GENRE));
        artist.set_imageUri(c.getString(NUM_KEY_IMAGEURI));
        //artist.get_musicList(c.getString(NUM_KEY_MUSICLIST));
        //On ferme le cursor
        c.close();

        //On retourne l'artist
        return artist;
    }

   /* public ArrayList<HashMap<String, String>> getmusicList() {
        //Open connection to read only
        open();
        String selectQuery =  "SELECT  " +
                KEY_ID + "," +
                KEY_NOMARTIST + "," +
                KEY_GENRE + "," +
                KEY_IMAGEURI +
                KEY_MUSICLIST+
                " FROM " + TABLE_FAVORIS;

        //Student music = new Artist();
        ArrayList<HashMap<String, String>> musicList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = bdd.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> music = new HashMap<String, String>();
                music.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
                music.put("name", cursor.getString(cursor.getColumnIndex(KEY_NOMMusic)));
                musicList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        bdd.close();
        return studentList;

    } */

}

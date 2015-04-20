package com.example.tanguinoche.discover;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanguinoche on 10/04/15.
 */
public class Artist {

    private int id;
    private String name;
    private URL img; //Pourquoi ne pas stocker que le lien ? Et aller chercher l'image au besoin
    private String genre; //Idem, pourrait stocker l'url du genre ? mais le nom est important(offline)
    private Map<String, Album> tracks; //A vérifier

    //CONSTRUCTEUR // A retenir : classes URL, JSONObject
    public Artist(URL url) { //Ou renvoyer un seul type d'erreur
        //devrait renvoyer une erreur si le contenu n'est pas de la forme d'un JSON : surround with try/catch
        //Sinon instancier un parseur pour tout le temps, et avoir des méthodes appropriées

        DeezerLinkParser.getInstance().setURL(url);

        try {
            id = DeezerLinkParser.getInstance().getIDFromArtist();
            name = DeezerLinkParser.getInstance().getNameFromArtist();
            img = DeezerLinkParser.getInstance().getImgFromArtist();
            genre = DeezerLinkParser.getInstance().getGenreFromArtist();
            tracks = DeezerLinkParser.getInstance().getTracksFromArtist();
    }catch(Exception e) {
        Log.d("Artist :", "constructeur");
    }

    }

    //GETTERS
    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Drawable getImg(){
        try {
            InputStream is = (InputStream) img.getContent();
            Drawable d = Drawable.createFromStream(is, "picture"+id);
            return d;
        } catch (Exception e) {
            Log.d("Artist :", "getImg");
            return null; //Sinon renvoyer une image de base, stockée dans l'app
        }

    }
    public String getGenre(){//Pas dispo dans l'api.. Aller voir chez developers.music-story.com
        return genre;
    }
    /*Les genres dépendent des albums
    * une solution : si un album passé en paramètre, on retourne e genre de l'album
    * Sinon, on peut retourne la liste complète des genres.
     */

    //De la forme : http://api.deezer.com/artist/27/related
    //Devrait retourner une ArrayList d'artistes
    public List<Artist> getRelated(){ //TODO : related
        return null;
    }
    public void getBiography(){} //Pas dispo dans l'api.. Aller voir chez developers.music-story.com
    public String getDiscography(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Album> entry : tracks.entrySet()) {
            sb.append(entry.getValue().getTitle()); //Nom de l'album
            for(Track t : entry.getValue().getTracks()) {
                sb.append("--");
                sb.append(t.getTitle());
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public void putFavorites(){ } //On pourrait utiliser la même pour ajouter/enlever

    public void share(){ } //Android implémente déjà quelque chose, il faut aller voir la doc

}

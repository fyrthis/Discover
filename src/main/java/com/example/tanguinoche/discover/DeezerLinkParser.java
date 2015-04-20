package com.example.tanguinoche.discover;

import android.media.MediaPlayer;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanguinoche on 10/04/15.
 */
final class DeezerLinkParser {
    private URL url;
    private JSONObject file;

    //DESIGN PATTERN SINGLETON
    private static volatile DeezerLinkParser instance = new DeezerLinkParser();
    private DeezerLinkParser(){}
    public final static DeezerLinkParser getInstance() {
        return instance;
    }
    //FIN SINGLETON

    /* Ouvre une URL contenant un JSON à parser */
    public void setURL(URL url) {
        this.url = url;
        try {
            this.file = openURLToJSONObject(url);
        }catch(IOException e){
            Log.d("Parser :", "setURL");
        }
    }

    private JSONObject openURLToJSONObject(URL url) throws IOException{
        InputStream is = url.openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            return new JSONObject(jsonText);
        }catch (IOException e) {
            Log.d("Parser :", "openURLToJSON, IOException");
        }catch (JSONException e) {
            Log.d("Parser :", "openURLToJSON, JSONException");
        } finally {
            is.close();
        }

        Log.d("Parser :", "openURLToJSON, IOException");
        return null;
    }

    public int getIDFromArtist() throws JSONException { //OK
        return file.getInt("id");
    }

    public String getNameFromArtist() throws JSONException {
        return file.getString("name");
    }

    public URL getImgFromArtist() throws JSONException, MalformedURLException {
        return new URL(file.getString("picture"));
    }

    public String getGenreFromArtist() {
        return null;
    }

    public Map<String,Album> getTracksFromArtist() throws IOException, JSONException {

        URL albumsUrl = new URL(url.toString()+"/albums");
        Map<String,Album> albumsMap = new HashMap<>();

        try {

            //Contient tous les albums
            JSONObject albums = openURLToJSONObject(albumsUrl);
            JSONArray albumsArray = new JSONArray(albums.getString("data"));
            for (int i = 0; i < albumsArray.length(); i++) {
                //Contient un unique album
                Log.d("test parser i :", i+"");
                JSONObject album = new JSONObject(albumsArray.getString(i));
                Album a = new Album();
                albumsMap.put(album.getString("id"), a);
                a.setID(album.getInt("id"));
                a.setTitle(album.getString("title"));

                a.setCover(album.getString("cover"));
                a.setGenre(album.getString("genre_id"));
                //Contient toutes les tracks de l'album
                Log.d("test parser :", "work");
                JSONObject tracks = openURLToJSONObject(new URL("http://api.deezer.com/album/" + album.getString("id") + "/tracks"));

                JSONArray tracksArray = new JSONArray(tracks.getString("data"));
                for (int j = 0; j < tracksArray.length(); j++) {
                    Log.d("test parser j :", j+"");
                    //Contient une track
                    JSONObject track = new JSONObject(tracksArray.getString(j));
                    Track t = new Track();
                    t.setID(track.getInt("id"));
                    t.setTitle(track.getString("title"));
                    t.setPreview(track.getString("preview"));
                    a.pushTrack(t);
                }
            }
        }catch(Exception e){ Log.d("Parser :", "getTracksFromArtists"); }
        return albumsMap;
    }
}

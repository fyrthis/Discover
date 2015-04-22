package com.example.zohr.customizedlistview2;

 import java.util.ArrayList;


public class Artist {
    private String _nomArtist;
    private String _genre;
    private String _imageUri;
    private ArrayList<String> _musicList = new ArrayList<>();
    private int _id;

    public Artist(int id,String nomArtist,String genre, String imageUri, ArrayList<String> musiclist){
        _id=id;
        _nomArtist=nomArtist;
        _genre=genre;
        _imageUri=imageUri;
        _musicList=musiclist;
    }
    public Artist(){}

    public int music_ID;
    public String nomArtist;
    public String genre;
    public String imageURI;

    public int get_id() {return _id;}
    public String get_nomArtist(){return _nomArtist;}
    public String get_genre(){return _genre;}
    public String get_imageUri(){return _imageUri;}
    public ArrayList<String> get_musicList(){return _musicList;}

    public void set_id(int id){_id=id;}
    public void set_nomArtist(String nomArtist){_nomArtist=nomArtist;}
    public void set_genre(String genre){_genre=genre;}
    public void set_imageUri(String imageUri){_imageUri=imageUri;}
    // public void set_musicList(String musicList){_musicList.put(musicList);}


}

package com.example.tanguinoche.discover.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanguinoche.discover.mainPackage.Artist;
import com.example.tanguinoche.discover.R;

import java.io.IOException;
import java.io.InputStream;


public class Home extends ActionBarActivity {
    TextView nameArtist;
    TextView discography;
    ImageView picture;

    Artist a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameArtist = (TextView)findViewById(R.id.nameArtist);
        discography = (TextView)findViewById(R.id.discography);
        picture = (ImageView)findViewById(R.id.picture);



        new DownloadArtist().execute();
        new DownloadImageTask().execute();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //AsyncTask : Informations textuelles de l'artiste
    private class DownloadArtist extends AsyncTask<String, String, Artist> {
        protected  Artist doInBackground(String... params) {
            try {
                a = new Artist("http://api.deezer.com/artist/2");
            } catch (IOException e) {
                Log.d("erreur ouverture artist", e.getMessage());
            } catch (Exception e) {
                Log.d("other exception artist", e.getMessage());
            }

            return a;
        }
        protected void onPostExecute(Artist web) {
            nameArtist.setText(a.getName());
            discography.setText(a.getDiscography());
        }
    }

    //AsyncTask : Image de l'artiste.
    private class DownloadImageTask extends AsyncTask<String, String, Bitmap> {
        protected Bitmap doInBackground(String... params) {
            return a.getImg();
        }
        protected void onPostExecute(Bitmap result) {
            picture.setImageBitmap(result);
        }
    }
}

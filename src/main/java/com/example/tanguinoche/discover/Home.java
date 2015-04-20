package com.example.tanguinoche.discover;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


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

    private class DownloadArtist extends AsyncTask<String, String, Artist> {

        @Override
        protected  Artist doInBackground(String... params) {
            try {
                a = new Artist(new java.net.URL("http://api.deezer.com/artist/27"));
            } catch (IOException e) {
                Log.d("erreur ouverture artist", e.getMessage());
            }

            return a;
        }


        @Override
        protected void onPostExecute(Artist web) {
            nameArtist.setText(a.getName());
            discography.setText(a.getDiscography());
            //picture.setImageDrawable(R.drawable.ic_launcher);
        }
    }
}

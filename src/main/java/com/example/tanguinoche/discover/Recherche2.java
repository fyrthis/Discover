
package com.example.rania.listview2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Recherche2 extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {


    ListView listv3;
    TextView texte;
    String[] liste_artistes = {"Nexeropositif","Retatatatail","zozo","BeclinDeLhumanité"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche2);








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Item "+position+"!!!", Toast.LENGTH_LONG).show();
    }

    private class myAdapter extends BaseAdapter{

        LayoutInflater inflater;
        ArrayList<HashMap<String,String>> data;

        public myAdapter(ArrayList<HashMap<String,String>> data){
            inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View recycleView, ViewGroup parent) {
            View v = recycleView;

            if(v == null){
                v = inflater.inflate(R.layout.item_artiste, parent, false);

            }

            Random r = new Random();
            TextView n = (TextView)v.findViewById(R.id.nom_item_artiste);


            n.setText(data.get(position).get("nom"));


            return v;
        }
    }
}
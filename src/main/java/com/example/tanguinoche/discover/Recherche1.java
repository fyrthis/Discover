
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;



public class Recherche1 extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {


    ListView listv2;
    String[] liste_artistes = {"Nexeropositif","Retatatatail","zozo","BeclinDeLhumanité"}; //On reçoit une liste d'objet Artiste normalement

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ListAdapter adapter;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche1);
        listv2 = (ListView)findViewById(R.id.listv2);
        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String, String>>();

        for (int i=0;i<liste_artistes.length;i++){
            HashMap<String,String> h = new HashMap<String,String>();
            h.put("Nom",liste_artistes[i]);
            h.put("Image","Image");  //remplacer "Image" par une vraie image de l'artiste
            data.add(h);
        }
        // adapter qui reçoit une liste d'artiste en donné et en fait une liste d'items: image => nom de l'artiste
        adapter = new SimpleAdapter(this,data,R.layout.item_artiste,new String[]{"Nom","Image"},new int[]{R.id.nom_item_artiste,R.id.image_item_artiste});

        //on envoi l'adapter a une listeView composé de une image et un text
        listv2.setAdapter(adapter);
        listv2.setOnItemClickListener(this);


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


            n.setText(data.get(1).get("nom"));


            return v;
        }
    }

}

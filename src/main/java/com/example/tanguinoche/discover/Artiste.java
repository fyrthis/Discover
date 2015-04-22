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


public class Artiste extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {


    ListView listv;
    TextView texte;
    String[] meteo = {"Beau","Chaud","Pluvieux","Glacial"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myAdapter adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        listv = (ListView)findViewById(R.id.listv);



        Random r = new Random();
        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String, String>>();
        for (int i=0;i<40;i++){
            HashMap<String,String> h = new HashMap<String,String>();

            h.put("temperature",""+r.nextInt(100));
            data.add(h);
        }

        adapter = new myAdapter(data);
        listv.setAdapter(adapter);

        listv.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Next!!", Toast.LENGTH_LONG).show();
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
                v = inflater.inflate(R.layout.rangee, parent, false);
                //v.setTag(" ("+position+")");
            }

            Random r = new Random();
            TextView cond = (TextView)v.findViewById(R.id.rangee_titre);

            //tv.setText(meteo[r.nextInt(4)]);
            cond.setText(data.get(position).get("condition"));


            return v;
        }
    }
}
package com.bond.daniel.appcalidadv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityBtn2 extends AppCompatActivity {
    private ListView lstLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn2);

        lstLista = (ListView) findViewById(R.id.lstLista);

        List<Celular> data = new ArrayList<Celular>();
        data.add(new Celular(100, R.drawable.sansung01, "DESCRIPCIÓN DEL SANSUNG"));
        data.add(new Celular(101, R.drawable.lg01, "DESCRIPCIÓN DEL LG"));
        data.add(new Celular(102, R.drawable.galaxy01, "DESCRIPCIÓN DEL GALAXY"));
        data.add(new Celular(101, R.drawable.sansung02, "DESCRIPCIÓN DEL SANSUNG 0002"));

        CelularAdapter adapter = new CelularAdapter(this, R.layout.item_layout, data);
        lstLista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_btn2, menu);
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
}

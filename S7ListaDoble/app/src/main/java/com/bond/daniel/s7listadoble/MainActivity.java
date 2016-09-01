package com.bond.daniel.s7listadoble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lstUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstUser = (ListView) findViewById(R.id.lstUser);

        List<UserEntity> data = new ArrayList<UserEntity>();
        data.add(new UserEntity(100, "Yonatan Ochoa", "danielochoa@outlook.com"));
        data.add(new UserEntity(101, "Lola Smith", "lolalolita@hotmail.com.com"));
        data.add(new UserEntity(102, "Svetlana Kutnetsova", "esvkutna11@outlook.com"));
        data.add(new UserEntity(103, "Rodrigo Calonge", "calrodrigo@yahoo.com"));
        data.add(new UserEntity(104, "María Leena es de Gracia", "llenadegracia123@yahoo.com"));
        data.add(new UserEntity(105, "Rodolfo Buendía", "soledadbuendia@hotmail.com"));

        UserAdapter adapter = new UserAdapter(this, R.layout.item_layout, data);
        lstUser.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

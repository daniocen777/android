package com.bond.daniel.appcalidadv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt1;
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.btn1);
        bt1.setOnClickListener(this);

        bt2 = (Button) findViewById(R.id.btn2);
        bt2.setOnClickListener(this);
    }

    public void Enviar_Boton1() {
        Intent intent = new Intent(MainActivity.this, ActivityBtn1.class);
        startActivity(intent);
    }

    public void Enviar_Boton2() {
        Intent intent = new Intent(MainActivity.this, ActivityBtn2.class);
        startActivity(intent);
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

    @Override
    public void onClick(View v) {
        if (v == bt1) {
            Enviar_Boton1();
        }
        if (v == bt2) {
            Enviar_Boton2();
        }
    }
}

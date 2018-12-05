package com.rohsins.icetea

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent(this, BackgroundService::class.java).also {intent ->
            startService(intent);
        }

        val gridView: GridView = findViewById(R.id.gridView);
        gridView.adapter = ImageAdapter(this);

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show();
            if (position == 1) {
                Connectivity.MqttPublish("what is up".toByteArray());
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy();
        Intent(this, BackgroundService::class.java).also {intent ->
            stopService(intent);
        }
    }
}
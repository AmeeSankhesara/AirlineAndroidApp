package edu.pdx.cs410J.sankhes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchReadMe(View view) {
        Intent intent = new Intent(this,ReadMeActivity.class);
        startActivity(intent);
    }

    public void launchAddFlight(View view) {
        Intent intent = new Intent(this,AddFlightActivity.class);
        startActivity(intent);
    }

    public void launchSearch(View view) {
        Intent intent = new Intent(this,SearchFlightActivity.class);
        startActivity(intent);
    }
}

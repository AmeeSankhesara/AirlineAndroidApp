package edu.pdx.cs410J.sankhes2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AddFlightActivity extends AppCompatActivity
{
    EditText input_airlineName;
    EditText input_flightNumber;
    EditText input_source;
    EditText input_destination;
    EditText input_departureTime;
    EditText input_arrivalTime;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        input_airlineName = findViewById(R.id.name);
        input_flightNumber = findViewById(R.id.num);
        input_source = findViewById(R.id.source);
        input_destination = findViewById(R.id.destination);
        input_departureTime = findViewById(R.id.departure);
        input_arrivalTime = findViewById(R.id.arrival);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writefile();
            }
        });
    }

    public void writefile()
    {
        try
        {
            String airlineName = input_airlineName.getText().toString();
            String flightNum = input_flightNumber.getText().toString();
            String source = input_source.getText().toString();
            String destination = input_destination.getText().toString();
            String departure = input_departureTime.getText().toString();
            String arrival = input_arrivalTime.getText().toString();
            Airline airline = new Airline(airlineName);
            Flight flight = new Flight(flightNum);
            flight.setSource(source);
            flight.setDestination(destination);
            String[] departStr = departure.split("\\s+");
            String[] arrivalStr = arrival.split("\\s+");
            if(departStr.length!=3 || arrivalStr.length!=3)
                throw new UnsupportedOperationException("Date should be in MM/dd/yyyy hh:mm am/pm format");
            flight.setDepartureTime(departStr[0],departStr[1],departStr[2]);
            flight.setArrivalTime(arrivalStr[0],arrivalStr[1],arrivalStr[2]);
            airline.addFlight(flight);

            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            Date depart = formatter.parse(flight.getDepartureString());
            Date Arrival =  formatter.parse(flight.getArrivalString());
            long duration =Arrival.getTime()-depart.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            if(diffInMinutes<=0)
                throw  new UnsupportedOperationException("Arrival time must be greater than departure time");

            String content = airline.getName() + "," + flightNum + "," + source + "," + flight.getDepartureString()+ "," + destination + "," + flight.getArrivalString()+"\n";
            String filename = airlineName+".txt";

            FileOutputStream fileOut = openFileOutput(filename,MODE_APPEND);
            fileOut.write(content.getBytes());
            fileOut.close();

            Toast toast = Toast.makeText(getApplicationContext(),"Flight added successfully",Toast.LENGTH_SHORT);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTextSize(20);
            toastTV.setBackgroundColor(Color.LTGRAY);
            toast.show();
            input_airlineName.setText("");
            input_flightNumber.setText("");
            input_source.setText("");
            input_destination.setText("");
            input_departureTime.setText("");
            input_arrivalTime.setText("");
        }
        catch (Exception e)
        {

            Toast toast=Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTextSize(20);
            toastTV.setBackgroundColor(Color.LTGRAY);
            toast.show();
        }
    }

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view!=null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

}

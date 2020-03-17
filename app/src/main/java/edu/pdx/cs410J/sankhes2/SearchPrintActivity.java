package edu.pdx.cs410J.sankhes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SearchPrintActivity extends AppCompatActivity {
    String airline;
    String source;
    String destination;
    TextView searchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_print);
        searchText=findViewById(R.id.searchPrint);

        Intent intent = getIntent();
        airline=intent.getStringExtra("SearchName");
        source=intent.getStringExtra("SearchSrc");
        destination=intent.getStringExtra("SearchDest");
        searchprint();
    }

    public void searchprint()
    {
            String filename = airline+".txt";
            try
            {
                FileInputStream fileInputStream = openFileInput(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                String lines;

                while ((lines=bufferedReader.readLine())!=null)
                {
                    String str[] = lines.split(",");
                    String content="";
                    String departStr[] = str[3].split("\\s+");
                    String arrivalStr[] = str[5].split("\\s+");
                    Flight flight = new Flight(str[1]);
                    flight.setSource(str[2]);
                    flight.setDepartureTime(departStr[0],departStr[1],departStr[2]);
                    flight.setDestination(str[4]);
                    flight.setArrivalTime(arrivalStr[0],arrivalStr[1],arrivalStr[2]);
                    if(source.length()==0 && destination.length()==0)
                    {
                        content = "\n"+flight.toString()+"\n";
                    }
                    else if(source.length()!=0 && destination.length()!=0)
                    {
                        if(str[2].toLowerCase().equals(source.toLowerCase()) && str[4].toLowerCase().equals(destination.toLowerCase()))
                        {
                            content = "\n"+flight.toString()+"\n";
                        }
                    }
                    else if(source.length()==0 && destination.length()!=0)
                    {
                        if(str[4].toLowerCase().equals(destination.toLowerCase()))
                        {
                            content = "\n"+flight.toString()+"\n";
                        }
                    }
                    else if (source.length()!=0 && destination.length()==0)
                    {
                        if(str[2].toLowerCase().equals(source.toLowerCase()))
                        {
                            content = "\n"+flight.toString()+"\n";
                        }
                    }
                    stringBuffer.append(content);
                }
                if (stringBuffer.toString().length()==0)
                    throw new UnsupportedOperationException("Flights not exists for given inputs.Please go back to search flight to search again.");
                searchText.setText(stringBuffer.toString());
                searchText.setMovementMethod(new ScrollingMovementMethod());
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
}

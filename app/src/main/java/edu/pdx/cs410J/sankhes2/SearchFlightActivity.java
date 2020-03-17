package edu.pdx.cs410J.sankhes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class SearchFlightActivity extends AppCompatActivity {

    Button prettyButton;
    Button searchButton;
    EditText airline;
    EditText source;
    EditText destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        airline = findViewById(R.id.SearchName) ;
        source = findViewById(R.id.SearchSrc) ;
        destination = findViewById(R.id.SearchDest);
        airline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        source.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        destination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        prettyButton=findViewById(R.id.prettyPrint);
        searchButton=findViewById(R.id.searchButton);
        prettyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String filename = airline.getText().toString()+".txt";
                    File file = getBaseContext().getFileStreamPath(filename);
                    boolean flag = true;
                    if (flag && airline.getText().toString().length()==0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Please Enter Airline Name",Toast.LENGTH_SHORT);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toast.show();
                        flag = false;
                    }

                    if(flag && source.getText().toString().length()!=0 && (source.getText().toString().length() != 3 || !source.getText().toString().matches("^[a-zA-Z]*$")))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Please enter only three letter code of departure airport",Toast.LENGTH_SHORT);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toast.show();
                        flag=false;
                    }

                    if(flag && destination.getText().toString().length()!=0 && (destination.getText().toString().length()!=3 || !destination.getText().toString().matches("^[a-zA-Z]*$")))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Please enter only three letter code of arrival airport",Toast.LENGTH_SHORT);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toast.show();
                        flag=false;
                    }

                    if (flag && !file.exists())
                    {
                        Toast toast=Toast.makeText(getApplicationContext(),"Airline "+airline.getText().toString()+" does not exists.",Toast.LENGTH_LONG);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toastTV.setBackgroundColor(Color.LTGRAY);
                        toast.show();
                        flag=false;
                    }

                    if(flag)
                    {
                        Intent intent = new Intent(getApplicationContext(),PrettyPrintActivity.class);
                        intent.putExtra("SearchName",airline.getText().toString());
                        intent.putExtra("SearchSrc",source.getText().toString());
                        intent.putExtra("SearchDest",destination.getText().toString());
                        startActivity(intent);
                    }
                }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String filename = airline.getText().toString()+".txt";
                    File file = getBaseContext().getFileStreamPath(filename);
                    boolean flag = true;
                    if (flag && airline.getText().toString().length()==0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Please Enter Airline Name",Toast.LENGTH_SHORT);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toast.show();
                        flag=false;
                    }

                    if(flag && source.getText().toString().length()!=0 && (source.getText().toString().length() != 3 || !source.getText().toString().matches("^[a-zA-Z]*$")))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Please enter only three letter code of departure airport",Toast.LENGTH_SHORT);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toast.show();
                        flag=false;
                    }

                    if(flag && destination.getText().toString().length()!=0 && (destination.getText().toString().length()!=3 || !destination.getText().toString().matches("^[a-zA-Z]*$")))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Please enter only three letter code of arrival airport",Toast.LENGTH_SHORT);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toast.show();
                        flag=false;
                    }

                    if (flag && !file.exists())
                    {
                        Toast toast=Toast.makeText(getApplicationContext(),"Airline "+airline.getText().toString()+" does not exists.",Toast.LENGTH_LONG);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(20);
                        toastTV.setBackgroundColor(Color.LTGRAY);
                        toast.show();
                        flag=false;
                    }

                    if (flag)
                    {
                        Intent intent = new Intent(getApplicationContext(), SearchPrintActivity.class);
                        intent.putExtra("SearchName", airline.getText().toString());
                        intent.putExtra("SearchSrc", source.getText().toString());
                        intent.putExtra("SearchDest", destination.getText().toString());
                        startActivity(intent);
                    }
                }
        });
    }
}

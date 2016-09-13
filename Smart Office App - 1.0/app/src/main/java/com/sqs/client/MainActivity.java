package com.sqs.client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TCPClient mTcpClient;
    String ON = "/05237843login";
    String OFF = "/05237943logout";
    Boolean Validate = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button L1ON = (Button) findViewById(R.id.send_button);
        Button L1OFF = (Button) findViewById(R.id.send_button2);
        Button L2ON = (Button) findViewById(R.id.send_button3);
        Button L2OFF = (Button) findViewById(R.id.send_button4);
        Button ACON = (Button) findViewById(R.id.send_button5);
        Button ACOFF = (Button) findViewById(R.id.send_button6);

        new connectTask().execute("");
        L1ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TCP Client", "light 1 ON.");
                turnLEDOn();

            }
        });
        L1OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TCP Client", "light 1 Off.");
                turnLEDOff();

            }
        });
        L2ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TCP Client", "light 2 ON.");
                turnLEDOff();

            }
        });
        L2OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TCP Client", "light 2 off.");
                turnLEDOff();

            }
        });
        ACON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TCP Client", "AC ON.");
                turnLEDOff();

            }
        });
        ACOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TCP Client", "AC OFF");
                turnLEDOff();

            }
        });
    }
    public void turnLEDOn(){
        Log.e("MainActivity", "function turnLEDOn: In");

//            if(Validate == false) {
//                Log.e("MainActivity", "function turnLEDOn: Validate False");
        new connectTask().execute("");
//                Validate = true;
//                Log.e("MainActivity", "function turnLEDOn: Validate True");
//            }
        mTcpClient.sendMessage(ON);
    }
    public void turnLEDOff(){
        Log.e("MainActivity", "function turnLEDOn: In");
        new connectTask().execute("");
        mTcpClient.sendMessage(OFF);
        }
    public class connectTask extends AsyncTask<String,String,TCPClient> {
     @Override
     protected TCPClient doInBackground(String... message) {
         //we create a TCPClient object and
         mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
             @Override
                    //here the messageReceived method is implemented
             public void messageReceived(String message) {
                        //this method calls the onProgressUpdate
                        publishProgress(message);
             }
             });
                mTcpClient.run();
                return null;
            }

        }

    }

package com.example.formation12.firstappandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    EditText inputText, inputDestination, inputSubject;
    TextView textDest, textSub, textText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Floating button was clicked", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        findViewById(R.id.img_button_tel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0299856453"));
                startActivity(intent);
            }
        });

        findViewById(R.id.img_button_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(InfoActivity.this);
                dlgAlert.setTitle("MAIL");
                inputText = new EditText(InfoActivity.this);
                /*
                inputDestination = new EditText(InfoActivity.this);
                inputSubject = new EditText(InfoActivity.this);
                textDest = new TextView(InfoActivity.this);
                textSub = new TextView(InfoActivity.this);
                textText = new TextView(InfoActivity.this);
                inputDestination.setText("j.bourdelle35@gmail.com");
                textDest.setText("Destinataire :");
                textSub.setText("Objet :");
                textText.setText("message :");
                */
                ScrollView scroll = new ScrollView(InfoActivity.this);
                /*
                scroll.addView(textDest);
                scroll.addView(inputDestination);
                scroll.addView(textSub);
                scroll.addView(inputSubject);
                scroll.addView(textText);
                */
                scroll.addView(inputText);
                dlgAlert.setView(scroll);
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{inputDestination.getText().toString()});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Application caluclatrice");
                        i.putExtra(Intent.EXTRA_TEXT   , inputText.getText());
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(InfoActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dlgAlert.setCancelable(true);
                dlgAlert.create().show();


            }
        });

        /*
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MyActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
        */





    }

}

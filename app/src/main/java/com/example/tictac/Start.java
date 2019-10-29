package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Start extends AppCompatActivity {
    Button btn;
    EditText p1,p2;
    String pl1,pl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn=findViewById(R.id.play);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pl1=p1.getText().toString().trim();
                pl2=p2.getText().toString().trim();
                if(pl1.equals("")||pl2.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter Player names ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent it=new Intent(Start.this,MainActivity.class);
                    it.putExtra("Player1",pl1);
                    it.putExtra("Player2",pl2);
                    startActivity(it);
                    finish();
                }
            }
        });

    }
    boolean f=false;

    @Override
    public void onBackPressed() {
        if(!f) {
            f=true;
            Toast.makeText(getApplicationContext(), "Press back again to exit.", Toast.LENGTH_LONG).show();
        }
        else
            finish();

    }
}

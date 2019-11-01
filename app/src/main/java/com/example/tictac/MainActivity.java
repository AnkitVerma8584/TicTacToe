package com.example.tictac;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn[][]=new Button[3][3];
    Button rst;
    TextView p1,p2;
    boolean p1turn=true,p2turn;
    int i,j,round=0,pl1,pl2,c=1;
    String player1name,player2name,p1name,p2name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rst=findViewById(R.id.reset);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
        player1name=getIntent().getExtras().getString("Player1");
        player2name=getIntent().getExtras().getString("Player2");
        p1name=player1name+" : 0";
        p2name=player2name+" : 0";
        p1.setText(p1name);
        p2.setText(p2name);
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                String btnid="btn"+i+j;
                int resId=getResources().getIdentifier(btnid,"id",getPackageName());
                btn[i][j]=findViewById(resId);
                btn[i][j].setOnClickListener(this);
            }
        }
        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                p1.setText(p1name);
                pl1=0;
                p2.setText(p2name);
                pl2=0;
                c=1;
                p1turn=true;
                p2turn=false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(((Button) v).getText().toString().equals("")) {
            if (p1turn)
            {
                ((Button) v).setText("X");

            }
            else
            {
                ((Button) v).setText("O");

            }
            p1turn=p2turn;
            p2turn=!p2turn;
            round++;
        }else return;
        if(Whowin().equals("X"))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();

                }
            },1500);
            Toast.makeText(getApplicationContext(),player1name+ " wins",Toast.LENGTH_LONG).show();
            change();
            pl1++;
            String a=p1.getText().toString();
            a=a.substring(0,a.indexOf(':'))+": "+pl1;
            p1.setText(a);


        


        }
        if(Whowin().equals("O"))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();
                }
            },1500);
            Toast.makeText(getApplicationContext(),player2name+" wins",Toast.LENGTH_LONG).show();

            change();
            pl2++;
            String a=p2.getText().toString();
            a=a.substring(0,a.indexOf(':'))+": "+pl2;
            p2.setText(a);

        }
        if(round==9&&Whowin().equals("No"))
        {
            Toast.makeText(getApplicationContext(),"DRAW",Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    reset();
                }
            },1500);
            change();
        }
    }

    private void reset() {
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++)
                btn[i][j].setText("");
        }

        round=0;
    }

    private String Whowin() {
        int i,j;
        String ch[][]=new String[3][3];
        for(i=0;i<3;i++) {
            for (j = 0; j < 3; j++)
                ch[i][j] = btn[i][j].getText().toString();
        }

        for (j = 0; j < 3; j++)
        {   if((ch[j][0].equals(ch[j][1]))&&(ch[j][0].equals(ch[j][2])))
                    return ch[j][0];
        }
        for (j = 0; j < 3; j++)
        {   if((ch[0][j].equals(ch[1][j]))&&(ch[0][j].equals(ch[2][j])))
                return ch[0][j];

        }
        if(((ch[0][0].equals(ch[1][1]))&&(ch[0][0].equals(ch[2][2])))||(((ch[0][2].equals(ch[1][1]))&&(ch[0][2].equals(ch[2][0])))))
            return ch[1][1];
        return "No";
    }
    boolean f=false;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alt=new AlertDialog.Builder(this);
        alt.setTitle("Alert!")
                .setCancelable(false)
                .setMessage("Are you sure you wanna quit")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog a=alt.create();
                a.show();
    }
    public void change()
    {
        if(c==1) {
            p1turn = false;
            p2turn=true;
        }
        if(c==-1) {
            p2turn = false;
            p1turn=true;
        }
        if(p1turn)
            Toast.makeText(getApplicationContext(), player1name+" will start", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), player2name+" will start", Toast.LENGTH_LONG).show();
        c=c*-1;

    }
}

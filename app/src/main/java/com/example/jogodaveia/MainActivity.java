package com.example.jogodaveia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int qtde;
    private int player;
    private int mat[][] = new int[3][3];
    private Button b[] = new Button[9];
    private String winner;
    private String Player1;
    private String Player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qtde=1;
        player=1;
        b[0] = findViewById(R.id.bt_1);
        b[1] = findViewById(R.id.bt_2);
        b[2] = findViewById(R.id.bt_3);
        b[3] = findViewById(R.id.bt_4);
        b[4] = findViewById(R.id.bt_5);
        b[5] = findViewById(R.id.bt_6);
        b[6] = findViewById(R.id.bt_7);
        b[7] = findViewById(R.id.bt_8);
        b[8] = findViewById(R.id.bt_9);


        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[0],0,0);

            }
        });

        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[1],0,1);

            }
        });

        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[2],0,2);

            }
        });

        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[3],1,0);

            }
        });

        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[4],1,1);

            }
        });

        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[5],1,2);

            }
        });

        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[6],2,0);

            }
        });

        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[7],2,1);

            }
        });

        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move(b[8],2,2);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuprincipal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.itemNovo:
                clean();
                final EditText edittext2 = new EditText(this);
                AlertDialog.Builder player2 = new AlertDialog.Builder(this);
                player2.setMessage("WRITE NAME OF PLAYER 2 (O)");
                player2.setTitle("PLAYER 2");
                player2.setView(edittext2);
                player2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Player2 = edittext2.getText().toString();

                    }
                });
                player2.create();
                player2.show();


                final EditText edittext = new EditText(this);
                AlertDialog.Builder player1 = new AlertDialog.Builder(this);
                player1.setMessage("WRITE NAME OF PLAYER 1 (X)");
                player1.setTitle("PLAYER 1");
                player1.setView(edittext);
                player1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Player2 = edittext2.getText().toString();

                    }
                });
                player1.create();
                player1.show();

                //Toast.makeText(getApplicationContext(),"Start New Game", Toast.LENGTH_LONG).show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void move(Button b, int x, int y){
        b.setEnabled(true);
        if (player==1){
            mat[x][y]=1;
            b.setText("X");
            player=2;
            winner=Player1;
            Verifymoves(1);
        }
        else{
            mat[x][y]=2;
            b.setText("O");
            player=1;
            winner=Player2;
            Verifymoves(2);

        }
        qtde++;
    }

    public boolean victory(int x) {

        for(int i=0; i<mat.length;i++){

            if (mat[i][0]==x && mat[i][1]==x && mat[i][2]==x){

                return true;

            }
            if (mat[0][i]==x && mat[1][i]==x && mat[2][i]==x){

                return true;

            }


        }
        if(mat[0][0]==x && mat[1][1]==x && mat[2][2]==x){

            return true;

        }
        if(mat[0][2]==x && mat[1][1]==x && mat[2][0]==x){

            return true;

        }
        return false;
    }

    public void Verifymoves(int x){

        if(victory(x)==true){

            AlertDialog.Builder alertwinner = new AlertDialog.Builder(this);
            alertwinner.setTitle("YOU WIN");
            alertwinner.setMessage("PLAYER" + " " +  winner + " "   + "YOU WIN");
            alertwinner.setIcon(android.R.drawable.star_on);
            alertwinner.setPositiveButton("OK", null );
            alertwinner.create();
            alertwinner.show();
            Endgame();


        }

    }

    public void Endgame(){

        for (int i=0;i<9;i++){

            b[i].setEnabled(false);

        }

    }

    public void clean(){

        for(int i=0;i<9;i++){

            b[i].setEnabled(true);
            b[i].setText("");

        }
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){

                mat[x][y]=0;

            }

        }
        player=1;
        Player1="";
        Player2="";
        winner="";

    }
}
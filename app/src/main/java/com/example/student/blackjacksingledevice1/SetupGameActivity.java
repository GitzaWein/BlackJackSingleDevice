package com.example.student.blackjacksingledevice1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SetupGameActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setup_game);

       // TextView numOfPlayers = (TextView)findViewById(R.id.tvNumOfPlayers);
       // numOfPlayers.setText(getIntent().getStringExtra("numberOfPlayers"));
        //int numInt = Integer.parseInt(numOfPlayers.toString());
        String numOfPlayers = (getIntent().getStringExtra("numberOfPlayers"));
        final int num = Integer.parseInt(numOfPlayers);
        final ArrayList<String> namesArray  = new ArrayList<>();
        final EditText[] etArray = new EditText[num];

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        //TextView textViewWho = new TextView(this);
        //textViewWho.setText("Who will be playing?");
        //textViewWho.setLayoutParams(params);

        for(int i = 0; i < num; i++){
            EditText et  = new EditText(this);
            et.setId(i + 1);
            et.setHint("Player " + (i + 1) + " enter your name here...");
            etArray[i] = et;
            et.setLayoutParams(params);
            layout.addView(et);
        }
        Button btn = new Button(this);
        btn.setText("Let's Play!");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //populate namesArray
                for(int i = 0; i < num; i++){
                    namesArray.add(i,((etArray[i].getText()).toString()));
                }
                Intent intent = new Intent(v.getContext(), GamePlayActivity.class);
                intent.putExtra("namesArray",namesArray);
                //intent.putExtra("num",numOfPlayers);
                startActivity(intent);
            }
        });

        layout.addView(btn);

        setContentView(layout);
    }


}

package com.example.student.blackjacksingledevice1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;


public class OpenActivity extends Activity{
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        editText= (EditText)findViewById(R.id.editTextNumberOfPlayers);
    }


    public void letsPlay(View view){
        String numberOfPlayers = editText.getText().toString();

        Intent intent = new Intent(this, SetupGameActivity.class);
        intent.putExtra("numberOfPlayers", numberOfPlayers);
        startActivity(intent);

    }



}

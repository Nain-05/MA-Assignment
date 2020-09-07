package com.nainshykh.noteapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText note_subject, note_body;
    Button update_btn;
    String id, subject, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        note_subject = findViewById(R.id.sub_input2);
        note_body = findViewById(R.id.body_input2);

        getAndSetIntentData();


        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(subject);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id_up = item.getItemId();
        if(id_up == R.id.update_note) {
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            myDB.updateData(id, subject, body);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id:") && getIntent().hasExtra("subject:")){

            //getting data from intent
            id = getIntent().getStringExtra("id:");
            subject = getIntent().getStringExtra("subject:");
            body = getIntent().getStringExtra("body");

            //setting intent data
            note_subject.setText(subject);
            note_body.setText(body);
            Log.d("nain", subject+" "+body+" ");

        }else{
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }
    }
}
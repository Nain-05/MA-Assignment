package com.nainshykh.noteapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

        EditText sub_input, body_input;
        Button add_button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);

            sub_input = findViewById(R.id.sub_input);
            body_input = findViewById(R.id.body_input);
            add_button = findViewById(R.id.add_button);
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addNote(sub_input.getText().toString().trim(),
                            body_input.getText().toString().trim());
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.savenote) {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
            myDB.addNote(sub_input.getText().toString().trim(),
                    body_input.getText().toString().trim());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
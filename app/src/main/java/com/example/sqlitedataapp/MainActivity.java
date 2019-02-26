package com.example.sqlitedataapp;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameField, emailField, favoriteTVShowField, idField;
    private Button addBtn, viewBtn, updateBtn, deleteBtn;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.nameField);
        emailField = (EditText) findViewById(R.id.emailField);
        favoriteTVShowField = (EditText) findViewById(R.id.favoriteTVShowField);
        idField = (EditText) findViewById(R.id.idField);

        addBtn = (Button) findViewById(R.id.addBtn);
        viewBtn = (Button) findViewById(R.id.viewBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);

        addBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        sqLiteHelper = new SQLiteHelper(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        boolean progress;
        String name = nameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String favoriteTVShow = favoriteTVShowField.getText().toString().trim();
        String id = idField.getText().toString().trim();

        switch(v.getId()){

            case R.id.addBtn:
                progress = sqLiteHelper.insert(name, email, favoriteTVShow);
                if(progress){
                    Toast.makeText(MainActivity.this, "Added!", Toast.LENGTH_SHORT).show();
                    nameField.setText("");
                    emailField.setText("");
                    favoriteTVShowField.setText("");
                    idField.setText("");
                }
                else Toast.makeText(MainActivity.this, "There was an error, try again", Toast.LENGTH_SHORT).show();
                break;
            case R.id.viewBtn:
                ArrayList<String> list = sqLiteHelper.getData();
                StringBuffer sb = new StringBuffer();
                for(String s : list){
                    sb.append(s);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("All Stored Data:");
                alert.setMessage(sb.toString());
                alert.show();
                break;
            case R.id.updateBtn:
                if(id.isEmpty()) Toast.makeText(MainActivity.this, "ID Field cannot be empty", Toast.LENGTH_SHORT).show();

                progress = sqLiteHelper.updateData(id, name, email, favoriteTVShow);
                if(progress){
                    Toast.makeText(MainActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                    nameField.setText("");
                    emailField.setText("");
                    favoriteTVShowField.setText("");
                    idField.setText("");
                }
                else Toast.makeText(MainActivity.this, "There was an error, try again", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteBtn:
                if(id.isEmpty()) Toast.makeText(MainActivity.this, "ID Field cannot be empty", Toast.LENGTH_SHORT).show();

                progress = sqLiteHelper.delete(id);
                if(progress){
                    Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                    nameField.setText("");
                    emailField.setText("");
                    favoriteTVShowField.setText("");
                    idField.setText("");
                }
                else Toast.makeText(MainActivity.this, "There was an error, try again", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

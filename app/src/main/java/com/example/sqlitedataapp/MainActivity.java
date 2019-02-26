package com.example.sqlitedataapp;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        switch(v.getId()){

            case R.id.addBtn:
                boolean t = sqLiteHelper.insert(nameField.getText().toString(), emailField.getText().toString(), favoriteTVShowField.getText().toString());
                Log.i("test", "" + t);
                break;
            case R.id.viewBtn:
                Cursor cursor = sqLiteHelper.getData();
                while (cursor.moveToNext()) {
                    String ts = DatabaseUtils.dumpCursorToString(cursor);
                    Log.i("====", ts);
                }
                break;
            case R.id.updateBtn:
                break;
            case R.id.deleteBtn:
                break;
        }
    }
}

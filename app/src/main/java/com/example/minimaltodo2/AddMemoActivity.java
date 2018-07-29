package com.example.minimaltodo2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);
        Button btnCancel= findViewById(R.id.btn_cancel);
        Button btnConfirm= findViewById(R.id.btn_confirm);
        final EditText txtTitle = findViewById(R.id.txt_title);
        final EditText txtContent = findViewById(R.id.txt_content);
        final EditText txtDescription = findViewById(R.id.txt_description);
        final DBHelper dbHelper= new DBHelper(getBaseContext());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(TodoContract.TodoEntry.COLUMN_NAME_TITLE, txtTitle.getText().toString());
                values.put(TodoContract.TodoEntry.COLUMN_NAME_CONTENT, txtContent.getText().toString());
                values.put(TodoContract.TodoEntry.COLUMN_NAME_DESCRIPTION, txtDescription.getText().toString());
                db.insert(TodoContract.TodoEntry.TABLE_NAME,null,values);
                finish();
            }
        });

    }


}

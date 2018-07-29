package com.example.minimaltodo2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailMemoActivity extends AppCompatActivity {

    public static final String ID_KEY="ID_KEY";

    private Long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memo);

        TextView txtTitle = findViewById(R.id.txt_title);
        TextView txtContent = findViewById(R.id.txt_content);
        TextView txtDescription = findViewById(R.id.txt_description);
        Button btnConfirm = findViewById(R.id.btn_confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id = getIntent().getLongExtra(ID_KEY,0);
        DBHelper dbHelper = new DBHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = new String[]{
                TodoContract.TodoEntry._ID,
                TodoContract.TodoEntry.COLUMN_NAME_TITLE,
                TodoContract.TodoEntry.COLUMN_NAME_CONTENT,
                TodoContract.TodoEntry.COLUMN_NAME_DESCRIPTION
        };
        String selection = TodoContract.TodoEntry._ID+"=?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(TodoContract.TodoEntry.TABLE_NAME,
                projection,selection,selectionArgs,null,null,null);

        if (cursor.moveToNext()){
            Todo todo = new Todo(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            txtTitle.setText(todo.getTitle());
            txtContent.setText(todo.getContent());
            txtDescription.setText(todo.getDescription());
        }
    }
}

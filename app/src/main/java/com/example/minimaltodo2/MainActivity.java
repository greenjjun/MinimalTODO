package com.example.minimaltodo2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_MEMO = 10000;

    private DBHelper dbHelper;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.list);
        FloatingActionButton btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getBaseContext(),AddMemoActivity.class), ADD_MEMO);
            }
        });

        adapter = new TodoAdapter(getBaseContext(), updateData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        list.setAdapter(adapter);
        list.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_MEMO:
                if (adapter == null) return;
                adapter.setItems(updateData());
                adapter.notifyDataSetChanged();
        }
    }

    private ArrayList<Todo> updateData() {
        DBHelper dbHelper = new DBHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = new String[]{
                TodoContract.TodoEntry._ID,
                TodoContract.TodoEntry.COLUMN_NAME_TITLE,
                TodoContract.TodoEntry.COLUMN_NAME_CONTENT,
                TodoContract.TodoEntry.COLUMN_NAME_DESCRIPTION
        };
        Cursor cursor = db.query(TodoContract.TodoEntry.TABLE_NAME,
                projection,null,null,null,null,null);
        ArrayList<Todo> data = new ArrayList<>();
        while (cursor.moveToNext()){
            Todo todo = new Todo(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            data.add(todo);
        }

        return data;
    }
}

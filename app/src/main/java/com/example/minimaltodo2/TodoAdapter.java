package com.example.minimaltodo2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder>{

    private List<Todo> items;
    private Context context;

    private final DBHelper dbHelper;

    public TodoAdapter(Context context,List<Todo> todos) {
        this.context = context;
        this.items = todos;
        dbHelper = new DBHelper(context);
    }

    public void setItems(List<Todo> todos) {
        this.items = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_item,parent,false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        final Todo todo = items.get(position);
        holder.getTxtTitle().setText(todo.getTitle());
        holder.getTxtContent().setText(todo.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DetailMemoActivity.class)
                        .putExtra(DetailMemoActivity.ID_KEY,todo.getId()));
            }
        });
        holder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String selection = TodoContract.TodoEntry._ID+"=?";
                String[] selectionArgs = {String.valueOf(todo.getId())};
                db.delete(TodoContract.TodoEntry.TABLE_NAME,selection,selectionArgs);

                items.remove(todo);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

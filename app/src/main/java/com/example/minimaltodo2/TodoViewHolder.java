package com.example.minimaltodo2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;
    private TextView txtContent;
    private Button btnDelete;

    public TodoViewHolder(View itemView){
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txt_title);
        txtContent = itemView.findViewById(R.id.txt_content);
        btnDelete = itemView.findViewById(R.id.btn_delete);
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtContent() {
        return txtContent;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }
}

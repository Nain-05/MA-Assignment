package com.nainshykh.noteapplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList note_id, note_subject, note_body;
    ActionMode actionMode;
    Activity activity;

    CustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note_subject){
        this.activity = activity;
        this.context = context;
        this.note_id = note_id;
        this.note_subject = note_subject;
        this.note_body = note_body;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_sub_txt.setText(String.valueOf(note_subject.get(position)));


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(note_id.get(position)));
                intent.putExtra("subject", String.valueOf(note_subject.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }


    @Override
    public int getItemCount() {
        return note_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView note_id_txt, note_sub_txt, note_body_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_id_txt = itemView.findViewById(R.id.note_id_txt);
            note_sub_txt = itemView.findViewById(R.id.note_sub_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            mainLayout.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(note_sub_txt.getText().toString());
            menu.add(getAdapterPosition(), 0, 0, "Edit");
            menu.add(getAdapterPosition(), 1, 1, "Delete");
            menu.add(getAdapterPosition(), 2, 2, "Share");
        }
    }

}

package com.example.alexey.teacherhelp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private List <Student> students;
    private DataHelper dh;
    public StudentAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        dh = DataHelper.getInstance(context);
    }
    public StudentAdapter(Context context){
        super(context,R.layout.layout_student_item_new);
    }

    public void setStudents(List<Student> students){
        this.students = students;
        try {
            clear();
            notifyDataSetChanged();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View itemView, @NonNull ViewGroup parent) {

        if (itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_student_item_new,null);
        }

        ((TextView)itemView.findViewById(R.id.textFIO))
                .setText(students.get(position).getFIO());
        ((TextView)itemView.findViewById(R.id.textTime))
                .setText(students.get(position).getTime());

        ((TextView)itemView.findViewById(R.id.textSubject))
                .setText(students.get(position).getSubject());

        ((TextView)itemView.findViewById(R.id.textDay))
                .setText(students.get(position).getDay());

        Button btnUpdate = (Button)itemView.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddActivity.class).putExtra("id",position + 1);
                getContext().startActivity(intent);
            }
        });

        if(dh == null) {
            dh = DataHelper.getInstance(getContext());
        }
        final SQLiteDatabase database = dh.getWritableDatabase();
        ((TextView)itemView.findViewById(R.id.textFIO)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                database.delete("students","id = " + students.get(position).getId(),null);
                students.remove(position);
                setStudents(students);
                Toast.makeText(getContext(),"Delete",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        return itemView;
    }


    @Override
    public int getCount() {
        return students != null ? students.size() : 0;
    }

}

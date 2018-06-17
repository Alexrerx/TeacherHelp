package com.example.alexey.teacherhelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private List <Student> students;
    private DataHelper dh;
    public StudentAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        dh=DataHelper.getInstance(context);
    }
    public StudentAdapter(Context context){
        super(context,R.layout.layout_student_item);
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
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_student_item,null);
        }

        ((TextView)itemView.findViewById(R.id.textFIO))
                .setText(students.get(position).getFIO());
        ((TextView)itemView.findViewById(R.id.textAddress))
                .setText(students.get(position).getAddress());
        ((TextView)itemView.findViewById(R.id.textSubject))
                .setText(students.get(position).getSubject());
        ((TextView)itemView.findViewById(R.id.textPrice))
                .setText(String.valueOf(students.get(position).getPrice()));

        Button btnDel = itemView.findViewById(R.id.delstudent);
        if(dh==null) {
            dh = DataHelper.getInstance(getContext());
        }
        final SQLiteDatabase database = dh.getWritableDatabase();
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.delstudent:
                        database.delete("students","id = " +
                                students.get(position).getId(),null);
                        students.remove(position);
                        setStudents(students);
                        break;

                    default:
                        break;
                }
            }
        });

        return itemView;
    }

    @Override
    public int getCount() {
        return students != null ? students.size() : 0;
    }

}

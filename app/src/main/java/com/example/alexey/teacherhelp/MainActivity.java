package com.example.alexey.teacherhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lvstudents;
    Button btnAdd;
    DataHelper dh;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        lvstudents = (ListView)findViewById(R.id.lvstudents);
        btnAdd.setOnClickListener(this);
        dh = DataHelper.getInstance(this);
        studentAdapter = new StudentAdapter(this,R.layout.layout_student_item);
        lvstudents.setAdapter(studentAdapter);
    }

    @Override
    protected void onResume() {
        if (studentAdapter != null) {
            studentAdapter.setStudents(dh.getAll());
        }else {
            Toast.makeText(this, "NULLL!!!!!", Toast.LENGTH_SHORT).show();
        }
        super.onResume();
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnAdd:
                intent = new Intent(this,AddActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

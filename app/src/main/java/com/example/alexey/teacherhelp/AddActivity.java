package com.example.alexey.teacherhelp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.FileObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    DataHelper dataHelper;
    Button btnOk,btnRst;
    EditText FIO,subject,timelesson,duration,address,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnOk = (Button)findViewById(R.id.btnOk);
        btnRst = (Button)findViewById(R.id.btnRst);

        FIO = (EditText)findViewById(R.id.FIO);
        subject = (EditText)findViewById(R.id.subject);
        timelesson = (EditText)findViewById(R.id.timelesson);
        duration = (EditText)findViewById(R.id.duration);
        address = (EditText)findViewById(R.id.address);
        price = (EditText)findViewById(R.id.price);

        btnRst.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        dataHelper = new DataHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        String FIOtext = FIO.getText().toString();
        String subjecttext = subject.getText().toString();
        String timelessontext = timelesson.getText().toString();
        String durationtext = duration.getText().toString();
        String addresstext = address.getText().toString();
        String pricetext = price.getText().toString();

        SQLiteDatabase database = dataHelper.getWritableDatabase();
        switch (v.getId()){
            case R.id.btnOk:
//                btnOk.setClickable((isEmpty(FIO)) && (isEmpty(address)));
//                if ((!isEmpty(FIO,"Введите ФИО!"))&&(!isEmpty(address,"Введите адрес!"))){
                    contentValues.put("FIO",FIOtext);
                    contentValues.put("subject",subjecttext);
                    contentValues.put("timelesson",timelessontext);
                    contentValues.put("duration",durationtext);
                    contentValues.put("address",addresstext);
                    contentValues.put("price",pricetext);
                    database.insert("students",null,contentValues);

                    finish();
//                }
//                btnOk.setClickable(true);
                break;


            case R.id.btnRst:
                FIO.setText("");
                subject.setText("");
                timelesson.setText("");
                duration.setText("");
                address.setText("");
                price.setText("");
                break;
        }
        database.close();
    }

    public boolean isEmpty(EditText editText,String s){
        int l = editText.getText().toString().trim().length();
        if (l == 0){
            Toast.makeText(this,s,Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
    public boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length() == 0 ? true : false;
    }
}

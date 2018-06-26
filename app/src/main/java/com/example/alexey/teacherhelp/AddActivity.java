package com.example.alexey.teacherhelp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.FileObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    String[] daysofweek = {"пн","вт","ср","чт","пт","сб","вс"};
    int DIALOG_TIME = 1;
    int myHour = 0;
    int myMinute = 0;
    DataHelper dataHelper;
    Button btnOk;
    TextView timelesson,day;
    EditText FIO,subject,duration,address,price;
    ImageButton FIOclear,subjectclear,addressclear,durationclear,timelessonclear,priceclear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnOk = (Button)findViewById(R.id.btnOk);

        FIO = (EditText)findViewById(R.id.FIO);
        subject = (EditText)findViewById(R.id.subject);

        timelesson = (TextView)findViewById(R.id.timelesson);
        day = (TextView)findViewById(R.id.day);

        duration = (EditText)findViewById(R.id.duration);
        address = (EditText)findViewById(R.id.address);
        price = (EditText)findViewById(R.id.price);

        FIOclear = (ImageButton)findViewById(R.id.clear_FIO);
        subjectclear = (ImageButton)findViewById(R.id.clear_subject);
        durationclear = (ImageButton)findViewById(R.id.clear_duration);
        addressclear = (ImageButton)findViewById(R.id.clear_address);
        timelessonclear = (ImageButton)findViewById(R.id.clear_timelesson);
        priceclear = (ImageButton)findViewById(R.id.clear_price);

        FIOclear.setOnClickListener(this);
        subjectclear.setOnClickListener(this);
        durationclear.setOnClickListener(this);
        addressclear.setOnClickListener(this);
        timelessonclear.setOnClickListener(this);
        priceclear.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        timelesson.setOnClickListener(this);
        dataHelper = DataHelper.getInstance(this);

        ArrayAdapter <String> spinneradapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,daysofweek);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner dayspinner = (Spinner)findViewById(R.id.dayspinner);
        dayspinner.setAdapter(spinneradapter);
        dayspinner.setPrompt("День недели");
        dayspinner.setSelection(2);
        dayspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day.setText(dayspinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_TIME){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,myCallBack,myHour,myMinute,true);
            return timePickerDialog;
        }
        return super.onCreateDialog(id);
    }
    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            if (myMinute < 10)
                timelesson.setText(myHour + " : " + "0" + myMinute);
            else
            timelesson.setText(myHour +" : "+ myMinute);
        }
    };

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        String FIOtext = FIO.getText().toString();
        String subjecttext = subject.getText().toString();
        String timelessontext = timelesson.getText().toString();
        String durationtext = duration.getText().toString();
        String addresstext = address.getText().toString();
        String pricetext = price.getText().toString();
        String daytext = day.getText().toString();

        SQLiteDatabase database = dataHelper.getWritableDatabase();
        switch (v.getId()){

            case R.id.timelesson:
                showDialog(DIALOG_TIME);
                break;
            case R.id.btnOk:
                btnOk.setClickable((isEmpty(FIO)) && (isEmpty(address)));
                if ((!isEmpty(FIO,"Введите ФИО!"))&&(!isEmpty(address,"Введите адрес!"))){
                    contentValues.put("FIO",FIOtext);
                    contentValues.put("subject",subjecttext);
                    contentValues.put("time",timelessontext);
                    contentValues.put("day",daytext);
                    contentValues.put("duration",durationtext);
                    contentValues.put("address",addresstext);
                    contentValues.put("price",pricetext);
                    database.insert("students",null,contentValues);

                    finish();
                }
                btnOk.setClickable(true);
                break;


            case R.id.clear_FIO:
                FIO.setText("");
                break;
            case R.id.clear_subject:
                subject.setText("");
                break;
            case R.id.clear_timelesson:
                timelesson.setText("");
                break;
            case R.id.clear_duration:
                duration.setText("");
                break;
            case R.id.clear_address:
                address.setText("");
                break;
            case R.id.clear_price:
                price.setText("");
                break;
            default:
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

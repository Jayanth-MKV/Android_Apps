package com.vugido.homeservices.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.L;
import com.vugido.homeservices.R;
import com.vugido.homeservices.app.MyPreferences;
import com.vugido.homeservices.dialogs.MyStatusDialog;
import com.vugido.homeservices.models.bookservice.Response;
import com.vugido.homeservices.network.RetrofitBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class DateAndTimePicker extends AppCompatActivity {

    public static final int SUCCESS_BOOKED = 89;
    Toolbar toolbar;
    Button proceed;
    EditText d,t;
    Button a,b;
    TextView loc,head,ser;
    MyStatusDialog myStatusDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);


        d=findViewById(R.id.editTextDate);
        t=findViewById(R.id.editTextTime);

        a=findViewById(R.id.button5);
        b=findViewById(R.id.button6);
        proceed=findViewById(R.id.button4);
        head=findViewById(R.id.textView);
        ser=findViewById(R.id.textView4);


        ser.setText(getIntent().getStringExtra("SERVICES"));
        head.setText(getIntent().getStringExtra("N"));

        loc=findViewById(R.id.textView2);
        loc.setText(getIntent().getStringExtra("ADD")+"\n"+new MyPreferences(getApplicationContext()).getUserMobile());
        proceed.setOnClickListener(v -> {



                bookService("no","no",getIntent().getStringExtra("LA"),getIntent().getStringExtra("LO"),
                        getIntent().getStringExtra("ADD"),getIntent().getStringExtra("A1"),getIntent().getStringExtra("A2"),
                        getIntent().getStringExtra("SERVICES"),getIntent().getIntExtra("SID",0)
                        );
                proceed.setEnabled(false);
        });
        a.setOnClickListener(v -> {

            showTime();
        });

        b.setOnClickListener(v -> {
            showCalender();

        });
        toolbar=findViewById(R.id.bar);


        toolbar.setTitle("Book Service Now");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());


    }

    private void bookService(String toString, String toString1, String la, String lo, String add, String a1, String a2, String services, int sid) {


        Map<String,Object> map=new HashMap<>();
        map.put("DATE", toString);
        map.put("TIME", toString1);
        map.put("LA",la);
        map.put("LO",lo);
        map.put("ADDRESS",add);
        map.put("A1",a1);
        map.put("A2",a2);
        map.put("SERVICES",services);
        map.put("SID",String.valueOf(sid));
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().bookService(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful())
                {
                    assert response.body() != null;
                    if (response.body().isSTATUS())
                    {
                        //ok booked success

                        Log.e("success","booked");
                        setResult(SUCCESS_BOOKED);
                        finish();

                    }else
                        showDialog();
                        //Log.e("success","no .. booked");





                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("error","no .. booked");

            }
        });
    }

    private void showDialog(){
        if (myStatusDialog==null)
            myStatusDialog=new MyStatusDialog();

        Bundle bundle=new Bundle();

        bundle.putString("MSG","Not Booked!! Try Again");

        bundle.putBoolean("STATUS",false);
        myStatusDialog.setArguments(bundle);
        myStatusDialog.show(getSupportFragmentManager(),"S");



    }

    private void showTimeDate(){
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener d1=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

              //  d.setText(calendar.getTime());
                TimePickerDialog.OnTimeSetListener timeSetListener =new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");
                        t.setText(simpleDateFormat.format(calendar.getTime()));

                        if (calendar.getTimeInMillis() >= calendar.getTimeInMillis()) {
                            //it's after current
                            int hour = hourOfDay % 12;
//                            btnPickStartTime.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
//                                    minute, hourOfDay < 12 ? "am" : "pm"));
                        } else {
                            //it's before current'
                            Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                new TimePickerDialog(DateAndTimePicker.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();


            }


        } ;
        DatePickerDialog datePickerDialog=new DatePickerDialog(DateAndTimePicker.this,d1,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

                    }

    private void showTime(){

        Calendar calendar=Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                simpleDateFormat.format(calendar.getTime());

                if (calendar.getTimeInMillis() >= calendar.getTimeInMillis()) {
                    //it's after current
                    int hour = hourOfDay % 12;
//                            btnPickStartTime.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
//                                    minute, hourOfDay < 12 ? "am" : "pm"));
                    t.setText(simpleDateFormat.format(calendar.getTime()));
                } else {
                    //it's before current'
                    Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_LONG).show();
                }

            }


        };

        new TimePickerDialog(DateAndTimePicker.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();

    }
    private  void showCalender(){

        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd--MM--yy");
                d.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        DatePickerDialog datePickerDialog=new DatePickerDialog(DateAndTimePicker.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }
}

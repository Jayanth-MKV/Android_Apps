package com.vugido.homeservices.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vugido.homeservices.R;
import com.vugido.homeservices.adapters.service_details.ServiceDetailsAdapter;
import com.vugido.homeservices.models.homepage.ServiceDetailsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.vugido.homeservices.activities.DateAndTimePicker.SUCCESS_BOOKED;
import static com.vugido.homeservices.adapters.homepage.SquareMediumRecyclerViewAdapter.SERVICE_CODE;

public class ServiceDetails extends AppCompatActivity implements ServiceDetailsAdapter.CHECKBOX {
List<String> stringList;
List<String> images;
RecyclerView recyclerView;
Toolbar toolbar;
Button proceed;
int service_id;
List<ServiceDetailsModel> serviceDetailsModelList;
FloatingActionButton floatingActionButton;
TextInputEditText textInputEditText;
TextInputLayout textInputLayout;
boolean c=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);


        proceed=findViewById(R.id.button2);
        toolbar=findViewById(R.id.include2);
        floatingActionButton=findViewById(R.id.floatingActionButton2);
        textInputEditText=findViewById(R.id.username);
        textInputLayout=findViewById(R.id.student_name);

        floatingActionButton.setOnClickListener(v -> {

            if (!c){
                floatingActionButton.setImageResource(R.drawable.close);
                recyclerView.setVisibility(View.INVISIBLE);
                textInputLayout.setVisibility(View.VISIBLE);
                c=true;
            }else {
                floatingActionButton.setImageResource(R.drawable.ed);
                recyclerView.setVisibility(View.VISIBLE);
                textInputLayout.setVisibility(View.INVISIBLE);
                c=false;
            }
        });

        toolbar.setTitle("Choose Your Services");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());
        recyclerView=findViewById(R.id.rec);

        //stringList=getServiceList(getIntent().getStringExtra("SERVICES"));
        stringList=new ArrayList<>();
        images=new ArrayList<>();

         String img=getIntent().getStringExtra("SERVICES");
        String[] strArray = img.split(",");

        for (int i=0;i<strArray.length;i++){

            String[] strArray2=strArray[i].split(";");
            stringList.add(strArray2[0]);
            if (strArray2.length==2)
                images.add(strArray2[1]);
            else
                images.add("no");
        }

        //Log.e("images ss",images.toString());

        service_id=getIntent().getIntExtra("SID",0);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        serviceDetailsModelList=new ArrayList<>();
        for(int i=0;i<stringList.size();i++)
            serviceDetailsModelList.add(new ServiceDetailsModel(i,stringList.get(i),false));

        ServiceDetailsAdapter serviceDetailsAdapter=new ServiceDetailsAdapter(serviceDetailsModelList,this,images);

        recyclerView.setAdapter(serviceDetailsAdapter);



        proceed.setOnClickListener(v -> {
            if (c) {
                if (!textInputEditText.getText().toString().isEmpty()) {
                    Intent intent = new Intent(ServiceDetails.this, MapsActivity.class);
                    intent.putExtra("SERVICES", getServiceStrings());
                    intent.putExtra("SERVICE_ID", service_id);
                    intent.putExtra("N",getIntent().getStringExtra("N"));
                    startActivityForResult(intent, SERVICE_CODE);
                }
            }else {

                if (checkSelection()) {
                    Intent intent = new Intent(ServiceDetails.this, MapsActivity.class);
                    intent.putExtra("SERVICES", getServiceStrings());
                    intent.putExtra("SERVICE_ID", service_id);
                    intent.putExtra("N",getIntent().getStringExtra("N"));
                    startActivityForResult(intent, SERVICE_CODE);


                } else
                    Toast.makeText(getApplicationContext(), "Select minimum one option ", Toast.LENGTH_LONG).show();


            }
        });

    }

    private String getServiceStrings() {
        String x="";
        if (c)
            return textInputEditText.getText().toString();
        for(int i=0;i<serviceDetailsModelList.size();i++ ){
            if(serviceDetailsModelList.get(i).isChecked())
            {
                x=x+serviceDetailsModelList.get(i).getService()+",";
            }
        }

        return x;

    }

    private boolean checkSelection() {
        for(int i=0;i<serviceDetailsModelList.size();i++ ){
            if(serviceDetailsModelList.get(i).isChecked())
            {
                return true;
            }
        }
        return false;

    }

    private List<String> getServiceList(String services) {


        return Arrays.asList(services.split(","));

    }

    @Override
    public void checkService(int id) {

        for(int i=0;i<serviceDetailsModelList.size();i++ ){
            if(serviceDetailsModelList.get(i).getService_id()==id)
            {
                serviceDetailsModelList.get(i).setChecked(!serviceDetailsModelList.get(i).isChecked());
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==SERVICE_CODE&&resultCode==SUCCESS_BOOKED) {
            setResult(SUCCESS_BOOKED);
            finish();

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

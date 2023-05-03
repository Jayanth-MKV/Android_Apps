package com.vugido.info.homeservicesadmin.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.adapters.homepage.ServiceDetailsAdapter;
import com.vugido.info.homeservicesadmin.dialogs.MyDialogLoader;
import com.vugido.info.homeservicesadmin.models.Response;
import com.vugido.info.homeservicesadmin.models.homepage.ServiceDetailsModel;
import com.vugido.info.homeservicesadmin.network.Retrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class ServiceDetails extends AppCompatActivity implements ServiceDetailsAdapter.CHECKBOX {
    private static final int PICK_IMAGE = 22;
    List<String> stringList;
    List<String> images;
RecyclerView recyclerView;
Toolbar toolbar;
    ServiceDetailsAdapter serviceDetailsAdapter;
int service_id,id;
List<ServiceDetailsModel> serviceDetailsModelList;
MyDialogLoader myDialogLoader;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);


//        toolbar=findViewById(R.id.include2);
//
//        toolbar.setTitle("Upload Images");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
//        setSupportActionBar(toolbar);
//
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationOnClickListener(view -> finish());
        recyclerView=findViewById(R.id.rec);

        stringList=getServiceList(getIntent().getStringExtra("SERVICES"));
        images=getServiceList(getIntent().getStringExtra("SERVICES"));

        service_id=getIntent().getIntExtra("SID",0);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        serviceDetailsModelList=new ArrayList<>();
        for(int i=0;i<stringList.size();i++)
            serviceDetailsModelList.add(new ServiceDetailsModel(i,stringList.get(i),false,images.get(i)));

   serviceDetailsAdapter=new ServiceDetailsAdapter(serviceDetailsModelList,this);

        recyclerView.setAdapter(serviceDetailsAdapter);




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

    private void galleryImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    @Override
    public void checkService(int iid) {


        id=iid;
        galleryImage();

        //upload..


//        for(int i=0;i<serviceDetailsModelList.size();i++ ){
//            if(serviceDetailsModelList.get(i).getService_id()==id)
//            {
//                serviceDetailsModelList.get(i).setChecked(!serviceDetailsModelList.get(i).isChecked());
//            }
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode== PICK_IMAGE && resultCode==RESULT_OK && data!=null){




            getPicFromGallery(data);

            //  Log.e("pick image","clicked");

        }
    }


    private void getPicFromGallery(Intent data) {


        Uri ImageUri=data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageUri);
//            serviceDetailsModelList.get(id).setImage();
//            imageView.setImageBitmap(bitmap);
//            imageView.setEnabled(false);
//            RetryImage.setVisibility(View.VISIBLE);
//            RetryImage.setEnabled(true);
            //ImageString = getImageString(bitmap);

            sendImage(getImageString(bitmap));

            // Toast.makeText(this,ImageString, Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void sendImage(String imageString) {

        Map<String,Object> map=new HashMap<>();
        map.put("index",stringList.get(id));
        map.put("IMG",imageString);
        map.put("SID",String.valueOf(service_id));

        if (myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Adding...");
        myDialogLoader.setArguments(bundle);

        myDialogLoader.show(getSupportFragmentManager(),"D");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().sendImage(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                myDialogLoader.dismiss();
                if (response.isSuccessful()){

                    assert response.body() != null;
                    if (response.body().isSTATUS())
                    {

                        showDialogStatus(true);

                        // serviceDetailsModelList.get(id)
                        stringList.remove(id);
                        images.remove(id);
                        serviceDetailsModelList.remove(id);
                        serviceDetailsAdapter.notifyItemRemoved(id);

                    }else
                        showDialogStatus(false);


                }else
                {
                    showDialogStatus(false);

                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                showDialogStatus(false);
                myDialogLoader.dismiss();
            }
        });

    }


    private void showDialogStatus(boolean status){
        if(status){
            Toast.makeText(this,"Uploaded Successfully",Toast.LENGTH_LONG).show();
//            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
//                    .setTitleText("Added Successfully")
//            ;
//            s.show();
        }else{
            Toast.makeText(this,"Error.. Try again!!",Toast.LENGTH_LONG).show();

//            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
//                    .setTitleText("Error.. Try again!!")
//            ;
//            s.show();

        }

    }

    private String getImageString(Bitmap bitmap) {
        // Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream); //compress to which format you want.
        byte [] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }
}

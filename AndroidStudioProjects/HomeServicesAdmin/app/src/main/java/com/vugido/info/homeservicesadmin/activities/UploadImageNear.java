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

import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.adapters.homepage.ServiceDetailsAdapter;
import com.vugido.info.homeservicesadmin.adapters.near.NearMediumRecyclerViewAdapter;
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

import retrofit2.Call;
import retrofit2.Callback;

public class UploadImageNear extends AppCompatActivity {
    private static final int PICK_IMAGE = 22;
//    List<String> stringList;
//    List<String> images;
//RecyclerView recyclerView;
//Toolbar toolbar;
//    ServiceDetailsAdapter serviceDetailsAdapter;
int service_id;
//List<ServiceDetailsModel> serviceDetailsModelList;
MyDialogLoader myDialogLoader;
Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image_near);
        service_id=getIntent().getIntExtra("ID",0);
button=findViewById(R.id.button4);
button.setOnClickListener(v -> {
    galleryImage();
});



    }






    private void galleryImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

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

        map.put("IMG",imageString);
        map.put("CID",String.valueOf(service_id));

        if (myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Adding...");
        myDialogLoader.setArguments(bundle);

        myDialogLoader.show(getSupportFragmentManager(),"D");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().nearUploadImage(map);
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

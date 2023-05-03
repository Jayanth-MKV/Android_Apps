package com.vugido.vugidoinventorymanagement.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.dialogs.MyStatusDialog;
import com.vugido.vugidoinventorymanagement.models.new_categories.Response;
import com.vugido.vugidoinventorymanagement.models.new_images.DATAItem;
import com.vugido.vugidoinventorymanagement.network.Retrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,  AdapterView.OnItemSelectedListener {

    View Progress;
    private static final int PICK_IMAGE = 1;
    ImageView imageView;
    String ImageString="",Des="";
    CardView cardView,retry;
    TextInputEditText name,price,tags,coins;
    Spinner description;
    TextInputLayout coined;
    FloatingActionButton floatingActionButton;
    List<String> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_affiliated);
        Progress=findViewById(R.id.my_progress);
        cardView=findViewById(R.id.cardView);
        retry=findViewById(R.id.retry_image);
        name=findViewById(R.id.pn);
        price=findViewById(R.id.product_price);
        tags=findViewById(R.id.product_tag);
        imageView=findViewById(R.id.selectedImage);
        description=findViewById(R.id.description);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        coins=findViewById(R.id.coins);
        coined=findViewById(R.id.product_coins);

        floatingActionButton.setOnClickListener(this);
        cardView.setOnClickListener(this);
        retry.setOnClickListener(this);
        description.setOnItemSelectedListener(this);

        Progress.setVisibility(View.GONE);
        categories= new ArrayList<String>();
        categories.add("Unit");
        categories.add("Packet");
        categories.add("Bundle");
        categories.add("Pack");
        categories.add("Sachet");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        description.setAdapter(arrayAdapter);

        int value=getIntent().getIntExtra("OPTION",0);
        if(value==1){
            //affiliate..
            coined.setVisibility(View.VISIBLE);

        }else if(value==2){
            coined.setVisibility(View.GONE);
            //show coined...
        }else {
            coined.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.floatingActionButton:
                //open new activity..

                // start uploading...
                //startActivity(new Intent(MainActivity.this,AdminAccessOptions.class));
                floatingActionButton.setEnabled(false);


                if(!ImageString.equals("")&& !Des.equals("") && !Objects.requireNonNull(name.getText()).toString().equals("") && !Objects.requireNonNull(tags.getText()).toString().equals("") && !Objects.requireNonNull(price.getText()).toString().equals("")){

                    // upload the data...

                    if(getIntent().getIntExtra("OPTION",0)==1){
                        uploadData(ImageString,name.getText().toString(),Des,price.getText().toString(),tags.getText().toString(), Objects.requireNonNull(coins.getText()).toString());
                    }else if(getIntent().getIntExtra("OPTION",0)==2){
                        uploadDataCoined(ImageString,name.getText().toString(),Des,price.getText().toString(),tags.getText().toString());
                    }else {
                        uploadDataGroceries(ImageString,name.getText().toString(),Des,price.getText().toString(),tags.getText().toString());
                    }

                }else {
                    floatingActionButton.setEnabled(true);
                    Toast.makeText(this,"Enter Valid Data To Upload",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cardView:
            case R.id.retry_image:
                //retry image ..
                galleryImage();
                break;

        }

    }

    private void uploadData(String imageString, String toString, String des, String toString1, String toString2,String coins) {

        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("NAME",toString);
        map.put("IMG",imageString);
        map.put("DES",des);
        map.put("PRICE",toString1);
        map.put("TAG",toString2);
        map.put("COINS",coins);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().uploadData(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                Progress.setVisibility(View.GONE);
                Log.e("response",response.toString());
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        showDialog(true);
                        Toast.makeText(getApplicationContext(),"UPLOADED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                        resetFields();
                    }else {
                        showDialog(false);
                        Toast.makeText(getApplicationContext(),"ERROR ! TRY AGAIN",Toast.LENGTH_LONG).show();

                    }
                }else {
                    showDialog(false);
                }

                floatingActionButton.setEnabled(true);

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"ERROR ! TRY AGAIN",Toast.LENGTH_LONG).show();
                Progress.setVisibility(View.GONE);
                floatingActionButton.setEnabled(true);
                showDialog(false);
            }
        });

    }

    private void uploadDataGroceries(String imageString, String toString, String des, String toString1, String toString2)
    {

        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("NAME",toString);
        map.put("IMG",imageString);
        map.put("DES",des);
        map.put("PRICE",toString1);
        map.put("TAG",toString2);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().uploadDataGeneral(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                Progress.setVisibility(View.GONE);
                Log.e("response",response.toString());
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        showDialog(true);
                        Toast.makeText(getApplicationContext(),"UPLOADED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                        resetFields();
                    }else {
                        showDialog(false);
                        Toast.makeText(getApplicationContext(),"ERROR ! TRY AGAIN",Toast.LENGTH_LONG).show();

                    }
                }else {
                    showDialog(false);
                }

                floatingActionButton.setEnabled(true);

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"ERROR ! TRY AGAIN",Toast.LENGTH_LONG).show();
                Progress.setVisibility(View.GONE);
                floatingActionButton.setEnabled(true);
                showDialog(false);
            }
        });

    }

    private void uploadDataCoined(String imageString, String toString, String des, String toString1, String toString2)
    {

        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("NAME",toString);
        map.put("IMG",imageString);
        map.put("DES",des);
        map.put("PRICE",toString1);
        map.put("TAG",toString2);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().uploadDataCoined(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                Progress.setVisibility(View.GONE);
                Log.e("response",response.toString());
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        showDialog(true);
                        Toast.makeText(getApplicationContext(),"UPLOADED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                        resetFields();
                    }else {
                        showDialog(false);
                        Toast.makeText(getApplicationContext(),"ERROR ! TRY AGAIN",Toast.LENGTH_LONG).show();

                    }
                }else {
                    showDialog(false);
                }

                floatingActionButton.setEnabled(true);

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"ERROR ! TRY AGAIN",Toast.LENGTH_LONG).show();
                Progress.setVisibility(View.GONE);
                floatingActionButton.setEnabled(true);
                showDialog(false);
            }
        });

    }


    private void resetFields() {

        name.setText("");
        price.setText("");
        tags.setText("");
        imageView.setImageBitmap(null);
        imageView.setImageDrawable(getDrawable(R.drawable.add_button));
        retry.setVisibility(View.GONE);
    }

    private void showDialog(boolean status){

        final MyStatusDialog s;
        s=new MyStatusDialog();
        Bundle bundle=new Bundle();

        if(status){
            bundle.putString("MSG","Uploaded Successfully");
            bundle.putBoolean("STATUS",true);



        }else {
            bundle.putString("MSG","Not - Uploaded Try Again!");
            bundle.putBoolean("STATUS",false);

        }

        s.setArguments(bundle);
        s.show(getSupportFragmentManager(),"STATUS");


    }
    private void galleryImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
//            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 150,
//                    150,true);
            imageView.setImageBitmap(bitmap);
            imageView.setEnabled(false);
            retry.setVisibility(View.VISIBLE);
            retry.setEnabled(true);
            ImageString = getImageString(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private String getImageString(Bitmap bitmap) {
        // Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream); //compress to which format you want.
        byte [] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Des=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

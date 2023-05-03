package com.vugido.info.homeservicesadmin.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.dialogs.MyDialogLoader;
import com.vugido.info.homeservicesadmin.models.services.c.DATAItem;
import com.vugido.info.homeservicesadmin.models.services.c.Response;
import com.vugido.info.homeservicesadmin.network.Retrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AddNewNearInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    MyDialogLoader myDialogLoader;
    private static final int PICK_IMAGE = 1;
    private static final int LOCC = 5;
    Spinner SubCatSpinner;
   // View KgLayout,UnitsLayout;
     ImageView imageView;
    CardView RetryImage;
    int SubCatId;
    Button AddProduct;
    String la,lname;
    String lo;
    String ImageString="";
  //  private View Progress;
    TextView llc;
    List<DATAItem> dataItemList;
    int v;
    TextInputEditText p_name,ags,des,hours,wa,ra;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_near_info);
        SubCatSpinner=findViewById(R.id.sub_cat);
        AddProduct=findViewById(R.id.addProduct);
        imageView=findViewById(R.id.selectedImage);
        RetryImage=findViewById(R.id.retry_image);
        p_name=findViewById(R.id.productname);

        ags=findViewById(R.id.tname);

        des=findViewById(R.id.describe);
        hours=findViewById(R.id.bhours);
        wa=findViewById(R.id.bwa);

        ra=findViewById(R.id.bra);
        llc=findViewById(R.id.business_loc);

        v=getIntent().getIntExtra("V",0);

        fetchSubCategoriesList();


        llc.setOnClickListener(v -> {
            //launch map..

            Intent intent=new Intent(AddNewNearInfoActivity.this,MapsActivity.class);
            startActivityForResult(intent,LOCC);
        });



        SubCatSpinner.setOnItemSelectedListener(this);

        AddProduct.setOnClickListener(this);

        imageView.setOnClickListener(this);
        RetryImage.setOnClickListener(this);

    }

   private void fetchSubCategoriesList() {


        Map<String, Object> map=new HashMap<>();
        map.put("K","2");


       // Progress.setVisibility(View.VISIBLE);
       if (myDialogLoader==null)
           myDialogLoader=new MyDialogLoader();
       Bundle bundle=new Bundle();
       bundle.putString("MSG","Adding...");
       myDialogLoader.setArguments(bundle);

       myDialogLoader.show(getSupportFragmentManager(),"D");
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchSubCategories(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                myDialogLoader.dismiss();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        Toast.makeText(getApplicationContext(),"sub cats fetched", Toast.LENGTH_LONG).show();
                        dataItemList= response.body().getDATA();
                        bindDataToSpinner(dataItemList);

                    }else {
                        Toast.makeText(getApplicationContext(),"error sub cats fetched", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"error sub cats fetched", Toast.LENGTH_LONG).show();

                    // error
                }
                //Progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(),"error sub cats fetched", Toast.LENGTH_LONG).show();
               // Progress.setVisibility(View.GONE);

                myDialogLoader.dismiss();
            }
        });

    }

    private void bindDataToSpinner(List<DATAItem> dataItemList) {


        if(dataItemList!=null) {
            List<String> Subcategories = new ArrayList<String>();


            for (DATAItem dataItem : dataItemList) {

                Subcategories.add(dataItem.getNAME());

            }


            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Subcategories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            SubCatSpinner.setAdapter(dataAdapter);



            p_name.setText(getIntent().getStringExtra("N"));
            ags.setText(getIntent().getStringExtra("S"));
            hours.setText(getIntent().getStringExtra("HRS"));
            wa.setText(getIntent().getStringExtra("WAP"));
            ra.setText(getIntent().getStringExtra("RA"));
            des.setText(getIntent().getStringExtra("DES"));
//            intent.putExtra("ID",squareMediumModel.getID());
//

        }else {

            Toast.makeText(this,"Please add sub categort first", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        switch (parent.getId()){

            case R.id.sub_cat:

                setSubCatId(position,parent.getItemAtPosition(position).toString());
                break;
        }



    }

    private void setSubCatId(int position, String toString) {

       // for(int i=0;i<dataItemList.size();i++){

          //  if(position==i)
            SubCatId=dataItemList.get(position).getID();
            Toast.makeText(this,"Name:"+dataItemList.get(position).getNAME()+position, Toast.LENGTH_LONG).show();

       // }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode== PICK_IMAGE && resultCode==RESULT_OK && data!=null){




            getPicFromGallery(data);

            //  Log.e("pick image","clicked");

        }else if (requestCode==LOCC && resultCode==RESULT_OK && data!=null){

            //use loc info...


            lo=data.getStringExtra("LO");
            la=data.getStringExtra("LA");
            lname=data.getStringExtra("ADD");
            llc.setText(data.getStringExtra("ADD"));

        }
    }


    private void getPicFromGallery(Intent data) {


        Uri ImageUri=data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageUri);
            imageView.setImageBitmap(bitmap);
            imageView.setEnabled(false);
            RetryImage.setVisibility(View.VISIBLE);
            RetryImage.setEnabled(true);
            ImageString = getImageString(bitmap);


           // Toast.makeText(this,ImageString, Toast.LENGTH_LONG).show();

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


    private void galleryImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.selectedImage:
            case R.id.retry_image:
                galleryImage();
                break;
            case R.id.addProduct:


                AddProduct.setEnabled(false);

                // fetch all the data here and send

                String ProductImage,ProductName,ProductSubCategory,tags,desc,hrs,wap,locc;

                ProductImage=ImageString;
                ProductName= Objects.requireNonNull(p_name.getText()).toString();
                ProductSubCategory= String.valueOf(SubCatId);
                tags= Objects.requireNonNull(ags.getText()).toString();

                desc= Objects.requireNonNull(des.getText()).toString();
                hrs= Objects.requireNonNull(hours.getText()).toString();
                wap= Objects.requireNonNull(wa.getText()).toString();



                if(ProductImage.isEmpty() || ProductName.isEmpty() || tags.isEmpty() || Objects.requireNonNull(ra.getText()).toString().isEmpty()){

                    Toast.makeText(this,"Enter Valid Product Details", Toast.LENGTH_LONG).show();
                    AddProduct.setEnabled(true);
                }else {
                    Map<String, Object> map=new HashMap<>();

                    if (v==1){
                        map.put("V","1");
                        map.put("CID",String.valueOf(getIntent().getIntExtra("CID",0)));
                    }else {
                        map.put("CID","0");
                        map.put("V","0");

                    }
                    // here send data..
                    map.put("sid",ProductSubCategory);
                    map.put("title",ProductName);
                    map.put("IMAGE",ProductImage);
                    map.put("TAG",tags);
                    map.put("desc",desc);
                    map.put("hrs",hrs);
                    map.put("wap",wap);
                    map.put("la",la);
                    map.put("lo",lo);
                    map.put("lname",lname);
                    map.put("RA", Objects.requireNonNull(ra.getText()).toString());
                    addNewProduct(map);

                }

                break;
        }

    }




    private void addNewProduct(Map<String, Object> map) {


        //Progress.setVisibility(View.VISIBLE);
        if (myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Adding...");
        myDialogLoader.setArguments(bundle);

        myDialogLoader.show(getSupportFragmentManager(),"D");
        Call<com.vugido.info.homeservicesadmin.models.Response> call=RetrofitBuilder.getInstance().getRetrofit().addNewNearProduct(map);

        call.enqueue(new Callback<com.vugido.info.homeservicesadmin.models.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.info.homeservicesadmin.models.Response> call,
                                   @NonNull retrofit2.Response<com.vugido.info.homeservicesadmin.models.Response> response) {


                myDialogLoader.dismiss();

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isSTATUS()){
                        //ok
                        showDialogStatus(true);
                    }else {

                        // some error
                        showDialogStatus(false);
                    }

                }else {

                    showDialogStatus(false);
                    // some error
                }
                //Progress.setVisibility(View.GONE);
                AddProduct.setEnabled(true);
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.info.homeservicesadmin.models.Response> call,
                                  @NonNull Throwable t) {

                myDialogLoader.dismiss();
                // some error
                showDialogStatus(false);
                //Progress.setVisibility(View.GONE);
                AddProduct.setEnabled(true);
            }
        });



    }

    private void showDialogStatus(boolean status){
        if(status){
            Toast.makeText(this,"Added Successfully",Toast.LENGTH_LONG).show();
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
}

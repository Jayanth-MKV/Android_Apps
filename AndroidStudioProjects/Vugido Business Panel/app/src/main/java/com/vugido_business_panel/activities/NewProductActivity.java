package com.vugido_business_panel.activities;

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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.vugido_business_panel.R;
import com.vugido_business_panel.Retrofit.RetrofitBuilder;
import com.vugido_business_panel.app_congif.MyPreferences;
import com.vugido_business_panel.models.Categories.fetchCategories.DATAItem;
import com.vugido_business_panel.models.Categories.fetchCategories.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class NewProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final int PICK_IMAGE = 1;
    Spinner SubCatSpinner;
   // View KgLayout,UnitsLayout;
     ImageView imageView;
    CardView RetryImage;
    int SubCatId;
    Button AddProduct;
    TextInputEditText p_name,p_quantity,p_price,p_des,p_offer,p_offerlimit,p_cartlimit;
    String ImageString;
    CheckBox productcheck;
    List<DATAItem> dataItemList;
    private SweetAlertDialog s;
    private View Progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        SubCatSpinner=findViewById(R.id.sub_cat);
        AddProduct=findViewById(R.id.addProduct);
        imageView=findViewById(R.id.selectedImage);
        RetryImage=findViewById(R.id.retry_image);
        productcheck=findViewById(R.id.productcheck);
        p_name=findViewById(R.id.productname);
        p_quantity=findViewById(R.id.unitinterval);
        p_price=findViewById(R.id.unitprice);
        p_des=findViewById(R.id.unitproductdescription);
        p_offer=findViewById(R.id.unitsproductoffer);
        p_offerlimit=findViewById(R.id.unitsproductofferlimit);
        p_cartlimit=findViewById(R.id.unitsproductcartlimit);

        Progress=findViewById(R.id.my_progress);


        Progress.setVisibility(View.INVISIBLE);



        fetchSubCategoriesList();
        List<String> categories = new ArrayList<String>();
        categories.add("Kg or grams");
        categories.add("Units");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner

        SubCatSpinner.setOnItemSelectedListener(this);

        AddProduct.setOnClickListener(this);

        imageView.setOnClickListener(this);
        RetryImage.setOnClickListener(this);

    }

   private void fetchSubCategoriesList() {


        Map<String, Object> map=new HashMap<>();
        map.put("CID",new MyPreferences(this).getCID());


        Progress.setVisibility(View.VISIBLE);
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchSubCategories(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        Toast.makeText(getApplicationContext(),"sub cats fetched",Toast.LENGTH_LONG).show();
                        dataItemList= response.body().getDATA();
                        bindDataToSpinner(dataItemList);

                    }else {
                        Toast.makeText(getApplicationContext(),"error sub cats fetched",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"error sub cats fetched",Toast.LENGTH_LONG).show();

                    // error
                }
                Progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(),"error sub cats fetched",Toast.LENGTH_LONG).show();
                Progress.setVisibility(View.GONE);

            }
        });

    }

    private void bindDataToSpinner(List<DATAItem> dataItemList) {


        if(dataItemList!=null) {
            List<String> Subcategories = new ArrayList<String>();


            for (DATAItem dataItem : dataItemList) {

                Subcategories.add(dataItem.getSUBNAME());

            }


            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Subcategories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            SubCatSpinner.setAdapter(dataAdapter);

        }else {

            Toast.makeText(this,"Please add sub categort first",Toast.LENGTH_LONG).show();

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
            Toast.makeText(this,"Name:"+dataItemList.get(position).getSUBNAME()+position,Toast.LENGTH_LONG).show();

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

        }
    }


    private void getPicFromGallery(Intent data) {


        Uri ImageUri=data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageUri);
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 150,
                    150,true);
            imageView.setImageBitmap(bitmap);
            imageView.setEnabled(false);
            RetryImage.setVisibility(View.VISIBLE);
            RetryImage.setEnabled(true);
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


    private void galleryImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.selectedImage:
            case R.id.retry_image:
                galleryImage();
                break;
            case R.id.addProduct:


                AddProduct.setEnabled(false);

                // fetch all the data here and send

                String ProductImage,ProductName,ProductSubCategory,Quantity,Price,Description, Offer,OfferLimit,CartLimit,InStock;

                ProductImage=ImageString;
                ProductName= Objects.requireNonNull(p_name.getText()).toString();
                ProductSubCategory= String.valueOf(SubCatId);
                Quantity= Objects.requireNonNull(p_quantity.getText()).toString();
                Price= Objects.requireNonNull(p_price.getText()).toString();
                Description= Objects.requireNonNull(p_des.getText()).toString();
                Offer= Objects.requireNonNull(p_offer.getText()).toString();
                OfferLimit= Objects.requireNonNull(p_offerlimit.getText()).toString();
                CartLimit= Objects.requireNonNull(p_cartlimit.getText()).toString();

                if(productcheck.isChecked()){
                    InStock="0";

                }else {

                    InStock="1";
                }

                if(ProductImage.isEmpty() || ProductName.isEmpty() || Quantity.isEmpty() || Price.isEmpty() || Description.isEmpty() || Offer.isEmpty() || OfferLimit.isEmpty() || CartLimit.isEmpty()){

                    Toast.makeText(this,"Enter Valid Product Details",Toast.LENGTH_LONG).show();
                    AddProduct.setEnabled(true);
                }else {

                    // here send data..
                    Map<String, Object> map=new HashMap<>();
                    map.put("CID",new MyPreferences(this).getCID());
                    map.put("SUB_CAT_ID",ProductSubCategory);
                    map.put("E_NAME",ProductName);
                    map.put("T_NAME",ProductName);
                    map.put("IMAGE",ProductImage);
                    map.put("QUANTITY",Quantity);
                    map.put("PRICE",Price);
                    map.put("CART_LIMIT",CartLimit);
                    map.put("OFFER",Offer);
                    map.put("IN_STOCK",InStock);
                    map.put("OFFER_LIMIT",OfferLimit);
                    map.put("DESCRIPTION",Description);
                    map.put("TID","0");
                    addNewProduct(map);

                }

                break;
        }

    }

    private void addNewProduct(Map<String, Object> map) {


        Progress.setVisibility(View.VISIBLE);
        Call<com.vugido_business_panel.models.Product.addProduct.Response> call=RetrofitBuilder.getInstance().getRetrofit().addNewProduct(map);

        call.enqueue(new Callback<com.vugido_business_panel.models.Product.addProduct.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido_business_panel.models.Product.addProduct.Response> call,
                                   @NonNull retrofit2.Response<com.vugido_business_panel.models.Product.addProduct.Response> response) {



                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
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
                Progress.setVisibility(View.GONE);
                AddProduct.setEnabled(true);
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido_business_panel.models.Product.addProduct.Response> call,
                                  @NonNull Throwable t) {

                // some error
                showDialogStatus(false);
                Progress.setVisibility(View.GONE);
                AddProduct.setEnabled(true);
            }
        });



    }

    private void showDialogStatus(boolean status){
        if(status){
            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Added Successfully")
            ;
            s.show();
        }else{
            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error.. Try again!!")
            ;
            s.show();

        }

    }
}

package com.vugido.foodhub.v_c.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vugido.foodhub.v_c.R;
import com.vugido.foodhub.v_c.Retrofit.RetrofitBuilder;
import com.vugido.foodhub.v_c.app_congif.MyPreferences;
import com.vugido.foodhub.v_c.dialogs.MyDialogLoader;
import com.vugido.foodhub.v_c.models.error.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class EditProductActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    SweetAlertDialog s;
    private static final int PICK_IMAGE = 10;
    TextView Name,Category,Price,Quantity,Description,Offer,OfferLimit,CartLimit;
    ImageView image;
    Button ChangeImage;
    ArrayList<String> CategoryNameList;
    ArrayList<Integer> CategoryIdList;
    String S_Name,S_Category,S_Price,S_Quantity,S_Description,S_Offer,S_OfferLimit,S_CartLimit,S_Image;
    String SubCategory;
    int PID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_layout);



        Name=findViewById(R.id.edit_productname);
        Category=findViewById(R.id.edit_sub_category);
        Price=findViewById(R.id.edit_price);
        Quantity=findViewById(R.id.edit_quantity);
        Description=findViewById(R.id.edit_description);
        Offer=findViewById(R.id.edit_offer);
        OfferLimit=findViewById(R.id.edit_offer_limit);
        CartLimit=findViewById(R.id.edit_cart_limit);
        image=findViewById(R.id.edit_ImageSelected);
        ChangeImage=findViewById(R.id.edit_Image);

        Name.setOnClickListener(this);
        Category.setOnClickListener(this);
        Price.setOnClickListener(this);
        Quantity.setOnClickListener(this);
        Description.setOnClickListener(this);
        Offer.setOnClickListener(this);
        OfferLimit.setOnClickListener(this);
        CartLimit.setOnClickListener(this);
        ChangeImage.setOnClickListener(this);

        setAllFields();

    }

    private void setAllFields() {

        Bundle bundle=getIntent().getBundleExtra("BUNDLE");
        assert bundle!=null;
        S_Name=bundle.getString("N");
        S_Category=bundle.getString("CAT_NAME");
        S_Image=bundle.getString("I");
        S_Quantity= bundle.getString("Q");
        S_Price= String.valueOf(bundle.getInt("PRICE"));
        S_Description=bundle.getString("D");
        S_Offer= String.valueOf(bundle.getInt("O"));
        S_OfferLimit= String.valueOf(bundle.getInt("OL"));
        S_CartLimit= String.valueOf(bundle.getInt("CL"));
        CategoryIdList=bundle.getIntegerArrayList("ARRAY_CAT_ID");
        CategoryNameList=bundle.getStringArrayList("ARRAY_CAT_NAME");
        PID=bundle.getInt("PID");

        bindBundleData();

    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();

    }

    private void bindBundleData() {

        Glide.with(this).load(S_Image).into(image);
        Name.setText(String.format("Product Name : %s", S_Name));
        Category.setText(String.format("Category : %s", S_Category));
        Quantity.setText(String.format("Quantity : %s", S_Quantity));
        Price.setText(String.format("Price : %s", S_Price));
        Description.setText(String.format("Description : %s", S_Description));
        OfferLimit.setText(String.format("Offer Limit : %s", S_OfferLimit));
        Offer.setText(String.format("Offer Value : %s", S_Offer));
        CartLimit.setText(String.format("Cart Limit : %s", S_CartLimit));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.edit_productname:
                myDialogBox("Ex: Almonds, Bananas etc.. ","New Product Name","NAME",S_Name);
                break;
            case R.id.edit_price:
                myDialogBox("Ex: 30,56.8 ","New Price Value","PRICE",S_Price);

                break;
            case R.id.edit_quantity:
                myDialogBox("Ex : 250, 500, 1","New Quantity Value","QUANTITY",S_Quantity);

                break;
            case R.id.edit_offer:
                myDialogBox("0 in case no offer","New Offer Value","OFFER",S_Offer);

                break;
            case R.id.edit_offer_limit:
                myDialogBox("0 in case no offer limit ","New OfferLimit Value","OL",S_OfferLimit);

                break;
            case R.id.edit_cart_limit:
                myDialogBox("0 in case no limit ","New CartLimit Value","CL",S_CartLimit);

                break;
            case R.id.edit_Image:
                galleryImage();
                break;
            case R.id.edit_description:
                myDialogBox("Ex: packets, plates,bundles etc ","New Description Name","DESCRIPTION",S_Description);

                break;
            case R.id.edit_sub_category:

                myDialogBox("","Select One Category","SUB_CAT","");
                break;
        }

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
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 150,
                    150,true);
            image.setImageBitmap(bitmap);

          String ImageString = getImageString(bitmap);
          runUpdateStatement(ImageString,"IMAGE");



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
    private void myDialogBox(String hint, String textTitle, final String TAG, String val) {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_dialog, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
        Spinner spinner=dialogView.findViewById(R.id.spinner);
        TextView textView=dialogView.findViewById(R.id.textView);
        textView.setText(textTitle);
        editText.setHint(hint);
        editText.setText(val);

        switch (TAG){
            case "NAME":
            case "DESCRIPTION":
                // text...
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
            break;
            case "QUANTITY":
            case "PRICE":
            case "OFFER":
                // float
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case "OL":
            case "CL":
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            // int..
                break;
            case "SUB_CAT":
                editText.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
                spinner.setOnItemSelectedListener(this);
                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CategoryNameList);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);

                break;


        }
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS

                // check first for valid name...


                // get the text and send to server...
                if(TAG.equals("SUB_CAT")){
                    runUpdateStatement(SubCategory,TAG);
                }else {
                    String value= editText.getText().toString();
                    runUpdateStatement(value,TAG);
                }

                dialogBuilder.dismiss();




            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    MyDialogLoader myDialogLoader;
    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }


    private void runUpdateStatement(String value, String tag) {


        Map<String, Object> map=new HashMap<>();
        map.put("CID",new MyPreferences(this).getCID());
        map.put("PID", String.valueOf(PID));
        map.put("TAG",tag);
        map.put("VALUE",value);

        loginLoader(true,"Please Wait...");
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().update(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                loginLoader(false,"");

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok
                        showDialogStatus(true);

                    }else {

                        // error
                        showDialogStatus(false);
                    }

                }else {

                    // some error
                    showDialogStatus(false);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                loginLoader(false,"");
                // error
                showDialogStatus(false);
            }
        });

        // if successful...
        bindData(value,tag);


    }

    private void showDialogStatus(boolean status){
        if(status){
            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Updated Successfully")
            ;
            s.show();
        }else{
            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error.. Try again!!")
            ;
            s.show();

        }



    }

    private void bindData(String value, String TAG) {

        switch (TAG) {
            case "NAME":
                Name.setText("Product Name : "+value);
                break;
            case "DESCRIPTION":
                Description.setText("Description : "+value);
                break;
            case "QUANTITY":
                Quantity.setText("Quantity : "+value);
                break;
            case "PRICE":
                Price.setText("Price : "+value);
                break;
            case "OFFER":
                Offer.setText("Offer : "+value);
                break;

            case "OL":
                OfferLimit.setText("Offer Limit : "+value);
                break;
            case "CL":
                CartLimit.setText("Cart Limit : "+value);
                break;

            case "SUB_CAT":
                for(int index=0;index<CategoryIdList.size();index++){
                    if(CategoryIdList.get(index)== Integer.parseInt(SubCategory)){
                        Category.setText("Category : "+CategoryNameList.get(index));

                    }
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        SubCategory=CategoryIdList.get(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

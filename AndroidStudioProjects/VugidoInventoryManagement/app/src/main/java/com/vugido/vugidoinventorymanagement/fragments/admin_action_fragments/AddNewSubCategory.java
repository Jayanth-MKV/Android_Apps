package com.vugido.vugidoinventorymanagement.fragments.admin_action_fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.models.new_images.DATAItem;
import com.vugido.vugidoinventorymanagement.models.new_images.Response;
import com.vugido.vugidoinventorymanagement.network.Retrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;

public class AddNewSubCategory extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    List<DATAItem> dataItems;
    private Context context;
    private static final int PICK_IMAGE = 2;
    ImageView AddImage;
    CardView RetryImage;
    Button SendSubCategoryData;
    private  String ImageString=null;
    private TextInputEditText CategoryEditBox;
    private Spinner CategorySpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_new_sub_category,container,false);

        AddImage=view.findViewById(R.id.selectedImage);
        RetryImage=view.findViewById(R.id.retry_image);
        SendSubCategoryData=view.findViewById(R.id.add_sub_category_button);
        CategoryEditBox=view.findViewById(R.id.category_name_edit_box);
        CategorySpinner=view.findViewById(R.id.category_spinner_from_add_sub_cat);
        CategorySpinner.setOnItemSelectedListener(this);
        SendSubCategoryData.setOnClickListener(this);
        RetryImage.setOnClickListener(this);
        AddImage.setOnClickListener(this);

        // now get the categories data from server and bind to server..
        getCategoriesData();
        return view;
    }

    private void getCategoriesData() {


        Call<Response> responseCall=RetrofitBuilder.getInstance().getRetrofit().fetchFoodCategories();
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    List<DATAItem> dataItemList=response.body().getDATA();
                    dataItems=dataItemList;
                    List<String> categories = new ArrayList<String>();
                    for(DATAItem dataItem: dataItemList){

                        categories.add(dataItem.getTITLE());
                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item,categories);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    CategorySpinner.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {



            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== PICK_IMAGE && resultCode==RESULT_OK && data!=null){
            getPicFromGallery(data);

        }
    }

    private void getPicFromGallery(Intent data) {
        
        Uri ImageUri=data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), ImageUri);
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 150,
                    150,true);
            AddImage.setImageBitmap(bitmap);
            AddImage.setEnabled(false);
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


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.selectedImage:
            case R.id.retry_image:
                galleryImage();
                break;
            case R.id.add_category_button:
                SendSubCategoryData.setEnabled(false);
                // validate and send the data to server to store it ...
                // use regex.. to validate the text
                String category= Objects.requireNonNull(CategoryEditBox.getText()).toString();

                Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
                Matcher ms = ps.matcher(category);
                boolean bs = ms.matches();

                if(ImageString==null ||  !bs || category.equals("") || category.equals(" ")){

                    // tell user to enter valid data
                    Toast.makeText(context,"Enter valid Info",Toast.LENGTH_LONG).show();
                    SendSubCategoryData.setEnabled(true);

                }else {

                    sendDataToServer();
                }

                break;

        }
    }

    private void sendDataToServer() {


    }

    private void galleryImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }


    private void statusDialog(boolean status){

        SweetAlertDialog s;

        if(status){
            s=new SweetAlertDialog(context,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Uploaded Successfully");
            s.show();
            // refresh the data set of..adapter..
            resetPage();
        }else {
            s=new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Not - Uploaded Try Again!");
            s.show();

        }
        SendSubCategoryData.setEnabled(true);

    }

    private void resetPage() {

        RetryImage.setVisibility(View.GONE);
        AddImage.setEnabled(true);
        AddImage.setImageDrawable(context.getDrawable(R.drawable.add_button));
        CategoryEditBox.setText("");
        CategoryEditBox.setHint("Enter Category Name");
        ImageString=null;


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

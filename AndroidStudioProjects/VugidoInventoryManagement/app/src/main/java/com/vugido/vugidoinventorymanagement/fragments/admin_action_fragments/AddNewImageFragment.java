package com.vugido.vugidoinventorymanagement.fragments.admin_action_fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
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

import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.models.new_images.DATAItem;
import com.vugido.vugidoinventorymanagement.models.new_images.Response;
import com.vugido.vugidoinventorymanagement.network.Retrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;

public class AddNewImageFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final int PICK_IMAGE = 1;
    Spinner SpinnerSelectCategory;
    ImageView AddImage;
    CardView RetryImage;
    Button SendImageData;
    // add images spinner items list
    List<DATAItem> dataItems;
    private Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_add_images,container,false);
                SpinnerSelectCategory=view.findViewById(R.id.select_category_add_images_spinner);
                AddImage=view.findViewById(R.id.selectedImage);
                RetryImage=view.findViewById(R.id.retry_image);
                SendImageData=view.findViewById(R.id.send_image_button);
                SendImageData.setOnClickListener(this);
                RetryImage.setOnClickListener(this);
                AddImage.setOnClickListener(this);
                SpinnerSelectCategory.setOnItemSelectedListener(this);
                // settings related to this view layout call here

                setSpinnerCategory();


        return  view;

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
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), ImageUri);
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 150,
                    150,true);
            AddImage.setImageBitmap(bitmap);
            AddImage.setEnabled(false);
            RetryImage.setVisibility(View.VISIBLE);
            RetryImage.setEnabled(true);
           String  ImageString = getImageString(bitmap);

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


    private void setSpinnerCategory() {


        Call<Response> responseCall= RetrofitBuilder.getInstance().getRetrofit().fetchFoodCategories();
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
                    SpinnerSelectCategory.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call,@NonNull Throwable t) {


            }
        });






    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){
            case R.id.select_category_add_images_spinner:
                Log.e("ok","ok");


                for(DATAItem dataItem:dataItems){

                    if(parent.getItemAtPosition(position).toString().toLowerCase().equals(dataItem.getTITLE().toLowerCase())){

                        // print id
                        Toast.makeText(context,"clicked position="+parent.getItemAtPosition(position).toString()+","+position+"\nOriginal Id ="+dataItem.getID(),Toast.LENGTH_LONG).show();
                        Log.e("clicked position="+parent.getItemAtPosition(position).toString()+","+position,"Original Id ="+dataItem.getID());
                        break;
                    }
                }

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.selectedImage:
            case R.id.retry_image:
                galleryImage();
                break;
            case R.id.send_image_button:

                // validate and send the data to server to store it ...

                break;

        }
    }


    private void galleryImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }
}

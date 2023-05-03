package com.vugido.foodhub.hungrybirdsadmin.fragments.manage_homepage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vugido.foodhub.hungrybirdsadmin.R;
import com.vugido.foodhub.hungrybirdsadmin.adapters.home_adapters.SliderAdapter;
import com.vugido.foodhub.hungrybirdsadmin.dialogs.MyDialogLoader;
import com.vugido.foodhub.hungrybirdsadmin.dialogs.MyStatusDialog;
import com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.DATAItem;
import com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.Response;
import com.vugido.foodhub.hungrybirdsadmin.network.Retrofit.RetrofitBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

public class FragmentSlider extends Fragment implements SliderAdapter.SLIDER_FUN, SwipeRefreshLayout.OnRefreshListener {

    private static final int PICK_IMAGE = 10;
    RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;
    List<DATAItem> dataItemList;
    private Context context;
    private FloatingActionButton floatingActionButton;
    private String ImageString="";

    private MyDialogLoader myDialogLoader;
    private FragmentManager fragmentManager;
    SliderAdapter sliderAdapter;
    private MyStatusDialog myStatusDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        fragmentManager= Objects.requireNonNull(getActivity()).getSupportFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_slider,container,false);

        recyclerView=view.findViewById(R.id.rec);
        swipeRefreshLayout=view.findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.black);
        // called first time.. with out pulling..
        // make sure network connection before calling..
        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        //fetchMenu();
        swipeRefreshLayout.post(this::getSliders);
        floatingActionButton=view.findViewById(R.id.floatingActionButton);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy>0)
                    floatingActionButton.hide();
                else
                    floatingActionButton.show();

            }
        });


        floatingActionButton.setOnClickListener(v -> {

            uploadSlider();

        });

        return  view;
    }

    private void galleryImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }
    private void uploadSlider() {



        galleryImage();
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
//            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 150,
//                    150,true);
//            imageView.setImageBitmap(bitmap);
//            imageView.setEnabled(false);
//            RetryImage.setVisibility(View.VISIBLE);
//            RetryImage.setEnabled(true);
            ImageString = getImageString(bitmap);

            showAlertDialog();




        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void showAlertDialog() {

        final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Are you sure want to Upload?");
        alertDialogBuilder.setPositiveButton("Yes", (dialog, which) -> upload());
        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {

        });

        final AlertDialog alertDialog=alertDialogBuilder.create();

        alertDialog.setOnShowListener(dialog -> {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.secondaryColor));
        });

        alertDialog.show();

    }

    private void upload() {

        if(myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","UPLOADING IMAGE..");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(fragmentManager,"");

        Map<String,Object> map=new HashMap<>();

        map.put("IMAGE",ImageString);
        map.put("AID","1");

        Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.add.Response> responseCall=RetrofitBuilder.getInstance().getRetrofit().addSlider(map);
        responseCall.enqueue(new Callback<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.add.Response>() {
            @Override
            public void onResponse(Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.add.Response> call, retrofit2.Response<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.add.Response> response) {
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isSTATUS()){

                        Log.e("ok","uploaded");
                        showDialog(1);
                       //refreshRec();

                    }else
                        showDialog(0);
                }

                myDialogLoader.dismiss();
            }

            @Override
            public void onFailure(Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.add.Response> call, Throwable t) {

                myDialogLoader.dismiss();
                Log.e("ok","uploaded error");

                showDialog(0);
            }
        });
    }

    private void showDialog(int i) {

        if (myStatusDialog==null)
            myStatusDialog=new MyStatusDialog();

        Bundle bundle=new Bundle();

        if(i==1){
            bundle.putString("MSG","Uploaded Successfully");
            bundle.putBoolean("STATUS",true);




        }else {
            bundle.putString("MSG","Error Try Again !!");
            bundle.putBoolean("STATUS",false);

        }

        myStatusDialog.setArguments(bundle);
        myStatusDialog.show(fragmentManager,"STATUS");

    }

    private String getImageString(Bitmap bitmap) {
        // Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream); //compress to which format you want.
        byte [] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }



    private void getSliders() {

        if(myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","GETTING SLIDERS...");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(fragmentManager,"");

        Map<String,Object> map=new HashMap<>();
        map.put("AID","1");

        Call<Response> responseCall= RetrofitBuilder.getInstance().getRetrofit().fetchSliders(map);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        bind(response.body().getDATA());


                    }
                }

                swipeRefreshLayout.setRefreshing(false);
                myDialogLoader.dismiss();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                myDialogLoader.dismiss();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void bind(List<DATAItem> data) {

        Collections.reverse(data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        dataItemList=data;
        recyclerView.setLayoutManager(linearLayoutManager);

         sliderAdapter=new SliderAdapter(context,dataItemList,this);
        recyclerView.setAdapter(sliderAdapter);
        Log.e("ok","bind called");


    }

    @Override
    public void delSlider(int id) {


        if(myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","DELETING IMAGE..");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(fragmentManager,"");

        Map<String ,Object> map=new HashMap<>();
        map.put("SID",String.valueOf(id));

        Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> call=RetrofitBuilder.getInstance().getRetrofit().delSLider(map);
        call.enqueue(new Callback<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response>() {
            @Override
            public void onResponse(Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> call, retrofit2.Response<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> response) {

                myDialogLoader.dismiss();

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if (response.body().isSTATUS()){
                        showDialog(1);
                        refreshRec();
                    }else
                        showDialog(0);

                }
            }

            @Override
            public void onFailure(Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> call, Throwable t) {

                myDialogLoader.dismiss();
                showDialog(0);
            }
        });
    }

    @Override
    public void toggleSlider(int id) {

        if(myDialogLoader==null)
            myDialogLoader=new MyDialogLoader();
        Bundle bundle=new Bundle();
        bundle.putString("MSG","CHANGING STATUS..");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(fragmentManager,"");

        Map<String ,Object> map=new HashMap<>();
        map.put("SID",String.valueOf(id));

        Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> call=RetrofitBuilder.getInstance().getRetrofit().toggleSlider(map);
        call.enqueue(new Callback<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response>() {
            @Override
            public void onResponse(Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> call, retrofit2.Response<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> response) {


                myDialogLoader.dismiss();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if (response.body().isSTATUS()){
                        showDialog(1);
                        refreshRec();

                    }else
                        showDialog(0);

                }
            }

            @Override
            public void onFailure(Call<com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.status.Response> call, Throwable t) {

                myDialogLoader.dismiss();
                showDialog(0);
            }
        });
    }

    private void refreshRec(){
    dataItemList.clear();
    sliderAdapter.notifyDataSetChanged();
    recyclerView.removeAllViews();
    getSliders();
        Log.e("ok","refresh called");

    }


    @Override
    public void onRefresh() {
        getSliders();
    }
}

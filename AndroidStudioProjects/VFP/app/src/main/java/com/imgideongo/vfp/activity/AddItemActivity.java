package com.imgideongo.vfp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.google.android.material.snackbar.Snackbar;
import androidx.exifinterface.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.imgideongo.vfp.ConfigVariables.Config;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.fragments.ChooseImageDialog;
import com.imgideongo.vfp.fragments.ErrorDialogBottomSheet;
import com.imgideongo.vfp.fragments.ProcessingDialog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import static com.imgideongo.vfp.fragments.ChooseImageDialog.PICK_IMAGE;
import static com.imgideongo.vfp.fragments.ChooseImageDialog.REQUEST_IMAGE_CAPTURE;



public class AddItemActivity extends AppCompatActivity implements View.OnClickListener ,
        ChooseImageDialog.ImageSelection {
    Toolbar toolbar;

    // Form  Elements for adding items

    Spinner Category,Unit;
    EditText EnglishItemName,TeluguItemName,Price,Quantity,Offer;
    CheckBox OfferStatus,TagStatus;
    ImageButton takeImage;
    ImageView Image,ChangeImage;
    TextView textView;


    String currentPhotoPath;
    File photoFile;
    boolean hasImage=false;

    ProcessingDialog processingDialog;
    private String ImageString;

    public static final int REFRESH=1;
  //  private int CID;
    private RelativeLayout relativeLayout;
    private AlertDialog alertDialog;
    private String mEnglishName,mCategory,mUnit,mTeluguName,mPrice,mQuantity,mOffer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        relativeLayout=findViewById(R.id.add_item_rel_layout);

        //linking the id's with actual form elements.


        //spinners
        Category=findViewById(R.id.item_category);
        Unit=findViewById(R.id.unit);
        //EditText boxes
        EnglishItemName=findViewById(R.id.item_name_english);
        TeluguItemName=findViewById(R.id.item_name_telugu);
        Price=findViewById(R.id.item_price);
        Quantity=findViewById(R.id.item_quantity);
        Offer=findViewById(R.id.item_offer);
        //CheckBoxes
        OfferStatus=findViewById(R.id.offer_status);
        TagStatus=findViewById(R.id.Tag_status);
        //TextViews
        textView=findViewById(R.id.set_item_image_text);
        //ImagesElements
        ChangeImage=findViewById(R.id.changeImage);
        takeImage=findViewById(R.id.item_image_add_button);
        Image=findViewById(R.id.item_image);



        //setting on click listeners


        takeImage.setOnClickListener(this);
        ChangeImage.setOnClickListener(this);


    }

    // initiation to send data

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.save){
            //show final dialog box...
            Log.e("clicked","save");
            checkInfoOfItem();
            return  true;

        }
        return super.onOptionsItemSelected(item);
    }


    private void checkInfoOfItem() {

        //if info valid... send info..

        // getting spinners data.
        mCategory=Category.getSelectedItem().toString();
        mUnit=Unit.getSelectedItem().toString();

        //getting EditText data.

        mEnglishName=EnglishItemName.getText().toString();
        mTeluguName=TeluguItemName.getText().toString();
        mPrice=Price.getText().toString();
        mQuantity=Quantity.getText().toString();
        mOffer=Offer.getText().toString();

        //getting checkboxes data.



        // validating data.

        if(mEnglishName.isEmpty() || mTeluguName.isEmpty() || !hasImage ){
            ErrorDialogBottomSheet errorDialogBottomSheet=new ErrorDialogBottomSheet();
            errorDialogBottomSheet.show(getSupportFragmentManager(),"ErrorDialog");
        }else {
            //submit info to the server

            processingDialog=new ProcessingDialog();
            processingDialog.show(getSupportFragmentManager(),"ProgressDialog");
            sendItemInfoToServer();
        }

        //else show error dialog...

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.item_image_add_button:
            case R.id.changeImage:
                ChooseImageDialog chooseImageDialog=new ChooseImageDialog();
                chooseImageDialog.show(getSupportFragmentManager(),"ChooseImageDialog");
                break;
        }

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            // Create the File where the photo should go
            photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("ex",ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(AddItemActivity.this,
                        getApplicationContext().getPackageName(),
                        photoFile);
                Log.e("uri",photoURI.toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
               // galleryAddPic();
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE ) {

            Log.e("From Camera","clicked");


            if(resultCode==RESULT_OK){
                hasImage=true;
                ChangeImage.setVisibility(View.VISIBLE);
                textView.setText(R.string.change);
                setPic();

            }
        }else  if(requestCode== PICK_IMAGE && resultCode==RESULT_OK && data!=null){


            getPicFromGallery(data);

            Log.e("pick image","clicked");

        }

    }

    private void getPicFromGallery(Intent data) {


        Uri ImageUri=data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 150,
                    150,true);
            hasImage=true;
            ChangeImage.setVisibility(View.VISIBLE);
            textView.setText(R.string.change);
            takeImage.setVisibility(View.GONE);
            Image.setVisibility(View.VISIBLE);
            Image.setImageBitmap(newBitmap);
            ImageString=getImageString(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }



    private String getImageString(Bitmap bitmap) {
       // Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream); //compress to which format you want.
        byte [] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }

    private void setPic() {


        Bitmap b = rotateBitmapOrientation();
        //resize bitmap...
         Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);

        ImageString=getImageString(bitmap);
        Bitmap newBitmap = Bitmap.createScaledBitmap(b, 150,
                150,true);
// Compress the image further
        takeImage.setVisibility(View.GONE);
        Image.setVisibility(View.VISIBLE);
        Image.setImageBitmap(newBitmap);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private Bitmap rotateBitmapOrientation() {
        // Create and configure BitmapFactory
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(currentPhotoPath, opts);
        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(currentPhotoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert exif != null;
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        // Return result
        return Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
    }


    private void sendItemInfoToServer(){
        Log.e("sending..","data");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.URL_ADD_ITEM_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("response",response);
                        if(response.toLowerCase().equals("true")){
                            processingDialog.dismiss();
                            setResult(REFRESH);
                            finish();

                        }else {
                            processingDialog.dismiss();
                            showSnackBar("Network Connection error");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        processingDialog.dismiss();
                        showSnackBar("Network Connection error");
                      //  Log.e("error",error.toString());
                    }
                }){

            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params=new HashMap<>();

                params.put("E_NAME",mEnglishName);
                params.put("T_NAME",mTeluguName);


               /* params.put("ITEM_NAME",ItemName);
                params.put("ITEM_TYPE",ItemType);
                params.put("ITEM_CATEGORY",ItemCategory);
                params.put("ITEM_PRICE",ItemPrice);
                params.put("REGULAR",String.valueOf(0));
                params.put("IMAGE",ImageString);
                params.put("SUB_CATEGORY",ItemSubCategory);*/
                return params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void imageSelection(int Option) {
        if(Option==REQUEST_IMAGE_CAPTURE){

            dispatchTakePictureIntent();
        }else {
            galleryImage();
        }

    }

    private void galleryImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    private void showSnackBar(String message){
        Snackbar snackbar;
        snackbar=Snackbar.make(relativeLayout,message,Snackbar.LENGTH_LONG);
        snackbar.show();
    }

  /*  interface RefreshDashBoard{
        void refreshDashBoard();
    }


    private void hideDialog() {

        if(alertDialog!=null){
            alertDialog.dismiss();
            alertDialog=null;
        }
    }

    private void showNetworkError() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Check Your Internet Connection !");
        alertDialog= alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }*/
}

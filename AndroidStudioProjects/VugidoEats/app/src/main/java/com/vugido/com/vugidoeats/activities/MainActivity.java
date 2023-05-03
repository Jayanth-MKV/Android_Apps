package com.vugido.com.vugidoeats.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
import com.google.zxing.Result;
import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.WebActivity;
import com.vugido.com.vugidoeats.app_config.MyPreferences;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private static final int ON_GOING = 1;
//    private PreviewView previewView;
//    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    private Button qrCodeFoundButton;
    private ZXingScannerView zXingScannerView;
    private String qrCode;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        previewView = findViewById(R.id.activity_main_previewView);

        //zXingScannerView=new ZXingScannerView(this);
        setContentView(R.layout.activity_main);
        lottieAnimationView=findViewById(R.id.scanline);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);

        new MyPreferences(this).setCartProducts(null);
        new MyPreferences(this).setCartCount(0);
        //set also table number and cid..
//        new MyPreferences(this).setCID(0);
//        new MyPreferences(this).setTableNumber(0);

        zXingScannerView = new ZXingScannerView(this) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return new CustomViewFinderView(context);
            }
        };
        contentFrame.addView(zXingScannerView);

        lottieAnimationView.setVisibility(View.VISIBLE);



//        qrCodeFoundButton = findViewById(R.id.activity_main_qrCodeFoundButton);
//        qrCodeFoundButton.setVisibility(View.INVISIBLE);
//        qrCodeFoundButton.setOnClickListener(v -> {
//
//
//        });
//
//        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
      //  requestCamera();
    }

    private void requestCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startQrCodeScanner();
           // startCamera();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }

    private void startQrCodeScanner() {

        if(zXingScannerView!=null){

            zXingScannerView.setResultHandler(this);
            zXingScannerView.startCamera();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingScannerView.stopCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startQrCodeScanner();
               // startCamera();
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        lottieAnimationView.setVisibility(View.GONE);
        setResult(RESULT_OK);
        String result=rawResult.getText();
       // Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        Intent intent=new Intent(MainActivity.this, MenuActivity.class);
        intent.putExtra("URL",result);
        startActivityForResult(intent,ON_GOING);
        //load this url in the activity..

    }
//    private void startCamera() {
//
//
//        cameraProviderFuture.addListener(() -> {
//            try {
//                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//                bindCameraPreview(cameraProvider);
//            } catch (ExecutionException | InterruptedException e) {
//                Toast.makeText(this, "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }, ContextCompat.getMainExecutor(this));
//    }
//
////    private void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
////
////
////        previewView.setImplementationMode(PreviewView.ImplementationMode.PERFORMANCE);
////
////        Preview preview = new Preview.Builder()
////                .build();
////
////        CameraSelector cameraSelector = new CameraSelector.Builder()
////                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
////                .build();
////
////        preview.setSurfaceProvider(previewView.getSurfaceProvider());
////
////        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview);
////    }
//
//    private void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
//        previewView.setPreferredImplementationMode(PreviewView.ImplementationMode.SURFACE_VIEW);
//
//        Preview preview = new Preview.Builder()
//                .build();
//
//        CameraSelector cameraSelector = new CameraSelector.Builder()
//                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//                .build();
//
//        preview.setSurfaceProvider(previewView.createSurfaceProvider());
//
//        ImageAnalysis imageAnalysis =
//                new ImageAnalysis.Builder()
//                        .setTargetResolution(new Size(1280, 720))
//                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//                        .build();
//
//        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new QRCodeImageAnalyzer(new QRCodeImageAnalyzer.QRCodeFoundListener() {
//            @Override
//            public void onQRCodeFound(String _qrCode) {
//                qrCode = _qrCode;
//                qrCodeFoundButton.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void qrCodeNotFound() {
//                qrCodeFoundButton.setVisibility(View.INVISIBLE);
//
//            }
//        }));
//
//
//        cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageAnalysis, preview);
//    }
//


    private static class CustomViewFinderView extends ViewFinderView {
        public static final String TRADE_MARK_TEXT = "";
        public static final int TRADE_MARK_TEXT_SIZE_SP = 18;
        public final Paint PAINT = new Paint();

        public CustomViewFinderView(Context context) {
            super(context);
            init();
        }

        public CustomViewFinderView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            PAINT.setColor(Color.WHITE);
            PAINT.setAntiAlias(true);
            float textPixelSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    TRADE_MARK_TEXT_SIZE_SP, getResources().getDisplayMetrics());
            PAINT.setTextSize(textPixelSize);

            setSquareViewFinder(true);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawTradeMark(canvas);
        }

        private void drawTradeMark(Canvas canvas) {
            Rect framingRect = getFramingRect();
            float tradeMarkTop;
            float tradeMarkLeft;
            if (framingRect != null) {
                tradeMarkTop = framingRect.bottom + PAINT.getTextSize() + 10;
                tradeMarkLeft = framingRect.left;
            } else {
                tradeMarkTop = 10;
                tradeMarkLeft = canvas.getHeight() - PAINT.getTextSize() - 10;
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, PAINT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==ON_GOING && resultCode== RESULT_OK)
        {
            setResult(RESULT_OK);
            finish();
        }
    }
}
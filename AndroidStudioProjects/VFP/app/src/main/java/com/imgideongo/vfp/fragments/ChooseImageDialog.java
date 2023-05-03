package com.imgideongo.vfp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.imgideongo.vfp.R;


public class ChooseImageDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    public static final int PICK_IMAGE =10 ;
    public static final int REQUEST_IMAGE_CAPTURE = 11;
    TextView ExistingImage;
    TextView FromCameraImage;
    Context context;
    ImageSelection imageSelection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        imageSelection= (ImageSelection) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.choose_image,container,false);
        ExistingImage=v.findViewById(R.id.fromGallery);
        FromCameraImage=v.findViewById(R.id.fromCamera);
        ExistingImage.setOnClickListener(this);
        FromCameraImage.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fromGallery){
            imageSelection.imageSelection(PICK_IMAGE);
            dismiss();
        }else {
            imageSelection.imageSelection(REQUEST_IMAGE_CAPTURE);
            dismiss();
        }
    }

    public interface ImageSelection{
        void imageSelection(int Option);
    }
}

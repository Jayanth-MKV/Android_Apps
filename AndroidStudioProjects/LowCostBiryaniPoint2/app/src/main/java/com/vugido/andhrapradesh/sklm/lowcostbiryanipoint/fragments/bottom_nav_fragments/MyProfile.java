package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.TermsAndConditionsPrivacyPolicy;
import com.foodhub.vugido.app_config_variables.ConfigVariables;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class MyProfile extends Fragment implements View.OnClickListener {

    private TextView ShareApp,RateUs,Tc,PP,ABOUT_US,UserName,UserPhone;
    private Context context;
    private FloatingActionButton UserSettings;
    private FragmentActivity activity;
    private MyDialogLoader myDialogLoader;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_profile, container, false);

        ShareApp=view.findViewById(R.id.textView24);
        RateUs=view.findViewById(R.id.textView23);
        Tc=view.findViewById(R.id.tc_pp_option);
        PP=view.findViewById(R.id.pp_option);
        ABOUT_US=view.findViewById(R.id.about_us);
        UserName=view.findViewById(R.id.user_name);
        UserPhone=view.findViewById(R.id.user_phone);
        UserSettings=view.findViewById(R.id.floatingActionButton);




        ABOUT_US.setOnClickListener(this);
        PP.setOnClickListener(this);
        RateUs.setOnClickListener(this);
        ShareApp.setOnClickListener(this);
        Tc.setOnClickListener(this);
        UserSettings.setOnClickListener(this);

        UserName.setText(new MyPreferences(context).getUserName());
        UserPhone.setText(String.format("+91 %s", new MyPreferences(context).getUserMobile()));

        return view;
    }

    @Override
    public void onClick(View v) {



        switch (v.getId()){
            // share app
            case R.id.textView24:
                createDynamicLink();
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "\nMade By Srikakulam Government Polytechnic Students \n");
//                intent.putExtra(Intent.EXTRA_TEXT, "\nBuy Online Groceries, Vegetables, Fruits, Non-veg, Foods , Dr-Fruits and  many more : " + "\n\n" + );
//                startActivity(Intent.createChooser(intent,"\nShare Vugido Food-Hub\n"));
//                intent.putExtra(Intent.EXTRA_TEXT, "Hi! Install App Vugido Food-Hub, Made In Srikakulam By Government Polytechnic Students Srikakulam\nBuy Vegetables, Groceries, Foods, Snacks, Fruits, and many more :\n"+"App Download Link \n "+System.lineSeparator()+ ConfigVariables.MY_APP_URL);
//                startActivity(intent);
                break;
             // Rate Us
            case R.id.textView23:

                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
                break;

            case R.id.tc_pp_option:
                Intent i1=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
                i1.putExtra("ID",1);
                startActivity(i1);
                break;
            case R.id.pp_option:
                Intent i2=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
                i2.putExtra("ID",2);
                startActivity(i2);
                break;

            case R.id.about_us:
                Intent i3=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
                i3.putExtra("ID",3);
                startActivity(i3);
                break;
            case R.id.floatingActionButton:

                break;

        }
    }

    private void createDynamicLink(){

        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("http://www.vugido.com/"))
                .setDomainUriPrefix("https://vugido.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                .buildDynamicLink();

        Uri dynamicLinkUri = dynamicLink.getUri();

        //   Log.e("Long DLink",dynamicLinkUri.toString());

        shortenLongDynamicLink(dynamicLink.getUri());
    }

    private void shortenLongDynamicLink(Uri longLink){

        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Generating Share Link");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(activity.getSupportFragmentManager(), "DL");
        String manualLink="https://vugido.page.link/?"+
                "link="+"http://www.vugido.com"+
                "&apn="+context.getPackageName()+
                "&st="+"Install Vugido Food-Hub App"+
                "&sd="+"Buy Groceries, Vegetables,Tiffins ,Groceries, Foods, Snacks, Fruits, and many more."+
                "&si="+"http://www.vugido.com/vugido_logo.jpeg";

        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(Uri.parse(manualLink)) // automatic long link..
                //manually
                .buildShortDynamicLink()
                .addOnCompleteListener(activity, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();

                            assert shortLink != null;
//                            Log.e("Short Link",shortLink.toString());
//                            Log.e("flowchartLink",flowchartLink.toString());
                            if(myDialogLoader!=null)
                                myDialogLoader.dismiss();


                            Intent intent=new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "\nMade By Srikakulam Government Polytechnic Students \n");
                            intent.putExtra(Intent.EXTRA_TEXT, "\nMade By Srikakulam Government Polytechnic Students \nBuy Vegetables, Fruits, Non-veg, Foods , Dr-Fruits and  many more : " + "\n\n" + shortLink.toString());
                            startActivity(Intent.createChooser(intent,"\nShare Vugido Food-Hub\n"));
                        } else {
                            // Error
                            // ...
                            if(myDialogLoader!=null)
                                myDialogLoader.dismiss();

                            Log.e("Error","Short link not got");
                        }
                    }
                });
    }
}

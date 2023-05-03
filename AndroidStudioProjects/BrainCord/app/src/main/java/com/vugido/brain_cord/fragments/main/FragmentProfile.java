package com.vugido.brain_cord.fragments.main;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vugido.brain_cord.R;
import com.vugido.brain_cord.app.MyPreferences;

public class FragmentProfile  extends Fragment implements View.OnClickListener {


    private Context context;
    TextView r,sApp,aUS,pp,tc_pp_option,feed,p;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        r=view.findViewById(R.id.textView23);
        sApp=view.findViewById(R.id.textView24);
        aUS=view.findViewById(R.id.about_us);
        pp=view.findViewById(R.id.pp_option);
        tc_pp_option=view.findViewById(R.id.tc_pp_option);
//        un=view.findViewById(R.id.user_name);
//        up=view.findViewById(R.id.user_phone);
        feed=view.findViewById(R.id.feedback);
        p=view.findViewById(R.id.textView16);
//        link=view.findViewById(R.id.textView161);


//        un.setText(new MyPreferences(context).getUserName());
//        up.setText(new MyPreferences(context).getUserMobile());

        p.setOnClickListener(this);

        feed.setOnClickListener(this);
        r.setOnClickListener(this);
        sApp.setOnClickListener(this);
        aUS.setOnClickListener(this);
        pp.setOnClickListener(this);
        tc_pp_option.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

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
                            Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
                break;
            case R.id.textView24:

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "\nMade In India \n");
                intent.putExtra(Intent.EXTRA_TEXT, "\n : " + "\n\n" );
                startActivity(Intent.createChooser(intent,"\nShare Brain-Cord App\n"));
                intent.putExtra(Intent.EXTRA_TEXT, "Hi! Install App Brain-Cord, Made In India \n  :\n"+"App Download Link \n "+"https://play.google.com/store/apps/details?id=" + context.getPackageName());
                startActivity(intent);
                break;

            case R.id.about_us:

//                Intent i3=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
//                i3.putExtra("ID",3);
//                startActivity(i3);
                break;
            case R.id.pp_option:

//                Intent i2=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
//                i2.putExtra("ID",2);
//                startActivity(i2);

                break;
            case  R.id.tc_pp_option:
//                Intent i1=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
//                i1.putExtra("ID",1);
//                startActivity(i1);
                break;
            case R.id.feedback:
                String mailto = "homeservices.sklm@gmail.com" +
                        //      "?cc=" + "alice@example.com" +
                        "&subject=" + Uri.encode("Dear Home Services Team") +
                        "&body=" + Uri.encode("your message");

                Intent emailIntent = new Intent();

                emailIntent.setAction(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.setType("text/plain");
                // emailIntent.setData(Uri.parse("homeservices.sklm@gmail.com"));
                // emailIntent.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");

                emailIntent.putExtra(Intent.EXTRA_BCC,new String[]{"imgideongo@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_CC,new String[]{"imgideongo@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"From : "+new MyPreferences(context).getUserName()+",\n Dear Brain-Cord Team");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"your message here..");
                //emailIntent.putExtra(Intent.)
                //

//                emailIntent.setData(Uri.parse(mailto));

                try {
                    startActivity(Intent.createChooser(emailIntent,"Choose Gmail App"));
                } catch (ActivityNotFoundException e) {
                    //TODO: Handle case where no email app is available
                    Toast.makeText(context,"no avail",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.textView16:

                //myDialogBox();
                break;



        }
    }
}

package com.vugido.homeservices.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.homeservices.R;
import com.vugido.homeservices.activities.TermsAndConditionsPrivacyPolicy;
import com.vugido.homeservices.app.ConfigVariables;
import com.vugido.homeservices.app.MyPreferences;

import java.util.ArrayList;
import java.util.List;

public class MyAccountFragment extends Fragment implements View.OnClickListener {


    private Context context;
    TextView r,sApp,aUS,pp,tc_pp_option,un,up,feed,p,link;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        r=view.findViewById(R.id.textView23);
        sApp=view.findViewById(R.id.textView24);
        aUS=view.findViewById(R.id.about_us);
        pp=view.findViewById(R.id.pp_option);
        tc_pp_option=view.findViewById(R.id.tc_pp_option);
        un=view.findViewById(R.id.user_name);
        up=view.findViewById(R.id.user_phone);
        feed=view.findViewById(R.id.feedback);
        p=view.findViewById(R.id.textView16);
        link=view.findViewById(R.id.textView161);

        link.setOnClickListener(this);

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    private void setAccountOptions() {

//        List<AccountOptionsModel> accountOptionsModelList=new ArrayList<>();
//
//        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.loc,"Manage Addresses","View your saved addresses",1));
//        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.msg,"Chat Helpline","Chat with us here",2));
//        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.food,"Food Preferences","Setting for your foods tastes ",3));
//        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.his,"Order History","View your orders",4));
//        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.refer,"Refer & Earn","Earn money by referring friends",5));
//        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.faq,"Help & FAQs","Need Help , contact us here",6));
//
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
//
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        AccountAdapter accountAdapter=new AccountAdapter(accountOptionsModelList,context);
//
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setAdapter(accountAdapter);





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
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
                break;
            case R.id.textView24:

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "\nMade In Srikakulam \n");
                intent.putExtra(Intent.EXTRA_TEXT, "\nBook services like home shifting,electrical repairs,interior designing..etc many more ONLINE : " + "\n\n" );
                startActivity(Intent.createChooser(intent,"\nShare Home Services App\n"));
                intent.putExtra(Intent.EXTRA_TEXT, "Hi! Install App Home Services, Made In Srikakulam \nWe provide services like home shifting,electrical repairs,interior designing..etc many more  :\n"+"App Download Link \n "+System.lineSeparator()+ ConfigVariables.MY_APP_URL);
                startActivity(intent);
                break;

            case R.id.about_us:

                Intent i3=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
                i3.putExtra("ID",3);
                startActivity(i3);
                break;
            case R.id.pp_option:

                Intent i2=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
                i2.putExtra("ID",2);
                startActivity(i2);

                break;
            case  R.id.tc_pp_option:
                Intent i1=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
                i1.putExtra("ID",1);
                startActivity(i1);
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

                emailIntent.putExtra(Intent.EXTRA_BCC,new String[]{"homeservices.sklm@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_CC,new String[]{"homeservices.sklm@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"From : "+new MyPreferences(context).getUserName()+",\n Dear Home Services Team");
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

                myDialogBox();
                break;

            case R.id.textView161:
                //whatsapp us
                try {
                    String text = "Dear Home Services Team:\nI want to link my business with your app!\n";// Replace with your message.

                    String toNumber = "918096171791"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;

        }
    }

    private void myDialogBox() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout_add_sub_cat, null);

        final TextView n =  dialogView.findViewById(R.id.textView);
        final TextView p = dialogView.findViewById(R.id.textView23);
        final TextView e =  dialogView.findViewById(R.id.textView24);
//        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
//        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogBuilder.dismiss();
//            }
//        });
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // DO SOMETHINGS
//
//                // check first for valid name...
//
//
//                // get the text and send to server...
//                String CategoryName= editText.getText().toString();
//
//
//
//
//
//
//            }
//        });


        n.setText(new MyPreferences(context).getUserName());
        p.setText(new MyPreferences(context).getUserMobile());
        if (new MyPreferences(context).getGmail()==null)
            e.setVisibility(View.GONE);
        else
            e.setText(new MyPreferences(context).getGmail());


        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

}

package com.vugido.online_groceries.fragments.bottom_sheet;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.activities.ProfileActivity;
import com.vugido.online_groceries.activities.TermsAndConditionsPrivacyPolicy;
import com.vugido.online_groceries.adapters.homepage.HomeMenuBottomSheetAdapter;
import com.vugido.online_groceries.models.BOTTOM_HOME_MODEL.HomeMenuBottomModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeOptionsBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener, HomeMenuBottomSheetAdapter.OPTION_SELECTED {


    private RecyclerView recyclerView;
    private List<HomeMenuBottomModel> homeMenuBottomModelList;
    private Context context;
    private FragmentActivity fragmentActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        fragmentActivity=getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_bottom_sheet,container,false);

        recyclerView=view.findViewById(R.id.BottomHomeRecyclerView);
        homeMenuBottomModelList=new ArrayList<>();
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.profile,"Profile",1));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.link,"Link your business",2));

        //homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.history,"Orders History",2));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.whatsapp_icon,"WhatsApp Us",3));
       // homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.call_us,"Call Helpline",4));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.share,"Share Our App",5));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.rate,"Rate Our App",6));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.tt,"Terms & Conditions",7));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.privacy,"Privacy Policy",8));
     //   homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.lo,"About Us",9));
       // homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.feedback,"Email FeedBack",10));



        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeMenuBottomSheetAdapter homeMenuBottomSheetAdapter=new HomeMenuBottomSheetAdapter(context,homeMenuBottomModelList,this);
        recyclerView.setAdapter(homeMenuBottomSheetAdapter);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


        }
    }

    @Override
    public void optionSelected(int id) {

        //initiate the task... of option selected....
        switch (id){
            case 1:
                //profile
                startActivity(new Intent(context, ProfileActivity.class));
                break;
            case 2:
                try {
                    String text = "Dear Team: I wanna link my business with you ..\n";// Replace with your message.

                    String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;


            case 3:
                //whatsapp us
                try {
                    String text = "Dear Team:\n";// Replace with your message.

                    String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 4:
                //helpline
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:9346834541"));
                startActivity(callIntent);
                break;
            case 5:
                //share app
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "\nInstall App Online Groceries App\n");
                intent.putExtra(Intent.EXTRA_TEXT, "\nInstall App Online Groceries \nOrder Food,Vegetables,Groceries,Fruits,etc  Online\nClick On Link To Install" + "\n\n" + "https://play.google.com/store/apps/details?id=" + context.getPackageName());
                startActivity(Intent.createChooser(intent,"\nShare App With Friends\n"));
                startActivity(intent);
                break;
            case 6:
                //rate us
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
            case 7:
                //tc
                Intent i1=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
                i1.putExtra("ID",1);
                startActivity(i1);
                break;
            case 8:
                //pp
                Intent i2=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
                i2.putExtra("ID",2);
                startActivity(i2);
                break;
            case 9 :
                //about us
                Intent i3=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
                i3.putExtra("ID",3);
                startActivity(i3);
                break;
            case 10 :
                //email feedback
                break;

        }
        dismiss();

    }


    private void restartApplication() {

        Intent i = fragmentActivity.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(fragmentActivity.getBaseContext().getPackageName() );
        assert i != null;
        //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


}

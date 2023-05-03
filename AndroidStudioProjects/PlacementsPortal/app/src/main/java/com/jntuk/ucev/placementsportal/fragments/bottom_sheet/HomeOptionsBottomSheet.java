package com.jntuk.ucev.placementsportal.fragments.bottom_sheet;

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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.activities.ProfessorsActivity;
import com.jntuk.ucev.placementsportal.activities.QuickActionsActivity;
import com.jntuk.ucev.placementsportal.adapters.home.HomeMenuBottomSheetAdapter;
import com.jntuk.ucev.placementsportal.app_config.MyPreferences;
import com.jntuk.ucev.placementsportal.models.BOTTOM_HOME_MODEL.HomeMenuBottomModel;

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
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.person,"Account",1));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.professors,"Faculty Members",2));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.preferences_settings,"Manage Preferences",3));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.quick_actions,"Quick Actions",4));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.share,"Share Our App",5));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.rate,"Rate Our App",6));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.feedback,"Email FeedBack",10));
        homeMenuBottomModelList.add(new HomeMenuBottomModel(R.drawable.logout,"Logout",11));





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
                break;
            case 2:
                startActivity(new Intent(context, ProfessorsActivity.class));
                break;
            case 3:
                //preferences
                break;
            case 4:
                startActivity(new Intent(context, QuickActionsActivity.class));
                break;
            case 5:
                //share app
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "\nInstall App Placements Portal\n");
                intent.putExtra(Intent.EXTRA_TEXT, "\nInstall App Placements Portal \nDeveloped By JNTUK-UCEV \nCSE Department Students" + "\n\n" +
                        "http://play.google.com/store/apps/details?id=" + context.getPackageName());
                startActivity(Intent.createChooser(intent,"\nShare App With Friends\n"));
                //startActivity(intent);
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
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
                break;
//            case 7:
//                //tc
//                Intent i1=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
//                i1.putExtra("ID",1);
//                startActivity(i1);
//                break;
//            case 8:
//                //pp
//                Intent i2=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
//                i2.putExtra("ID",2);
//                startActivity(i2);
//                 break;
//            case 9 :
//                //about us
//                Intent i3=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
//                i3.putExtra("ID",3);
//                startActivity(i3);
//                break;
            case 10 :
                //email feedback
                break;
            case 11:
                //logout
                new MyPreferences(context).setVerified(false);
                restartApplication();
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

package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.Delivery_Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.SlotActivity;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.Location.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Get_Address extends Fragment implements View.OnClickListener {


    private Context context;
    private MaterialButton FormSubmitButton;
    private TextInputEditText firstName,Office,address1,active_phone,zip,state;
    FragmentActivity fragmentActivity;
    String CompleteAddress  ;
    String Locality,State ;
    String Country      ;
    String Zip;
    Double LAT;
    Double LON;
    private ShowProgress showProgress;
    public Fragment_Get_Address() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        showProgress= (ShowProgress) getActivity();
        fragmentActivity=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment__get__address, container, false);


        //get all form ids to views..

        firstName=view.findViewById(R.id.firstName);
        Office=view.findViewById(R.id.office);
        address1=view.findViewById(R.id.address1);
        active_phone=view.findViewById(R.id.active_phone);
        zip=view.findViewById(R.id.zip);
        state=view.findViewById(R.id.state);

        FormSubmitButton=view.findViewById(R.id.LocationFormSubmit);
        FormSubmitButton.setOnClickListener(this);


        // get bundle.. data

        Bundle bundle=getArguments();

        assert bundle != null;
        CompleteAddress=bundle.getString("CA");
        Locality=bundle.getString("L");
        Country=bundle.getString("C");
        Zip=bundle.getString("Z");
        LAT=bundle.getDouble("LAT");
        LON=bundle.getDouble("LON");
        State=bundle.getString("S");

        setFormData();

        return view;
    }

    private void setFormData() {

        zip.setText(Zip);
        state.setText(String.format("%s,%s", Country, State));
        address1.setText(Locality);

        Log.e("address",CompleteAddress);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.LocationFormSubmit:


                //check all fields..
                boolean status=checkInputFields();

              /*  if(status){

                    // all ok
                    // send all info to the server...




                }else {

                    // not ok..
                }*/


                break;

        }

    }



    private boolean checkInputFields() {
    String FN,DN,AN,ACT_P,ZIP,CS;
    FN= Objects.requireNonNull(firstName.getText()).toString();
   // LN= Objects.requireNonNull(Lastname.getText()).toString();
    DN= Objects.requireNonNull(Office.getText()).toString();
    AN= Objects.requireNonNull(address1.getText()).toString();
    ACT_P= Objects.requireNonNull(active_phone.getText()).toString();
   // ALT_P= Objects.requireNonNull(alternate_phone.getText()).toString();
    ZIP= Objects.requireNonNull(zip.getText()).toString();
    CS= Objects.requireNonNull(state.getText()).toString();


    if(FN.equals("") || FN.isEmpty() || !checkName(FN)){

        Toast.makeText(context,"Check Your FirstName",Toast.LENGTH_LONG).show();
        return false;


    }

        if(DN.equals("") || DN.isEmpty()){

            Toast.makeText(context,"Check Your Door No/ Office",Toast.LENGTH_LONG).show();

            return false;


        }
        if(ACT_P.equals("") || ACT_P.isEmpty() || !checkNumber(ACT_P)){

            Toast.makeText(context,"Check Your Active Phone",Toast.LENGTH_LONG).show();

            return false;

        }
        if(AN.equals("") || AN.isEmpty()){

            Toast.makeText(context,"Check Your Area /Street",Toast.LENGTH_LONG).show();

            return false;

        }



        //here send or save all info..
        sendNewAddress2Server(FN,DN,AN,ACT_P,ZIP,CS);


        return true;


    }

    public interface ShowProgress{

        void showProgress(boolean show);
    }

    private void sendNewAddress2Server(String fn, String dn, String an, String act_p,String zip, String cs) {

        showProgress.showProgress(true);
        // start progress bar..
        Map<String, String> map=new HashMap<>();
        map.put("UID", new MyPreferences(context).getUID());// user id
        map.put("FN",fn);
        map.put("DN",dn);
        map.put("AN",an);
        map.put("ACT_P",act_p);
        map.put("ZIP",zip);
        map.put("CS",cs);
        map.put("COMPLETE_ADDRESS",CompleteAddress);
        map.put("LAT",String.valueOf(LAT));
        map.put("LON",String.valueOf(LON));




        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(context).getBaseLocationURL()).addNewAddress(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){


                        // success
                        Intent intent=new Intent(context,SlotActivity.class);
                        fragmentActivity.startActivityForResult(intent,ORDER_PLACED_CODE);

                    }else{

                        // service not available or error
                        Toast.makeText(context,"Service not available in your Area!",Toast.LENGTH_LONG).show();
                    }
                }
                showProgress.showProgress(false);
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                Toast.makeText(context,"Network Error. Try Again !",Toast.LENGTH_SHORT).show();
                showProgress.showProgress(false);
            }
        });

    }


    private boolean checkName(String username) {
        Pattern pattern= Pattern.compile("^[\\p{L} .'-]+$");
        Matcher matcher= pattern.matcher(username);

        if(matcher.find() && matcher.group().equals(username)){
            return  true;
        }
        else {

            return  false;
        }


    }

    private boolean checkNumber(String number) {


        Pattern pattern = Pattern.compile("[6-9][0-9]{9}");
        Matcher matcher = pattern.matcher(number);

        if(matcher.find() && matcher.group().equals(number)){
            return  true;
        }
        else {

            return  false;
        }

    }
}

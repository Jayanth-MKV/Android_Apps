package com.vugido.com.vugidoeats.fragments.login_fragments;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsClient;
import com.google.android.gms.auth.api.credentials.CredentialsOptions;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.activities.TermsAndConditionsPrivacyPolicy;
import com.vugido.com.vugidoeats.activities.VerifyActivity;
import com.vugido.com.vugidoeats.app_config.MyPreferences;
import com.vugido.com.vugidoeats.models.login_sign_up_response.Response;
import com.vugido.com.vugidoeats.retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;

public class SignUpFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, CompoundButton.OnCheckedChangeListener {

     private CheckBox checkBox;
     private static boolean FLAG= false;
    private Activity activity;
    TextView TC, PP;
    private static final int RESOLVE_HINT = 10;
    private TextView Login;
    private Context context;
    private LoginFragmentInterface loginFragmentInterface;
    private Button button;
    private TextInputEditText Phone,UserName;
    TextInputLayout textInputLayout;
    private static final int LA_CODE = 100;
    private BottomLoader bottomLoader;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
        bottomLoader= (BottomLoader) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        Login=view.findViewById(R.id.login);
        UserName=view.findViewById(R.id.username);
        textInputLayout=view.findViewById(R.id.student_mobile);
        Phone=view.findViewById(R.id.mobile);
        Login.setOnClickListener(this);
        button=view.findViewById(R.id.button);
        button.setEnabled(false);
        button.setOnClickListener(this);

         checkBox=view.findViewById(R.id.checkBox_login);
        TC=view.findViewById(R.id.tc_link);
        PP=view.findViewById(R.id.pp_link);
        checkBox.setOnCheckedChangeListener(this);
        TC.setOnClickListener(this);
        PP.setOnClickListener(this);

        Phone.setOnFocusChangeListener(this);
        textInputLayout.setOnClickListener(this);
        Phone.setOnClickListener(this);

        String text = "<font color=#cc0029>Already have account? </font> <font color=#ffcc00>Login</font>";
        Login.setText(Html.fromHtml(text));
        return view;
    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.login){

            loginFragmentInterface= (LoginFragmentInterface) context;
            loginFragmentInterface.loginFragment();
        }else if(view.getId()==R.id.button){

            // check for user name and phone... if valid then proceed..
            String name= Objects.requireNonNull(UserName.getText()).toString();
            String phone= Objects.requireNonNull(Phone.getText()).toString();

            if(checkName(name) && checkNumber(phone)){
                // ok valid
                button.setEnabled(false);

                // send details to the server..
                signUp(name,phone);


            }else {

                button.setEnabled(true);
                // not valid..
                Toast.makeText(context,"Enter Valid Information",Toast.LENGTH_LONG).show();
            }

        }else if(view.getId()==R.id.student_mobile || view.getId()==R.id.mobile){

            if(!FLAG)
                requestHint();
        }else if(view.getId()==R.id.tc_link){

        Intent i1=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
        i1.putExtra("ID",1);
        startActivity(i1);
    }else if(view.getId()==R.id.pp_link){
        Intent i2=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
        i2.putExtra("ID",2);
        startActivity(i2);

    }

    }

    private void signUp(final String name, final String phone) {

        Map<String,Object> map=new HashMap<>();
        map.put("MOBILE",phone);
        map.put("USER_NAME",name);

        bottomLoader.bottomLoader(true,"Signing Up");
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").signUpUrl(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                button.setEnabled(true);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    Log.e("response",response.toString());
                    if(!response.body().isERROR()){

                        // Created User In DB...
                        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
                        // sign_up attempt..
                        new MyPreferences(context).isSignUp(true);
                        bottomLoader.bottomLoader(false,"");
                        Intent intent=new Intent(context, VerifyActivity.class);
                        intent.putExtra("ID",response.body().getID());
                        intent.putExtra("LS",2);
                        intent.putExtra("MOBILE",phone);
                        intent.putExtra("NAME",name);
                        startActivityForResult(intent,LA_CODE);
                    }else{

                        // Error in User Creation..
                        Toast.makeText(context,"You might already have account\nLogin",Toast.LENGTH_LONG).show();
                        bottomLoader.bottomLoader(false,"");
                    }
                }else {
                    Toast.makeText(context,"You might already have account\nLogin",Toast.LENGTH_LONG).show();

                    // error
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                bottomLoader.bottomLoader(false,"");
                button.setEnabled(true);
            }
        });

    }

    private boolean checkName(String username) {
        Pattern pattern= Pattern.compile("^[\\p{L} .'-]+$");
        Matcher matcher= pattern.matcher(username);

        if(matcher.find() && matcher.group().equals(username)){
            return  true;
        }
        else
            return false;



    }

    private boolean checkNumber(String number) {


        Pattern pattern = Pattern.compile("[6-9][0-9]{9}");
        Matcher matcher = pattern.matcher(number);

        if(matcher.find() && matcher.group().equals(number)){
            return  true;
        }
        else
            return false;


    }

    private void requestHint() {

        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();



        CredentialsOptions options = new CredentialsOptions.Builder()
                .forceEnableSaveDialog()
                .build();


        CredentialsClient mCredentialsApiClient = Credentials.getClient(context,options);
        PendingIntent intent=mCredentialsApiClient.getHintPickerIntent(hintRequest);


        try {
            startIntentSenderForResult(intent.getIntentSender(),
                    RESOLVE_HINT, null, 0, 0, 0,null);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESOLVE_HINT){
            Log.e("dialog","ok");
            if (resultCode == RESULT_OK) {
                assert data != null;
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                assert credential != null;

                String n=credential.getId();
                List<String> number=new ArrayList<>();
                char[] nn=n.toCharArray();
                int count=0;
                for(int i=nn.length-1;i>=0;i--){
                    count+=1;
                    number.add(String.valueOf(nn[i]));
                    if(count==10)
                        break;
                }

                Collections.reverse(number);
                StringBuilder stringBuilder=new StringBuilder();
                for(String c : number){
                    stringBuilder.append(c);
                }
                Toast.makeText(context,credential.getId(),Toast.LENGTH_LONG).show();
                // bind the data to the box..
                Phone.setText(stringBuilder.toString());
                // credential.getId();  <-- will need to process phone number string
            }else {
                FLAG=true;
            }
        }else if(requestCode==LA_CODE){

            if(resultCode==1){
                activity.finish();
            }/*else if(resultCode==100){
                // enable button..
                button.setEnabled(true);
            }*/
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        if(view.getId()==R.id.student_mobile || view.getId()==R.id.mobile){

            if(hasFocus&& !FLAG)
            requestHint();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

          if(buttonView.getId()==R.id.checkBox_login){

            if(buttonView.isChecked()){
                // enable button
                button.setEnabled(true);
            }else {
                button.setEnabled(false);
            }
        }
    }

    public interface LoginFragmentInterface{

        void loginFragment();


    }
    public interface BottomLoader{

        void bottomLoader(boolean state, String msg);
    }
}

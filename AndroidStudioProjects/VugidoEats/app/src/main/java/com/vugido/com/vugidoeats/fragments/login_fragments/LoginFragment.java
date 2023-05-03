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
import com.vugido.com.vugidoeats.R;
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
import static com.vugido.com.vugidoeats.activities.LoginActivity.CODE;


public class LoginFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {

    private static final int RESOLVE_HINT = 10;
    private static final int LA_CODE = 100;
    private static boolean FLAG = false;
    private Context context;
    private Button button;

    TextView SignUp;

    private String User,Mobile;
    private TextInputEditText P;
    private ShowProgress showProgress;
    private Activity activity;
    private SignUpFragmentInterface signUpFragmentInterface;
    private LoginLoader loginLoader;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
        showProgress= (ShowProgress) getActivity();
        signUpFragmentInterface= (SignUpFragmentInterface) context;
        loginLoader = (LoginLoader) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        button=view.findViewById(R.id.button);
       /* checkBox=view.findViewById(R.id.checkBox_login);
        TC=view.findViewById(R.id.tc_link);
        U=view.findViewById(R.id.user_name);*/
        P=view.findViewById(R.id.user_phone);
        //PP=view.findViewById(R.id.pp_link);
        button.setOnClickListener(this);
       /* checkBox.setOnCheckedChangeListener(this);*/
        SignUp=view.findViewById(R.id.SignUp);
        /*TC.setOnClickListener(this);
        PP.setOnClickListener(this);*/
        button.setEnabled(false);
        P.setOnClickListener(this);
        P.setOnFocusChangeListener(this);
        String text = "<font color=#cc0029>No account? </font> <font color=#ffcc00>Sign-Up Now</font>";
        SignUp.setText(Html.fromHtml(text));
        SignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

    if(view.getId()==R.id.button){


        // check the fields and pass the data....
      //  User= Objects.requireNonNull(U.getText()).toString();
        Mobile= Objects.requireNonNull(P.getText()).toString();

        boolean numberStatus=checkNumber(Mobile);
     //   boolean nameStatus=checkName(User);

        if(numberStatus){


            //send the otp to that number and launch the verify page..

            Toast.makeText(context, "Ok We can initiate OTP", Toast.LENGTH_SHORT).show();

            // send info to server...

            sendUserLoginInfo(Mobile);




        }else{

            Toast toast=  Toast.makeText(context,"Enter Valid Information",Toast.LENGTH_SHORT);
            toast.show();

        }



        // now check the entered details
          /*  Intent intent=new Intent(context, VerifyActivity.class);
            startActivity(intent);
*/
        }/*else if(view.getId()==R.id.tc_link){

        Intent i1=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
        i1.putExtra("ID",1);
        startActivity(i1);
    }else if(view.getId()==R.id.pp_link){
        Intent i2=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
        i2.putExtra("ID",2);
        startActivity(i2);

    }*/else if(view.getId()==R.id.SignUp){

        // launch sign up fragment..
        signUpFragmentInterface.signUpFragment();

    }else if(view.getId()==R.id.user_phone){

        if(!FLAG)
            requestHint();
    }

    }

    // Construct a request for phone numbers and show the picker
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







    private void sendUserLoginInfo(final String mobile) {


        // disable the login button and show progress bar..

        button.setEnabled(false);
        showProgress.showProgress(true);

        loginLoader.loginLoader(true,"Logging In");

        Map<String, Object> map=new HashMap<>();
        map.put("MOBILE",mobile);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").loginUrl(map);

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
                        loginLoader.loginLoader(false,"");
                        new MyPreferences(context).isSignUp(false);
                        Intent intent=new Intent(context, VerifyActivity.class);
                        intent.putExtra("ID",response.body().getID());
                        intent.putExtra("LS",1);
                        intent.putExtra("MOBILE",mobile);

                        startActivityForResult(intent,LA_CODE);

                    }else{

                        // Error in User Creation..
                        Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
                        loginLoader.loginLoader(false,"");
                    }
                }else {

                    // error
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                loginLoader.loginLoader(false,"");
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
     @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         Log.e("executed","fragment");

            if(requestCode==CODE ){

                Log.e("executed","fragment");
                if(resultCode==1){

                    Log.e("ok","ok");
                activity.finish();
                }else {

                    Log.e("not","ok");
                }

            }else if(requestCode==RESOLVE_HINT){
                Log.e("dialog","ok");
                    if (resultCode == RESULT_OK) {
                        assert data != null;
                        Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                        assert credential != null;
                        Log.e("credential",credential.getId());
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
                        P.setText(stringBuilder.toString());
                        button.setEnabled(true);

                    }else {
                        FLAG=true;
                        button.setEnabled(true);
                       // Toast.makeText(context,String.valueOf(resultCode),Toast.LENGTH_LONG).show();
                    }
                }else if(requestCode==LA_CODE){

                if(resultCode==1) {
                    activity.finish();
                }
//                }else if(resultCode==100){
//                    // enable button..
//                    button.setEnabled(true);
//                }
            }




    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

       /* if(buttonView.getId()==R.id.checkBox_login){

            if(buttonView.isChecked()){
                // enable button
                button.setEnabled(true);
            }else {
                button.setEnabled(false);
            }
        }*/

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(view.getId()==R.id.user_phone){

            if(hasFocus && !FLAG)
                requestHint();
        }
    }

    public interface ShowProgress{

        void showProgress(boolean show);
    }

    public interface SignUpFragmentInterface{

        void signUpFragment();

    }

    public interface LoginLoader{

        void loginLoader(boolean state, String msg);
    }

}

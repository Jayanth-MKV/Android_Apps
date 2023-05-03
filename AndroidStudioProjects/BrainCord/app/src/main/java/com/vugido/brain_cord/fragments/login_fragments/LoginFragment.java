package com.vugido.brain_cord.fragments.login_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.activities.MainActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;


public class LoginFragment extends Fragment implements View.OnClickListener {


    public static final int VA = 1000;
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
        P=view.findViewById(R.id.user_phone);
        button.setOnClickListener(this);
        SignUp=view.findViewById(R.id.SignUp);
        P.setOnClickListener(this);
        String text = "<font color=#cc0029>No account? </font> <font color=#ffcc00>Sign-Up Now</font>";
        SignUp.setText(Html.fromHtml(text));
        SignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

    if(view.getId()==R.id.button){


        // check the fields and pass the data....
        startActivity(new Intent(context, MainActivity.class));
//        Mobile= Objects.requireNonNull(P.getText()).toString();
//
//        boolean numberStatus=checkNumber(Mobile);
//
//        if(numberStatus){
//
//
//            //send the otp to that number and launch the verify page..
//
//
//            // send info to server...
//
//            sendUserLoginInfo(Mobile);
//
//
//
//
//        }else{
//
//            Toast toast=  Toast.makeText(context,"Enter Valid Information",Toast.LENGTH_SHORT);
//            toast.show();
//
//        }





        }else if(view.getId()==R.id.SignUp){

        // launch sign up fragment..
        signUpFragmentInterface.signUpFragment();

    }else if(view.getId()==R.id.user_phone){


    }

    }







    private void sendUserLoginInfo(final String mobile) {


        // disable the login button and show progress bar..

        button.setEnabled(false);
        showProgress.showProgress(true);

        loginLoader.loginLoader(true,"Logging In");

        Map<String, Object> map=new HashMap<>();
        map.put("MOBILE",mobile);





//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().userReg(map);
//
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(response.body().isUSEREXISTS()){
//                        Intent intent=new Intent(context, VerifyActivity.class);
//                        intent.putExtra("M",mobile);
//                        intent.putExtra("T",1);
//                        startActivityForResult(intent,VA);
//                    }else{
//                        signUpFragmentInterface.signUpFragment();
//                        Toast.makeText(context,"Please Sign-Up ! You don't have account",Toast.LENGTH_LONG).show();
//                    }
//
//                }
//
//                loginLoader.loginLoader(false,"");
//                button.setEnabled(true);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//                loginLoader.loginLoader(false,"");
//                button.setEnabled(true);
//
//            }
//        });






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

        if (requestCode==VA && resultCode == RESULT_OK){

            activity.setResult(RESULT_OK);
            activity.finish();

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

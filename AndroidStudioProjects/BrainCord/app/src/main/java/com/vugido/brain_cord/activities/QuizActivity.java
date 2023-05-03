package com.vugido.brain_cord.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.app.MyPreferences;
import com.vugido.brain_cord.dialogs.MyDialogLoader;
import com.vugido.brain_cord.fragments.quiz.FragmentImageBased;
import com.vugido.brain_cord.fragments.quiz.FragmentMCQMultiple;
import com.vugido.brain_cord.fragments.quiz.FragmentMCQSingle;
import com.vugido.brain_cord.fragments.quiz.FragmentParagraph;
import com.vugido.brain_cord.models.quiz.QIMG;
import com.vugido.brain_cord.models.quiz.QMCQM;
import com.vugido.brain_cord.models.quiz.QMCQS;
import com.vugido.brain_cord.models.quiz.QPARA;
import com.vugido.brain_cord.models.section.DATA;
import com.vugido.brain_cord.models.section.Response;
import com.vugido.brain_cord.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    int sq1=0;
    int[] qll={25,9,10};
    int[] cl={30,15,10};

//    String[] qids = {"6", "9", "11", "12", "13", "14", "15", "16", "17", "19", "21", "22", "23", "24", "26",
//            "27", "28", "29", "30", "31", "33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
//            "49","50","51","52","53","54","55","56"};



    int[] img={0,1,2,3,4,5,6,7,8,9};//done 1

    int[] mcqm={0,1,2,3,4};   //  4
    int[] mcqs={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14  //done
            ,15,16,17,18,19,20,21,22,23,24,25};  //3
    int[] para={0,1,2};//2

    String[] s1={"0:3","1:3","2:3","3:3","4:3","5:3","6:3","7:3","8:3","9:3","10:3","11:3","12:3","13:3","14:3","0:1","1:1","2:1","3:1","4:1","5:1","6:1","7:1","8:1","9:1"};

    String[] s2={"15:3","16:3","17:3","18:3","19:3","0:2","1:2","2:2","20:3"};

    String[] s3={"21:3","22:3","23:3","24:3","25:3","0:4","1:4","2:4","3:4","4:4"};










    private MyDialogLoader myDialogLoader;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    Toolbar toolbar;
    private CountDownTimer countDownTimer;
    private long min,rs;
    private int qnumber=0,ql=0,cqn=0;
    TextView time,q,sechead;
    Button nq;
    FragmentImageBased fragmentImageBased;
    FragmentParagraph fragmentParagraph;
    FragmentMCQMultiple fragmentMCQMultiple;
    FragmentMCQSingle fragmentMCQSingle;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qa);
        nq=findViewById(R.id.button4);
        q=findViewById(R.id.textView3);


        fragmentManager=getSupportFragmentManager();

        time=findViewById(R.id.textView);
        sechead=findViewById(R.id.textView4);


        fetchSecInfo();


        nq.setOnClickListener(this);

    }


    private void fetchQuestionInfo(String qnumber,int sid,int s,long sec,String ans) {
        loginLoader(true,"Getting Question");

        String[] ss=qnumber.split(":");

        if (ss[1].equals("3"))
            databaseReference=FirebaseDatabase.getInstance().getReference("MCQS").child(ss[0]);
        else if (ss[1].equals("1"))
            databaseReference=FirebaseDatabase.getInstance().getReference("Image Based").child(ss[0]);
        else if ((ss[1].equals("2")))
            databaseReference=FirebaseDatabase.getInstance().getReference("Paragraph based").child(ss[0]);
        else
            databaseReference=FirebaseDatabase.getInstance().getReference("MCQ Multiple Answers").child(ss[0]);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("Ques",snapshot.toString());
                loginLoader(false,"Getting Question");


                cqn+=1;
                //qnumber=data.getQID();
                //Toast.makeText(getApplicationContext(),String.valueOf(qnumber),Toast.LENGTH_LONG).show();
                bindQData(snapshot,ss[1],ss[0]);

                //sid=data.getSID();
                q.setText(cqn+"/"+ql);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("Ques",error.toString());
                loginLoader(false,"Getting Question");

            }
        });



//        // Toast.makeText(getApplicationContext(),String.valueOf(qnumber),Toast.LENGTH_LONG).show();
//        Map<String,Object> map=new HashMap<>();
//
//
//        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
//        map.put("SID",String.valueOf(sid));
//        map.put("QID",String.valueOf(qnumber));
//        map.put("RID",String.valueOf(45));
//        map.put("SS",String.valueOf(s));
//        map.put("SEC",String.valueOf(sec));
//        if (ans==null)
//            map.put("AS","-1");
//        else
//            map.put("AS",ans);
//
//        Call<com.vugido.brain_cord.models.quiz.Response>call=
//
//                RetrofitBuilder.getInstance().getRetrofit().getQuizInfo(map);
//
//     call.enqueue(new Callback<com.vugido.brain_cord.models.quiz.Response>() {
//            @Override
//            public void onResponse(Call<com.vugido.brain_cord.models.quiz.Response> call, retrofit2.Response<com.vugido.brain_cord.models.quiz.Response> response) {
//                loginLoader(false,"Getting Question");
//                //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
//
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(!response.body().isError()){
//
//
//
//                        bindQData(response.body().getDATA());
//
//
//                    }else
//                    {
//
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<com.vugido.brain_cord.models.quiz.Response> call, Throwable t) {
//                loginLoader(false,"Getting Question");
//                Log.e("error",t.toString());
//                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
//
//            }
//        });

    }



    private void bindQData(DataSnapshot data, String s, String s1) {

        //Log.e("called", String.valueOf(data.getQTYPE()));
        new MyPreferences(getApplicationContext()).setAnswer("");

//        cqn+=1;
        qnumber= Integer.parseInt(s1);
        //Toast.makeText(getApplicationContext(),String.valueOf(qnumber),Toast.LENGTH_LONG).show();

        //sid=data.getSID();
     //   q.setText(cqn+"/"+ql);

        //Toast.makeText(getApplicationContext(),String.valueOf(data.getQTYPE()),Toast.LENGTH_LONG).show();

        if (s.equals("1")){

            //image
            //if (fragmentImageBased==null)
                fragmentImageBased=new FragmentImageBased();

            Bundle b=new Bundle();
            b.putString("IMG",data.child("image").getValue().toString());
            b.putString("Q",data.child("question").getValue().toString());
            b.putString("O1",data.child("option_one").getValue().toString());
            b.putString("O2",data.child("option_two").getValue().toString());
            b.putString("O3",data.child("option_three").getValue().toString());
            b.putString("O4",data.child("option_four").getValue().toString());
            b.putInt("ID", Integer.parseInt(data.child("id").getValue().toString()));
            fragmentImageBased.setArguments(b);
            mFragment(fragmentImageBased,"IMAGE");


        }else if (s.equals("2")){

            //paragraph
           // if (fragmentParagraph==null)
                fragmentParagraph=new FragmentParagraph();

//            QPARA qpara=data.getQPARA();
            Bundle b2=new Bundle();
//            Log.e("paragraph",data.toString());
            b2.putInt("ID", Integer.parseInt(data.child("id").getValue().toString()));

            b2.putString("Q",data.child("paragraph").getValue().toString());

            b2.putString("Q1",data.child("question_one").getValue().toString());
            b2.putString("Q1_O1",data.child("question_one_option_one").getValue().toString());
            b2.putString("Q1_O2",data.child("question_one_option_two").getValue().toString());
            b2.putString("Q1_O3",data.child("question_one_option_three").getValue().toString());
            b2.putString("Q1_O4",data.child("question_one_option_four").getValue().toString());

            b2.putString("Q2",data.child("question_two").getValue().toString());
            b2.putString("Q2_O1",data.child("question_two_option_one").getValue().toString());
            b2.putString("Q2_O2",data.child("question_two_option_two").getValue().toString());
            b2.putString("Q2_O3",data.child("question_two_option_three").getValue().toString());
            b2.putString("Q2_O4",data.child("question_two_option_four").getValue().toString());

            b2.putString("Q3",data.child("question_three").getValue().toString());
            b2.putString("Q3_O1",data.child("question_three_option_one").getValue().toString());
            b2.putString("Q3_O2",data.child("question_three_option_two").getValue().toString());
            b2.putString("Q3_O3",data.child("question_three_option_three").getValue().toString());
            b2.putString("Q3_O4",data.child("question_three_option_four").getValue().toString());

            fragmentParagraph.setArguments(b2);
            mFragment(fragmentParagraph,"PARAGRAPH");

        }else if (s.equals("3")){

            //if (fragmentMCQSingle==null)
                fragmentMCQSingle=new FragmentMCQSingle();
//            QMCQS qimg=data.getQMCQS();
            Bundle b3=new Bundle();
            b3.putString("Q",data.child("question").getValue().toString());
            b3.putString("O1",data.child("option_one").getValue().toString());
            b3.putString("O2",data.child("option_two").getValue().toString());
            b3.putString("O3",data.child("option_three").getValue().toString());
            b3.putString("O4",data.child("option_four").getValue().toString());
            b3.putInt("ID", Integer.parseInt(data.child("id").getValue().toString()));
            fragmentMCQSingle.setArguments(b3);
            mFragment(fragmentMCQSingle,"SINGLE");

            //mcsingle
        }else {


           // if (fragmentMCQMultiple==null)
                fragmentMCQMultiple=new FragmentMCQMultiple();
//            QMCQM qimg=data.getQMCQM();
            Bundle b4=new Bundle();
            b4.putString("Q",data.child("question").getValue().toString());
            b4.putString("O1",data.child("option_one").getValue().toString());
            b4.putString("O2",data.child("option_two").getValue().toString());
            b4.putString("O3",data.child("option_three").getValue().toString());
            b4.putString("O4",data.child("option_four").getValue().toString());
            b4.putInt("ID", Integer.parseInt(data.child("id").getValue().toString()));
            fragmentMCQMultiple.setArguments(b4);
            mFragment(fragmentMCQMultiple,"Multiple");
            //mcm


        }


    }



    private void mFragment(Fragment fragment,String s) {

        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment,s);
        fragmentTransaction.commit();

    }

    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }

    private void fetchSecInfo() {

//        loginLoader(true,"Please Wait..");

//        Map<String,Object> map=new HashMap<>();
//        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
//        map.put("SID",String.valueOf(sid));
//
//        Call<Response>call= RetrofitBuilder.getInstance().getRetrofit().getSecInfo(map);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                loginLoader(false,"Getting Question");
//
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(response.body().isSTATUS()){
//
//
//
//                        bindData(response.body().getDATA());
//
//
//                    }else
//                    {
//
//                        submitQuiz();
//
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                loginLoader(false,"Getting Question");
//
//            }
//        });

        bindData();
    }

    private void submitQuiz() {
        //qnumber,sid,1,rs,new MyPreferences(getApplicationContext()).getAnswerS()

        loginLoader(true,"Submitting Answer..");

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("QID",String.valueOf(qnumber));
        map.put("SID",String.valueOf(sq1));
        map.put("TIME_LAP",String.valueOf(rs));
        if (new MyPreferences(getApplicationContext()).getAnswerS()==null)
            new MyPreferences(getApplicationContext()).setAnswer("z");

        map.put("ANS",new MyPreferences(getApplicationContext()).getAnswerS());


        databaseReference= FirebaseDatabase.getInstance().getReference("braincord");
        databaseReference.addValueEventListener(this);
        databaseReference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Do what you need to do
                    loginLoader(false,"Submitting Answer..");
                    quizEnd();
                } else {
//                    Log.d(TAG, task.getException().getMessage());
                    loginLoader(false,"Submitting Answer..");

                }
            }
        });

//
//        Call<com.vugido.brain_cord.models.Response> call=RetrofitBuilder.getInstance().getRetrofit().answer(map);
//        call.enqueue(new Callback<com.vugido.brain_cord.models.Response>() {
//            @Override
//            public void onResponse(Call<com.vugido.brain_cord.models.Response> call, retrofit2.Response<com.vugido.brain_cord.models.Response> response) {
//                loginLoader(false,"Submitting Answer..");
//
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    if (!response.body().isError()){
//
//
//
//
//                    }
//                    else
//                        Toast.makeText(getApplicationContext(),"Try Again..Error",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<com.vugido.brain_cord.models.Response> call, Throwable t) {
//                loginLoader(false,"Submitting Answer..");
//
//            }
//        });



//        Toast.makeText(getApplicationContext(),"Submit Quiz",Toast.LENGTH_SHORT).show();

    }

    private void quizEnd() {

        startActivity(new Intent(QuizActivity.this,CollegeForm.class));
        new MyPreferences(getApplicationContext()).setVerified(false);
        finish();

    }

    private void showTimer(){

        countDownTimer=  new CountDownTimer(

                min,1000 ) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                long remainedSecs = millisUntilFinished / 1000;
                rs=remainedSecs;
                if (rs==600)
                    time.setTextColor(getResources().getColor(R.color.red));
                time.setText((remainedSecs / 60) + ":" + (remainedSecs % 60));
            }

            @Override
            public void onFinish() {
//                sid += 1;
                cqn=0;
                if (countDownTimer!=null)
                    countDownTimer.cancel();
                countDownTimer=null;
                fetchSecInfo();

            }
        };
        countDownTimer.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void bindData() {


//

        if (sq1<=2) {
            ql = qll[sq1];
            min = cl[sq1] * 60 * 1000;
            String k = "";
            if (sq1 == 0)
                k = s1[0];
            else if (sq1 == 1)
                k = s2[0];
            else if (sq1 == 2)
                k = s3[0];
            sechead.setText("Section-"+(sq1+1));
            fetchQuestionInfo(k, sq1 + 1, 1, rs, new MyPreferences(getApplicationContext()).getAnswerS());
            ++sq1;
            showTimer();
        }else
            submitQuiz();

    }


    @Override
    public void onBackPressed() {

        ///
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.button4){

            if (qll[sq1-1]==cqn) {
                //sq1 += 1;
                cqn=0;
                if (countDownTimer!=null)
                    countDownTimer.cancel();
                countDownTimer=null;
                fetchSecInfo();
            }else {

                answerSubmission();
                //fetchQuestionInfo(qnumber, sid, 1, rs, new MyPreferences(getApplicationContext()).getAnswerS());
            }

        }

    }

    private void answerSubmission() {
        loginLoader(true,"Submitting Answer..");




        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("QID",String.valueOf(qnumber));
        map.put("SID",String.valueOf(sq1));
        map.put("TIME_LAP",String.valueOf(rs));
        if (new MyPreferences(getApplicationContext()).getAnswerS()==null)
            new MyPreferences(getApplicationContext()).setAnswer("z");

        map.put("ANS",new MyPreferences(getApplicationContext()).getAnswerS());
        databaseReference= FirebaseDatabase.getInstance().getReference("braincord");
        databaseReference.addValueEventListener(this);
        databaseReference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Do what you need to do
                        loginLoader(false,"Submitting Answer..");
//                        String s=s1[cqn];
                    String k="";
                    if (sq1==1)
                        k=s1[cqn];
                    else if (sq1==2)
                        k=s2[cqn];
                    else if (sq1==3)
                        k=s3[cqn];
                        fetchQuestionInfo(k,sq1,1,rs,new MyPreferences(getApplicationContext()).getAnswerS());

                } else {
//                    Log.d(TAG, task.getException().getMessage());
                }
            }
        });



//        Call<com.vugido.brain_cord.models.Response> call=RetrofitBuilder.getInstance().getRetrofit().answer(map);
//        call.enqueue(new Callback<com.vugido.brain_cord.models.Response>() {
//            @Override
//            public void onResponse(Call<com.vugido.brain_cord.models.Response> call, retrofit2.Response<com.vugido.brain_cord.models.Response> response) {
//                loginLoader(false,"Submitting Answer..");
//
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    if (!response.body().isError())
//                        fetchQuestionInfo(qnumber,sid,1,rs,new MyPreferences(getApplicationContext()).getAnswerS());
//                    else
//                        Toast.makeText(getApplicationContext(),"Try Again..Error",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<com.vugido.brain_cord.models.Response> call, Throwable t) {
//                loginLoader(false,"Submitting Answer..");
//
//            }
//        });


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {


//        Toast.makeText(this,snapshot.toString(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show();


    }
}




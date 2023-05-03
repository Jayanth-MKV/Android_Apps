package com.vugido.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LearnFragment extends Fragment {

    List<LearningModel> learningModelList;
    Activity activity;
    RecyclerView recyclerView;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.learn_fragment,container,false);
        recyclerView=view.findViewById(R.id.rec);
        learningModelList=new ArrayList<>();
        learningModelList.add(new LearningModel(1,R.drawable.ic_launcher_background,"A"));
        learningModelList.add(new LearningModel(2,R.drawable.ic_launcher_background,"B"));

        learningModelList.add(new LearningModel(3,R.drawable.ic_launcher_background,"C"));

        learningModelList.add(new LearningModel(4,R.drawable.ic_launcher_background,"D"));
        learningModelList.add(new LearningModel(5,R.drawable.ic_launcher_background,"E"));

        learningModelList.add(new LearningModel(6,R.drawable.ic_launcher_background,"F"));

        learningModelList.add(new LearningModel(7,R.drawable.ic_launcher_background,"G"));
        learningModelList.add(new LearningModel(8,R.drawable.ic_launcher_background,"H"));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        LearningRecyclerViewAdapter learningRecyclerViewAdapter=new LearningRecyclerViewAdapter(learningModelList,context);
        recyclerView.setAdapter(learningRecyclerViewAdapter);










        Toast.makeText(activity,"FRAGMENT",Toast.LENGTH_LONG).show();
        return view;
    }
}

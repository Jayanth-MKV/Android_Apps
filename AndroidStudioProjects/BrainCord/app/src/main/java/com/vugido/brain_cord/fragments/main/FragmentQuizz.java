package com.vugido.brain_cord.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.brain_cord.R;
import com.vugido.brain_cord.adapters.QuizzRecyclerViewAdapter;
import com.vugido.brain_cord.models.Quizz;

import java.util.ArrayList;
import java.util.List;

public class FragmentQuizz extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    List<Quizz> quizzList;
    QuizzRecyclerViewAdapter quizzRecyclerViewAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_quizz,container,false);

        recyclerView=view.findViewById(R.id.quizz_recyclerView);


        quizzList=new ArrayList<>();

        quizzList.add(new Quizz(R.drawable.legac_geek,1));
        quizzList.add(new Quizz(R.drawable.quizzz,2));
        quizzList.add(new Quizz(R.drawable.qui,3));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        quizzRecyclerViewAdapter=new QuizzRecyclerViewAdapter(quizzList,context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(quizzRecyclerViewAdapter);

        return view;
    }


}

package com.jntuk.ucev.placementsportal.fragments.chats;

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

import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.adapters.chats.ChatsRecyclerAdapter;
import com.jntuk.ucev.placementsportal.models.discussion_board.ChatsModel;

import java.util.ArrayList;
import java.util.List;

public class Chats extends Fragment {


    List<ChatsModel> chatsModelList;
    Context context;
    RecyclerView recyclerView;
    Hider hider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chats,container,false);
        recyclerView=view.findViewById(R.id.chats_recyclerview);

        chatsModelList=new ArrayList<>();
        chatsModelList.add(new ChatsModel(1,R.drawable.prathyusha,"Prathyusha","Hi! How are you ??","8","3:45am"));
        chatsModelList.add(new ChatsModel(2,R.drawable.govind,"Vurjana","What's the progress??","0","3:45am"));
        chatsModelList.add(new ChatsModel(3,R.drawable.sameer,"Sameer","Testing ??","0","3:45am"));
        chatsModelList.add(new ChatsModel(4,R.drawable.nandini,"Nandini","Hi! How are you ??","7","3:45am"));
        chatsModelList.add(new ChatsModel(5,R.drawable.swathi,"Swathi","Hi! How are you ??","1","3:45am"));
        chatsModelList.add(new ChatsModel(6,R.drawable.siddarth,"Siddartha","Hi! How are you ??","3","3:45am"));
        chatsModelList.add(new ChatsModel(7,R.drawable.me,"Gideon","Hi! How are you ??","8","3:45am"));
        chatsModelList.add(new ChatsModel(8,R.drawable.prathyusha,"Prathyusha","Hi! How are you ??","0","3:45am"));




        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        ChatsRecyclerAdapter chatsRecyclerAdapter=new ChatsRecyclerAdapter(chatsModelList,context);
        recyclerView.setAdapter(chatsRecyclerAdapter);




        return view;
    }

    public interface Hider{
        void hide(boolean b);
    }
}

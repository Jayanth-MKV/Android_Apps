package com.jntuk.ucev.placementsportal.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.adapters.chats.ChatsViewPagerAdapter;
import com.jntuk.ucev.placementsportal.adapters.jobs.JobsViewPagerAdapter;
import com.jntuk.ucev.placementsportal.fragments.chats.Chats;
import com.jntuk.ucev.placementsportal.fragments.chats.DiscussionBoard;
import com.jntuk.ucev.placementsportal.fragments.chats.Groups;
import com.jntuk.ucev.placementsportal.fragments.jobs.InternshipFragment;
import com.jntuk.ucev.placementsportal.fragments.jobs.JobsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ActivityChats extends AppCompatActivity {

    ViewPager2 viewPager2;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);


        toolbar=findViewById(R.id.toolbar);


        toolbar.setTitle("Discussion Board");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());

        viewPager2=findViewById(R.id.chats_pager);

        List<Map<String, Object>> mapList = new ArrayList<>();

        Map<String, Object> stringObjectMap = setTabTitleAndFragment("Chats", new Chats());
        Map<String, Object> settingMap = setTabTitleAndFragment("QADiscuss", new DiscussionBoard());
        Map<String, Object> settingMap2 = setTabTitleAndFragment("Groups", new Groups());



        mapList.add(stringObjectMap);
        mapList.add(settingMap);
        mapList.add(settingMap2);

        final ChatsViewPagerAdapter viewPagerAdapter = new ChatsViewPagerAdapter(this, mapList);
        viewPager2.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText(viewPagerAdapter.getTitle(position))).attach();
    }


    Map<String, Object> setTabTitleAndFragment(String title, Fragment fragment){

        Map<String, Object> fragmentWithTitleMap = new HashMap<>();

        fragmentWithTitleMap.put("fragmentTitle", title);
        fragmentWithTitleMap.put("fragment", fragment);

        return fragmentWithTitleMap;
    }


}

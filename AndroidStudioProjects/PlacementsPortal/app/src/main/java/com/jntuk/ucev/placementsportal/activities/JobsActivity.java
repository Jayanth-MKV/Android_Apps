package com.jntuk.ucev.placementsportal.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.adapters.jobs.JobsViewPagerAdapter;
import com.jntuk.ucev.placementsportal.fragments.jobs.InternshipFragment;
import com.jntuk.ucev.placementsportal.fragments.jobs.JobsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JobsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager2 viewPager2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        toolbar=findViewById(R.id.toolbar);
        viewPager2=findViewById(R.id.jos_pager);


        toolbar.setTitle("Jobs/Internships");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());


        List<Map<String, Object>> mapList = new ArrayList<>();

        Map<String, Object> stringObjectMap = setTabTitleAndFragment("Jobs", new JobsFragment());
        Map<String, Object> settingMap = setTabTitleAndFragment("Internships", new InternshipFragment());

        mapList.add(stringObjectMap);
        mapList.add(settingMap);

        final JobsViewPagerAdapter viewPagerAdapter = new JobsViewPagerAdapter(this, mapList);
        viewPager2.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText(viewPagerAdapter.getTitle(position))).attach();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem=menu.findItem(R.id.notifications);
        menuItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }


    Map<String, Object> setTabTitleAndFragment(String title, Fragment fragment){

        Map<String, Object> fragmentWithTitleMap = new HashMap<>();

        fragmentWithTitleMap.put("fragmentTitle", title);
        fragmentWithTitleMap.put("fragment", fragment);

        return fragmentWithTitleMap;
    }

}

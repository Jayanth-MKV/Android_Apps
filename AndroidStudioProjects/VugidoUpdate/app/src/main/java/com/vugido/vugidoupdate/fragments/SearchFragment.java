package com.vugido.vugidoupdate.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.card.MaterialCardView;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.adapters.SearchPage.SearchAdapter;
import com.vugido.vugidoupdate.app_settings.MyPreferences;
import com.vugido.vugidoupdate.designs.Space;
import com.vugido.vugidoupdate.models.search.SEarchDemoModel;
import com.vugido.vugidoupdate.models.search.search_suggestions.DATAItem;
import com.vugido.vugidoupdate.models.search.search_suggestions.Response;
import com.vugido.vugidoupdate.network.Retrofit.RetrofitBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private Context context;
    private SearchView searchView;
    private FragmentActivity fragmentActivity;
    private RecyclerView SearchRecyclerView;
    private List<DATAItem> searchSuggestionsList;
    private SearchAdapter searchAdapter;
    MaterialCardView materialCardViewCenter;
    LottieAnimationView lottieAnimationViewCenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentActivity= getActivity();
        context=getContext();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search,container,false);
        SearchRecyclerView=view.findViewById(R.id.SearchRecyclerView);
        materialCardViewCenter=view.findViewById(R.id.CenterProgressBar);
        lottieAnimationViewCenter=view.findViewById(R.id.lottie_progress_center);
        fetchUsersRecentSearches();
       // setAdapter();
        return view;
    }

    private void fetchUsersRecentSearches() {


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_fragment,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) fragmentActivity.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null && searchManager!=null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(fragmentActivity.getComponentName()));
            searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.setOnQueryTextListener(this);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        Toast.makeText(getContext(),query,Toast.LENGTH_SHORT).show();
        //send the submit request
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        //Toast.makeText(getContext(),newText,Toast.LENGTH_SHORT).show();
        fetchSuggestions(newText);
        return true;
    }

    private void fetchSuggestions(final String newText) {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(context).getUID());
        map.put("SEARCH_ON",newText);
        materialCardViewCenter.setVisibility(View.VISIBLE);
        //play lottie
        lottieAnimationViewCenter.playAnimation();
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().searchSuggestionsUrl(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NotNull Call<Response> call, @NotNull retrofit2.Response<Response> response) {

                materialCardViewCenter.setVisibility(View.GONE);
                lottieAnimationViewCenter.pauseAnimation();
                //play lottie
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //data got
                        SearchRecyclerView.setVisibility(View.VISIBLE);
                        searchSuggestionsList=response.body().getDATA();
                        bindSuggestionsData(newText);
                    }else {
                        //no data..
                        SearchRecyclerView.setVisibility(View.GONE);
                        fetchUsersRecentSearches();
                        Toast.makeText(context,"No Results",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    // error
                }
            }

            @Override
            public void onFailure(@NotNull Call<Response> call, @NotNull Throwable t) {
                materialCardViewCenter.setVisibility(View.GONE);
                lottieAnimationViewCenter.pauseAnimation();
            }
        });

    }

    private void bindSuggestionsData(String key) {

        if(searchAdapter==null){
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            SearchRecyclerView.setLayoutManager(linearLayoutManager);
            SearchRecyclerView.addItemDecoration(new Space(1,5,false,0));
            searchAdapter=new SearchAdapter(searchSuggestionsList,context,key);
            SearchRecyclerView.setAdapter(searchAdapter);


        }else {
            searchAdapter.setItems(searchSuggestionsList);
            searchAdapter.notifyDataSetChanged();
        }

    }
}
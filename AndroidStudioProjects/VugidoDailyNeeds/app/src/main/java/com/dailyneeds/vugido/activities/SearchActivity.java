package com.dailyneeds.vugido.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.adapters.SearchAdapter.SuggestionAdapter;
import com.suhel.library.CenteredLayoutManager;
import com.suhel.library.ReelSearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements CenteredLayoutManager.OnSelectionChangedListener {
    RecyclerView recyclerView;
    private EditText editText;
    private ReelSearchView reelSearchView;


    List<String> data=new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        recyclerView=findViewById(R.id.lstSuggestions);
        editText=findViewById(R.id.txtQuery);
        reelSearchView=findViewById(R.id.reelSearchView);

        data.add("abc");
        data.add("xyz");
        data.add("a");
        data.add("mno");
        data.add("apple");
        data.add("mango");
        data.add("ant");
        data.add("aeroplane");
        data.add("almonds");
        data.add("apricot");
        data.add("potato");
        data.add("abcd");
        data.add("abcdef");

        SuggestionAdapter suggestionAdapter=new SuggestionAdapter(this,data);

        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // get the char sequence here and fetch data from server...
                // bind it to the adapter list..
                

                suggestionAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };



        editText.addTextChangedListener(textWatcher);
        recyclerView.setAdapter(suggestionAdapter);


        reelSearchView.setOnSelectionChangedListener(this);

        int position = reelSearchView.getSelection();

        Log.e("position",String.valueOf(position)+ data.get(position));



    }

    @Override
    public void onSelectionChanged(int previousSelection, int newSelection) {


        Log.e("onSel","executed");

        Log.e("position",String.valueOf(previousSelection));
        Log.e("position",String.valueOf(newSelection));
        int position = reelSearchView.getSelection();

        Log.e("position",String.valueOf(position)+ data.get(position));



    }
}

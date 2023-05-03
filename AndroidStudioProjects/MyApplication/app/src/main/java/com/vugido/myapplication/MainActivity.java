package com.vugido.myapplication;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView MyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTextView=findViewById(R.id.my_text_view);

        MyTextView.setText("Anjali Thakur!");
    }
}

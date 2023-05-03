package com.vugido.online_groceries.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.app.MyPreferences;

public class ProfileActivity extends AppCompatActivity {

    TextView logout, delete_acc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        logout=findViewById(R.id.textView16);
        delete_acc=findViewById(R.id.textView15);

        logout.setOnClickListener(v -> {
            new MyPreferences(getApplicationContext()).setVerified(false);
            restartApplication();
        });

        delete_acc.setOnClickListener(v -> {


        });

    }
    private void restartApplication() {

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName() );
        assert i != null;
        //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}

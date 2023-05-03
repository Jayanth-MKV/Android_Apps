package Com.Vugido.FoodHub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppSignatureHelper appSignatureHelper=new AppSignatureHelper(this);
        ArrayList<String> stringArrayList=appSignatureHelper.getAppSignatures();


        Log.e("HashString",stringArrayList.toString());
    }
}
package vugido.foodhub.ap.sklm.hungrybirds.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.MyPreferences;
import vugido.foodhub.ap.sklm.hungrybirds.dialogs.MyDialogLoader;
import vugido.foodhub.ap.sklm.hungrybirds.dialogs.MyStatusDialog;
import vugido.foodhub.ap.sklm.hungrybirds.fragments.bottom_sheet.PayMethodsBottomSheet;
import vugido.foodhub.ap.sklm.hungrybirds.models.status.Response;
import vugido.foodhub.ap.sklm.hungrybirds.network.Retrofit.RetrofitBuilder;

import static vugido.foodhub.ap.sklm.hungrybirds.fragments.bottom_sheet.PayMethodsBottomSheet.CASH_ON_DELIVERY;

public class CheckOutActivity extends AppCompatActivity implements PaymentResultListener, PayMethodsBottomSheet.PAY_METHOD_INTERFACE {

    Button pay;
    TextView at,pi,dc,ip,am,up,addUN,addUP,addUD,addUA;
    Toolbar toolbar;
    private MyDialogLoader myDialogLoader;
    private MyStatusDialog myStatusDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        toolbar=findViewById(R.id.checkOutToolbar);

        toolbar.setTitle("Check-Out Order");
        toolbar.setTitleTextColor(getResources().getColor(R.color.gradient_end_color));
        setSupportActionBar(toolbar);


        pay=findViewById(R.id.button4);
        at=findViewById(R.id.delivery_time);
        pi=findViewById(R.id.textView5);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_blue);
        toolbar.setNavigationOnClickListener(v -> finish());

        addUN=findViewById(R.id.address_user_name);
        addUP=findViewById(R.id.address_user_phone);
        addUD=findViewById(R.id.address_user_door);
        addUA=findViewById(R.id.address_user_street);

        addUD.setText(new MyPreferences(getApplicationContext()).getUserPrimaryAddress());
        addUP.setText(new MyPreferences(getApplicationContext()).getMapPhone());
        addUN.setText(new MyPreferences(getApplicationContext()).getUserName());
        addUA.setText(new MyPreferences(getApplicationContext()).getBaseLocationName());




        pi.setText("Price ("+new MyPreferences(getApplicationContext()).getCartCount()+" Items)");
        at.setText(new MyPreferences(getApplicationContext()).getDelTimeMsg());
        dc=findViewById(R.id.delivery_charges);
        dc.setText("Rs."+new MyPreferences(getApplicationContext()).getDC()+"/-");

        ip=findViewById(R.id.ItemsPrice);
        ip.setText("Rs."+new MyPreferences(getApplicationContext()).getCartPrice()+"/-");

        am=findViewById(R.id.total_amount);
        am.setText("Rs."+(new MyPreferences(getApplicationContext()).getCartPrice()+new MyPreferences(getApplicationContext()).getDC())+"/-");

        up=findViewById(R.id.textView39);

        up.setText("Rs."+(new MyPreferences(getApplicationContext()).getCartPrice()+new MyPreferences(getApplicationContext()).getDC())+"/-");

        pay.setOnClickListener(v -> startPayment());
        Checkout.preload(getApplicationContext());


    }

    public void startPayment() {


        PayMethodsBottomSheet payMethodsBottomSheet=new PayMethodsBottomSheet();
        payMethodsBottomSheet.show(getSupportFragmentManager(),"P");


//        Checkout checkout = new Checkout();  /**   * Set your logo here   */
//        checkout.setKeyID("rzp_test_lTCM9TkiGNJMFu");
//        checkout.setImage(R.drawable.hbicon);  /**   * Reference to current activity   */
//        final Activity activity = this;  /* *   * Pass your payment options to the Razorpay Checkout as a JSONObject   */
//        try {
//            JSONObject options = new JSONObject();
//            options.put("name", "Merchant Name");
//            options.put("description", "Reference No. #123456");
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//           // options.put("order_id", "order_DBJOWzybf0sJbb");
//            //from response of step 3.
//             options.put("theme.color", "#3399cc");
//             options.put("currency", "INR");
//             options.put("amount", "100");//pass amount in currency subunits
////             options.put("prefill.email", "gaurav.kumar@example.com");
////             options.put("prefill.contact","9988776655");
//             checkout.open(activity, options);
//        } catch(Exception e) {
//             Log.e("pppay ooonliine", "Error in starting Razorpay Checkout", e);
//        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        Log.e("sccss",s);
    }

    @Override
    public void onPaymentError(int i, String s) {

        Log.e("rroooorr",s);

    }

    @Override
    public void payMethodInterface(int pm) {



        if (pm==CASH_ON_DELIVERY)
        {
            placeOrder(CASH_ON_DELIVERY);
        }


    }

    private void placeOrder(int i) {


        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Placing Order...");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");


        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("PIDS",new MyPreferences(getApplicationContext()).getCartProducts());
        map.put("ITEMS_PRICE",new MyPreferences(getApplicationContext()).getCartPrice());
        map.put("DC",new MyPreferences(getApplicationContext()).getDC());
        map.put("NOTE",new MyPreferences(getApplicationContext()).getUserPrimaryAddress());
        map.put("ITEM_COUNT",String.valueOf(new MyPreferences(getApplicationContext()).getCartCount()));
        map.put("APHONE",new MyPreferences(getApplicationContext()).getMapPhone());
        map.put("LAT",new MyPreferences(getApplicationContext()).getLocLat());
        map.put("LON",new MyPreferences(getApplicationContext()).getLocLan());
        map.put("MADDRESS",new MyPreferences(getApplicationContext()).getBaseLocationName());
        map.put("PM",String.valueOf(i));


        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().placeOrder(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful())
                {
                    assert response.body() != null;
                    if(response.body().isSTATUS()){




                        showDialogStatus(1);



                    }else {

                        showDialogStatus(2);
                    }
                }
                myDialogLoader.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {



                myDialogLoader.dismiss();
                showDialogStatus(2);
            }
        });
    }




    private void showDialogStatus(int status){

        if (myStatusDialog==null)
            myStatusDialog=new MyStatusDialog();

        Bundle bundle=new Bundle();
        if(status==2){
            bundle.putString("MSG","Order Not Placed ...");

            bundle.putBoolean("STATUS",false);
            myStatusDialog.setArguments(bundle);
            myStatusDialog.show(getSupportFragmentManager(),"S");

        }else if(status==1){
            bundle.putString("MSG","Order Placed Successfully...");

            bundle.putBoolean("STATUS",true);
            myStatusDialog.setArguments(bundle);
            myStatusDialog.show(getSupportFragmentManager(),"S");
            new MyPreferences(getApplicationContext()).setCartProducts(null);
            new MyPreferences(getApplicationContext()).setCartCount(0);
            new MyPreferences(getApplicationContext()).setCartPrice(0);
            setResult(RESULT_OK);
            finish();

        }

    }
}

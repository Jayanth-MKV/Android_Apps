package com.foodhub.vugido.sklm.fresh_cuts.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.foodhub.vugido.sklm.fresh_cuts.R;

import org.json.JSONObject;

public class CheckOutActivity extends AppCompatActivity implements PaymentResultListener {

    Button pay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        pay=findViewById(R.id.button4);
        pay.setOnClickListener(v -> startPayment());
        Checkout.preload(getApplicationContext());


    }

    public void startPayment() {
        Checkout checkout = new Checkout();  /**   * Set your logo here   */
        checkout.setKeyID("rzp_test_rHc93eV9VO6dY7");
        checkout.setImage(R.drawable.hbicon);  /**   * Reference to current activity   */
        final Activity activity = this;  /* *   * Pass your payment options to the Razorpay Checkout as a JSONObject   */
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
           // options.put("order_id", "order_DBJOWzybf0sJbb");
            //from response of step 3.
             options.put("theme.color", "#3399cc");
             options.put("currency", "INR");
             options.put("amount", "100");//pass amount in currency subunits
//             options.put("prefill.email", "gaurav.kumar@example.com");
//             options.put("prefill.contact","9988776655");
             checkout.open(activity, options);
        } catch(Exception e) {
             Log.e("pppay ooonliine", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        Log.e("sccss",s);
    }

    @Override
    public void onPaymentError(int i, String s) {

        Log.e("rroooorr",s);

    }
}

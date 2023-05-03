package com.dailyneeds.vugido.fragments;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.dialogs.ResponseStatus;
import com.dailyneeds.vugido.dialogs.StartDateDialog;
import com.dailyneeds.vugido.models.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductAddOnDialog extends DialogFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, NetworkQueries.NetworkQueryInterface {

    private Button Cancel,ToCart;
    private Context context;
    private TextView name,price,QQ,UQ,SQ,Offer_Tag;
    private ImageView imageView;
    private RadioButton Srb,Mrb,Lrb;
    private RelativeLayout AdjustQuantity,UnitAdjuster,SizeAdjuster;
    private ImageButton QIB,QDB,UIB,UDB,SIB,SDB;
    private Product product;
    private int SWITCHED_SIZE;
    private int QUANTIFIER;
    private FragmentActivity fragmentActivity;
    private UpdateCartCount updateCartCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
       context=getContext();
       fragmentActivity= (FragmentActivity) getContext();
       updateCartCount= (UpdateCartCount) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.add_cart,container,false);
        Cancel=view.findViewById(R.id.cancelButton);
        ToCart=view.findViewById(R.id.addButton);
        Cancel.setOnClickListener(this);
        ToCart.setOnClickListener(this);
        name=view.findViewById(R.id.item_name);
        imageView=view.findViewById(R.id.item_image);
        price=view.findViewById(R.id.price);
        Offer_Tag=view.findViewById(R.id.offer_price);

        Srb=view.findViewById(R.id.SmallRadioButton);
        Mrb=view.findViewById(R.id.MediumRadioButton);
        Lrb=view.findViewById(R.id.LargeRadioButton);
        // all adjusters..
        AdjustQuantity=view.findViewById(R.id.adjust_quantity);
        UnitAdjuster=view.findViewById(R.id.adjust_units);
        SizeAdjuster=view.findViewById(R.id.adjust_size);


        // set all Quantifier  settings..
        QIB=view.findViewById(R.id.Q_INC);
        QDB=view.findViewById(R.id.Q_DEC);
        UIB=view.findViewById(R.id.U_INC);
        UDB=view.findViewById(R.id.U_DEC);
        SIB=view.findViewById(R.id.S_INC);
        SDB=view.findViewById(R.id.S_DEC);

        // set all quantifier quantities..
        QQ=view.findViewById(R.id.QQ);
        UQ=view.findViewById(R.id.UQ);
        SQ=view.findViewById(R.id.SQ);

        QIB.setOnClickListener(this);
        QDB.setOnClickListener(this);
        UDB.setOnClickListener(this);
        UIB.setOnClickListener(this);
        SDB.setOnClickListener(this);
        SIB.setOnClickListener(this);




        Srb.setOnCheckedChangeListener(this);
        Mrb.setOnCheckedChangeListener(this);
        Lrb.setOnCheckedChangeListener(this);


        //getting product details...
        Bundle bundle=getArguments();
        assert bundle != null;
        product=bundle.getParcelable("PRODUCT");
        assert product != null;
        name.setText(product.getProductTNAme());
        Glide.with(context).load(product.getProductImage()).into(imageView);


        // get the product qualifier and set the adjuster
        setQuantifier(product);


        //set quantifier must be always preceded...

        setProductAdjuster(product);







        return view;

    }

    private void setQuantifier(Product product) {

        QUANTIFIER=Product.getDefaultQuantifier(product);
        SWITCHED_SIZE=Product.getIntegerParser(product.getDefaultSize());
        setPrice(SWITCHED_SIZE);




    }


    private void setPrice(int size){

      /*  Log.e("Price",String.valueOf(product.getProductQualifiedPrice()));
        Log.e("OfferTag",String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true)));*/
      String offer=String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER,size)));
      if(Product.getIntegerParser(product.getProductOfferStatus())==ConfigVariables.OFFER){
          Offer_Tag.setText(offer);
          Offer_Tag.setPaintFlags(Offer_Tag.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
      }else {
          Offer_Tag.setVisibility(View.GONE);
      }
        price.setText(String.format("Rs.%s/-", String.valueOf(product.getProductQualifiedPrice())));


    }
    private void setProductAdjuster(Product product) {


        switch (Integer.parseInt(product.getProductQualifier())){
            case ConfigVariables.QUANTITY:
                AdjustQuantity.setVisibility(View.VISIBLE);
                SizeAdjuster.setVisibility(View.GONE);
                UnitAdjuster.setVisibility(View.GONE);

                if(QUANTIFIER<1000){
                    QQ.setText(String.format("%sg", String.valueOf(QUANTIFIER)));
                }else {
                    QQ.setText(String.format("%sKg", String.valueOf(Product.gram2Kg(QUANTIFIER))));

                }
                break;
                case ConfigVariables.UNITS:
                    UnitAdjuster.setVisibility(View.VISIBLE);
                    SizeAdjuster.setVisibility(View.GONE);
                    AdjustQuantity.setVisibility(View.GONE);
                    UQ.setText(String.valueOf(QUANTIFIER));
                    break;
                    case ConfigVariables.SIZE:


                        SQ.setText(String.valueOf(QUANTIFIER));
                        SizeAdjuster.setVisibility(View.VISIBLE);
                        AdjustQuantity.setVisibility(View.GONE);
                        UnitAdjuster.setVisibility(View.GONE);
                        setDefaultRadioBoxChecked();
                        break;

        }



    }

    private void setDefaultRadioBoxChecked() {

        switch (Product.getIntegerParser(product.getDefaultSize())){

            case ConfigVariables.SMALL:
                Srb.setChecked(true);
                break;
                case ConfigVariables.MEDIUM:
                    Mrb.setChecked(true);
                    break;
                    case  ConfigVariables.LARGE:
                        Lrb.setChecked(true);
                        break;

        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.cancelButton:
                dismiss();
                break;
            case R.id.addButton:
                // to cart


              /*  StartDateDialog startDateDialog=new StartDateDialog();
                startDateDialog.show(fragmentActivity.getSupportFragmentManager(),"START");*/
                NetworkQueries networkQueries=new NetworkQueries(context,this,
                        ConfigVariables.ADD_TO_CART_URL,buildParams());
                networkQueries.sendRequest("Adding To Cart");



                break;
                // all inc and dec
            case R.id.Q_DEC:

                if(QUANTIFIER == ConfigVariables.BASE_QUANTITY){

                    Toast.makeText(context,"Reached Min Value",Toast.LENGTH_SHORT).show();

                }else {

                    QUANTIFIER= Product.getQuantifierInterval(false,QUANTIFIER,product);

                    if(QUANTIFIER<1000){
                        QQ.setText(String.format("%sg", String.valueOf(QUANTIFIER)));
                    }else {
                        QQ.setText(String.format("%sKg", String.valueOf(Product.gram2Kg(QUANTIFIER))));

                    }

                }
              //  price.setText(String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true))));
                setPrice(0);

                break;
            case R.id.Q_INC:
                QUANTIFIER= Product.getQuantifierInterval(true, QUANTIFIER,product);

                if(QUANTIFIER<1000){
                    QQ.setText(String.format("%sg", String.valueOf(QUANTIFIER)));
                }else {
                    QQ.setText(String.format("%sKg", String.valueOf(Product.gram2Kg(QUANTIFIER))));

                }
               // QQ.setText(String.valueOf(QUANTIFIER));
                //price.setText(String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true))));
                setPrice(0);
                break;
            case R.id.U_DEC:

                if(QUANTIFIER==Integer.parseInt(product.getUnitInterval())){
                    Toast.makeText(context,"Reached Min Value",Toast.LENGTH_SHORT).show();

                }else {

                    QUANTIFIER=Product.getQuantifierInterval(false,QUANTIFIER,product);
                    UQ.setText(String.valueOf(QUANTIFIER));

                }

              //  price.setText(String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true))));
                setPrice(0);
                break;
            case R.id.U_INC:
                QUANTIFIER=Product.getQuantifierInterval(true, QUANTIFIER,product);
                UQ.setText(String.valueOf(QUANTIFIER));
               // price.setText(String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true))));
               // price.setText(String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true))));
                setPrice(0);
                break;
            case R.id.S_DEC:

                if(QUANTIFIER==1){

                    Toast.makeText(context,"Reached Min Value",Toast.LENGTH_SHORT).show();

                }else {

                    QUANTIFIER=Product.getQuantifierInterval(false,  QUANTIFIER,product);
                    SQ.setText(String.valueOf(QUANTIFIER));
                }
//                price.setText(String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true))));
                setPrice(SWITCHED_SIZE);

                break;
            case R.id.S_INC:
                QUANTIFIER=Product.getQuantifierInterval(true,QUANTIFIER,product);
                SQ.setText(String.valueOf(QUANTIFIER));
               // price.setText(String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER, true))));
                setPrice(SWITCHED_SIZE);
                break;

        }


    }

    private Map<String,String> buildParams(){



        Map<String,String> params=new HashMap<>();
        params.put("UID",new MyPreferences(context).getUID());
        params.put("CAT",product.getProductCatID() );
        params.put("PID",product.getProductID());
        params.put("PQ", String.valueOf(QUANTIFIER));
        params.put("TOTAL",String.valueOf(product.getProductQualifiedPrice()));
        params.put("PRICE",product.getProductPrice());
        params.put("ON",ConfigVariables.getCurrentDate()+" "+ConfigVariables.getCurrentTime());
        params.put("Q",product.getProductQualifier());
        params.put("SIZE", String.valueOf(SWITCHED_SIZE));
        params.put("E_NAME",product.getProductEName());
        params.put("T_NAME",product.getProductTNAme());
        return  params;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

      //  int TEMP_CHECK=SWITCHED_SIZE;
        if (b) {
            if (compoundButton.getId() == R.id.SmallRadioButton) {
                SWITCHED_SIZE=ConfigVariables.SMALL;
                Mrb.setChecked(false);
                Lrb.setChecked(false);
            }
            if (compoundButton.getId() == R.id.MediumRadioButton) {
                SWITCHED_SIZE=ConfigVariables.MEDIUM;
                Srb.setChecked(false);
                Lrb.setChecked(false);
            }
            if(compoundButton.getId()==R.id.LargeRadioButton){
                SWITCHED_SIZE=ConfigVariables.LARGE;
                Srb.setChecked(false);
                Mrb.setChecked(false);

            }

            setPrice(SWITCHED_SIZE);
           // switchSizeQuantifier();
        }

    }

    @Override
    public void networkQueryInterface(String Response) {

        try {
            JSONObject jsonObject=new JSONObject(Response);

                final boolean error=jsonObject.getBoolean("error");
                final ResponseStatus responseStatus=new ResponseStatus();
                Bundle bundle=new Bundle();
                bundle.putBoolean("error",error);

                if(!error){


                    //set the cart value...

                    bundle.putString("msg","Added to Cart");
                    // verified successfully..
                    updateCartCount.updateCartCount(jsonObject.getInt("count"));
                    new MyPreferences(context).setCartCount(jsonObject.getInt("count"));

                }else {

                    bundle.putString("msg","Failed Try Again..");
                    // not a valid otp..

                }
                responseStatus.setArguments(bundle);
                responseStatus.show(fragmentActivity.getSupportFragmentManager(),"STATUS");

            int TIME = 1000;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    responseStatus.dismiss();

                    Objects.requireNonNull(getDialog()).dismiss();

                }
            }, TIME);




        } catch (JSONException e) {
            e.printStackTrace();
        }











    }

    @Override
    public void networkQueryError(String error) {





    }

    /*private void switchSizeQuantifier() {

        String offer=String.format("Rs.%s/-", String.valueOf(Product.getProductQualifierPrice(product, QUANTIFIER,SWITCHED_SIZE)));
        if(Product.getIntegerParser(product.getProductOfferStatus())==ConfigVariables.OFFER){
            Offer_Tag.setText(offer);
            Offer_Tag.setPaintFlags(Offer_Tag.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            Offer_Tag.setVisibility(View.GONE);
        }
        price.setText(String.format("Rs.%s/-", String.valueOf(product.getProductQualifiedPrice())));

    }*/

   public interface  UpdateCartCount{

        void updateCartCount(int Count);
    }


}

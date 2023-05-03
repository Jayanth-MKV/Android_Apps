package com.dailyneeds.vugido.app;


import com.dailyneeds.vugido.models.CartProducts;
import com.dailyneeds.vugido.models.OnTheWay;
import com.dailyneeds.vugido.models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonResponseParser {

    private String JsonString;

    public JsonResponseParser(String JsonString){
        this.JsonString=JsonString;
    }

    public JSONObject getJsonOTPParserData() {
        try {
            return new JSONObject(JsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    // Parser Methods for particular task..
   public List<Product> getJsonProductsParser(){
        List<Product> productList=new ArrayList<>();

       try {
           JSONObject jsonObject=new JSONObject(JsonString);
           JSONArray jsonArray=jsonObject.getJSONArray("data");

           for(int i=0;i<jsonArray.length();i++){

               JSONObject object=jsonArray.getJSONObject(i);
               Product product=new Product();
               product.setProductID(object.getString("ID"));
               product.setProductEName(object.getString("E_NAME"));
               product.setProductTNAme(object.getString("T_NAME"));
               product.setProductInStock(object.getString("IN_STOCK"));
               product.setProductImage(object.getString("IMAGE"));
               product.setProductOffer(object.getString("OFFER"));
               product.setProductOfferStatus(object.getString("OFFER_STATUS"));
               product.setProductPrice(object.getString("PRICE"));
               product.setProductQuantity(object.getString("QUANTITY"));
               product.setProductUnit(object.getString("QUANTITY_UNIT"));
               product.setProductTag(object.getString("TAG"));
               product.setDefaultSize(object.getString("DEFAULT_SIZE"));
               product.setUnitInterval(object.getString("UNIT_INTERVAL"));
               product.setSmallPrice(object.getString("S"));
               product.setLargePrice(object.getString("L"));
               product.setMediumPrice(object.getString("M"));
               product.setProductQualifier(object.getString("QUALIFIER"));
               product.setProductCatID(object.getString("CID"));
               productList.add(product);

           }


       } catch (JSONException e) {
           e.printStackTrace();
       }


       return  productList;
    }


    public List<CartProducts> getJsonCartProductParser() {

        List<CartProducts> productList=new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(JsonString);
            JSONArray jsonArray=jsonObject.getJSONArray("data");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                CartProducts cartProducts=new CartProducts();
                cartProducts.setPID(object.getString("PID"));
                cartProducts.setProductEname(object.getString("E_NAME"));
                cartProducts.setProductTname(object.getString("T_NAME"));
                cartProducts.setProductSinglePrice(object.getString("PRICE"));
                cartProducts.setProductQuantity(object.getString("QUANTITY"));
                cartProducts.setProductSwitchedSize(object.getString("SIZE"));
                cartProducts.setProductQualifier(object.getString("QUALIFIER"));
                cartProducts.setProductTotalPrice(object.getString("TOTAL_PRICE"));
                cartProducts.setProductCategory(object.getString("CAT"));
                cartProducts.setUNIQUE_ID(object.getString("ID"));
                int active=Integer.parseInt(object.getString("STATUS"));
                if(active==0)
                cartProducts.setChecked(true);
                else
                    cartProducts.setChecked(false);
                cartProducts.setImage(object.getString("IMAGE"));
                cartProducts.setDescription(object.getString("DES"));
                cartProducts.setACTIVE(object.getString("STATUS"));

                productList.add(cartProducts);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  productList;


    }

    public List<OnTheWay> getJsonOrderInfoProductParser() {

        List<OnTheWay> onTheWayList=new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(JsonString);
            JSONArray jsonArray=jsonObject.getJSONArray("data");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                OnTheWay onTheWay=new OnTheWay();
                onTheWay.setOID(object.getString("ID"));
                onTheWay.setCount(object.getString("C"));
                onTheWay.setDATE(object.getString("D"));
                onTheWay.setTIME(object.getString("T"));
                onTheWay.setDeliveryCharges(object.getString("DC"));
                onTheWay.setOrderStatus(object.getInt("S"));
                onTheWay.setLocation(object.getString("L"));
                onTheWay.setItemPrice(object.getString("TP"));
                double FinalPay= (Double.parseDouble(onTheWay.getDeliveryCharges())+Double.parseDouble(onTheWay.getItemPrice()));
                onTheWay.setFinalPay(String.valueOf(FinalPay));
                onTheWay.setSlot(object.getInt("SLOT"));
                onTheWay.setDMODE(object.getInt("DMODE"));
                onTheWayList.add(onTheWay);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  onTheWayList;


    }

    public List<CartProducts> getJsonOrderedProductParser() {
        List<CartProducts> productList=new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(JsonString);
            JSONArray jsonArray=jsonObject.getJSONArray("data");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                CartProducts cartProducts=new CartProducts();
                cartProducts.setProductEname(object.getString("EN"));
                cartProducts.setProductTname(object.getString("TN"));
                cartProducts.setProductSinglePrice(object.getString("PP"));
                cartProducts.setProductQuantity(object.getString("QUANTITY"));
                cartProducts.setProductSwitchedSize(object.getString("S"));
                cartProducts.setProductQualifier(object.getString("QUALIFIER"));
                cartProducts.setProductTotalPrice(object.getString("TP"));
                productList.add(cartProducts);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  productList;



    }
}

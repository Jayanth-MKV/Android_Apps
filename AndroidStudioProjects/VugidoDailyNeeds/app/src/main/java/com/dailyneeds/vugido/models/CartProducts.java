package com.dailyneeds.vugido.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.dailyneeds.vugido.app.ConfigVariables;

import static com.dailyneeds.vugido.models.Product.getIntegerParser;

public class CartProducts implements Parcelable {

    private String ProductCategory;
    private String PID;
    private String ProductQuantity;
    private String ProductSinglePrice;
    private String ProductTotalPrice;
    private String ProductQualifier;
    private String ProductSwitchedSize;
    private String ProductEname;
    private String ProductTname;
    private boolean checked;
    private String UNIQUE_ID;
    private String Image;
    private String Description;

    public String getACTIVE() {
        return ACTIVE;
    }

    public void setACTIVE(String ACTIVE) {
        this.ACTIVE = ACTIVE;
    }

    private String ACTIVE;





    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private CartProducts(Parcel in) {
        ProductCategory = in.readString();
        PID = in.readString();
        ProductQuantity = in.readString();
        ProductSinglePrice = in.readString();
        ProductTotalPrice = in.readString();
        ProductQualifier = in.readString();
        ProductSwitchedSize = in.readString();
        ProductEname = in.readString();
        ProductTname = in.readString();
        checked = in.readByte() != 0;
        UNIQUE_ID=in.readString();

    }
    public  CartProducts()
    {


    }

    public static final Creator<CartProducts> CREATOR = new Creator<CartProducts>() {
        @Override
        public CartProducts createFromParcel(Parcel in) {
            return new CartProducts(in);
        }

        @Override
        public CartProducts[] newArray(int size) {
            return new CartProducts[size];
        }
    };

    public static String getProductQualifierQuantity(CartProducts cartProducts){


        switch (getIntegerParser(cartProducts.getProductQualifier())){

            case ConfigVariables.SIZE:
                if(getIntegerParser(cartProducts.getProductSwitchedSize())==ConfigVariables.SMALL){
                    return "Small";
                }else if(getIntegerParser(cartProducts.ProductSwitchedSize)==ConfigVariables.MEDIUM){
                    return "Medium";
                }else {
                    return "Large";
                }
            case ConfigVariables.UNITS:

                return cartProducts.getProductQuantity()+" Units";

            case ConfigVariables.QUANTITY:
                String quantity;
                int Q= Integer.parseInt(cartProducts.getProductQuantity());
                if(Q<1000){

                    quantity=Q+"g";

                }else {

                    quantity=(Product.gram2Kg(Q))+"Kg";
                }


                return quantity;
        }
        return null;

    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductSinglePrice() {
        return ProductSinglePrice;
    }

    public void setProductSinglePrice(String productSinglePrice) {
        ProductSinglePrice = productSinglePrice;
    }

    public String getProductTotalPrice() {
        return ProductTotalPrice;
    }

    public void setProductTotalPrice(String productTotalPrice) {
        ProductTotalPrice = productTotalPrice;
    }

    public String getProductQualifier() {
        return ProductQualifier;
    }

    public void setProductQualifier(String productQualifier) {
        ProductQualifier = productQualifier;
    }

    public String getProductSwitchedSize() {
        return ProductSwitchedSize;
    }

    public void setProductSwitchedSize(String productSwitchedSize) {
        ProductSwitchedSize = productSwitchedSize;
    }

    public String getProductEname() {
        return ProductEname;
    }

    public void setProductEname(String productEname) {
        ProductEname = productEname;
    }

    public String getProductTname() {
        return ProductTname;
    }

    public void setProductTname(String productTname) {
        ProductTname = productTname;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ProductCategory);
        parcel.writeString(PID);
        parcel.writeString(ProductQuantity);
        parcel.writeString(ProductSinglePrice);
        parcel.writeString(ProductTotalPrice);
        parcel.writeString(ProductQualifier);
        parcel.writeString(ProductSwitchedSize);
        parcel.writeString(ProductEname);
        parcel.writeString(ProductTname);
        parcel.writeByte((byte) (checked ? 1 : 0));
        parcel.writeString(UNIQUE_ID);
    }

    public String getUNIQUE_ID() {
        return UNIQUE_ID;
    }

    public void setUNIQUE_ID(String UNIQUE_ID) {
        this.UNIQUE_ID = UNIQUE_ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    // global values..

    // these are calculated based on checked ...







}

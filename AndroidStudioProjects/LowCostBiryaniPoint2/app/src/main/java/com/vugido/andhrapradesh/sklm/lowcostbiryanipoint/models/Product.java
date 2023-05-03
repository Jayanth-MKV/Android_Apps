package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String ProductImage;
    private String ProductID;
    private String ProductPrice;
    private String ProductQuantity;
    private String ProductEName;
    private String ProductTNAme;
    private String ProductUnit;
    private String ProductOffer;
    private String ProductTag;
    private String ProductOfferStatus;
    private String ProductInStock;
    private String ProductQualifier;
    private String SmallPrice;
    private String MediumPrice;
    private String LargePrice;
    private String UnitInterval;
    private String DefaultSize;
    private double ProductQualifiedPrice;
    private String ProductCatID;


    public double getProductQualifiedPrice() {
        return ProductQualifiedPrice;
    }

    public void setProductQualifiedPrice(double productQualifiedPrice) {
        ProductQualifiedPrice = productQualifiedPrice;
    }



    // attributes related to cart...
    private int ProductSizeCartCount;
    private float ProductQuantityCartCount;  // in grams..
    private int ProductUnitCartCount;

    public int getProductSizeCartCount() {
        return ProductSizeCartCount;
    }

    public void setProductSizeCartCount(int productSizeCartCount) {
        ProductSizeCartCount = productSizeCartCount;
    }

    public float getProductQuantityCartCount() {
        return ProductQuantityCartCount;
    }

    public void setProductQuantityCartCount(float productQuantityCartCount) {
        ProductQuantityCartCount = productQuantityCartCount;
    }

    public int getProductUnitCartCount() {
        return ProductUnitCartCount;
    }

    public void setProductUnitCartCount(int productUnitCartCount) {
        ProductUnitCartCount = productUnitCartCount;
    }

    public String getUnitInterval() {
        return UnitInterval;
    }

    public void setUnitInterval(String unitInterval) {
        UnitInterval = unitInterval;
    }

    public String getDefaultSize() {
        return DefaultSize;
    }

    public void setDefaultSize(String defaultSize) {
        DefaultSize = defaultSize;
    }

    public String getProductQualifier() {
        return ProductQualifier;
    }

    public void setProductQualifier(String productQualifier) {
        ProductQualifier = productQualifier;
    }

    public String getSmallPrice() {
        return SmallPrice;
    }

    public void setSmallPrice(String smallPrice) {
        SmallPrice = smallPrice;
    }

    public String getMediumPrice() {
        return MediumPrice;
    }

    public void setMediumPrice(String mediumPrice) {
        MediumPrice = mediumPrice;
    }

    public String getLargePrice() {
        return LargePrice;
    }

    public void setLargePrice(String largePrice) {
        LargePrice = largePrice;
    }

    public Product(){

    }
    public Product(Parcel in) {
        ProductImage = in.readString();
        ProductID = in.readString();
        ProductPrice = in.readString();
        ProductQuantity = in.readString();
        ProductEName = in.readString();
        ProductTNAme = in.readString();
        ProductUnit = in.readString();
        ProductOffer = in.readString();
        ProductTag = in.readString();
        ProductOfferStatus = in.readString();
        ProductInStock = in.readString();
        ProductQualifier=in.readString();
        SmallPrice=in.readString();
        MediumPrice=in.readString();
        LargePrice=in.readString();
        UnitInterval=in.readString();
        DefaultSize=in.readString();
        ProductQuantityCartCount=in.readFloat();
        ProductSizeCartCount=in.readInt();
        ProductUnitCartCount=in.readInt();
        ProductQualifiedPrice=in.readDouble();
        ProductCatID=in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public static double offerPercentage(double percentage, double actualPrice){


        double cutOffPrice=((percentage * actualPrice)/ 100);
        return actualPrice-cutOffPrice;

    }


    public String getProductEName() {
        return ProductEName;
    }

    public void setProductEName(String productEName) {
        ProductEName = productEName;
    }

    public String getProductTNAme() {
        return ProductTNAme;
    }

    public void setProductTNAme(String productTNAme) {
        ProductTNAme = productTNAme;
    }

    public String getProductUnit() {
        return ProductUnit;
    }

    public void setProductUnit(String productUnit) {
        ProductUnit = productUnit;
    }

    public String getProductOffer() {
        return ProductOffer;
    }

    public void setProductOffer(String productOffer) {
        ProductOffer = productOffer;
    }

    public String getProductTag() {
        return ProductTag;
    }

    public void setProductTag(String productTag) {
        ProductTag = productTag;
    }

    public String getProductOfferStatus() {
        return ProductOfferStatus;
    }

    public void setProductOfferStatus(String productOfferStatus) {
        ProductOfferStatus = productOfferStatus;
    }

    public String getProductInStock() {
        return ProductInStock;
    }

    public void setProductInStock(String productInStock) {
        ProductInStock = productInStock;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ProductImage);
        parcel.writeString(ProductID);
        parcel.writeString(ProductPrice);
        parcel.writeString(ProductQuantity);
        parcel.writeString(ProductEName);
        parcel.writeString(ProductTNAme);
        parcel.writeString(ProductUnit);
        parcel.writeString(ProductOffer);
        parcel.writeString(ProductTag);
        parcel.writeString(ProductOfferStatus);
        parcel.writeString(ProductInStock);
        parcel.writeString(ProductQualifier);
        parcel.writeString(SmallPrice);
        parcel.writeString(MediumPrice);
        parcel.writeString(LargePrice);
        parcel.writeString(UnitInterval);
        parcel.writeString(DefaultSize);
        parcel.writeFloat(ProductQuantityCartCount);
        parcel.writeInt(ProductSizeCartCount);
        parcel.writeInt(ProductUnitCartCount);
        parcel.writeDouble(ProductQualifiedPrice);
        parcel.writeString(ProductCatID);

    }

    public static double gram2Kg( int grams){

        double g=Double.parseDouble(String.valueOf(grams));
        return (g/1000);

    }


    public static int getIntegerParser(String string){

        return Integer.parseInt(string);
    }




    private static int getInterval(boolean Inc, int value, int Interval){

        if(Inc){

            // increment as per interval
            return  value+Interval;


        }else {
            // decrement as per interval
            return value-Interval;

        }

    }


    public String getProductCatID() {
        return ProductCatID;
    }

    public void setProductCatID(String productCatID) {
        ProductCatID = productCatID;
    }
}

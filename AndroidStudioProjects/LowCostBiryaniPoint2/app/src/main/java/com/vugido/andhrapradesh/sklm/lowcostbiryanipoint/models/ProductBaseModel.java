package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductBaseModel  implements Parcelable{


    private String iMAGE;
    private int pRICE;
    private int oFFER;
    private int oFFERSTATUS;
    private String eNAME;
    private int pID;
    private int uNITINTERVAL;
    private int iNSTOCK;
    private int sID;
    private String Description;
    private String tNAME;
    private int dEFAULTSIZE;
    private int qUANTITYUNIT;
    private int oFFERLIMIT;
    private int qUANTITY;
    private int L;
    private int M;
    private int S;
    private int tAG;
    private int qUALIFIER;



    // attributes related to cart...
    private int ProductSizeCartCount;
    private float ProductQuantityCartCount;  // in grams..
    private int ProductUnitCartCount;
    private double ProductQualifiedPrice;


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



    protected ProductBaseModel(Parcel in) {
        iMAGE = in.readString();
        pRICE = in.readInt();
        oFFER = in.readInt();
        oFFERSTATUS = in.readInt();
        eNAME = in.readString();
        pID = in.readInt();
        uNITINTERVAL = in.readInt();
        iNSTOCK = in.readInt();
        sID = in.readInt();
        Description = in.readString();
        ProductUnitCartCount = in.readInt();
        tNAME = in.readString();
        dEFAULTSIZE = in.readInt();
        qUANTITYUNIT = in.readInt();
        oFFERLIMIT = in.readInt();
        qUANTITY = in.readInt();
        L = in.readInt();
        M = in.readInt();
        S = in.readInt();
        tAG = in.readInt();
        qUALIFIER = in.readInt();
        ProductQuantityCartCount=in.readFloat();
        ProductSizeCartCount=in.readInt();
        ProductQualifiedPrice=in.readDouble();
    }

    public static final Creator<ProductBaseModel> CREATOR = new Creator<ProductBaseModel>() {
        @Override
        public ProductBaseModel createFromParcel(Parcel in) {
            return new ProductBaseModel(in);
        }

        @Override
        public ProductBaseModel[] newArray(int size) {
            return new ProductBaseModel[size];
        }
    };

    public int getProductUnitCartCount() {
        return ProductUnitCartCount;
    }

    public void setProductUnitCartCount(int productUnitCartCount) {
        ProductUnitCartCount = productUnitCartCount;
    }



    public void setTNAME(String tNAME){
        this.tNAME = tNAME;
    }

    public String getTNAME(){
        return tNAME;
    }

    public void setDEFAULTSIZE(int dEFAULTSIZE){
        this.dEFAULTSIZE = dEFAULTSIZE;
    }

    public int getDEFAULTSIZE(){
        return dEFAULTSIZE;
    }

    public void setQUANTITYUNIT(int qUANTITYUNIT){
        this.qUANTITYUNIT = qUANTITYUNIT;
    }

    public int getQUANTITYUNIT(){
        return qUANTITYUNIT;
    }


    public void setOFFERLIMIT(int oFFERLIMIT){
        this.oFFERLIMIT = oFFERLIMIT;
    }

    public int getOFFERLIMIT(){
        return oFFERLIMIT;
    }

    public void setQUANTITY(int qUANTITY){
        this.qUANTITY = qUANTITY;
    }

    public int getQUANTITY(){
        return qUANTITY;
    }


    public void setL(int L){
        this.L = L;
    }

    public int getL(){
        return L;
    }


    public void setM(int M){
        this.M = M;
    }

    public int getM(){
        return M;
    }



    public void setS(int S){
        this.S = S;
    }

    public int getS(){
        return S;
    }


    public void setTAG(int tAG){
        this.tAG = tAG;
    }

    public int getTAG(){
        return tAG;
    }

    public void setQUALIFIER(int qUALIFIER){
        this.qUALIFIER = qUALIFIER;
    }

    public int getQUALIFIER(){
        return qUALIFIER;
    }

    public double getProductQualifiedPrice() {
        return ProductQualifiedPrice;
    }

    public void setProductQualifiedPrice(double productQualifiedPrice) {
        ProductQualifiedPrice = productQualifiedPrice;
    }





    public ProductBaseModel(){


    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setIMAGE(String iMAGE){
        this.iMAGE = iMAGE;
    }

    public String getIMAGE(){
        return iMAGE;
    }

    public void setPRICE(int pRICE){
        this.pRICE = pRICE;
    }

    public int getPRICE(){
        return pRICE;
    }

    public void setOFFER(int oFFER){
        this.oFFER = oFFER;
    }

    public int getOFFER(){
        return oFFER;
    }

    public void setOFFERSTATUS(int oFFERSTATUS){
        this.oFFERSTATUS = oFFERSTATUS;
    }

    public int getOFFERSTATUS(){
        return oFFERSTATUS;
    }

    public void setENAME(String eNAME){
        this.eNAME = eNAME;
    }

    public String getENAME(){
        return eNAME;
    }

    public void setPID(int pID){
        this.pID = pID;
    }

    public int getPID(){
        return pID;
    }

    public void setUNITINTERVAL(int uNITINTERVAL){
        this.uNITINTERVAL = uNITINTERVAL;
    }

    public int getUNITINTERVAL(){
        return uNITINTERVAL;
    }

    public void setINSTOCK(int iNSTOCK){
        this.iNSTOCK = iNSTOCK;
    }

    public int getINSTOCK(){
        return iNSTOCK;
    }

    public void setSID(int sID){
        this.sID = sID;
    }

    public int getSID(){
        return sID;
    }


    public static int offerPercentagePrice(double percentage, double actualPrice,int flat){


        double cutOffPrice=((percentage * actualPrice)/ 100);
        int reduced_price=(int) Math.round(actualPrice-cutOffPrice);

        if((actualPrice-reduced_price)> flat){

            return (int) (actualPrice-flat);

        }else {

            return reduced_price;
        }

    }




    public static double gram2Kg( int grams){

        double g=Double.parseDouble(String.valueOf(grams));
        return (g/1000);

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





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iMAGE);
        dest.writeInt(pRICE);
        dest.writeInt(oFFER);
        dest.writeInt(oFFERSTATUS);
        dest.writeString(eNAME);
        dest.writeInt(pID);
        dest.writeInt(uNITINTERVAL);
        dest.writeInt(iNSTOCK);
        dest.writeInt(sID);
        dest.writeString(Description);
        dest.writeInt(ProductUnitCartCount);
        dest.writeString(tNAME);
        dest.writeInt(dEFAULTSIZE);
        dest.writeInt(qUANTITYUNIT);
        dest.writeInt(oFFERLIMIT);
        dest.writeInt(qUANTITY);
        dest.writeInt(L);
        dest.writeInt(M);
        dest.writeInt(S);
        dest.writeInt(tAG);
        dest.writeInt(qUALIFIER);
        dest.writeFloat(ProductQuantityCartCount);
        dest.writeInt(ProductSizeCartCount);
        dest.writeDouble(ProductQualifiedPrice);
    }
}

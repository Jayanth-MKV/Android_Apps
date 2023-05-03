package com.imgideongo.vfp.models;

public class ItemAbstractModel {

    private String ItemName;
    private String ItemID;
    private String ItemType;
    private int ItemState;
    private int Regular;

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public int getItemState() {
        return ItemState;
    }

    public void setItemState(int itemState) {
        ItemState = itemState;
    }

    public int getRegular() {
        return Regular;
    }

    public void setRegular(int regular) {
        Regular = regular;
    }
}

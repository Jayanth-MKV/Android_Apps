package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.ServiceAvailability;

public class LocationSelector {


    String ZIP;



    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public LocationSelector(String ZIP, int ID, String NAME, boolean isChecked) {
        this.ZIP = ZIP;
        this.ID = ID;
        this.NAME = NAME;
        this.isChecked = isChecked;
    }

    int ID;
    String NAME;
    boolean isChecked;



}

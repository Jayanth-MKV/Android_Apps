package com.imgideongo.vfp.models;

import java.util.List;

public class ActiveOrder {

    private int OID;
    private int QUANTITY;
    private int AID;
    private String AgentName;
    private String Contact;
    private String About;
    private String Pic;
    private List<ActiveOrderItems> activeOrderItemsList;

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String agentName) {
        AgentName = agentName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public List<ActiveOrderItems> getActiveOrderItemsList() {
        return activeOrderItemsList;
    }

    public void setActiveOrderItemsList(List<ActiveOrderItems> activeOrderItemsList) {
        this.activeOrderItemsList = activeOrderItemsList;
    }
}

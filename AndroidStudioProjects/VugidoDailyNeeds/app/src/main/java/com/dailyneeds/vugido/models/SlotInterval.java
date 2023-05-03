package com.dailyneeds.vugido.models;

public class SlotInterval {

    private String SlotName;
    private int SlotInterval;

    public String getSlotName() {
        return SlotName;
    }

    public void setSlotName(String slotName) {
        SlotName = slotName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked;


    public int getSlotInterval() {
        return SlotInterval;
    }

    public void setSlotInterval(int slotInterval) {
        SlotInterval = slotInterval;
    }
}

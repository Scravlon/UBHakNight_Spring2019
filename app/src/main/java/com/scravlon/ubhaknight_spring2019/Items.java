package com.scravlon.ubhaknight_spring2019;

public class Items {
    String itemName;
    String remark;
    int Amount;
    boolean deliverStatus;

    public Items(String name, String remark, int amount) {
        this.itemName = name;
        this.remark = remark;
        this.Amount = amount;
        this.deliverStatus = false;
    }

    public void setDeliverStatus(boolean deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public boolean isDeliverStatus() {
        return deliverStatus;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public String getRemark() {
        return remark;
    }

    public int getAmount() {
        return Amount;
    }
}

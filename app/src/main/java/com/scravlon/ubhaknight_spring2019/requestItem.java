package com.scravlon.ubhaknight_spring2019;

import android.location.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Request Item object type
 */
public class requestItem {
    String requestUser;
    List<Items> listItem;
    Location loc;
    boolean deliverStatus;

    public requestItem(String user) {
        requestUser = user;
        listItem = new ArrayList<>();
        deliverStatus = false;
    }

    public void updateLoc(Location location){
        loc = location;
    }

    public void addItem(String str, String remark,int i){
        Items item = new Items(str,remark,i);
        listItem.add(item);
    }
}

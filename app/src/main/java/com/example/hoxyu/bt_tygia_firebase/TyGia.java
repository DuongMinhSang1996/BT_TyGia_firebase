package com.example.hoxyu.bt_tygia_firebase;

import android.text.Editable;

import java.io.Serializable;

public class TyGia implements Serializable {
    private String GOLD;
    private String DIAMOND;
    private String USD;
    private String GBP;
    private String VND;

    public String getGOLD() {
        return GOLD;
    }

    public void setGOLD(String GOLD) {
        this.GOLD = GOLD;
    }

    public String getDIAMOND() {
        return DIAMOND;
    }

    public void setDIAMOND(String DIAMOND) {
        this.DIAMOND = DIAMOND;
    }

    public String getUSD() {
        return USD;
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }

    public String getGBP() {
        return GBP;
    }

    public void setGBP(String GBP) {
        this.GBP = GBP;
    }

    public String getVND() {
        return VND;
    }

    public void setVND(String VND) {
        this.VND = VND;
    }

    public TyGia() {

    }

    public TyGia(String GOLD, String DIAMOND, String USD, String GBP, String VND) {

        this.GOLD = GOLD;
        this.DIAMOND = DIAMOND;
        this.USD = USD;
        this.GBP = GBP;
        this.VND = VND;
    }
}

package com.reviracio.gui_03;

/**
 * Created by reviracio on 12.04.18.
 */

public class RateModel {

    private String name;
    private int rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public RateModel(String name, int rate){
        this.name=name;
        this.rate=rate;

    }

}

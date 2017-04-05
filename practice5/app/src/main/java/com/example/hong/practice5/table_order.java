package com.example.hong.practice5;

import java.sql.Time;

/**
 * Created by hong on 2017-04-04.
 */

public class table_order {
    String table_name="";
    String time="";
    int spagetti_num=0;
    int pizza_num=0;
    String membership="";
    int price=0;

    table_order(String table_name, String time, int spagetti_num, int pizza_num, String membership, int price) {
        this.table_name = table_name;
        this.time = time;
        this.spagetti_num = spagetti_num;
        this.pizza_num = pizza_num;
        this.membership = membership;
        this.price = price;
    }

    public String getTable_name() {
        return table_name;
    }

    public String getTime() {
        return time;
    }

    public int getSpagetti_num() {
        return spagetti_num;
    }

    public int getPizza_num() {
        return pizza_num;
    }

    public String getMembership() {
        return membership;
    }

    public int getPrice() {
        return price;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSpagetti_num(int spagetti_num) {
        this.spagetti_num = spagetti_num;
    }

    public void setPizza_num(int pizza_num) {
        this.pizza_num = pizza_num;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void reset(String table_name, String time, int spagetti_num, int pizza_num, String membership, int price){
        this.table_name=table_name;
        this.time=time;
        this.spagetti_num=spagetti_num;
        this.pizza_num=pizza_num;
        this.membership=membership;
        this.price=price;
    }
}

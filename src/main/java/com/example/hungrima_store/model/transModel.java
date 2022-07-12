package com.example.hungrima_store.model;

public class transModel {
    private String pname;
    private String date;
    private String t_type;
    private Integer quantity;
    private Integer s_count;

    private Integer id;

    public transModel(String pname, String date , String t_type, Integer quantity, Integer s_count, Integer id){
        this.pname = pname;
        this.date = date;
        this.t_type = t_type;
        this.quantity = quantity;
        this.s_count = s_count;
        this.id = id;
    }
    public transModel(String pname, String date , String t_type, Integer quantity, Integer s_count){
        this.pname = pname;
        this.date = date;
        this.t_type = t_type;
        this.quantity = quantity;
        this.s_count = s_count;
    }

    public String getPname() {
        return pname;
    }

    public String getQuantity() {
        return String.valueOf(quantity);
    }

    public String getS_count() {
        return String.valueOf(s_count);
    }

    public String getDate() {
        return date;
    }

    public String getT_type() {
        return t_type;
    }
}

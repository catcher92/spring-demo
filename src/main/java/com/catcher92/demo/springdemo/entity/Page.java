package com.catcher92.demo.springdemo.entity;

import java.util.List;

/**
 * Created by caoxuedong on 2017/3/14.
 */
public class Page{

    private int total;

    private List<? extends Object> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<? extends Object> getRows() {
        return rows;
    }

    public void setRows(List<? extends Object> rows) {
        this.rows = rows;
    }
}

package com.example.adminvirtualwaiter;

public class ItemDetails
{
    public String name,tablenum;
    public Long cost;

    public ItemDetails() {
        // This is default constructor.
    }
    public ItemDetails(String name, Long cost,String tablenum)
    {
        this.name=name;
        this.cost=cost;
        this.tablenum=tablenum;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
    public String getTablenum() {

        return tablenum;
    }


    public Long getCost() {

        return cost;
    }

    public void setCost(Long cost) {

        this.cost = cost;
    }

}

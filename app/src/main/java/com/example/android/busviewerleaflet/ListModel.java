package com.example.android.busviewerleaflet;

/**
 * Created by Anton on 21.11.2017.
 */

public class ListModel {

    private  String buslinie="";
    private  String start="";
    private  String endpunkt="";

    /*********** Set Methods ******************/

    public void setBuslinie(String buslinie)
    {
        this.buslinie = buslinie;
    }

    public void setStart(String start)
    {
        this.start = start;
    }

    public void setEndpunkt(String endpunkt)
    {
        this.endpunkt = endpunkt;
    }

    /*********** Get Methods ****************/

    public String getBuslinie()
    {
        return this.buslinie;
    }

    public String getStart()
    {
        return this.start;
    }

    public String getEndpunkt()
    {
        return this.endpunkt;
    }
}

package com.example.android.busviewerleaflet;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anton on 21.11.2017.
 */

public class CustomAdapter extends BaseAdapter   implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private Context mContext;
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mStart = new ArrayList<>();
    private ArrayList<String> mEnd = new ArrayList<>();
    private static LayoutInflater inflater=null;
    public Resources res;
    ListModel tempValues=null;
    int i=0;

    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Context context, ArrayList name, ArrayList start, ArrayList end) {

        /********** Take passed values **********/
        mContext = context;
        mName= name;
        mStart = start;
        mEnd = end;

        //res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
       // inflater = ( LayoutInflater )activity.
         //       getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(mName.size()<=0)
            return 1;
        return mName.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView busName;
        public TextView busStart;
        public TextView busEnd;
        public ImageView image;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            //view = inflater.inflate(R.layout.fragment_item, null);
            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_item,parent,false);

            /****** View Holder Object to contain tabitem.xml file elements *****
            holder = new ViewHolder();
            holder.busName = (TextView) view.findViewById(R.id.busName);
            holder.busStart=(TextView)view.findViewById(R.id.busStart);
            holder.busEnd= (TextView)view.findViewById(R.id.busEnd);

            /************  Set holder with LayoutInflater ***********
            view.setTag( holder );
            */

        }
        else {
            //holder = (ViewHolder) view.getTag();
            view = convertView;
        }
/*
        if(data.size()<=0)
        {
            holder.busName.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist *******
            tempValues=null;
            tempValues = ( ListModel ) data.get( position );

            /************  Set Model values in Holder elements **********

            holder.busName.setText( tempValues.getBuslinie() );
            holder.busStart.setText( tempValues.getStart() );
            holder.busEnd.setText(tempValues.getEndpunkt());

            /******** Set Item Click Listner for LayoutInflater for each row *******/

            view.setOnClickListener(new OnItemClickListener( position ));
        //}
        TextView nameTextview = (TextView) view.findViewById(R.id.busName);
        TextView startTextview = (TextView) view.findViewById(R.id.busStart);
        TextView endeTextview = (TextView) view.findViewById(R.id.busEnd);
        if(mName.size()!= 0) {
            String name = mName.get(position);
            nameTextview.setText(mName.get(position));
            startTextview.setText(mStart.get(position));
            endeTextview.setText(mEnd.get(position));
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {


            MainActivity act =(MainActivity) activity;

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            act.onItemClick(mPosition);
        }
    }
}
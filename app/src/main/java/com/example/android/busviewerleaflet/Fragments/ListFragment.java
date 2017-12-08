package com.example.android.busviewerleaflet.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.busviewerleaflet.CustomAdapter;
import com.example.android.busviewerleaflet.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.Callback} interface
 * to handle interaction events.
 */
public class ListFragment extends Fragment {

    private Callback mCallback;

    ArrayList<String> alist = new ArrayList<>();
    ArrayList<String> blist = new ArrayList<>();
    ArrayList<String> clist = new ArrayList<>();

    private CustomAdapter mCustomAdapter;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        mCustomAdapter = new CustomAdapter(getContext(),mCallback,alist,blist,clist);
        view.setAdapter(mCustomAdapter);

        alist.clear();
        blist.clear();
        clist.clear();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Buslinien");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> results, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < results.size(); i++) {
                        String [] a = new String[results.size()];
                        String [] b = new String[results.size()];
                        String [] c = new String[results.size()];

                        a[i] = (results.get(i).getString("Buslinie"));
                        b[i] = (results.get(i).getString("Start"));
                        c[i] = (results.get(i).getString("Ende"));
                        alist.add(a[i]);
                        blist.add(b[i]);
                        clist.add(c[i]);
                        Log.d("Main","Arraylist b"+blist.get(i));


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                mCustomAdapter.notifyDataSetChanged();

            }
        });


        //mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,blist);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mCallback != null) {
           // mCallback.onListItemSelected(string);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Callback {
        // TODO: Update argument type and name
        void onListItemSelected(String string);
    }
}

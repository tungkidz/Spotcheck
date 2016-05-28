package com.spotcheck.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spotcheck.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscoverSpotsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscoverSpotsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverSpotsFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;
	private static final String TAG = "RecyclerViewFragment";
	//private CardView[] cardSet;
    public DiscoverSpotsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DiscoverSpotsFragment newInstance(String param1, String param2) {
        DiscoverSpotsFragment fragment = new DiscoverSpotsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

	    //TODO: populate cards from database?
	    //cardSet = new CardView[5];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View rootView = inflater.inflate(R.layout.fragment_discover_spots, container, false);
	    rootView.setTag(TAG);

	    //RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
	    //recyclerView.setHasFixedSize(true);

	    //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
	    //recyclerView.setLayoutManager(layoutManager);

	    /*for(int i = 0; i < cardSet.length; i++)
	    {
		    //cardSet[i] = inflater.inflate(R.id.card_view, container, false);
	    }

	    mAdapter = new CardAdapter(cardSet);*/
	    // Set CustomAdapter as the adapter for RecyclerView.
	    //mRecyclerView.setAdapter(mAdapter);


	    //recyclerView.setItemAnimator(new DefaultItemAnimator());



        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
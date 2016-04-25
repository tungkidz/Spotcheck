package com.spotcheck.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.spotcheck.R;
import com.spotcheck.api.spotApi.model.Spot;
import com.spotcheck.tasks.SpotAsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewSpotFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewSpotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewSpotFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_PARAM1 = "param1";
    private Spinner spinner;
    private EditText name, city, state, tags;
    private Spot spot = new Spot();
    private SpotTask spotTask = null;
    private Button submitButton;

    final String[]categories = {"Eat & Drink", "Education", "Entertainment", "Shopping"};
    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public NewSpotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverSpotsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewSpotFragment newInstance(String param1, String param2) {
        NewSpotFragment fragment = new NewSpotFragment();
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

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_spot, container, false);

        spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        String type1 = Arrays.asList(categories).get(0);
                        spot.setType(type1);
                        break;
                    case 1:
                        String type2 = Arrays.asList(categories).get(1);
                        spot.setType(type2);
                        break;
                    case 2:
                        String type3 = Arrays.asList(categories).get(2);
                        spot.setType(type3);
                        break;
                    case 3:
                        String type4 = Arrays.asList(categories).get(3);
                        spot.setType(type4);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton = (Button) v.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                spotTask = new SpotTask(setUpSpotInfo(spot));
                spotTask.execute((Void) null);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        name = (EditText) view.findViewById(R.id.spotNameInput);
        city = (EditText) view.findViewById(R.id.cityInput);
        state = (EditText) view.findViewById(R.id.stateInput);
        tags = (EditText) view.findViewById(R.id.tags);
    }

    private Spot setUpSpotInfo(Spot spot){
        if(spot != null){
            spot.setName(name.getText().toString());
            spot.setCity(city.getText().toString());
            spot.setState(state.getText().toString());
            String[] separatedTags = tags.getText().toString().split(", ");
            ArrayList<String> allTags = new ArrayList<>();
            allTags.addAll(Arrays.asList(separatedTags));
            spot.setTags(allTags);
        }
        return spot;
    }

    class SpotTask extends SpotAsyncTask{
        private Spot mySpot;
        public SpotTask(Spot spot){
            super();
            mySpot = spot;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            if(spotAPI == null){
                super.initializeAPI();
            }

            try {
                mySpot = spotAPI.insertSpot(spot).execute();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected  void onPostExecute(Boolean success){
            super.onPostExecute(success);
            if(success){
                Toast.makeText(getActivity(), mySpot.getName() + " was successfully added!", Toast.LENGTH_LONG).show();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
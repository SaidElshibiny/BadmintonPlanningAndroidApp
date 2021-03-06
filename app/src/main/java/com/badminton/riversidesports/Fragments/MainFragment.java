package com.badminton.riversidesports.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.badminton.riversidesports.R;


/**
 * @author Said Elshibiny
 * The main fragment aka the homepage of the app.
 * It contains 6 icons that reflect the 6 pages In the app
 **/

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Create Variables

    ImageButton checkinPlayers;
    ImageButton matchPlayers;
    ImageButton footworkDrills;
    ImageButton timer;
    ImageButton coaches;
    ImageButton rules;

    //Create fragment manager
    FragmentManager fm;


    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //separate view and inflate
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        //getsupportfragmentmanager
        fm = getActivity().getSupportFragmentManager();

        //set the title
        getActivity().setTitle("Riverside Badminton Club");

        //link imagebuttons
        checkinPlayers = view.findViewById(R.id.hpcheckinimage);
        matchPlayers = view.findViewById(R.id.hpmatchplayersimage);
        footworkDrills = view.findViewById(R.id.hpfootworkdrillsimage);
        timer = view.findViewById(R.id.hptimerimage);
        coaches = view.findViewById(R.id.hpcoachesimage);
        rules = view.findViewById(R.id.hprulesimage);

        final FragmentTransaction transaction = fm.beginTransaction();
//        final Fragment timerFragment = new Timer();
//        transaction.add(R.id.main_content, timerFragment);
//        transaction.hide(timerFragment);

        //create buttons
        checkinPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transaction.replace(R.id.main_content, new CheckInPlayers());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        matchPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transaction.replace(R.id.main_content, new MatchingPlayers());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        footworkDrills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transaction.replace(R.id.main_content, new FootworkDrills());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.main_content, new Timer());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        coaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.main_content, new Coaches());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.main_content, new Rules());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        // Inflate the layout for this fragment
        return view;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.saidelshibiny.badmintonplanningandroidapp.Database.DBHelper;
import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddPlayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddPlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPlayerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText firstName;
    EditText lastName;
    EditText ranking;
    FragmentManager fm;
    ImageView avatar1;
    ImageView avatar2;
    ImageView avatar3;
    ImageView avatar4;
    ImageView avatar5;
    ImageView avatar6;
    ImageView avatar7;
    ImageView avatar8;
    ImageView avatar9;
    String avatarName;


    private OnFragmentInteractionListener mListener;

    public AddPlayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPlayerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPlayerFragment newInstance(String param1, String param2) {
        AddPlayerFragment fragment = new AddPlayerFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_player, container, false);
        firstName = (EditText) view.findViewById(R.id.etFirstName);
        lastName = (EditText) view.findViewById(R.id.etLastName);
        ranking = (EditText) view.findViewById(R.id.etRanking);
        avatar1 = (ImageView)view.findViewById(R.id.avatar1);



        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarName = "girl1";
                avatar1.setPadding(18,18,18,18);
                avatar1.setBackgroundColor(Color.BLUE);
            }
        });

        Button submit = (Button) view.findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()
                ||ranking.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.show();
                }else{
                    Player player = new Player(
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            Integer.parseInt(ranking.getText().toString()),
                            0,
                            //To be debug
                            // Integer.parseInt("R.drawable." + avatarName),
                            false);
                    DBHelper db = new DBHelper(getContext());
                    db.addPlayer(player);
                    db.close();
                    fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                }

            }
        });

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

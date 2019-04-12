package com.badminton.riversidesports.Fragments;

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
import android.widget.TextView;

import com.badminton.riversidesports.Database.DBHelper;
import com.badminton.riversidesports.R;
/* @@author Chaonan Chen
 * Last updated on April 03, 2019
 */
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProtectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProtectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProtectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    TextView tvNotice;
    EditText etProtectPassword;
    private static final String PASSWORD = "admin";
    String inputPassword;
    Button btCancel;
    Button btConfirm;
    FragmentManager fm;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProtectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProtectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProtectFragment newInstance(String param1, String param2) {
        ProtectFragment fragment = new ProtectFragment();
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
        View view = inflater.inflate(R.layout.fragment_protect, container, false);
        tvNotice = (TextView) view.findViewById(R.id.textViewNotice);
        etProtectPassword = (EditText) view.findViewById(R.id.editTextProtectPassword);
        inputPassword = etProtectPassword.getText().toString();
        btCancel = (Button) view.findViewById(R.id.buttonCancel);
        btConfirm = (Button) view.findViewById(R.id.buttonConfirm);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((etProtectPassword.getText().toString()).equals(PASSWORD)){
                    DBHelper db = new DBHelper(getContext());
                    db.deleteAllPlayers();
                    fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                } else {
                    tvNotice.setText("Invalid Password: " + etProtectPassword.getText().toString());
                    tvNotice.setTextColor(Color.RED);
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

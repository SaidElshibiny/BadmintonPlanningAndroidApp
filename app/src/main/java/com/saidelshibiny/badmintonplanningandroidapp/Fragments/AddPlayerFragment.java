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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.saidelshibiny.badmintonplanningandroidapp.Database.DBHelper;
import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;

/*
 * Created by Chaonan Chen on March 9, 2019
 * Last updated on March 31, 2019
 * */
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
    TextView tvRanking;
    int ranking;
    SeekBar seekBarRanking;
    FragmentManager fm;
    ImageView avatar0;
    ImageView avatar1;
    ImageView avatar2;
    ImageView avatar3;
    ImageView avatar4;
    ImageView avatar5;
    ImageView avatar6;
    ImageView avatar7;
    ImageView avatar8;
    ImageView avatar9;
    ImageView avatar10;
    ImageView avatar11;
    ImageView avatar12;
    ImageView avatar13;
    ImageView avatar14;
    ImageView avatar15;
    ImageView avatar16;
    ImageView avatar17;
    ImageView avatar18;
    ImageView avatar19;
    ImageView avatar20;
    ImageView avatar21;
    ImageView avatar22;
    ImageView avatar23;
    ImageView avatar24;
//    String avatarName;
    int imageID;
//    int avatarIndex;
//    private int[] drawableImagesID = {
//            R.drawable.boy13,
//            R.drawable.girl1, R.drawable.boy1,
//            R.drawable.girl2, R.drawable.boy2,
//            R.drawable.girl3, R.drawable.boy3,
//            R.drawable.girl4, R.drawable.boy4,
//            R.drawable.girl5, R.drawable.boy5,
//            R.drawable.girl6, R.drawable.boy6,
//            R.drawable.girl7, R.drawable.boy7,
//            R.drawable.girl8, R.drawable.boy8,
//            R.drawable.girl9, R.drawable.boy9,
//            R.drawable.girl10, R.drawable.boy10,
//            R.drawable.girl11, R.drawable.boy11,
//            R.drawable.girl12, R.drawable.boy12,};
    boolean selected = false;
//    private ImageView[] avatars ={avatar0, avatar1, avatar2, avatar3,avatar4, avatar5,avatar6, avatar7,avatar8, avatar9,
//            avatar10, avatar11, avatar12, avatar13,avatar14, avatar15,avatar16, avatar17,avatar18, avatar19,
//            avatar20, avatar21, avatar22, avatar23,avatar24};
    private ArrayList<ImageView> avatars;

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
        //dismiss soft keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        firstName = (EditText) view.findViewById(R.id.etFirstName);
        imm.hideSoftInputFromWindow(firstName.getWindowToken(), 0);
        lastName = (EditText) view.findViewById(R.id.etLastName);
        imm.hideSoftInputFromWindow(lastName.getWindowToken(), 0);
        imageID = R.drawable.profile;

        //available avatars for players to pick from

        avatar0 = (ImageView)view.findViewById(R.id.avatar0);
        avatar1 = (ImageView)view.findViewById(R.id.avatar1);
        avatar2 = (ImageView)view.findViewById(R.id.avatar2);
        avatar3 = (ImageView)view.findViewById(R.id.avatar3);
        avatar4 = (ImageView)view.findViewById(R.id.avatar4);
        avatar5 = (ImageView)view.findViewById(R.id.avatar5);
        avatar6 = (ImageView)view.findViewById(R.id.avatar6);
        avatar7 = (ImageView)view.findViewById(R.id.avatar7);
        avatar8 = (ImageView)view.findViewById(R.id.avatar8);
        avatar9 = (ImageView)view.findViewById(R.id.avatar9);
        avatar10 = (ImageView)view.findViewById(R.id.avatar10);
        avatar11 = (ImageView)view.findViewById(R.id.avatar11);
        avatar12 = (ImageView)view.findViewById(R.id.avatar12);
        avatar13 = (ImageView)view.findViewById(R.id.avatar13);
        avatar14 = (ImageView)view.findViewById(R.id.avatar14);
        avatar15 = (ImageView)view.findViewById(R.id.avatar15);
        avatar16 = (ImageView)view.findViewById(R.id.avatar16);
        avatar17 = (ImageView)view.findViewById(R.id.avatar17);
        avatar18 = (ImageView)view.findViewById(R.id.avatar18);
        avatar19 = (ImageView)view.findViewById(R.id.avatar19);
        avatar20 = (ImageView)view.findViewById(R.id.avatar20);
        avatar21 = (ImageView)view.findViewById(R.id.avatar21);
        avatar22 = (ImageView)view.findViewById(R.id.avatar22);
        avatar23 = (ImageView)view.findViewById(R.id.avatar23);
        avatar24 = (ImageView)view.findViewById(R.id.avatar24);

     //onclick listener for all the avatars
        avatar0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy13;
                selected = !selected;
                if(selected){
                   deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar0);
                }
            }
        });

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl1;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar1);
                }
            }
        });

        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy1;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar2);
                }
            }
        });

        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl2;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar3);
                }
            }
        });

        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy2;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar4);
                }
            }
        });

        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl3;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar5);
                }
            }
        });

        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy3;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar6);
                }
            }
        });

        avatar7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl4;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar7);
                }
            }
        });

        avatar8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy4;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar8);
                }
            }
        });

        avatar9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl5;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar9);
                }
            }
        });

        avatar10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy5;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar10);
                }
            }
        });

        avatar11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl6;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar11);
                }
            }
        });

        avatar12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy6;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar12);
                }
            }
        });

        avatar13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl7;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar13);
                }
            }
        });

        avatar14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy7;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar14);
                }
            }
        });

        avatar15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl8;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar15);
                }
            }
        });

        avatar16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy8;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar16);
                }
            }
        });

        avatar17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl9;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar17);
                }
            }
        });

        avatar18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy9;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar18);
                }
            }
        });

        avatar19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl10;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar19);
                }
            }
        });

        avatar20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy10;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar20);
                }
            }
        });

        avatar21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl11;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar21);
                }
            }
        });

        avatar22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy11;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar22);
                }
            }
        });

        avatar23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.girl12;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar23);
                }
            }
        });

        avatar24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageID = R.drawable.boy12;
                selected = !selected;
                if(selected){
                    deSelectedAllAvatar();
                }else {
                    selectedAvatar(avatar24);
                }
            }
        });

        tvRanking = (TextView) view.findViewById(R.id.tvRanking);
        seekBarRanking = (SeekBar) view.findViewById(R.id.seekBarRanking);
        seekBarRanking.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvRanking.setText("Estimate ranking: " + progress);
                ranking = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button submit = (Button) view.findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.show();
                }else{
                    Player player = new Player(
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            ranking,
                            imageID,
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

    // set style for selected avatar
    public void selectedAvatar(ImageView avatar){
        avatar.setPadding(18,18,18,18);
        avatar.setBackgroundColor(Color.YELLOW);
    }

    // set style for deselected avatar
    public void deSelectedAvatar(ImageView avatar){
        avatar.setPadding(18,18,18,18);
        avatar.setBackgroundColor(Color.WHITE);
    }

    public void deSelectedAllAvatar(){
        deSelectedAvatar(avatar0);
        deSelectedAvatar(avatar1);
        deSelectedAvatar(avatar2);
        deSelectedAvatar(avatar3);
        deSelectedAvatar(avatar4);
        deSelectedAvatar(avatar5);
        deSelectedAvatar(avatar6);
        deSelectedAvatar(avatar7);
        deSelectedAvatar(avatar8);
        deSelectedAvatar(avatar9);
        deSelectedAvatar(avatar10);
        deSelectedAvatar(avatar11);
        deSelectedAvatar(avatar12);
        deSelectedAvatar(avatar13);
        deSelectedAvatar(avatar14);
        deSelectedAvatar(avatar15);
        deSelectedAvatar(avatar16);
        deSelectedAvatar(avatar17);
        deSelectedAvatar(avatar18);
        deSelectedAvatar(avatar19);
        deSelectedAvatar(avatar20);
        deSelectedAvatar(avatar21);
        deSelectedAvatar(avatar22);
        deSelectedAvatar(avatar23);
        deSelectedAvatar(avatar24);


    }

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

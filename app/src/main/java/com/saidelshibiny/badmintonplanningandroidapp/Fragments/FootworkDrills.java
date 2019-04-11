package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FootworkDrills.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FootworkDrills#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FootworkDrills extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FootworkDrills() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FootworkDrills.
     */
    // TODO: Rename and change types and number of parameters
    public static FootworkDrills newInstance(String param1, String param2) {
        FootworkDrills fragment = new FootworkDrills();
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


    /**
     * @author Said Elshibiny
     * Date April 10, 2019
     * */

    /* create the images and the button and sound */

    //Images
    ImageView position1;
    ImageView position2;
    ImageView position3;
    ImageView position4;
    ImageView position5;
    ImageView position6;

    //Button
    Button startDrill;

    //sound
    MediaPlayer one;
    MediaPlayer two;
    MediaPlayer three;
    MediaPlayer four;
    MediaPlayer five;
    MediaPlayer six;

    //timer
    CountDownTimer drillCountDown;

    //Create boolean for counterIsActive to change the button text
    Boolean counterIsActive = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_footwork_drills, container, false);

        //change title
        getActivity().setTitle("Footwork Drills");


        // grab images, button and sound

        //images
        position1 = view.findViewById(R.id.position1);
        position2 = view.findViewById(R.id.position2);
        position3 = view.findViewById(R.id.position3);
        position4 = view.findViewById(R.id.position4);
        position5 = view.findViewById(R.id.position5);
        position6 = view.findViewById(R.id.position6);

        //button
        startDrill = view.findViewById(R.id.startDrillButton);


        //sound
        one = MediaPlayer.create(getContext(), R.raw.one);
        two = MediaPlayer.create(getContext(), R.raw.two);
        three = MediaPlayer.create(getContext(), R.raw.three);
        four = MediaPlayer.create(getContext(), R.raw.four);
        five = MediaPlayer.create(getContext(), R.raw.five);
        six = MediaPlayer.create(getContext(), R.raw.six);


        //onclick listener for the button to start timer for drill
        startDrill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start timer
                controlDrillTimer();
            }
        });

        return view;
    }


    /* Footwork Drill */

    //Create a method for the that will control the drill
    public void controlDrillTimer(){

        if (counterIsActive == false) {

            //set counterIsActive to true
            counterIsActive = true;

            //change the text for the playPauseButton
            startDrill.setText("STOP DRILL");

            //Add 0.1 seconds to time to give time for the script to run and not impact timer
             drillCountDown = new CountDownTimer(100 * 1000 + 100, 5000) {

                @Override
                public void onTick(long millisUntilFinished) {

                        int random = new Random().nextInt((6 - 1) + 1) + 1;

                        switch (random){
                            case 1:
                                //highlight background with color red
                                position1.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                //play number sound to alert of number change
                                one.start();
                                break;
                            case 2:
                                //highlight background with color red
                                position2.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                //play number sound to alert of number change
                                two.start();
                                break;
                            case 3:
                                //highlight background and set color
                                position3.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                //play number sound to alert of number change
                                three.start();
                                break;
                            case 4:
                                //highlight background and set color
                                position4.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                //play number sound to alert of number change
                                four.start();
                                break;
                            case 5:
                                //highlight background and set color
                                position5.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                //play number sound to alert of number change
                                five.start();
                                break;
                            case 6:
                                //highlight background and set color
                                position6.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                //play number sound to alert of number change
                                six.start();
                                break;
                            default:
                                break;

                    }

                }

                @Override
                public void onFinish() {


                    //reset drill
                    resetDrill();


                    //Toast message to indicate that drill is done
                    Toast.makeText(getContext(), " The drill is done", Toast.LENGTH_LONG).show();

                }
            }.start();

        }else {

            //reset drill if button pressed and timer not active
            resetDrill();

        }
    }

    //Method to reset the drill
    public void resetDrill(){

        position1.setColorFilter(Color.TRANSPARENT);
        position2.setColorFilter(Color.TRANSPARENT);
        position3.setColorFilter(Color.TRANSPARENT);
        position4.setColorFilter(Color.TRANSPARENT);
        position5.setColorFilter(Color.TRANSPARENT);
        position6.setColorFilter(Color.TRANSPARENT);

        //Set counter to false
        counterIsActive = false;

        //cancel timer
        drillCountDown.cancel();

        //change the text for the playPauseButton
        startDrill.setText("START DRILL");

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
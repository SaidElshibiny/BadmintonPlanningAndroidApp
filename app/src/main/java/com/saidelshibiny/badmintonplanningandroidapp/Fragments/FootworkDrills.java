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
import android.widget.SeekBar;
import android.widget.TextView;
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

    /* create the images, button, sound, timer, spinner and variables */

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

    //spinner
    //Spinner intervalSpinner;

    //Create boolean for counterIsActive to change the button text
    Boolean counterIsActive = false;
   // Integer intervalTime;
    /*last modified by Chaonan Chen on April 11, 2019
    Use a seek bar to set the time interval so it can be set with 0.01 presicion
    */
    int timeOfInterval = 2000;
    SeekBar sbInterval;
    TextView tvInterval;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_footwork_drills, container, false);

        //change title
        getActivity().setTitle("Footwork Drills");

        // grab images, button, sound and spinner
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

        //spinner
       // intervalSpinner = view.findViewById(R.id.intervalSpinner);
        sbInterval = view.findViewById(R.id.seekBarInterval);
        tvInterval = view.findViewById(R.id.textViewInterval);

        sbInterval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int MIN = 150;
                float miliseconds = progress;
                if(miliseconds < MIN){

                    timeOfInterval = MIN * 10 ;
                    tvInterval.setText("Interval: " + String.format("%02d:%02d", MIN/100, MIN%100)  + " seconds");
                } else {
                    timeOfInterval = progress *10 ;
                    tvInterval.setText("Interval: " +  String.format("%02d:%02d", (int) (progress / 100) ,progress %100 ) + " seconds");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



        //Create an array adapter that will hold the values for the spinner
//        final ArrayAdapter<String> intervalAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.footworkDrillIntervalValues));
//        //set as a drop-down list
//        intervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //set the adapter to the spinner
//        intervalSpinner.setAdapter(intervalAdapter);
//
//
//        //create an on item selected listener for the spinner
//        intervalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                //create a switch for the position
//                switch (position){
//                    case 0:
//                        //set to 1 second
//                        intervalTime = 1000;
//                        break;
//                    case 1:
//                        //set to 2 seconds
//                        intervalTime = 2000;
//                        break;
//                    case 2:
//                        //set to 3 seconds
//                        intervalTime = 3000;
//                        break;
//                    case 3:
//                        //set to 4 seconds
//                        intervalTime = 4000;
//                        break;
//                    case 4:
//                        //set to 5 seconds
//                        intervalTime = 5000;
//                        break;
//                    default:
//                        //set default to 1 second
//                        intervalTime = 1000;
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//                //set interval time to 3 seconds
//                intervalTime = 3000;
//
//            }
//        });


        //onclick listener for the button to start timer for drill
        startDrill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start timer
//                controlDrillTimer(intervalTime);
                controlDrillTimer(timeOfInterval);
            }
        });

        //return the view
        return view;
    }

//    public void updatePlayCountdownTimer(int secondsLeft){
//        int minutes = (int) secondsLeft / 60;
//        int seconds = secondsLeft % 60;
//        tvInterval.setText(String.format("%02d : %02d", minutes, seconds) + " min");
//    }

    /* Footwork Drill */

    //Create a method for the that will control the drill
    public void controlDrillTimer(int setInterval){

        if (counterIsActive == false) {

            //set counterIsActive to true
            counterIsActive = true;

            //make the spinner uneditable
            //intervalSpinner.setEnabled(false);
           tvInterval.setVisibility(getView().INVISIBLE);
            sbInterval.setVisibility(getView().INVISIBLE);
            sbInterval.setEnabled(false);
            //change the text for the playPauseButton
            startDrill.setText("STOP");

            //Add 0.1 seconds to time to give time for the script to run and not impact timer
             drillCountDown = new CountDownTimer(100 * 1000 + 100, setInterval) {

                @Override
                public void onTick(long millisUntilFinished) {
                        //generate number between 1 and 6
                        int random = new Random().nextInt((6 - 1) + 1) + 1;
                        //switch the random number and perform tasks for every number
                        switch (random){
                            case 1:
                                //play number sound to alert of number change
                                one.start();
                                //highlight background with color red
                                position1.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                break;
                            case 2:
                                //play number sound to alert of number change
                                two.start();
                                //highlight background with color red
                                position2.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                break;
                            case 3:
                                //play number sound to alert of number change
                                three.start();
                                //highlight background and set color
                                position3.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                break;
                            case 4:
                                //play number sound to alert of number change
                                four.start();
                                //highlight background and set color
                                position4.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                break;
                            case 5:
                                //play number sound to alert of number change
                                five.start();
                                //highlight background and set color
                                position5.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position6.setColorFilter(Color.TRANSPARENT);
                                break;
                            case 6:
                                //play number sound to alert of number change
                                six.start();
                                //highlight background and set color
                                position6.setColorFilter(Color.RED);
                                //set other numbers to transparent
                                position1.setColorFilter(Color.TRANSPARENT);
                                position2.setColorFilter(Color.TRANSPARENT);
                                position3.setColorFilter(Color.TRANSPARENT);
                                position4.setColorFilter(Color.TRANSPARENT);
                                position5.setColorFilter(Color.TRANSPARENT);
                                break;
//                            default:
//                                break;
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

        //make spinner editable
//        intervalSpinner.setEnabled(true);

        sbInterval.setVisibility(getView().VISIBLE);
      sbInterval.setEnabled(true);
      tvInterval.setVisibility(getView().VISIBLE);


        //change the text for the playPauseButton
        startDrill.setText("START");

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
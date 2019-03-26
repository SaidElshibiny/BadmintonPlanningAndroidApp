package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.saidelshibiny.badmintonplanningandroidapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Timer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Timer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Timer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Timer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Timer.
     */
    // TODO: Rename and change types and number of parameters
    public static Timer newInstance(String param1, String param2) {
        Timer fragment = new Timer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     *
     * @@author Said Elshibiny
     * March 28, 2019
     */

    //Create the variables for the TextViews, Seek Bars and Button

    /* TextViews*/
    TextView warmUpTimerText;
    TextView footworkDrillTimerText;
    TextView breakTimerText;
    TextView matchTimerText;
    TextView cooldownTimerText;

    TextView warmupTV;
    TextView footworkDrillsTV;
    TextView breakTV;
    TextView matchTV;
    TextView cooldownTV;

    /* SeekBars */
    SeekBar warmupSB;
    SeekBar footworkDrillsSB;
    SeekBar breakSB;
    SeekBar matchSB;
    SeekBar cooldownSB;

    /* Button */
    Button playPauseButton;

    /*LinerLayouts*/

    LinearLayout warmupLL;
    LinearLayout footworkDrillLL;
    LinearLayout breakLL;
    LinearLayout matchLL;
    LinearLayout cooldownLL;

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
        //change the title
        getActivity().setTitle("Timer");

        final View view = inflater.inflate(R.layout.fragment_timer, container, false);

        //Grab the TextViews, SeekBars and Button
        /* TextViews*/
        warmUpTimerText = view.findViewById(R.id.warmUpTimerText);
        footworkDrillTimerText = view.findViewById(R.id.footWorkDrillsTimerText);
        breakTimerText = view.findViewById(R.id.breakTimerText);
        matchTimerText = view.findViewById(R.id.matchTimerText);
        cooldownTimerText = view.findViewById(R.id.coolDownTimerText);
        warmupTV = view.findViewById(R.id.warmupText);
        footworkDrillsTV = view.findViewById(R.id.hpfootworkdrillstext);
        breakTV = view.findViewById(R.id.breakText);
        matchTV = view.findViewById(R.id.matchText);
        cooldownTV = view.findViewById(R.id.cooldownText);

        /* SeekBars */
        warmupSB = view.findViewById(R.id.warmupSeekBar);
        footworkDrillsSB = view.findViewById(R.id.footworkDrillsSeekBar);
        breakSB = view.findViewById(R.id.breakSeekBar);
        matchSB = view.findViewById(R.id.matchSeekBar);
        cooldownSB = view.findViewById(R.id.cooldownSeekBar);

        /* Button */
        playPauseButton  = view.findViewById(R.id.playPauseButton);

        /* LinerLayout */
        warmupLL = view.findViewById(R.id.warmpUpLL);
        footworkDrillLL = view.findViewById(R.id.footworkDrillLL);
        warmupLL = view.findViewById(R.id.warmpUpLL);
        warmupLL = view.findViewById(R.id.warmpUpLL);
        warmupLL = view.findViewById(R.id.warmpUpLL);

        /*Setup SeekBars */

        //max values
        warmupSB.setMax(600);
        footworkDrillsSB.setMax(1800);
        breakSB.setMax(600);
        matchSB.setMax(1800);
        cooldownSB.setMax(600);


//        final Integer[] totalminutesArray = {0,0};
//        final Integer[] totalsecondsArray = {0,0};
//
//
//        Integer totalMintesForTimer = (totalminutesArray[0] + totalminutesArray[1]);
//        Integer totalSecondsForTimer = (totalsecondsArray[0] + totalsecondsArray[1]);


        //seekbar listeners
        warmupSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateWarmUpTimer(progress);

//                totalminutesArray[0] = minutes;
//                totalsecondsArray[0] = seconds;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        footworkDrillsSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Take the number of seconds and divide by 60, then round down
                int minutes = (int) progress / 60;

                //Take the total number of seconds and subtract the number of seconds already included in minutes to get the left over seconds.
                int seconds = progress - minutes * 60;

                //string to hold the minutes
                String minuteString = Integer.toString(minutes);

                //check if minutes are equal to zero and add another 0 to reflect in the timer
                if (minuteString == "0"){
                    minuteString = "00";
                }

                //string to hold the seconds
                String secondsString = Integer.toString(seconds);

                //check if seconds are equal to zero and add another 0 to reflect in the timer
                if (secondsString == "0"){
                    secondsString = "00";
                }
//
                //Update the timerTitle
                footworkDrillTimerText.setText(minuteString + ":" + secondsString);

//                totalminutesArray[1] = minutes;
//                totalsecondsArray[1] = seconds;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        breakSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Take the number of seconds and divide by 60, then round down
                int minutes = (int) progress / 60;

                //Take the total number of seconds and subtract the number of seconds already included in minutes to get the left over seconds.
                int seconds = progress - minutes * 60;

                //string to hold the minutes
                String minuteString = Integer.toString(minutes);

                //check if minutes are equal to zero and add another 0 to reflect in the timer
                if (minuteString == "0"){
                    minuteString = "00";
                }

                //string to hold the seconds
                String secondsString = Integer.toString(seconds);

                //check if seconds are equal to zero and add another 0 to reflect in the timer
                if (secondsString == "0"){
                    secondsString = "00";
                }
//
                //Update the timerTitle
                breakTimerText.setText(minuteString + ":" + secondsString);

//                totalminutesArray[1] = minutes;
//                totalsecondsArray[1] = seconds;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        matchSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Take the number of seconds and divide by 60, then round down
                int minutes = (int) progress / 60;

                //Take the total number of seconds and subtract the number of seconds already included in minutes to get the left over seconds.
                int seconds = progress - minutes * 60;

                //string to hold the minutes
                String minuteString = Integer.toString(minutes);

                //check if minutes are equal to zero and add another 0 to reflect in the timer
                if (minuteString == "0"){
                    minuteString = "00";
                }

                //string to hold the seconds
                String secondsString = Integer.toString(seconds);

                //check if seconds are equal to zero and add another 0 to reflect in the timer
                if (secondsString == "0"){
                    secondsString = "00";
                }
//
                //Update the timerTitle
                matchTimerText.setText(minuteString + ":" + secondsString);

//                totalminutesArray[1] = minutes;
//                totalsecondsArray[1] = seconds;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        cooldownSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Take the number of seconds and divide by 60, then round down
                int minutes = (int) progress / 60;

                //Take the total number of seconds and subtract the number of seconds already included in minutes to get the left over seconds.
                int seconds = progress - minutes * 60;

                //string to hold the minutes
                String minuteString = Integer.toString(minutes);

                //check if minutes are equal to zero and add another 0 to reflect in the timer
                if (minuteString == "0"){
                    minuteString = "00";
                }

                //string to hold the seconds
                String secondsString = Integer.toString(seconds);

                //check if seconds are equal to zero and add another 0 to reflect in the timer
                if (secondsString == "0"){
                    secondsString = "00";
                }
//
                //Update the timerTitle
                cooldownTimerText.setText(minuteString + ":" + secondsString);

//                totalminutesArray[1] = minutes;
//                totalsecondsArray[1] = seconds;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlTimer();
            }
        });


//        System.out.print(totalMintesForTimer);
//        System.out.print(totalSecondsForTimer);
//
//        //Update the timerTitle
//        timerTitle.setText("00:" + Integer.toString(totalMintesForTimer) + ":" + Integer.toString(totalSecondsForTimer));

        // Inflate the layout for this fragment
        return view;
    }

    //Create a method for updating the warmup timer
    public void updateWarmUpTimer(int secondsLeft){

        //Take the number of seconds and divide by 60, then round down
        int minutes = (int) secondsLeft / 60;

        //Take the total number of seconds and subtract the number of seconds already included in minutes to get the left over seconds.
        int seconds = secondsLeft - minutes * 60;

        //string to hold the minutes
        String minuteString = Integer.toString(minutes);

        //check if the number of minutes is single digit and add a zero to fix the timet
        if(minutes <= 9){
            minuteString = "0" + minuteString;
        }

        //string to hold the seconds
        String secondsString = Integer.toString(seconds);

        //check if the number of seconds is single digit and add a zero to fix the timet
        if(seconds <= 9){
            secondsString = "0" + secondsString;
        }

        //Update the timerTitle
        warmUpTimerText.setText(minuteString + ":" + secondsString);
    }

    //Create a method for the button, that will control the timers
    public void controlTimer(){
            Log.i("Button Pressed", "Pressed");

            //Add 0.1 seconds to time to give time for the script to run and not impact timer
            new CountDownTimer(warmupSB.getProgress()* 1000 + 100, 1000){

                @Override
                public void onTick(long millisUntilFinished) {

                    /*call updateTimer for warmup
                    /divide the number of milliseconds by 1000 to get seconds and cast as integer*/
                    updateWarmUpTimer((int) millisUntilFinished / 1000);
                    
                }

                @Override
                public void onFinish() {

                    Log.i("Finished","Timer Done");
                    warmUpTimerText.setText("00:00");
                    warmupLL.setBackgroundColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary));

                }
            }.start();
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

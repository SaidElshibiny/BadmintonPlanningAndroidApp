package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.Locale;

import javax.security.auth.login.LoginException;


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

    EditText warmupET;
    EditText footworkDrillsET;
    EditText breakET;
    EditText matchET;
    EditText cooldownET;

    /* SeekBars */
    SeekBar warmupSB;
    SeekBar footworkDrillsSB;
    SeekBar breakSB;
    SeekBar matchSB;
    SeekBar cooldownSB;

    /* Button */
    Button playPauseButton;

    /* TextToSpeech */
    TextToSpeech textToSpeech;

    /*LinerLayouts*/

    LinearLayout warmupLL;
    LinearLayout footworkDrillLL;
    LinearLayout breakLL;
    LinearLayout matchLL;
    LinearLayout cooldownLL;


    /*Countdown timers*/
    CountDownTimer warmUpCD;
    CountDownTimer footworkDrillsCD;
    CountDownTimer breakCD;
    CountDownTimer matchCD;
    CountDownTimer coolDownCD;

    //Create boolean for counterIsActive to indicate that timer is running
    Boolean counterIsActive = false;


    /* MediaPlayer (Sound) */
    //create a MediaPlayer for sound
    MediaPlayer horn;
    MediaPlayer beep;


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
        warmupET = view.findViewById(R.id.warmupText);
        footworkDrillsET = view.findViewById(R.id.footworkDrillText);
        breakET = view.findViewById(R.id.breakText);
        matchET = view.findViewById(R.id.matchText);
        cooldownET = view.findViewById(R.id.cooldownText);

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
        breakLL = view.findViewById(R.id.breakLL);
        matchLL = view.findViewById(R.id.matchLL);
        cooldownLL = view.findViewById(R.id.coolDownLL);

        /* Sound */
        horn = MediaPlayer.create(getContext(), R.raw.airhorn);
        beep = MediaPlayer.create(getContext(), R.raw.beep);


//        //TextToSpeech
//        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if(status == TextToSpeech.SUCCESS){
//                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
//
//                    if(result == TextToSpeech.LANG_MISSING_DATA || result == textToSpeech.LANG_NOT_SUPPORTED){
//                        Log.e("textToSpeech","Language not supported");
//                    }
//                }
//            }else{
//                Log.e("textToSpeech","Init Failed");
//            }
//        });
//
//        public void speak(String x){
//
//
//            String text = t;
//            textToSpeech.setPitch(0.1f);
//            textToSpeech.setSpeechRate(0.1f);
//            textToSpeech.speak(text,textToSpeech.QUEUE_FLUSH, null);
//
//        }



        /*Setup*/

        /* EditText */
        warmupET.setText("Warmup");
        footworkDrillsET.setText("Footwork Drills");
        breakET.setText("Break");
        matchET.setText("Match");
        cooldownET.setText("Cooldown");

        /*SeekBars */

        //max values
        warmupSB.setMax(1800);
        footworkDrillsSB.setMax(1800);
        breakSB.setMax(1800);
        matchSB.setMax(1800);
        cooldownSB.setMax(1800);

        //set the progress and the timer
        warmupSB.setProgress(900);
        warmUpTimerText.setText("15:00");
        footworkDrillsSB.setProgress(1800);
        footworkDrillTimerText.setText("30:00");
        breakSB.setProgress(900);
        breakTimerText.setText("15:00");
        matchSB.setProgress(1800);
        matchTimerText.setText("30:00");
        cooldownSB.setProgress(900);
        cooldownTimerText.setText("15:00");


//        //temp max values for testing
//        warmupSB.setMax(10);
//        footworkDrillsSB.setMax(10);
//        breakSB.setMax(10);
//        matchSB.setMax(10);
//        cooldownSB.setMax(10);

        //SeekBar Listeners

        warmupSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateWarmUpTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        footworkDrillsSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateFootWorkDrillTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        breakSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateBreakTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        matchSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateMatchTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        cooldownSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateCoolDownTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((warmupSB.getProgress() == 0 || footworkDrillsSB.getProgress() == 0 || breakSB.getProgress() == 0 || matchSB.getProgress() == 0 || cooldownSB.getProgress() == 0)){ //&& (warmupET.getText().equals("") == false || footworkDrillsET.getText().equals("") == false || breakET.getText().equals("") == false || matchET.getText().equals("") == false || cooldownET.getText().equals("") == false)){

                    Toast toast = Toast.makeText(getContext(), "Please set the timers first", Toast.LENGTH_LONG);
                    toast.show();

                } else{

                    //start timers in sequence
                    controlWarmUpTimer();

                    //disable SeekBars
                    warmupSB.setEnabled(false);
                    footworkDrillsSB.setEnabled(false);
                    breakSB.setEnabled(false);
                    matchSB.setEnabled(false);
                    cooldownSB.setEnabled(false);

                    //disable EditText
                    warmupET.setEnabled(false);
                    footworkDrillsET.setEnabled(false);
                    breakET.setEnabled(false);
                    matchET.setEnabled(false);
                    cooldownET.setEnabled(false);

                    playPauseButton.setVisibility(view.INVISIBLE);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    /*RESET TIMERS*/

    public void resetAllTimers(){

        //change timer to 10min
        warmUpTimerText.setText("10:00");
        warmupSB.setProgress(600);
        //enable SeekBar
        warmupSB.setEnabled(true);
        //enable EditText
        warmupET.setEnabled(true);
        //cancel the countdown
        warmUpCD.cancel();

        footworkDrillTimerText.setText("30:00");
        footworkDrillsSB.setProgress(1800);
        footworkDrillsSB.setEnabled(true);
        footworkDrillsET.setEnabled(true);
        footworkDrillsCD.cancel();


        breakTimerText.setText("10:00");
        breakSB.setProgress(600);
        breakSB.setEnabled(true);
        breakET.setEnabled(true);
        breakCD.cancel();


        matchTimerText.setText("30:00");
        matchSB.setProgress(1800);
        matchSB.setEnabled(true);
        matchET.setEnabled(true);
        matchCD.cancel();

        cooldownTimerText.setText("10:00");
        cooldownSB.setProgress(600);
        cooldownSB.setEnabled(true);
        cooldownET.setEnabled(true);
        coolDownCD.cancel();

        playPauseButton.setVisibility(getView().VISIBLE);
        playPauseButton.setText("START");
        counterIsActive = false;
    }

    /* WARMUP */

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
    public void controlWarmUpTimer() {

        if (counterIsActive == false) {

        //set counterIsActive to true
        counterIsActive = true;

        //change the text for the playPauseButton
        playPauseButton.setText("STOP");

        //Add 0.1 seconds to time to give time for the script to run and not impact timer
        warmUpCD = new CountDownTimer(warmupSB.getProgress() * 1000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                /*call updateTimer for warmup
                /divide the number of milliseconds by 1000 to get seconds and cast as integer*/
                updateWarmUpTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {

                //change the values of the timer to 00:00
                warmUpTimerText.setText("00:00");

                controlFootWorkDrillsTimer();

                //change the layout background color
//                warmupLL.setBackgroundColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary));

                //play beep sound to alert user when the timer shift to a new stage
                beep.start();

                //Toast
                Toast.makeText(getContext(), warmupET.getText() + " is done", Toast.LENGTH_SHORT).show();


            }
        }.start();

        }
    }

    /* FOOTWORK DRILLS */

    //Create a method for updating the footwork drills timer
    public void updateFootWorkDrillTimer(int secondsLeft){

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
        footworkDrillTimerText.setText(minuteString + ":" + secondsString);
    }

    //Create a method for the button, that will control the timer
    public void controlFootWorkDrillsTimer() {

            //Add 0.1 seconds to time to give time for the script to run and not impact timer
            footworkDrillsCD = new CountDownTimer(footworkDrillsSB.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                /*call updateTimer for warmup
                /divide the number of milliseconds by 1000 to get seconds and cast as integer*/
                    updateFootWorkDrillTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    //change the values of the timer to 00:00
                    footworkDrillTimerText.setText("00:00");

                    controlBreakTimer();

                    //change the layout background color
//                    footworkDrillLL.setBackgroundColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary));


                    //play beep sound to alert user when the timer shift to a new stage
                    beep.start();

                    //Toast
                    Toast.makeText(getContext(), footworkDrillsET.getText() + " is done", Toast.LENGTH_SHORT).show();

                }
            }.start();

        }

    /* BREAK */

    //Create a method for updating the break timer
    public void updateBreakTimer(int secondsLeft){

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
        breakTimerText.setText(minuteString + ":" + secondsString);
    }

    //Create a method for the button, that will control the timer
    public void controlBreakTimer() {

        //Add 0.1 seconds to time to give time for the script to run and not impact timer
        breakCD = new CountDownTimer(breakSB.getProgress() * 1000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                /*call updateTimer for warmup
                /divide the number of milliseconds by 1000 to get seconds and cast as integer*/
                updateBreakTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {

                //change the values of the timer to 00:00
                breakTimerText.setText("00:00");

                controlMatchTimer();

                //change the layout background color
//                breakLL.setBackgroundColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary));


                //play beep sound to alert user when the timer shift to a new stage
                beep.start();

                //Toast
                Toast.makeText(getContext(), breakET.getText() + " is done", Toast.LENGTH_SHORT).show();

            }
        }.start();

    }

    /* MATCH */

    //Create a method for updating the match timer
    public void updateMatchTimer(int secondsLeft){

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
        matchTimerText.setText(minuteString + ":" + secondsString);
    }

    //Create a method for the button, that will control the timer
    public void controlMatchTimer() {

        //Add 0.1 seconds to time to give time for the script to run and not impact timer
        matchCD = new CountDownTimer(matchSB.getProgress() * 1000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                /*call updateTimer for warmup
                /divide the number of milliseconds by 1000 to get seconds and cast as integer*/
                updateMatchTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {

                //change the values of the timer to 00:00
                matchTimerText.setText("00:00");

                controlCoolDownTimer();

                //change the layout background color
//                matchLL.setBackgroundColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary));

                //play beep sound to alert user when the timer shift to a new stage
                beep.start();

                //Toast
                Toast.makeText(getContext(), matchET.getText() + " is done", Toast.LENGTH_SHORT).show();

            }
        }.start();
    }

    /* COOLDOWN */

    //Create a method for updating the cooldown timer
    public void updateCoolDownTimer(int secondsLeft){

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
        cooldownTimerText.setText(minuteString + ":" + secondsString);
    }

    //Create a method for the button, that will control the timer
    public void controlCoolDownTimer() {

        //Add 0.1 seconds to time to give time for the script to run and not impact timer
        coolDownCD = new CountDownTimer(cooldownSB.getProgress() * 1000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                /*call updateTimer for warmup
                /divide the number of milliseconds by 1000 to get seconds and cast as integer*/
                updateCoolDownTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {

                resetAllTimers();

                //play horn sound to alert user when timer is done
                horn.start();

                //Toast
                Toast.makeText(getContext(), cooldownET.getText() + " is done", Toast.LENGTH_SHORT).show();

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
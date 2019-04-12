package com.badminton.riversidesports.Fragments;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.badminton.riversidesports.Database.DBHelper;
import com.badminton.riversidesports.Database.Player;
import com.badminton.riversidesports.R;

import java.util.ArrayList;

/* @@author Chaonan Chen
 * Last updated on April 02, 2019
 */
public class MatchingPlayers extends Fragment {

    FragmentManager fm;
    TextView txTotalPlayer;
    ;
    EditText etGuestName;
    Button btAddGuestPlayer;
    Button btStart;
    Button btReMatch;
    Button btScore;
    TextView tvPlayTime;
    SeekBar sbPlayTime;
    CountDownTimer playTimeCDT;
    Boolean timerIsActive = false;
    MediaPlayer beep;

    DBHelper db;

    LinearLayout court1A;
    LinearLayout court1B;
    LinearLayout court2A;
    LinearLayout court2B;
    LinearLayout court3A;
    LinearLayout court3B;
    LinearLayout court4A;
    LinearLayout court4B;
    LinearLayout court5A;
    LinearLayout court5B;
    LinearLayout court6A;
    LinearLayout court6B;
    LinearLayout court7;

    ArrayList<TextView> checkedPlayerTextViews;
    private ArrayList<Player> checkedPlayers;
    int count;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MatchingPlayers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchingPlayers.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchingPlayers newInstance(String param1, String param2) {
        MatchingPlayers fragment = new MatchingPlayers();
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
        getActivity().setTitle("Matching Players");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matching_players, container, false);
        fm = getActivity().getSupportFragmentManager();
        beep = MediaPlayer.create(getContext(), R.raw.beep);

        court1A = view.findViewById(R.id.court1A);
        court1A.setOnDragListener(new PlayerDragListener());
        court1B = view.findViewById(R.id.court1B);
        court1B.setOnDragListener(new PlayerDragListener());
        court2A = view.findViewById(R.id.court2A);
        court2A.setOnDragListener(new PlayerDragListener());
        court2B = view.findViewById(R.id.court2B);
        court2B.setOnDragListener(new PlayerDragListener());
        court3A = view.findViewById(R.id.court3A);
        court3A.setOnDragListener(new PlayerDragListener());
        court3B = view.findViewById(R.id.court3B);
        court3B.setOnDragListener(new PlayerDragListener());
        court4A = view.findViewById(R.id.court4A);
        court4A.setOnDragListener(new PlayerDragListener());
        court4B = view.findViewById(R.id.court4B);
        court4B.setOnDragListener(new PlayerDragListener());
        court5A = view.findViewById(R.id.court5A);
        court5A.setOnDragListener(new PlayerDragListener());
        court5B = view.findViewById(R.id.court5B);
        court5B.setOnDragListener(new PlayerDragListener());
        court6A = view.findViewById(R.id.court6A);
        court6A.setOnDragListener(new PlayerDragListener());
        court6B = view.findViewById(R.id.court6B);
        court6B.setOnDragListener(new PlayerDragListener());
        court7 = (LinearLayout)view.findViewById(R.id.court7);
        court7.setOnDragListener(new PlayerDragListener());

        checkedPlayers = new ArrayList<>();
        db = new DBHelper(getContext());
        checkedPlayers = db.getCheckedPlayer();
        count = checkedPlayers.size();
        txTotalPlayer = view.findViewById(R.id.numOfMatchPlayers);
        txTotalPlayer.setText(count + " Players");

        for(int i = 0; i<count; i++){
            String playerFirstName = checkedPlayers.get(i).getFirstName();
            String playerLastName = checkedPlayers.get(i).getLastName();
            String name = playerFirstName + " " + playerLastName;
            if(name.length() > 13){
                name = name.substring(0, 13) + "...";
            }
            court7.addView(createNewTextView(name));
        }
        db.close();
    //add a assistant coach before matching if needed
        etGuestName = (EditText) view.findViewById(R.id.guestName);
        btAddGuestPlayer = view.findViewById(R.id.addGuestPlayer);
        btAddGuestPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                if(!etGuestName.getText().toString().isEmpty()) {
                    court7.addView(createNewTextView(etGuestName.getText().toString()));
                    etGuestName.setText("");
                    toast = Toast.makeText(getContext(), "new player added", Toast.LENGTH_SHORT);
                }else{
                    toast = Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT);
                }
                toast.setMargin(50, 50);
                toast.show();
            }
        });

        //re-start matching
        btReMatch = (Button) view.findViewById(R.id.buttonReMatch);
        btReMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //refresh fragment
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new MatchingPlayers());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //go to the page for scoring
        btScore = (Button) view.findViewById(R.id.buttonScore);
        btScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new ScoreFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //set the play time and  then start  count down timer
        tvPlayTime = (TextView) view .findViewById(R.id.textViewPlayTime);
        sbPlayTime = (SeekBar) view.findViewById(R.id.seekBarPlayTime);
        btStart = (Button) view.findViewById(R.id.buttonPlayStart);
        sbPlayTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //seekbar progress value is design to be 0 - 60 minutes, * 60 to get seconds
                updatePlayCountdownTimer(progress * 60);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlPlayCountDownTimer();
                //sbPlayTime.setEnabled(false);
               // btStart.setVisibility(view.INVISIBLE);
            }
        });

        return view;
    }

    //
    public void updatePlayCountdownTimer(int secondsLeft){
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft%60;
        tvPlayTime.setText(String.format("%02d : %02d", minutes,seconds ) + " min");
    }

    public void controlPlayCountDownTimer(){
        if(timerIsActive == false){
            timerIsActive = true;
//            sbPlayTime.setVisibility(getView().INVISIBLE);
            sbPlayTime.setEnabled(false);
            btStart.setText("STOP");
            //the seekbard range is from 0 to 60 minutes. default 10 minutes, 10min * 60sec/min * 1000 millisec =  600k
            playTimeCDT = new CountDownTimer(sbPlayTime.getProgress()*60 * 1000  , 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updatePlayCountdownTimer((int) millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    beep.start();
                    resetTimer();
                }
            }.start();
        }else{
            resetTimer();
        }

    }

    public void resetTimer(){
        timerIsActive = false;
        playTimeCDT.cancel();
//        sbPlayTime.setVisibility(getView().VISIBLE);
        sbPlayTime.setEnabled(true);
        
        //   btStart.setVisibility(getView().VISIBLE);
        btStart.setText("START");
    }

    private final class PlayerTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class PlayerDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.drop_target);
        Drawable normalShape = getResources().getDrawable(R.drawable.court_background);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
//                    RelativeLayout container1 = (RelativeLayout) v;
//                    container1.addView(view);
//                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        lparams.setMargins(5, 2, 5, 2);
        lparams.gravity = Gravity.CENTER;
        final TextView textView = new TextView(getContext());
        textView.setLayoutParams(lparams);
        textView.setBackgroundColor(0x47212F);
        textView.setText(text);
        textView.setTextSize(28);
        textView.setGravity(Gravity.CENTER );
        textView.setTextColor(Color.BLACK);
        textView.setPadding(5, 5, 5, 5);
        textView.setOnTouchListener(new PlayerTouchListener());
        textView.setOnDragListener(new PlayerDragListener());
//        Player player = new Player();
//        player.setFirstName(text);
//        db.addPlayer(player);
//
//        db.close();
        return textView;
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

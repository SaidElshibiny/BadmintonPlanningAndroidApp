package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saidelshibiny.badmintonplanningandroidapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchingPlayers.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchingPlayers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchingPlayers extends Fragment {

    LinearLayout watingArea;
    Button addGuestPlayer;
    EditText mEditText;

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
        view.findViewById(R.id.matchPlayer1A).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer2A).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer3A).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer1B).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer2B).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer3B).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer4A).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer5A).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer6A).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer4B).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer5B).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer6B).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer6B).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.matchPlayer7).setOnTouchListener(new PlayerTouchListener());
        view.findViewById(R.id.court1A).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court2A).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court3A).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court4A).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court5A).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court6A).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court1B).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court2B).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court3B).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court4B).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court5B).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court6B).setOnDragListener(new PlayerDragListener());
        view.findViewById(R.id.court7).setOnDragListener(new PlayerDragListener());

        watingArea = (LinearLayout)view.findViewById(R.id.court1A);
        mEditText = (EditText) view.findViewById(R.id.guestName);
        addGuestPlayer = view.findViewById(R.id.addGuestPlayer);
        addGuestPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watingArea.addView(createNewTextView(mEditText.getText().toString()));
                Toast toast = Toast.makeText(getContext(), "new player added", Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        });

        return view;
    }

    private final class PlayerTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
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

    // TODO: Rename method, update argument and hook method into UI event
    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(5, 5, 5, 5);

        final TextView textView = new TextView(getContext());
        textView.setLayoutParams(lparams);
        textView.setText(text);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER );
        textView.setTextColor(Color.WHITE);
textView.setPadding(10, 10, 10, 10);
        // textView.setTextColor("#FFFFFF");
//        textView.setBackgroundResource(R.drawable.round);
        textView.setOnTouchListener(new PlayerTouchListener());
        textView.setOnDragListener(new PlayerDragListener());
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

package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.saidelshibiny.badmintonplanningandroidapp.Database.DBHelper;
import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckInPlayers.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckInPlayers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckInPlayers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String checkedPlayerNamesKey = "checkedPlayerNamesKey";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Player> players;
    Button btfinishedCheckin;
    Button btAddNewPlayer;

    //for testing purpose
//    Button btRemoveAllPlayers;
    Button btgetAllJuniorPlayers;
    Button btgetAllSeniorPlayers;
    TextView tvNumberOfPlayers;
    int count;

    FragmentManager fm;

    private OnFragmentInteractionListener mListener;

    public CheckInPlayers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckInPlayers.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckInPlayers newInstance(String param1, String param2) {
        CheckInPlayers fragment = new CheckInPlayers();
        Bundle args = new Bundle();
        args.putString(checkedPlayerNamesKey, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(checkedPlayerNamesKey);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_in_players, container, false);
        fm = getActivity().getSupportFragmentManager();
        //set title bar
        getActivity().setTitle("Check-In Player");
       players = new ArrayList<>();
        //get player info from DBHelper - player info not display
        DBHelper db = new DBHelper(getContext());
        players = db.getAllPlayers();
        count = db.getPlayerCount();
        db.close();
        //Display the player info in Gridview
        GridView gridView = (GridView)view.findViewById(R.id.playersGrid);
        final PlayersAdapter playersAdapter = new PlayersAdapter(getContext(),players);
        gridView.setAdapter(playersAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DBHelper db = new DBHelper(getContext());
                Player player = players.get(i);
                player.toggleChecked();
                db.updatePlayer(player);
                db.close();
                playersAdapter.notifyDataSetChanged();
            }
        });

//        final ArrayList<Integer> checkedPlayerNames = savedInstanceState.getIntegerArrayList(checkedPlayerNamesKey);
//        for(int playerid : checkedPlayerNames){
//            for(Player player : players){
//                if(player.getId() == playerid) {
//                    player.setChecked(true);
//                    break;
//                }
//            }
//        }

//        for(Player player : players){
//            if(player.getChecked()){
//                checkedPlayerNames.add(player.getId());
//            }
//            playersAdapter.putIntegerArrayList(checkedPlayerNamesKey, checkedPlayerNames);
//        }

        btfinishedCheckin = (Button) view.findViewById(R.id.finishCheckin);
        btfinishedCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new MatchingPlayers());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tvNumberOfPlayers = (TextView) view.findViewById(R.id.numOfPlayers);
        tvNumberOfPlayers.setText("" + count);
        btAddNewPlayer = (Button) view.findViewById(R.id.addNewPlayer);
        btAddNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new AddPlayerFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btgetAllJuniorPlayers = (Button) view.findViewById(R.id.juniorPlayers);
        btgetAllJuniorPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pop up a window to add new player's first name, last name, and select an avatar from a given list
                DBHelper db = new DBHelper(getContext());
                db.deleteAllPlayers();
                db.addPlayer(new Player("Lisa", "Zhao", 78, R.drawable.girl2,false));
                db.addPlayer(new Player("Mary", "Thomson", 88, R.drawable.boy2,false));
                db.addPlayer(new Player("Dan", "Underwood", 78, R.drawable.girl3,false));
                db.addPlayer(new Player("Laura", "Robertson", 88, R.drawable.boy3,false));
                db.addPlayer(new Player("Abigail", "MacDonald", 78, R.drawable.girl1, false));
                db.addPlayer(new Player("Carolyn", "MacDonald", 88, R.drawable.boy1,false));
                db.addPlayer(new Player("Angela", "Newman", 78, R.drawable.girl2,false));
                db.addPlayer(new Player("Emily", "Paterson", 88, R.drawable.boy2,false));
                db.addPlayer(new Player("Shelly", "Smith", 78, R.drawable.girl3,false));
                db.addPlayer(new Player("John", "Robertson", 88, R.drawable.boy3,false));
                db.addPlayer(new Player("Heather", "Underwood", 78, R.drawable.girl4,false));
                db.addPlayer(new Player("Jessica", "Thomson", 88, R.drawable.boy4,false));
                db.addPlayer(new Player("Karen", "White", 78, R.drawable.girl5,false));
                db.addPlayer(new Player("Steve", "White", 88, R.drawable.boy4,false));
                db.addPlayer(new Player("Leah", "Zhao", 78, R.drawable.girl1,false));
                db.addPlayer(new Player("Maria", "Hemmings", 88, R.drawable.boy1,false));
                count = db.getPlayerCount();
                tvNumberOfPlayers.setText("Total " +  count);
                db.close();
            }
        });
        btgetAllSeniorPlayers = (Button) view.findViewById(R.id.seniorPlayers);
        btgetAllSeniorPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pop up a window to add new player's first name, last name, and select an avatar from a given list
                DBHelper db = new DBHelper(getContext());
                db.deleteAllPlayers();
                db.addPlayer(new Player("Jenna", "Paterson", 78, R.drawable.girl4,false));
                db.addPlayer(new Player("Julian", "Newman", 88, R.drawable.boy4,false));
                db.addPlayer(new Player("Richard", "Hemmings", 88, R.drawable.boy1,false));
                db.addPlayer(new Player("Dominic", "Cameron", 78, R.drawable.girl2,false));
                db.addPlayer(new Player("John", "Buckland", 88, R.drawable.boy2,false));
                db.addPlayer(new Player("Jenny", "Cameron", 78, R.drawable.girl3,false));
                db.addPlayer(new Player("Tim", "Bower", 88, R.drawable.boy3,false));
                db.addPlayer(new Player("Warren", "Black", 78, R.drawable.girl4,false));
                db.addPlayer(new Player("Charles", "Allan", 88, R.drawable.boy4,false));
                count = db.getPlayerCount();
                tvNumberOfPlayers.setText("" +  count);
                db.close();
            }
        });
//        btRemoveAllPlayers = (Button) view.findViewById(R.id.removeAllPlayers);
//        btRemoveAllPlayers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DBHelper db = new DBHelper(getContext());
//                db.deleteAllPlayers();
//
//            }
//        });



        return view;
    } //end of oncreatview

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

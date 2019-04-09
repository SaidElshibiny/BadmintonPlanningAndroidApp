package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.saidelshibiny.badmintonplanningandroidapp.Database.DBHelper;
import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemLongClickListener;

/* @@author Chaonan Chen
        * Last updated on April 02, 2019
        */
public class CheckInPlayers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CHECKED_PLAYERS_ID_KEY = "CHECKED_PLAYERS_ID";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mcheckedPlayersID;
    private String mParam2;
    private ArrayList<Player> players;
    private ArrayList<Player> checkedPlayers;
    int countAllPlayers;
    int countCheckedPlayers;
    PlayersAdapter playersAdapter;
    Button btfinishedCheckin;
    Button btAddNewPlayer;

    //for testing purpose
    Button btRemoveAllPlayers;
    Button btgetAllJuniorPlayers;
    Button btgetAllSeniorPlayers;
    TextView tvNumberOfPlayers;

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
        args.putString(CHECKED_PLAYERS_ID_KEY, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mcheckedPlayersID = getArguments().getString(CHECKED_PLAYERS_ID_KEY);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @NonNull


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_in_players, container, false);
        fm = getActivity().getSupportFragmentManager();
        //set title bar
        getActivity().setTitle("Check-In Player");
       players = new ArrayList<>();
        //get player info from DBHelper
        DBHelper db = new DBHelper(getContext());
        players = db.getAllPlayers();
        for(int i=0; i<players.size(); i++){
            db.updatePlayer(players.get(i));
        }
        countAllPlayers = db.getPlayerCount();
       // db.close();
        tvNumberOfPlayers = (TextView) view.findViewById(R.id.numOfPlayers);

        tvNumberOfPlayers.setText(countAllPlayers + "");
        //Display the player info in Gridview
        final GridView gridView = (GridView)view.findViewById(R.id.playersGrid);
        playersAdapter = new PlayersAdapter(getContext(),players);
        gridView.setAdapter(playersAdapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DBHelper db = new DBHelper(getContext());
                Player player = players.get(i);
                player.toggleChecked();
                db.updatePlayer(player);
                checkedPlayers = db.getCheckedPlayer();
                countCheckedPlayers = checkedPlayers.size();
                tvNumberOfPlayers.setText(countCheckedPlayers + "/" + countAllPlayers );
                db.close();
                playersAdapter.notifyDataSetChanged();
            }
        });

        //long click to remove a player
    gridView.setOnItemLongClickListener(new OnItemLongClickListener(){

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

            DBHelper db = new DBHelper(getContext());
            Player player = players.get(position);
            // player.
            db.deletePlayer(player);
            Toast.makeText(getContext(), "Player " + (position +1) + " removed",  Toast.LENGTH_SHORT).show();
            db.close();
            playersAdapter.notifyDataSetChanged();
            return false;
        }
    });

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
                //pop up a window to add new player's first name, last name, and avatars from a given list
                DBHelper db = new DBHelper(getContext());
                db.deleteAllPlayers();
                db.addPlayer(new Player("Lisa", "Zhao", 72, R.drawable.girl10,false));
                db.addPlayer(new Player("Mary", "Thomson", 58, R.drawable.girl9,false));
                db.addPlayer(new Player("Dan", "Underwood", 64, R.drawable.boy10,false));
                db.addPlayer(new Player("Laura", "Robertson", 48, R.drawable.girl5,false));
                db.addPlayer(new Player("Abigail", "MacDonald", 54, R.drawable.boy5, false));
                db.addPlayer(new Player("Carolyn", "MacDonald", 66, R.drawable.boy9,false));
                db.addPlayer(new Player("Angela", "Newman", 46, R.drawable.girl2,false));
                db.addPlayer(new Player("Emily", "Paterson", 64, R.drawable.girl3,false));
                db.addPlayer(new Player("Shelly", "Smith", 72, R.drawable.girl6,false));
                db.addPlayer(new Player("John", "Robertson", 80, R.drawable.boy6,false));
                db.addPlayer(new Player("Heather", "Underwood", 62, R.drawable.girl7,false));
                db.addPlayer(new Player("Jessica", "Thomson", 56, R.drawable.girl8,false));
                db.addPlayer(new Player("David", "White", 58, R.drawable.boy4,false));
                db.addPlayer(new Player("Steve", "White", 52, R.drawable.boy8,false));
                db.addPlayer(new Player("Leah", "Zhao", 38, R.drawable.girl1,false));
                db.addPlayer(new Player("Mario", "Hemmings", 44, R.drawable.boy7,false));
                countAllPlayers = db.getPlayerCount();
                tvNumberOfPlayers.setText("" + countAllPlayers);
                db.close();
                //refresh fragment
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new CheckInPlayers());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btgetAllSeniorPlayers = (Button) view.findViewById(R.id.seniorPlayers);
        btgetAllSeniorPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pop up a window to add new player's first name, last name, and select an avatar from a given list
                DBHelper db = new DBHelper(getContext());
                db.deleteAllPlayers();
                db.addPlayer(new Player("Jenna", "Paterson", 70, R.drawable.girl4,false));
                db.addPlayer(new Player("Julian", "Newman", 68, R.drawable.boy4,false));
                db.addPlayer(new Player("Richard", "Hemmings", 74, R.drawable.boy1,false));
                db.addPlayer(new Player("Dominic", "Cameron", 36, R.drawable.girl2,false));
                db.addPlayer(new Player("John", "Buckland", 52, R.drawable.boy2,false));
                db.addPlayer(new Player("Jenny", "Cameron", 58, R.drawable.girl3,false));
                db.addPlayer(new Player("Tim", "Bower", 64, R.drawable.boy3,false));
                db.addPlayer(new Player("Warren", "Black", 56, R.drawable.boy5,false));
                db.addPlayer(new Player("Charles", "Allan", 54, R.drawable.boy6,false));
                countAllPlayers = db.getPlayerCount();
                tvNumberOfPlayers.setText("" + countAllPlayers);
//                btgetAllSeniorPlayers.setBackgroundColor(Color.GREEN);
                db.close();
                //refresh fragment
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new CheckInPlayers());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btRemoveAllPlayers = (Button) view.findViewById(R.id.removeAllPlayers);
        btRemoveAllPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_content, new ProtectFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

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

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        final ArrayList<Integer> checkedPlayerIDs = savedInstanceState.getIntegerArrayList(CHECKED_PLAYERS_ID_KEY);
//        for(int playerid : checkedPlayerIDs){
//            for(Player player : players){
//                if(player.getId() == playerid) {
//                    player.setChecked(true);
//                    break;
//                }
//            }
//        }
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

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
        //use a fab to add guest player
        MainActivity.fab.setImageResource(R.drawable.ic_add_circle_black_24dp);
        MainActivity.fab.show();
        MainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_content, new AddPlayerFragment());
                ft.commit();
            }
        });

       players = new ArrayList<>();
        //get player info from DBHelper - player info not display
        DBHelper db = new DBHelper(getContext());
        players = db.getAllPlayers();
        db.close();
        //Display the player info in Gridview
        GridView gridView = (GridView)view.findViewById(R.id.playersGrid);
        final PlayersAdapter playersAdapter = new PlayersAdapter(getContext(),players);
        gridView.setAdapter(playersAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player player = players.get(i);
                player.toggleChecked();
                playersAdapter.notifyDataSetChanged();
            }
        });

//        final ArrayList<Integer> checkedPlayerNames = savedInstanceState.getIntegerArrayList(checkedPlayerNamesKey);
//        for(int playerid : checkedPlayerNames){
//            for(Player player : players){
//                if(player.getPlayerId() == playerid) {
//                    player.setChecked(true);
//                    break;
//                }
//            }
//        }

//        for(Player player : players){
//            if(player.getChecked()){
//                checkedPlayerNames.add(player.getPlayerId());
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

        btAddNewPlayer = (Button) view.findViewById(R.id.addNewPlayer);
        btAddNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pop up a window to add new player's first name, last name, and select an avatar from a given list
                DBHelper db = new DBHelper(getContext());
                Player p1 = new Player("Chaonan", "Chen", 88, R.drawable.girl5, false);
                db.addPlayer(p1);
                Player p2 = new Player("Mike", "Duan", 78, R.drawable.boy4, false);
                db.addPlayer(p2);
                db.close();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

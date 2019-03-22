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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Player> players;
    Button btfinishedCheckin;
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
        fm = getActivity().getSupportFragmentManager();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_in_players, container, false);
        //set title bar
        getActivity().setTitle("Check-In Player");

       players = new ArrayList<>();
        //get player info from here - player info displayed
        players.add(new Player(0, "Sally", "Zhao", 78, R.drawable.girl1, false));
        players.add(new Player(1, "John", "Json", 88, R.drawable.boy1,false));
        players.add(new Player(2, "Mitch", "Zhao", 78, R.drawable.girl2,false));
        players.add(new Player(3, "Sitch", "Json", 88, R.drawable.boy2,false));
        players.add(new Player(4, "Shelly", "Smith", 78, R.drawable.girl3,false));
        players.add(new Player(5, "John", "Yuan", 88, R.drawable.boy3,false));
        players.add(new Player(6, "Sally", "Chen", 78, R.drawable.girl4,false));
        players.add(new Player(7, "Jess", "Json", 88, R.drawable.boy4,false));
        players.add(new Player(8, "Shally", "Zhao", 78, R.drawable.girl5,false));
        players.add(new Player(9, "Steve", "Json", 88, R.drawable.boy4,false));
        players.add(new Player(10, "Even", "Zhao", 78, R.drawable.girl1,false));
        players.add(new Player(11, "macheal", "Json", 88, R.drawable.boy1,false));
        players.add(new Player(12, "Efan", "Zhao", 78, R.drawable.girl2,false));
        players.add(new Player(13, "John", "Json", 88, R.drawable.boy2,false));
        players.add(new Player(14, "Jenny", "Zhao", 78, R.drawable.girl3,false));
        players.add(new Player(15, "Laura", "Json", 88, R.drawable.boy3,false));
        players.add(new Player(16, "Jenna", "Zhao", 78, R.drawable.girl4,false));
        players.add(new Player(17, "Sohan", "Json", 88, R.drawable.boy4,false));
        players.add(new Player(18, "macheal", "Json", 88, R.drawable.boy1,false));
        players.add(new Player(19, "Efan", "Zhao", 78, R.drawable.girl2,false));
        players.add(new Player(20, "John", "Json", 88, R.drawable.boy2,false));
        players.add(new Player(21, "Jenny", "Zhao", 78, R.drawable.girl3,false));
        players.add(new Player(22, "Laura", "Json", 88, R.drawable.boy3,false));
        players.add(new Player(23, "Jenna", "Zhao", 78, R.drawable.girl4,false));
        players.add(new Player(24, "Sohan", "Json", 88, R.drawable.boy4,false));
        //get player info from DBHelper - player info not display
//        DBHelper dbHelper = new DBHelper(this.getContext());
//        players = dbHelper.getAllPlayers();

       /* ====== //Display the players in Recycler view
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.playersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//        PlayersCustomAdapter adapter = new PlayersCustomAdapter(players, getContext());
        PlayersCustomAdapter adapter = new PlayersCustomAdapter(players);
        recyclerView.setAdapter(adapter);
*/
        //Display the player info in Gridview
        GridView gridView = (GridView)view.findViewById(R.id.playersGrid);
        final PlayersAdapter playersAdapter = new PlayersAdapter(getContext(), players);
        gridView.setAdapter(playersAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player player = players.get(i);
                player.toggleChecked();
                playersAdapter.notifyDataSetChanged();
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

        return view;
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

package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;

public class PlayersCustomAdapter extends RecyclerView.Adapter {
    CustomViewHolder viewHolder;
    private ArrayList<Player> players;
    private Context context;
    FragmentManager fm;
    View view;

    public PlayersCustomAdapter(ArrayList<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_recycler_view, parent, false);
        viewHolder = new CustomViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Player player = players.get(position);
        final MainActivity mainActivity = (MainActivity) context;
        ((CustomViewHolder) holder).firstName.setText(player.getFirstName());
        ((CustomViewHolder) holder).lastName.setText(player.getLastName());
     //   ((CustomViewHolder) holder).imgPlayer.setImageResource(player.getImageId());
        ((CustomViewHolder) holder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                view.setSelected(true);
                if(!isLongClick){
                    Bundle bundle = new Bundle();
                    bundle.putString("Player", player.getPlayerId().toString());

                    MatchingPlayers fragment1 = new MatchingPlayers();
                    fragment1.setArguments(bundle);
                    FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_content, fragment1);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            }

        });

    }


    @Override
    public int getItemCount() {
        if(players != null) {
            return players.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    //    protected ImageView imgPlayer;
        protected TextView firstName;
        protected TextView lastName;
        private ItemClickListener itemClickListener;

        public CustomViewHolder(View view) {
            super(view);
            this.firstName =  view.findViewById(R.id.firstName);
            this.lastName =  view.findViewById(R.id.lastName);
           // this.imgPlayer = view.findViewById(R.id.imgPlayer);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),true);
            return true;
        }
    }
}

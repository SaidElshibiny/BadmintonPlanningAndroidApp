package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;

public class PlayersCustomAdapter extends RecyclerView.Adapter {

    private ArrayList<Player> players;
    private Context context;
    View view;
    private int lastPostion = -1;
    //FragmentManager fm;


    public PlayersCustomAdapter(ArrayList<Player> players) {
//    public PlayersCustomAdapter(ArrayList<Player> players, Context context) {
        this.players = players;
//        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_recycler_view, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Player player = players.get(position);
        final MainActivity mainActivity = (MainActivity) context;
        CustomViewHolder holder1 = (CustomViewHolder) holder;
        holder1.firstName.setText(player.getFirstName());
        holder1.lastName.setText(player.getLastName());
        holder1.imageID.setImageResource(player.getImageID());

//        (holder1).setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                view.setSelected(true);
//                if(!isLongClick){
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Player", player.getPlayerId().toString());
//
//                    MatchingPlayers fragment1 = new MatchingPlayers();
//                    fragment1.setArguments(bundle);
//                    FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.main_content, fragment1);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//
//                }
//            }
//
//        });

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPostion) ? R.anim.load_down_anim : R.anim.load_up_anim);
        view.startAnimation(animation);
        lastPostion = position;
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
        protected ImageView imageID;
        private ItemClickListener itemClickListener;

        public CustomViewHolder(View view) {
            super(view);
            this.firstName =  view.findViewById(R.id.firstName);
            this.lastName =  view.findViewById(R.id.lastName);
            this.imageID = view.findViewById(R.id.playerImage);

          //  itemView.setOnClickListener(this);
         //   itemView.setOnLongClickListener(this);
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

package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;

public class PlayersCustomAdapter extends RecyclerView.Adapter {

    private ArrayList<Player> players;
    SparseBooleanArray itemStateArray = new SparseBooleanArray();
    private Context context;
    View view;
    private int lastPostion = -1;
    //FragmentManager fm;


    public PlayersCustomAdapter(ArrayList<Player> players) {
//    public PlayersCustomAdapter(ArrayList<Player> players, Context context) {
        this.players = players;
//        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
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
        if (!itemStateArray.get(position, false)) {
            holder1.checkBox.setChecked(false);
        }else {
            holder1.checkBox.setChecked(true);
        }


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
        protected CheckBox checkBox;
        protected CheckedTextView mCheckedTextView;

        private ItemClickListener itemClickListener;
       private SparseBooleanArray selectedItems = new SparseBooleanArray();

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.firstName =  itemView.findViewById(R.id.firstName);
            this.lastName =  itemView.findViewById(R.id.lastName);
            this.imageID = itemView.findViewById(R.id.playerImage);
            this.checkBox = itemView.findViewById(R.id.checkbox);
            itemView.setOnClickListener(this);
         //   itemView.setOnLongClickListener(this);
        }


//        public void setItemClickListener(ItemClickListener itemClickListener){
//            this.itemClickListener = itemClickListener;
//        }
        @Override
        public void onClick(View view) {
//           // set the background for selected item
//            if(selectedItems.get(getAdapterPosition(), false)){
//
//                view.setSelected(false);
//            }else{
//                selectedItems.put(getAdapterPosition(),true);
//                view.setSelected(true);
//            }

            int adapterPosition = getAdapterPosition();
            if(!itemStateArray.get(adapterPosition, false)){
//                selectedItems.delete(getAdapterPosition());
//                view.setSelected(false);
                checkBox.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            }else {
//                selectedItems.put(getAdapterPosition(),true);
//                view.setSelected(true);
                checkBox.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }

            //set the check status for the checked item


           // itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),true);
            return true;
        }
    }



}

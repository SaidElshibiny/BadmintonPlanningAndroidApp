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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saidelshibiny.badmintonplanningandroidapp.Database.DBHelper;
import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;

public class PlayersCustomAdapter extends RecyclerView.Adapter {

    private ArrayList<Player> players;
    SparseBooleanArray itemStateArray = new SparseBooleanArray();
    private Context context;
    DBHelper db;
    View view;
    private int lastPostion = -1;

    public PlayersCustomAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_recycler_view, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    //to reflect the user selected or unselected, implement onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        db = new DBHelper(view.getContext());
        final Player player = players.get(position);
        final MainActivity mainActivity = (MainActivity) context;
        CustomViewHolder holder1 = (CustomViewHolder) holder;
        holder1.firstName.setText(player.getFirstName());
        holder1.lastName.setText(player.getLastName());
        holder1.imageID.setImageResource(player.getImageID());
        holder1.currentScore.setText(player.getRanking() + "");
        holder1.btAddScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setRanking(player.getRanking() + 2);
                player.setChecked(true);
                db.updatePlayer(player);
                Toast toast = Toast.makeText(view.getContext(),  player.getFirstName() + " scored 2 more points", Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();

            }
        });
        holder1.btMinusScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setRanking(player.getRanking() - 2);
                player.setChecked(true);
                db.updatePlayer(player);
                Toast toast = Toast.makeText(view.getContext(),  player.getFirstName() + " decrease 2 points", Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();

            }
        });

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
        protected TextView firstName;
        protected TextView lastName;
        protected ImageView imageID;
        protected CheckBox checkBox;
        protected TextView currentScore;
        protected Button btAddScore;
        protected Button btMinusScore;
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
            this.currentScore = itemView.findViewById(R.id.currentScore);
            this.btAddScore = itemView.findViewById(R.id.buttonAddScore);
            this.btMinusScore = itemView.findViewById(R.id.buttonMinusScore);
        }

        @Override
        public void onClick(View view) {
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),true);
            return true;
        }
    }

}

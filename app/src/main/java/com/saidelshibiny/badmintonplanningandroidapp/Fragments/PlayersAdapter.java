package com.saidelshibiny.badmintonplanningandroidapp.Fragments;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.saidelshibiny.badmintonplanningandroidapp.Database.Player;
import com.saidelshibiny.badmintonplanningandroidapp.R;

import java.util.ArrayList;

public class PlayersAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<Player> players;
    private ItemClickListener itemClickListener;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    public PlayersAdapter(Context mContext, ArrayList<Player> players) {
        this.mContext = mContext;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Player player = players.get(position);
        if(view == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.linearlayout_player, null);
        }

        final ImageView imageView = (ImageView)view.findViewById(R.id.playerImageGrid);
        final TextView firstName =  (TextView)view.findViewById(R.id.firstNameGrid);
        final TextView lastName =  (TextView)view.findViewById(R.id.lastNameGrid);
        final ImageView imageChecked = (ImageView)view.findViewById(R.id.checkImageGrid);
        final ViewHolder viewHolder = new ViewHolder(firstName, lastName, imageView, imageChecked);
        view.setTag(viewHolder);
        final ViewHolder viewHolder1 =  (ViewHolder)view.getTag();
        viewHolder1.firstName.setText(player.getFirstName());
        viewHolder1.lastName.setText(player.getLastName());
        viewHolder1.imageID.setImageResource(player.getImageID());
        viewHolder1.imageCheck.setImageResource(player.getChecked()? R.drawable.check_enabled:R.drawable.check_disabled);
        return view;
    }

    private class ViewHolder {
        private final TextView firstName;
        private final TextView lastName;
        private final ImageView imageID;
        private final ImageView imageCheck;
        public ViewHolder(TextView firstName, TextView lastName, ImageView imageID, ImageView imageCheck) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.imageID = imageID;
            this.imageCheck = imageCheck;
        }
    }

} // end of base adapter

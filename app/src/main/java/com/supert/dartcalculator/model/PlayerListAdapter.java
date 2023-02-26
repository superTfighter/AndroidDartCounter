package com.supert.dartcalculator.model;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.supert.dartcalculator.R;

import java.util.List;

public class PlayerListAdapter extends BaseAdapter {

    private List<IPlayer> playerList;
    private Context context;

    public PlayerListAdapter(@NonNull Context context, @NonNull List<IPlayer> playerList)
    {
        this.playerList = playerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Player getItem(int i) {
        return (Player)playerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(context).inflate(R.layout.player_list_item, parent, false);
        }

        //Set name
        TextView player_name = currentItemView.findViewById(R.id.player_list_name);
        player_name.setText(getItem(position).getName());

        //Delete button
        Button deleteButton = currentItemView.findViewById(R.id.delete_button);
        deleteButton.setTag(position);

        deleteButton.setOnClickListener(arg0 -> {
            int pos = (int)arg0.getTag();
            playerList.remove(pos);
            PlayerListAdapter.this.notifyDataSetChanged();
        });

        return currentItemView;
    }
}

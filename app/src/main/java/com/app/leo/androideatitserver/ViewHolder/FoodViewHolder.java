package com.app.leo.androideatitserver.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.leo.androideatitserver.Common.Common;
import com.app.leo.androideatitserver.R;
import com.app.leo.androideatitserver.Interface.ItemClickListener;

public class FoodViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener

{
    public TextView food_name;
    public ImageView food_image;

    private ItemClickListener itemClickListener;



    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name =itemView.findViewById(R.id.food_name);
        food_image=itemView.findViewById(R.id.food_image);//////imageView??
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

    }

    public void setItemClickListner(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(itemView,getAdapterPosition(),false);
    }


    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        contextMenu.setHeaderTitle("Select the action");
        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,0,getAdapterPosition(), Common.DELETE);

    }
}
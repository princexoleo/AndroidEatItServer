package com.app.leo.androideatitserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.leo.androideatitserver.Common.Common;
import com.app.leo.androideatitserver.Interface.ItemClickListener;
import com.app.leo.androideatitserver.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
       View.OnCreateContextMenuListener

{
    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;



    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName=itemView.findViewById(R.id.menu_name);
        imageView=itemView.findViewById(R.id.menu_image);//////imageView??
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

package com.app.leo.androideatitserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.app.leo.androideatitserver.Common.Common;
import com.app.leo.androideatitserver.Interface.ItemClickListener;
import com.app.leo.androideatitserver.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {
    public TextView txtOrderId,txtOrderStatus,txtOrderAddress,txtOrderPhone;
    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderAddress=itemView.findViewById(R.id.order_address);
        txtOrderStatus=itemView.findViewById(R.id.order_status);
        txtOrderPhone=itemView.findViewById(R.id.order_phone);
        txtOrderId=itemView.findViewById(R.id.order_name);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setItemClickListner(ItemClickListener itemClickListner) {
        this.itemClickListener = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(itemView,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        contextMenu.setHeaderTitle("Select the Action");
        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,1,getAdapterPosition(), Common.DELETE);
    }
}


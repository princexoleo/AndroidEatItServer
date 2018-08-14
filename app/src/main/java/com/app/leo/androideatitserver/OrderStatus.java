package com.app.leo.androideatitserver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.LayoutInflater;


import com.app.leo.androideatitserver.Common.Common;
import com.app.leo.androideatitserver.Interface.ItemClickListener;
import com.app.leo.androideatitserver.Model.Request;
import com.app.leo.androideatitserver.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class OrderStatus extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHolder>adapter;

    FirebaseDatabase db;
    DatabaseReference requestRef;
    MaterialSpinner materialSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        //init firebase
        db=FirebaseDatabase.getInstance();
        requestRef =db.getReference("Requests");

        //init view
        recyclerView=findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();// load all the order
    }

    private void loadOrders() {
        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requestRef
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {

                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderAddress.setText(model.getAddress());
                viewHolder.txtOrderPhone.setText(model.getPhone());

                viewHolder.setItemClickListner(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //just implememnts to fixed crash when click to this item
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals(Common.UPDATE))
        {
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(), 
                    adapter.getItem(item.getOrder()));
        }
        else if(item.getTitle().equals(Common.DELETE))
        {
            deleteOrder(adapter.getRef(item.getOrder()).getKey());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteOrder(String key) {

        requestRef.child(key).removeValue();
    }

    private void showUpdateDialog(String key, final Request item) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderStatus.this);
        alertDialog.setTitle("Update Orders");
        alertDialog.setMessage("Please change order status: ");

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.update_lorder_layout,null);

        materialSpinner= view.findViewById(R.id.statusSpinnerID);
        materialSpinner.setItems("Placed","On my way","Shipped");

        alertDialog.setView(view);

        final String localKey= key;
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                item.setStatus(String.valueOf(materialSpinner.getSelectedIndex()));

                requestRef.child(localKey).setValue(item);
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }
}

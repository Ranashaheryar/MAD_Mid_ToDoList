package com.rsk.mad_mid_todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import static android.view.View.GONE;

public class ItemAdapter extends  RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    LinkedList<Item> items;
    public interface ItemClicked
    {

        void onRemoveClicked(int index);
        void onCheckClick(int index);
    }

    ItemClicked myActivity;

    public ItemAdapter(Context context, LinkedList<Item> list) {

        myActivity=(ItemClicked) context;
        items=list;


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvListItemName;
        ImageView ivRemoveItem,ivCheckOnItemDone;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListItemName=itemView.findViewById(R.id.tvListItemName);
            ivRemoveItem=itemView.findViewById(R.id.ivRemoveItem);
            ivCheckOnItemDone=itemView.findViewById(R.id.ivCheckOnItemDone);

            ivRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity.onRemoveClicked(items.indexOf(itemView.getTag()));
                }
            });

            ivCheckOnItemDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity.onCheckClick(items.indexOf(itemView.getTag()));
                }
            });





        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(items.get(position));
        holder.tvListItemName.setText(items.get(position).getName());

        if(AddList_Activity.editEnable==true && AddList_Activity.onDetail==true)
        {
            holder.ivRemoveItem.setVisibility(View.VISIBLE);
            holder.ivRemoveItem.setImageResource(R.drawable.remove);
            holder.ivCheckOnItemDone.setVisibility(GONE);
        }
        else if(AddList_Activity.editEnable==false && AddList_Activity.onDetail==true)
        {
            holder.ivRemoveItem.setImageResource(R.drawable.remove);
            holder.ivRemoveItem.setVisibility(GONE);
            holder.ivCheckOnItemDone.setVisibility(View.VISIBLE);
            if(items.get(position).isDone()==true)
            {
                holder.ivCheckOnItemDone.setImageResource(R.drawable.ic_check_red);
            }
            else
            {
                holder.ivCheckOnItemDone.setImageResource(R.drawable.ic_blue_circle);

            }

        }
        else if( AddList_Activity.onDetail==false)
        {
            holder.ivRemoveItem.setImageResource(R.drawable.remove_);
            holder.ivCheckOnItemDone.setVisibility(GONE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

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

public class TaskListAdapter extends  RecyclerView.Adapter<TaskListAdapter.ViewHolder>{

    LinkedList<TaskList> taskLists;
    public interface ItemClicked
    {

        void onRemoveClicked(int index);
        void onItemClicked(int index);
    }
    ItemClicked myActivity;

    public TaskListAdapter(Context context, LinkedList<TaskList> list) {

        myActivity=(ItemClicked) context;
        taskLists=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvListName,tvItem1,tvItem2,tvItem3,tvTimeOnList;
        ImageView ivPriorityTag,ivDeleteList,ivCheckList;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListName=itemView.findViewById(R.id.tvListName);
            tvItem1=itemView.findViewById(R.id.tvItem1);
            tvItem2=itemView.findViewById(R.id.tvItem2);
            tvItem3=itemView.findViewById(R.id.tvItem3);
            tvTimeOnList=itemView.findViewById(R.id.tvTimeOnList);
            ivDeleteList=itemView.findViewById(R.id.ivDeleteList);
            ivCheckList=itemView.findViewById(R.id.ivCheckList);


            ivPriorityTag=itemView.findViewById(R.id.ivPriorityTag);

            ivDeleteList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity.onRemoveClicked(taskLists.indexOf(itemView.getTag()));
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity.onItemClicked(taskLists.indexOf(itemView.getTag()));
                }
            });







        }
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_layout,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(taskLists.get(position));
        holder.tvListName.setText(taskLists.get(position).getName());
        holder.tvTimeOnList.setText(taskLists.get(position).getTime());
        holder.ivDeleteList.setVisibility(View.GONE);
        if(MainActivity.editEnable==true)
        {
            holder.ivDeleteList.setVisibility(View.VISIBLE);
            holder.tvTimeOnList.setVisibility(View.GONE);
            holder.ivCheckList.setVisibility(View.GONE);

        }
        if(MainActivity.editEnable==false)
        {
            holder.ivDeleteList.setVisibility(View.GONE);
            holder.tvTimeOnList.setVisibility(View.VISIBLE);
            holder.ivCheckList.setVisibility(View.VISIBLE);
        }



        if(taskLists.get(position).getPriority().equals("L"))
        {
            holder.ivPriorityTag.setImageResource(R.drawable.yellow);

        }
        else if(taskLists.get(position).getPriority().equals("M"))
        {
            holder.ivPriorityTag.setImageResource(R.drawable.green);

        }
        else if(taskLists.get(position).getPriority().equals("H"))
        {
            holder.ivPriorityTag.setImageResource(R.drawable.red);

        }



        if (taskLists.get(position).getItems().size() == 0)
        {
            holder.tvItem1.setVisibility(View.GONE);
            holder.tvItem2.setVisibility(View.GONE);
            holder.tvItem3.setVisibility(View.GONE);
        }
        else if(taskLists.get(position).getItems().size() == 1)
        {
            holder.tvItem1.setVisibility(View.VISIBLE);
            holder.tvItem2.setVisibility(View.GONE);
            holder.tvItem3.setVisibility(View.GONE);
            holder.tvItem1.setText(taskLists.get(position).getItems().get(0).getName());


        }
        else if(taskLists.get(position).getItems().size() == 2)
        {
            holder.tvItem1.setVisibility(View.VISIBLE);
            holder.tvItem2.setVisibility(View.VISIBLE);
            holder.tvItem3.setVisibility(View.GONE);
            holder.tvItem1.setText(taskLists.get(position).getItems().get(0).getName());
            holder.tvItem2.setText(taskLists.get(position).getItems().get(1).getName());


        }
        else if(taskLists.get(position).getItems().size() > 2)
        {
            holder.tvItem1.setVisibility(View.VISIBLE);
            holder.tvItem2.setVisibility(View.VISIBLE);
            holder.tvItem3.setVisibility(View.VISIBLE);
            holder.tvItem1.setText(taskLists.get(position).getItems().get(0).getName());
            holder.tvItem2.setText(taskLists.get(position).getItems().get(1).getName());
            holder.tvItem3.setText(taskLists.get(position).getItems().get(2).getName());


        }

        //Item item=taskLists.get(position).getItems().get(0);
        //holder.tvItem1.setText(taskLists.get(position).getItems().get(0).getName());



    }

    @Override
    public int getItemCount() {
        return taskLists.size();
    }
}

package com.rsk.mad_mid_todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TaskListAdapter.ItemClicked {

    TextView tvEmptyList,tvMainDate;
    ImageView ivEmptyList,ivEditList;

    public static boolean editEnable;

    FloatingActionButton fabAddList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    public static RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        recyclerView=findViewById(R.id.rvTaskList);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter=new TaskListAdapter(this,ApplicationData.tasks);
        recyclerView.setAdapter(myAdapter);

        ivEditList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editEnable==false)
                {
                    editEnable=true;
                    myAdapter.notifyDataSetChanged();

                }
                else
                {
                    editEnable=false;
                    myAdapter.notifyDataSetChanged();


                }

            }
        });







        fabAddList.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddList_Activity.class);


                AddList_Activity.onDetail=false;

                startActivity(intent);
                finish();

                }
        });
    }

    void init()
    {
        fabAddList=findViewById(R.id.fabAddList);
        tvEmptyList=findViewById(R.id.tvEmptyList);
        ivEmptyList=findViewById(R.id.ivEmptyList);
        ivEditList=findViewById(R.id.ivEditList);
        tvMainDate=findViewById(R.id.tvMainDate);

        tvMainDate.setText(getTodaysDate());

        AddList_Activity.onDetail=false;



        editEnable=false;

        if (ApplicationData.tasks.size()==0)
        {
            tvEmptyList.setVisibility(View.VISIBLE);
            ivEmptyList.setVisibility(View.VISIBLE);
        }
        else if(ApplicationData.tasks.size()!=0)
        {
            tvEmptyList.setVisibility(View.GONE);
            ivEmptyList.setVisibility(View.GONE);
        }
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }


    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    @Override
    public void onRemoveClicked(int index) {
        ApplicationData.tasks.remove(index);
        myAdapter.notifyDataSetChanged();

        if (ApplicationData.tasks.size()==0)
        {
            tvEmptyList.setVisibility(View.VISIBLE);
            ivEmptyList.setVisibility(View.VISIBLE);
        }
        else if(ApplicationData.tasks.size()!=0)
        {
            tvEmptyList.setVisibility(View.GONE);
            ivEmptyList.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClicked(int index) {

        AddList_Activity.onDetail=true;

        Intent intent=new Intent(MainActivity.this,AddList_Activity.class);

        intent.putExtra("index",index);

        startActivity(intent);


    }
}
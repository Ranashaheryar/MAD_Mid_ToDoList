package com.rsk.mad_mid_todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class AddList_Activity extends AppCompatActivity implements ItemAdapter.ItemClicked {
    final int ADD_ITEM_ACTIVITY=1;
    public static LinkedList<Item> item;
    TaskList task;
    boolean repeat;
    public static boolean onDetail,editEnable;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    int index;

    ImageView btnAddItem,cbH,cbM,cbL,ivCalender,ivClock,ivRepeat,ivEmpty,ivClose,ivBackfromDetail,ivCheckOnItemDone;
    TextView tvDate,tvTime,tvEmpty,tvSecondAcvtivity;
    EditText etListName;
    Button btnSaveList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_);
        init();
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddList_Activity.this,com.rsk.mad_mid_todolist.Add_Item_Name.class);
                startActivityForResult(intent,ADD_ITEM_ACTIVITY);
            }
        });

        cbH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task.setPriority("H");
                cbH.setImageResource(R.drawable.ic_red_check);
                cbM.setImageResource(R.drawable.ic_box);
                cbL.setImageResource(R.drawable.ic_box);


            }
        });

        cbM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setPriority("M");
                cbM.setImageResource(R.drawable.ic_green_check);
                cbL.setImageResource(R.drawable.ic_box);
                cbH.setImageResource(R.drawable.ic_box);


            }
        });

        cbL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setPriority("L");
                cbL.setImageResource(R.drawable.ic_yelow_check);
                cbM.setImageResource(R.drawable.ic_box);
                cbH.setImageResource(R.drawable.ic_box);



            }
        });

        ivRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(repeat!=true)
                {
                    task.setRepeat(true);
                    repeat=true;
                    ivRepeat.setImageResource(R.drawable.repeat_);
                }
                else
                {
                    task.setRepeat(false);
                    repeat=false;
                    ivRepeat.setImageResource(R.drawable.repeat);
                }

            }
        });

        ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                datePickerDialog.show();


            }
        });


        ivClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(tvTime);
            }
        });


         //Data Picker

        initDatePicker();

        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String listName=etListName.getText().toString().trim();
                task.setName(listName);
                task.setItems(item);
                ApplicationData.tasks.add(task);
                //item.clear();
                MainActivity.myAdapter.notifyDataSetChanged();

                Intent intent=new Intent(AddList_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivBackfromDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onDetail==true)
                {
                    ApplicationData.tasks.get(index).setRepeat(repeat);
                    ApplicationData.tasks.get(index).setName(etListName.getText().toString().trim());




                    Intent intent=new Intent(AddList_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }



            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editEnable==true && onDetail==true)
                {
                    editEnable=false;
                    ItemList_Frag.myAdapter.notifyDataSetChanged();

                    ivClose.setImageResource(R.drawable.ic_black_note);



                }
                else  if(editEnable==false && onDetail==true)
                {
                    editEnable=true;
                    ItemList_Frag.myAdapter.notifyDataSetChanged();
                    ivClose.setImageResource(R.drawable.ic_edit_note_blue);
                }
                else  if( onDetail==false)
                {

                    ivClose.setImageResource(R.drawable.ic_close);
                    Intent intent=new Intent(AddList_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });




    }

    private void init() {
        btnAddItem=findViewById(R.id.ivPriorityTag);
        cbH=findViewById(R.id.cbH);
        cbM=findViewById(R.id.cbM);
        cbL=findViewById(R.id.cbL);
        ivCalender=findViewById(R.id.ivCalender);
        ivClock=findViewById(R.id.ivClock);
        ivRepeat=findViewById(R.id.ivRepeat);
        ivEmpty=findViewById(R.id.ivEmpty);
        tvDate=findViewById(R.id.tvDate);
        tvTime=findViewById(R.id.tvTime);
        tvEmpty=findViewById(R.id.tvEmpty);
        btnSaveList=findViewById(R.id.btnSaveList);
        etListName=findViewById(R.id.etListName);
        ivClose=findViewById(R.id.ivClose);
        ivBackfromDetail=findViewById(R.id.ivBackfromDetail);
        tvSecondAcvtivity=findViewById(R.id.tvSecondAcvtivity);
        ivCheckOnItemDone=findViewById(R.id.ivCheckOnItemDone);
        editEnable=false;
        item=new LinkedList<Item>();
        task=new TaskList();




        if(onDetail==true)
        {
            Intent i=getIntent();
            index=i.getIntExtra("index",0);
            item=ApplicationData.tasks.get(index).getItems();
            btnSaveList.setVisibility(View.GONE);

            if (item.size()==0)
            {
                ivEmpty.setVisibility(View.VISIBLE);
                tvEmpty.setVisibility(View.VISIBLE);
            }
            else if(item.size()!=0)
            {
                ivEmpty.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.GONE);
            }



           // Toast.makeText(this, item.size(), Toast.LENGTH_SHORT).show();


            ivBackfromDetail.setImageResource(R.drawable.ic_back_arrow);
            tvSecondAcvtivity.setText(ApplicationData.tasks.get(index).getName());
            if(editEnable==true)
            {
                ivClose.setImageResource(R.drawable.ic_edit_note_blue);

            }
            else
            {
                ivClose.setImageResource(R.drawable.outline_edit_note);

            }

            if(ApplicationData.tasks.get(index).getPriority().equals("L"))
            {
                cbL.setImageResource(R.drawable.ic_yelow_check);
                cbM.setImageResource(R.drawable.ic_box);
                cbH.setImageResource(R.drawable.ic_box);

            }
            else  if(ApplicationData.tasks.get(index).getPriority().equals("M"))
            {
                cbL.setImageResource(R.drawable.ic_box);
                cbM.setImageResource(R.drawable.ic_green_check);
                cbH.setImageResource(R.drawable.ic_box);

            }
            else  if(ApplicationData.tasks.get(index).getPriority().equals("H"))
            {
                cbL.setImageResource(R.drawable.ic_box);
                cbM.setImageResource(R.drawable.ic_box);
                cbH.setImageResource(R.drawable.ic_red_check);

            }

            tvDate.setText(ApplicationData.tasks.get(index).getDate());
            tvTime.setText(ApplicationData.tasks.get(index).getTime());


            if(ApplicationData.tasks.get(index).isRepeat()==true)
            {
                ivRepeat.setImageResource(R.drawable.repeat_);
            }
            else{

                ivRepeat.setImageResource(R.drawable.repeat);

            }







        }
        if(onDetail==false)
        {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            repeat=false;

            task.setPriority("L");
            cbL.setImageResource(R.drawable.ic_yelow_check);
            cbM.setImageResource(R.drawable.ic_box);
            cbH.setImageResource(R.drawable.ic_box);
            btnSaveList.setVisibility(View.VISIBLE);




        }





        //Date

        tvDate.setText(getTodaysDate());
        task.setDate(getTodaysDate());


        if (item.size()==0)
        {
            ivEmpty.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.VISIBLE);
        }
        else if(item.size()!=0)
        {
            ivEmpty.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.GONE);
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_ITEM_ACTIVITY)
        {
            if(resultCode==RESULT_OK) {

                Item i=new Item();
                i.setName(data.getStringExtra("itemName"));

                item.add(i);
                ItemList_Frag.myAdapter.notifyDataSetChanged();

                if (item.size()==0)
                {
                    ivEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setVisibility(View.VISIBLE);
                }
                else if(item.size()!=0)
                {
                    ivEmpty.setVisibility(View.GONE);
                    tvEmpty.setVisibility(View.GONE);
                }
            }
            else if(requestCode==RESULT_CANCELED)
            {

            }
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

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                tvDate.setText(date);
                task.setDate(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

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
//
//    public void openDatePicker(View view)
//    {
//        datePickerDialog.show();
//    }


    ///Time

    private void showTimeDialog(final TextView time_in) {
        final Calendar calendar=Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("h:mm a");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
                task.setTime(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(AddList_Activity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }


    @Override
    public void onRemoveClicked(int index) {
        item.remove(index);
        ItemList_Frag.myAdapter.notifyDataSetChanged();

        if (item.size()==0)
        {
            ivEmpty.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.VISIBLE);
        }
        else if(item.size()!=0)
        {
            ivEmpty.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCheckClick(int index) {

        if(item.get(index).isDone()==false)
        {
            item.get(index).setDone(true);
            ItemList_Frag.myAdapter.notifyDataSetChanged();


        }
        else
        {
            item.get(index).setDone(false);
            ItemList_Frag.myAdapter.notifyDataSetChanged();

        }

    }
}
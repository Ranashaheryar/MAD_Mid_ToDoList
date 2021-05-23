package com.rsk.mad_mid_todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Item_Name extends AppCompatActivity {

    EditText etItemName;
    Button btnConfirmAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item__name);
        init();


        btnConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName;

                itemName=etItemName.getText().toString().trim();
                Intent intent=new Intent();
                intent.putExtra("itemName",itemName);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }

    private void init() {
        etItemName=findViewById(R.id.etItemName);
        btnConfirmAdd=findViewById(R.id.btnConfirmAdd);


    }
}
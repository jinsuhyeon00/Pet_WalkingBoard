package com.example.pet_walkingboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
     {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private final ArrayList<Board> listBundle = new ArrayList<>();
    private DatabaseReference databaseReference = database.getReference();

   private  RecyclerView recyclerView;
   private RecyclerAdapter adapter;
   private  RecyclerView.LayoutManager layoutManager;

   private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        add = findViewById(R.id.floatingActionButton2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(listBundle);
        recyclerView.setAdapter(adapter);

  //      final Button btnUp = findViewById(R.id.btnUp);
   //     final EditText PliceText = findViewById(R.id.PliceText);
   //     final EditText TimeText = findViewById(R.id.TimeText);
   //     final EditText Dog_breedText = findViewById(R.id.Dog_breedText);
   //     final EditText IDText = findViewById(R.id.IDText);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_writing, null, false);
                builder.setView(view);

                final Button upload = view.findViewById(R.id.btnUp);
                final EditText Plice = view.findViewById(R.id.PliceText);
                final EditText Time = view.findViewById(R.id.TimeText);
                final EditText dog  =  view.findViewById(R.id.Dog_breedText);
                final EditText id = view.findViewById(R.id.IDText);

                final AlertDialog dialog = builder.create();

                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strPlice = Plice.getText().toString();
                        String strTime = Time.getText().toString();
                        String strDog = dog.getText().toString();
                        String  strid = id.getText().toString();

                        if(strPlice.length() == 0)return;
                        listBundle.add(new Board(strPlice, strTime, strDog, strid));

                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
/*
        btnUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(PliceText.getText().toString().length() == 0)return;
                listBundle.add(new Board(PliceText.getText().toString(), TimeText.getText().toString(), Dog_breedText.getText().toString(),IDText.getText().toString()));
                adapter.notifyDataSetChanged();
              PliceText.setText("");
             TimeText.setText("");
             Dog_breedText.setText("");
             IDText.setText("");

            }
         }
        );
*/

         }
     }

package com.example.pet_walkingboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Board> listBundle = new ArrayList<>();

   private  RecyclerView recyclerView;
   private RecyclerAdapter adapter;
   private  RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(listBundle);
        recyclerView.setAdapter(adapter);

        final Button btnUp = findViewById(R.id.btnUp);
        final EditText PliceText = findViewById(R.id.PliceText);
        final EditText TimeText = findViewById(R.id.TimeText);
        final EditText Dog_breedText = findViewById(R.id.Dog_breedText);
        final EditText IDText = findViewById(R.id.IDText);




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
    }
}

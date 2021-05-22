package com.example.pet_walkingboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
     {


    private final ArrayList<Board> listBundle = new ArrayList<>();

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

                    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                      DatabaseReference rootRef = firebaseDatabase.getReference();

                     DatabaseReference BoardRef = rootRef.child("Bosrds");

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

   //             FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
  //              DatabaseReference rootRef = firebaseDatabase.getReference();

 //               DatabaseReference BoardRef = rootRef.child("Bosrds");

                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strPlice = Plice.getText().toString();
                        String strTime = Time.getText().toString();
                        String strDog = dog.getText().toString();
                        String  strid = id.getText().toString();

 //                       FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
 //                       DatabaseReference rootRef = firebaseDatabase.getReference();

                        Board board = new Board(strPlice, strTime, strDog, strid);

                        if(strPlice.length() == 0)return;
  //                      listBundle.add(board);

   //                     DatabaseReference BoardRef = rootRef.child("Bosrds");
                        BoardRef.push().setValue(board);

                        BoardRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                StringBuffer buffer = new StringBuffer();

                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                   Board board = snapshot.getValue(Board.class);
                                    String  strPlice = board.getPlace();
                                    String strTime = board.getTime();
                                    String strDog = board.getDog_breed();
                                    String  strid = board.getID();
                                    buffer.append(listBundle);
                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });

                       // adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                adapter.notifyDataSetChanged();
                dialog.show();

            }
        });
        BoardRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Board board = snapshot.getValue(Board.class);
                    listBundle.add(board);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
         }
     }

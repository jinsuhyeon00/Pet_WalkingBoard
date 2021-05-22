package com.example.pet_walkingboard;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.function.BiPredicate;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Board> listBundle = new ArrayList<>();
    Context mContext;

    public RecyclerAdapter(ArrayList<Board> bundle){
        this.listBundle = bundle;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext  = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Board  b = listBundle.get(position);

        holder.PliceView.setText(b.getPlace());
        holder.TimeView.setText(b.getTime());
        holder.Dog_breedView.setText(b.getDog_breed());
        holder.IDView.setText(b.getID());
    }

    @Override
    public int getItemCount() {
        return listBundle.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView PliceView;
         TextView TimeView;
         TextView Dog_breedView;
         TextView IDView;

        public ViewHolder(@NonNull View View) {
            super(View);
            PliceView = View.findViewById(R.id.list_Place);
            TimeView = View.findViewById(R.id.list_Time);
            Dog_breedView = View.findViewById(R.id.list_Dog_breed);
            IDView = View.findViewById(R.id.list_ID);
        }
    }


}

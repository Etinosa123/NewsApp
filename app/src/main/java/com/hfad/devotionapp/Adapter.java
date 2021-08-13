package com.hfad.devotionapp;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
//this class is an adapter class that will connect the layout to the recycleview
    Context context;
    ArrayList<NewsModel> modelArrayList;

    public Adapter(Context context, ArrayList<NewsModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        //if user clicks on the card, it will take him to the web view activity
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, webview.class);
                intent.putExtra("url",modelArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        //binding the variables to the layout
        holder.mtime.setText("Published At:" + modelArrayList.get(position).getPublishedAt());
        holder.mauthor.setText(modelArrayList.get(position).getAuthor());
        holder.mheading.setText(modelArrayList.get(position).getTitle());
        holder.mcontent.setText(modelArrayList.get(position).getDescription());

        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

   public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mheading, mcontent, mauthor,mtime;
        CardView cardView;
        ImageView imageView;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           //find the id of the layout to connect variables

           mheading = itemView.findViewById(R.id.mainHeading);
           mcontent = itemView.findViewById(R.id.content);
           mauthor = itemView.findViewById(R.id.author);
           mtime = itemView.findViewById(R.id.time);
           cardView = itemView.findViewById(R.id.cardview);
           imageView = itemView.findViewById(R.id.imageview);
       }
   }
}

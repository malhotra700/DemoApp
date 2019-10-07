package com.example.demoapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    Context context;
    ArrayList<Profile> profiles;

    public  ProgrammingAdapter(Context c,ArrayList<Profile> p){
        this.context=c;
        this.profiles=p;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProgrammingViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {
        holder.username.setText(profiles.get(position).getUsername());
        holder.content.setText(profiles.get(position).getContent());
        if(profiles.get(position).getProfilepic()!=null)
            Picasso.get().load(profiles.get(position).getProfilepic()).into(holder.imageView);
        else{
            Uri imgPath=Uri.parse("android.resource://com.example.demoapp/"+R.drawable.pic);
            Picasso.get().load(imgPath.toString()).into(holder.imageView);}

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView username;
        TextView content;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.post_content_imageView);
            username=(TextView)itemView.findViewById(R.id.post_content_username);
            content=(TextView)itemView.findViewById(R.id.post_content);

        }
    }
}

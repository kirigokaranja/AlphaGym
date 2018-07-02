package com.kirigokaranja.alphagym;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<MyWorkoutRecyclerViewAdapter.myviewHolder> {

    private Context context;
    private List<Workout> data;

    public MyWorkoutRecyclerViewAdapter(Context context, List<Workout> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView;
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.cardview_myworkout,parent,false);
        return new myviewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {

        holder.mworkout_name.setText(data.get(position).getWorkoutName());
        holder.mworkout_image.setImageResource(data.get(position).getWorkoutThumbnail());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class myviewHolder extends RecyclerView.ViewHolder{

        TextView mworkout_name;
        CircleImageView mworkout_image;

        public myviewHolder(View itemView) {
            super(itemView);

            mworkout_name = (TextView) itemView.findViewById(R.id.myworkout_name);
            mworkout_image = (CircleImageView) itemView.findViewById(R.id.myworkout_img);
        }
    }
}

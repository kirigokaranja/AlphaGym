package com.kirigokaranja.alphagym;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LogWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<LogWorkoutRecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Workout> mData;

    public LogWorkoutRecyclerViewAdapter(Context mContext, List<Workout> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_logworkout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.WorkoutName.setText(mData.get(position).getWorkoutName());
        holder.WorkoutImg_Thumbnail.setImageResource(mData.get(position).getWorkoutThumbnail());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView WorkoutName;
        ImageView WorkoutImg_Thumbnail;
       // CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            WorkoutName = (TextView) itemView.findViewById(R.id.logworkout_name);
            WorkoutImg_Thumbnail = (ImageView) itemView.findViewById(R.id.logworkout_image);
          //  cardView =  (CardView) itemView.findViewById(R.id.cardview_logworkout);

        }
    }
}

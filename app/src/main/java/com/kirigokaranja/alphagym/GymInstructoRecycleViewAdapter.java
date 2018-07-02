package com.kirigokaranja.alphagym;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GymInstructoRecycleViewAdapter extends RecyclerView.Adapter <GymInstructoRecycleViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Instructors> mData;

    public GymInstructoRecycleViewAdapter(Context mContext, List<Instructors> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_items_gyminstructors, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.InstructorName.setText(mData.get(position).getInstructorName());
        holder.InstructorImg_Thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InstructorDetails.class);
                intent.putExtra("Name", mData.get(position).getInstructorName());
                intent.putExtra("Exercise", mData.get(position).getInstructorExercise());
                intent.putExtra("Bio", mData.get(position).getInstructorBio());
                intent.putExtra("Contact", mData.get(position).getInstructorContact());
                intent.putExtra("Email", mData.get(position).getInstructorEmail());
                intent.putExtra("Thumbnail", mData.get(position).getThumbnail());
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView InstructorName;
        ImageView InstructorImg_Thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            InstructorName = (TextView) itemView.findViewById(R.id.instructor_name);
            InstructorImg_Thumbnail = (ImageView) itemView.findViewById(R.id.instructor_image);
            cardView =  (CardView) itemView.findViewById(R.id.cardview_instructor);

        }
    }
}

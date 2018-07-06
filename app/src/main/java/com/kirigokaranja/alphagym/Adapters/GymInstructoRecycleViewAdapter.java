package com.kirigokaranja.alphagym.Adapters;

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

import com.bumptech.glide.Glide;
import com.kirigokaranja.alphagym.Classes.InstructorDetails;
import com.kirigokaranja.alphagym.Model.Instructors;
import com.kirigokaranja.alphagym.R;

import java.util.List;

public class GymInstructoRecycleViewAdapter extends RecyclerView.Adapter <GymInstructoRecycleViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Instructors> InstructoList;


    public GymInstructoRecycleViewAdapter(  Context mContext, List<Instructors> mData ) {
        this.mContext = mContext;
        this.InstructoList = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.cardview_items_gyminstructors, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.InstructorName.setText(InstructoList.get(position).getInstructorName());
        Glide.with(mContext).load(InstructoList.get(position).getThumbnail()).into(holder.InstructorImg_Thumbnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InstructorDetails.class);
                intent.putExtra("Name", InstructoList.get(position).getInstructorName());
                intent.putExtra("Gender", InstructoList.get(position).getInstructorGender());
                intent.putExtra("Bio", InstructoList.get(position).getInstructorBio());
                intent.putExtra("Contact", InstructoList.get(position).getInstructorContact());
                intent.putExtra("Email", InstructoList.get(position).getInstructorEmail());
                intent.putExtra("Thumbnail", InstructoList.get(position).getThumbnail());
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return InstructoList.size();
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

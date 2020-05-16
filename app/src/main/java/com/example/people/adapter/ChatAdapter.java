package com.example.people.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.people.Entity.job.ResumeEntity;
import com.example.people.R;
import com.example.people.ui.login.job.ReceiveResumeActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ResumeEntity>list=new ArrayList<>();
    public void setMessage(List<ResumeEntity> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.cell_car_wchact, parent, false);

        final MyViewHolder holder=new MyViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String  People= holder.textViewPeople.getText().toString();
              Intent intent=new Intent(itemView.getContext(), ReceiveResumeActivity.class);;
                intent.putExtra("People",People);
               holder.itemView.getContext().startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        ResumeEntity resumeEntity=list.get(position);
        holder.textViewPeople.setText(resumeEntity.getYouname());
       holder.textViewContent.setText(resumeEntity.getQwer());


    }

    @Override
    public int getItemCount() {

            return list.size();

        }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPeople,textViewContent;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContent=itemView.findViewById(R.id.textView_chat_content);
            textViewPeople=itemView.findViewById(R.id.textView_chat_people);
            imageView=itemView.findViewById(R.id.imageView_chat_image);



        }
    }
}

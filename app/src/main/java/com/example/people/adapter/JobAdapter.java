package com.example.people.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.people.Entity.job.JobResultEntity;
import com.example.people.R;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {
    private List<JobResultEntity.DataBean> data=new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        JobResultEntity.DataBean dataBean=data.get(position);
        holder.textViewCpn.setText(dataBean.getCpnName());
        holder.textViewJob.setText(dataBean.getJobName());
        holder.textViewConditionOne.setText(dataBean.getJobConditionOne());
        holder.textViewConditionTwo.setText(dataBean.getJobConditionTwo());
        holder.textViewJobPay.setText(dataBean.getJobPay());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(JobResultEntity jobResultEntity) {

        data.clear();
        data.addAll(jobResultEntity.getData());
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCpn,textViewJob,textViewConditionOne,
                textViewConditionTwo,textViewDate,textViewJobPay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCpn=itemView.findViewById(R.id.textView_cpn_name_add);
            textViewJob=itemView.findViewById(R.id.textView_job_add);
            textViewConditionOne=itemView.findViewById(R.id.textView_job_condition_one_add);
            textViewConditionTwo=itemView.findViewById(R.id.textView_Job_condition_two_add);
            textViewJobPay=itemView.findViewById(R.id.textView_job_pay_add);
        }
        }
    }


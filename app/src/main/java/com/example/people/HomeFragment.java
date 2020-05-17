package com.example.people;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.job.JobMessage;
import com.example.people.Entity.job.JobMessageAll;
import com.example.people.Entity.job.JobResultEntity;
import com.example.people.Entity.job.ReturnJobMessageAll;
import com.example.people.adapter.JobAdapter;
import com.example.people.adapter.CnpAdapter;
import com.example.people.common.Common;
import com.example.people.data.model.LoginUser;
import com.example.people.loop.views.PagerItem;
import com.example.people.loop.views.SobLooperPager;
import com.example.people.tableDo.MsgViewModel;
import com.example.people.ui.login.AddJobActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private JobViewModel jobViewModel;
    private CnpAdapter cnpAdapter;
    private JobAdapter jobAdapter;
    private RecyclerView recyclerView;
    private List<JobMessage>allJobs;
    private LiveData<List<JobMessage>> fileJobs;
    private DividerItemDecoration dividerItemDecoration;
    private Button buttonRefresh;
    private static final String TAG = "ButtomActivity";
    private List<ReturnJobMessageAll.DataBean> data=new ArrayList<>();
    private List<JobResultEntity.DataBean> data2=new ArrayList<>();
    private List<JobMessage> jobMessageList;

    private UserViewModel userViewmodel;
    List<String>listall=new ArrayList<>();
    LiveData<List<LoginUser>>liveData;
    private String username;
List<String>stringList=new ArrayList<>();
    //广告
    private SobLooperPager mLooperPager;
private SwipeRefreshLayout refresh;
    private List<PagerItem> mData = new ArrayList<>();


 private    MsgViewModel msgViewModel;

    public  HomeFragment(){



        setHasOptionsMenu(true);
    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_job, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewmodel=ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        jobViewModel= ViewModelProviders.of(requireActivity()).get(JobViewModel.class);
        msgViewModel=ViewModelProviders.of(requireActivity()).get(MsgViewModel.class);
        recyclerView =requireActivity().findViewById(R.id.recycler_view_job_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        cnpAdapter =new CnpAdapter();
        jobAdapter =new JobAdapter();
        stringList.clear();
        refresh=requireActivity().findViewById(R.id.refresh_home);
findjobbyid();

//刷新
        refreshview();
        initData();
        initView();
        initEven();

        //分割线
        dividerItemDecoration=new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);
        fileJobs=jobViewModel.getAllJobsLive();//+
        fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
            @Override
            public void onChanged(List<JobMessage> jobMessages) {

                recyclerView.addItemDecoration(dividerItemDecoration);
                 jobMessageList=jobMessages;
                // int temp =myAdapter.getItemCount();
                cnpAdapter.setAllJobs(jobMessages);
                cnpAdapter.notifyDataSetChanged();

            }
        });
        recyclerView.setAdapter(cnpAdapter);

//滑动删除
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final JobMessage delete = jobMessageList.get(viewHolder.getAdapterPosition());
                jobViewModel.deleteJobs(delete);
                jobAdapter.notifyDataSetChanged();
                //网络删除
                Gson gson=new Gson();
                String jsonstr=gson.toJson(delete);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonstr);
Call<JobMessage>task= Common.apicommont.deletejob(requestBody);
task.enqueue(new Callback<JobMessage>() {
    @Override
    public void onResponse(Call<JobMessage> call, Response<JobMessage> response) {

    }

    @Override
    public void onFailure(Call<JobMessage> call, Throwable t) {

    }
});





            }
        }).attachToRecyclerView(recyclerView);



    }
    //刷新
    private void refreshview() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                jobViewModel.deleteAllJobs();
                cnpAdapter.notifyDataSetChanged();
//加载招聘信息
                JobResultEntity jobResultEntity=new JobResultEntity();
                jobResultEntity.setErrmsg(stringList.get(0));
                Gson gson=new Gson();
                String jsonstr=gson.toJson(jobResultEntity);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonstr);

                Api api= ApiRetrofit.getInstance().getService(Api.class);
                Call<JobResultEntity> task=api.getjob(requestBody);
                task.enqueue(new Callback<JobResultEntity>() {
                    @Override
                    public void onResponse(Call<JobResultEntity> call, Response<JobResultEntity> response) {


                        if (response.code()== HttpsURLConnection.HTTP_OK){

                            JobResultEntity jobResultEntity=response.body();
                        data2.clear();
                            data2.addAll(jobResultEntity.getData());


                            for (  JobResultEntity.DataBean dataBean:data2  ){

                                JobMessage jobMessage=new JobMessage(dataBean.getJobConditionOne()
                                        ,dataBean.getJobConditionTwo()
                                        ,dataBean.getJobName()
                                        ,dataBean.getJobPay()
                                        ,dataBean.getCpnName(),dataBean.getUuid(),dataBean.getUsername(),dataBean.getImage());


                                jobViewModel.insertJobs(jobMessage);

                            }



                        }

                    }

                    @Override
                    public void onFailure(Call<JobResultEntity> call, Throwable t) {
                        Log.d(TAG,"失败-->"+t.toString());
                    }
                });



                //..................加载全部joball
                Call<ReturnJobMessageAll> task1=api.findMsgsAll();
                task1.enqueue(new Callback<ReturnJobMessageAll>() {
                    @Override
                    public void onResponse(Call<ReturnJobMessageAll> call, Response<ReturnJobMessageAll> response) {




                        ReturnJobMessageAll returnJobMessageAll=response.body();
                        data.addAll(returnJobMessageAll.getData());


                        for (  ReturnJobMessageAll.DataBean dataBean:data   ) {

                            JobMessageAll jobMessageAll = new JobMessageAll(dataBean.getJobName()
                                    , dataBean.getCpnAddress()
                                    , dataBean.getGood1()
                                    , dataBean.getGood2()
                                    , dataBean.getGood3()
                                    , dataBean.getGood4()
                                    , dataBean.getJobPay()
                                    , dataBean.getConditionOne()
                                    , dataBean.getConditionTwo()
                                    , dataBean.getCondition3()
                                    , dataBean.getWorkContent()
                                    , dataBean.getWorkContentShow()
                                    , dataBean.getWorkAddress()
                                    , dataBean.getCpnImage()
                                    , dataBean.getCpnName1()
                                    , dataBean.getDizhi());

                            msgViewModel.insertMsgs(jobMessageAll);


                        }


                    }

                    @Override
                    public void onFailure(Call<ReturnJobMessageAll> call, Throwable t) {
                        Toast.makeText(requireActivity(),t.toString(),Toast.LENGTH_LONG).show();
                    }
                });


                refresh.setRefreshing(false);
            }

        });

    }

    private void initEven() {

        if(mLooperPager != null) {
            mLooperPager.setOnItemClickListener(new SobLooperPager.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(),"点击了第" + (position + 1) + "个item", Toast.LENGTH_SHORT).show();
                    //todo:根据交互业务去实现具体逻辑
                }
            });
        }
    }
    private SobLooperPager.InnerAdapter mInnerAdapter = new SobLooperPager.InnerAdapter() {
        @Override
        protected int getDataSize() {
            return mData.size();
        }

        @Override
        protected View getSubView(ViewGroup container, int position) {
            ImageView iv = new ImageView(container.getContext());
            iv.setImageResource(mData.get(position).getPicResId());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }
    };

    private void initView() {
        mLooperPager = requireActivity().findViewById(R.id.sob_looper_pager);
        mLooperPager.setData(mInnerAdapter,new SobLooperPager.BindTitleListener() {
            @Override
            public String getTitle(int position) {
                return mData.get(position).getTitle();
            }
        });
    }

    private void initData() {
        mData.add(new PagerItem("第一个图片",R.mipmap.pic0));
        mData.add(new PagerItem("第2个图片",R.mipmap.pic1));
        mData.add(new PagerItem("第三个图片",R.mipmap.pic2));
        mData.add(new PagerItem("第4个图片",R.mipmap.pic3));
    }

    private void findjobbyid() {
        liveData=userViewmodel.getAllUsersLive();
        liveData.observe(requireActivity(), new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser loginUser:loginUsers){
                   stringList.add(loginUser.getUserId());
                }
            }
        });

    }


    //...............................................搜索..............
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(500);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String pattern = newText.trim();
                fileJobs.removeObservers(requireActivity());
                fileJobs = jobViewModel.findAllJobsLive(pattern);
                fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
                    @Override
                    public void onChanged(List<JobMessage> msg) {
                        int temp = cnpAdapter.getItemCount();
                        cnpAdapter.setAllJobs(msg);
                        if (temp!=msg.size()) {

                            cnpAdapter.notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.search_menu_job:
                Intent intent=new Intent(requireActivity(), AddJobActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_bar_refresh_job:






                //....................................................

                break;
                default:
        }
      return super.onOptionsItemSelected(item);
    }





}
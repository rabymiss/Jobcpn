package com.example.people.ui.login;

import android.os.Bundle;
import android.widget.Button;

import com.example.people.Entity.job.JobMessage;
import com.example.people.JobViewModel;
import com.example.people.adapter.CnpAdapter;
import com.example.people.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ButtomActivity extends AppCompatActivity {
    private static final String TAG = "ButtomActivity";
    private JobViewModel jobViewModel;
    private CnpAdapter cnpAdapter;
    private RecyclerView recyclerView;
    private List<JobMessage>allJobs;
    private Button buttonRefresh;
    private LiveData<List<JobMessage>> fileJobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        jobViewModel=ViewModelProviders.of(this).get(JobViewModel.class);
        recyclerView=findViewById(R.id.recycler_view_job_show2);

    }





}

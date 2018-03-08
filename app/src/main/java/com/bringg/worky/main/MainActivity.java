package com.bringg.worky.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bringg.worky.R;
import com.bringg.worky.data.db.AppDatabase;
import com.bringg.worky.data.db.SessionModel;
import com.bringg.worky.monitor.MonitoringService;
import com.bringg.worky.utils.Constants;
import com.bringg.worky.utils.PermissionsUtils;
import com.bringg.worky.utils.SharedPrefUtils;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //ViewModel
    private SessionsListViewModel viewModel;

    //List
    private RecyclerView rvSessions;
    private SessionsListAdapter sessionsListAdapter;

    //ToggleButton
    private SwitchButton tbMonitoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupToolbar();

        setupRecyclerView();

        setupViewModel();

        toggleMonitoring(SharedPrefUtils.getInstance(this).isMonitoringEnabled());

    }

    private void startMonitoringService()
    {
        PermissionsUtils.requestLocationPermissions(this, new PermissionsUtils.PermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, R.string.started_monitoring, Toast.LENGTH_LONG).show();
                MonitoringService.startService(getBaseContext());
            }

            @Override
            public void onPermissionRefused() {
                tbMonitoring.setEnabled(false);
                Toast.makeText(getBaseContext(), R.string.permission_denied, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupRecyclerView()
    {
        rvSessions = findViewById(R.id.rv_sessions);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        rvSessions.addItemDecoration(dividerItemDecoration);

        sessionsListAdapter = new SessionsListAdapter(this);
        rvSessions.setLayoutManager(new LinearLayoutManager(this));

        rvSessions.setAdapter(sessionsListAdapter);

    }

    private void setupViewModel()
    {
        viewModel = ViewModelProviders.of(this).get(SessionsListViewModel.class);

        viewModel.getSessionsList().observe(this, new Observer<List<SessionModel>>() {
            @Override
            public void onChanged(@Nullable List<SessionModel> itemAndPeople) {
                sessionsListAdapter.addItems(itemAndPeople);
            }
        });
    }

    private void setupToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        tbMonitoring =
                (SwitchButton) menu.findItem(R.id.menu_toggle_monitoring).getActionView();

        tbMonitoring.setChecked(SharedPrefUtils.getInstance(this).isMonitoringEnabled());

        tbMonitoring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPrefUtils.getInstance(getBaseContext()).setMonitoringEnabled(isChecked);
                toggleMonitoring(isChecked);
            }
        });

        return true;
    }

    private void toggleMonitoring(boolean isEnabled)
    {
        if(isEnabled)
            startMonitoringService();
        else
            MonitoringService.stop(getBaseContext());
    }

}

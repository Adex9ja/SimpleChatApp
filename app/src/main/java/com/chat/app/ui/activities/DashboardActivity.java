package com.chat.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chat.app.R;
import com.chat.app.db.entities.UserEntity;
import com.chat.app.ui.adapters.UserListAdapter;
import com.chat.app.ui.bases.BaseActivity;
import com.chat.app.ui.viewmodel.DashBoardViewModel;
import com.chat.app.ui.view.DashBoardView;
import com.chat.app.util.ItemClickListener;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity implements DashBoardView, SwipeRefreshLayout.OnRefreshListener, ItemClickListener<UserEntity> {

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    private DashBoardViewModel presenter;
    private UserListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        presenter = new DashBoardViewModel(this,this);
        adapter = new UserListAdapter(this);
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    @Override
    public void loadingStart() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void loadingFailed(String msg) {
        swipeRefresh.setRefreshing(false);
        super.loadingFailed(msg);
    }

    @Override
    public void loadingSuccessful(String msg) {
        swipeRefresh.setRefreshing(false);
        super.loadingSuccessful(msg);
    }

    @Override
    public void onRefresh() {
        presenter.fetchDataFromApi();
    }

    @Override
    public void onDataChanged(List<UserEntity> userEntities) {
        txtEmpty.setVisibility(userEntities == null || userEntities.size() == 0 ? View.VISIBLE : View.GONE);
        adapter.swapData(userEntities);
    }

    @Override
    public void onItemClicked(UserEntity item) {
        Intent intent = new Intent(this, UserDetailActivity.class);
        intent.putExtra(getString(R.string.data), item.id);
        startActivity(intent);
    }
}

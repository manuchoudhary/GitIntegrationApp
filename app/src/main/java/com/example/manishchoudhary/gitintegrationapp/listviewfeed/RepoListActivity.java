package com.example.manishchoudhary.gitintegrationapp.listviewfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.manishchoudhary.gitintegrationapp.R;
import com.example.manishchoudhary.gitintegrationapp.adapter.RepositoryListAdapter;
import com.example.manishchoudhary.gitintegrationapp.data.GitUser;

/**
 * Created by manish.choudhary on 12/21/2016.
 */

public class RepoListActivity extends AppCompatActivity {
    GitUser gitUser;
    private RecyclerView listView;
    private RepositoryListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        gitUser = new GitUser();
        listView = (RecyclerView) findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        gitUser = (GitUser)intent.getSerializableExtra("GitUser");
        populateRecyclerView();
    }

    public void populateRecyclerView(){
        listAdapter = new RepositoryListAdapter(RepoListActivity.this, gitUser.getRepoList());
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}

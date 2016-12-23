package com.example.manishchoudhary.gitintegrationapp.listviewfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.manishchoudhary.gitintegrationapp.R;
import com.example.manishchoudhary.gitintegrationapp.app.AppController;
import com.example.manishchoudhary.gitintegrationapp.data.Repository;

public class DetailActivity extends AppCompatActivity {

    Repository repoItem;
    TextView repoName, txtRepoID, txtRepoName, txtOwnerID, txtOwnerName, txtDescription;
    CircularNetworkImageView profileImage;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        repoItem = new Repository();
        imageLoader = AppController.getInstance().getImageLoader();
        repoName = (TextView)findViewById(R.id.repoName);
        txtRepoID = (TextView)findViewById(R.id.txtRepoID);
        txtRepoName = (TextView)findViewById(R.id.txtRepoName);
        txtOwnerID = (TextView)findViewById(R.id.txtOwnerID);
        txtOwnerName = (TextView)findViewById(R.id.txtOwnerName);
        txtDescription = (TextView)findViewById(R.id.txtDescription);
        profileImage = (CircularNetworkImageView)findViewById(R.id.profilePic);
        Intent intent = getIntent();
        repoItem = (Repository)intent.getSerializableExtra("item");
        setData(repoItem);
    }

    public void setData(Repository item){
        profileImage.setImageUrl(item.getImageURL(), imageLoader);
        repoName.setText(item.getRepoName());
        txtRepoName.setText(item.getRepoName());
        txtRepoID.setText("" + item.getRepoID());
        txtOwnerID.setText("" + item.getRepoOwnerID());
        txtOwnerName.setText(item.getRepoOwnerName());
        txtDescription.setText(item.getRepoDesc());
    }
}

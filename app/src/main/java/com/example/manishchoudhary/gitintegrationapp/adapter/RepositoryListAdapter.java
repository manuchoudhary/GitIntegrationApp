package com.example.manishchoudhary.gitintegrationapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.manishchoudhary.gitintegrationapp.R;
import com.example.manishchoudhary.gitintegrationapp.app.AppController;
import com.example.manishchoudhary.gitintegrationapp.data.Repository;
import com.example.manishchoudhary.gitintegrationapp.listviewfeed.DetailActivity;
import com.example.manishchoudhary.gitintegrationapp.listviewfeed.MainActivity;

import java.util.List;

/**
 * Created by manish.choudhary on 12/21/2016.
 */

public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.ViewHolder> {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Repository> repoList;
    ImageLoader imageLoader;

    public RepositoryListAdapter(Activity activity, List<Repository> feedItems) {
        this.activity = activity;
        this.repoList = feedItems;
        imageLoader = AppController.getInstance().getImageLoader();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class UserHolder extends ViewHolder {
        TextView repoName, count, repoDesc;
        com.android.volley.toolbox.NetworkImageView profilePic;
        CardView card;

        public UserHolder(View v) {
            super(v);
            this.card = (CardView) v.findViewById(R.id.repo_card_view);
            this.repoName = (TextView) v.findViewById(R.id.repoName);
            this.count = (TextView) v.findViewById(R.id.count);
            this.repoDesc = (TextView) v.findViewById(R.id.repoDesc);
            this.profilePic = (com.android.volley.toolbox.NetworkImageView) v.findViewById(R.id.profilePic);
        }
    }

    @Override
    public RepositoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_card, parent, false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final UserHolder holder = (UserHolder) viewHolder;
        if (!TextUtils.isEmpty(repoList.get(position).getRepoName())) {
            holder.repoName.setText(repoList.get(position).getRepoName());
            holder.count.setText("Watched : " + repoList.get(position).getRepoWatchCount());
            holder.repoDesc.setText(repoList.get(position).getRepoDesc());
            holder.profilePic.setImageUrl(repoList.get(position).getImageURL(), imageLoader);
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDetails(repoList.get(position), holder);
                }
            });
        } else {
            holder.repoName.setVisibility(View.GONE);
            holder.count.setVisibility(View.GONE);
            holder.repoDesc.setVisibility(View.GONE);
        }
    }

    public void showDetails(Repository item, UserHolder holder){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("item", item);ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, holder.profilePic, "getImageTrans");
        activity.startActivity(intent, options.toBundle());
    }
}

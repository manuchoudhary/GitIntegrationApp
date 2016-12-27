package com.example.manishchoudhary.gitintegrationapp.listviewfeed;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.manishchoudhary.gitintegrationapp.R;
import com.example.manishchoudhary.gitintegrationapp.data.GitUser;
import com.example.manishchoudhary.gitintegrationapp.data.Repository;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnGet;
    EditText txtUserName;
    private static final String TAG = MainActivity.class.getSimpleName();
    private GitUser gitUser;
    private List<Repository> userRepository;
    private String URL_FEED = "https://api.github.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = (Button) findViewById(R.id.btnGet);
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUserName.getText().toString();
                if(userName != null && userName != "") {
                    URL_FEED += "/" + userName;
                    getGitUserDetails();
                }else{
                    Toast.makeText(MainActivity.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getGitUserDetails() {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                URL_FEED, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    parseJsonFeed(response);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Invalid User Name", Toast.LENGTH_SHORT).show();
                URL_FEED = "https://api.github.com/users";
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonReq);
    }

    private void parseJsonFeed(JSONObject response) {
        try {
            gitUser = new GitUser();
            JSONObject feedObj = response;
            gitUser.setGitUserID(feedObj.getInt("id"));
            gitUser.setGitUserName(feedObj.getString("login"));
            gitUser.setGitName(feedObj.getString("name"));
            gitUser.setImgURL(feedObj.getString("avatar_url"));
            String repoLink = feedObj.getString("repos_url");
            getGitUserRepo(repoLink, gitUser.getImgURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void passIntent(){
        Intent intent = new Intent(MainActivity.this, RepoListActivity.class);
        gitUser.setRepoList(userRepository);
        intent.putExtra("GitUser", gitUser);
        URL_FEED = "https://api.github.com/users";
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MainActivity.this, (View)btnGet, "getTrans");
        startActivity(intent, options.toBundle());
    }

    private List<Repository> getUserRepoList(JSONArray response, String imgURL){
        List<Repository> repoList = new ArrayList<Repository>();
        if(response != null){
            try {
                for (int i = 0; i < response.length() ; i++){
                    JSONObject repoObj = (JSONObject) response.get(i);
                    Repository repo = new Repository();
                    repo.setRepoID(repoObj.getInt("id"));
                    repo.setRepoName(repoObj.getString("name"));
                    repo.setRepoDesc(repoObj.getString("description"));
                    repo.setRepoWatchCount(repoObj.getInt("watchers_count"));
                    repo.setRepoStarCount(repoObj.getInt("stargazers_count"));
                    repo.setImageURL(imgURL);
                    JSONObject ownerObj = repoObj.getJSONObject("owner");
                    repo.setRepoOwnerName(ownerObj.getString("login"));
                    repo.setRepoOwnerID(ownerObj.getInt("id"));
                    repoList.add(repo);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return repoList;
    }

    private void getGitUserRepo(String link, final String imgURL){
        userRepository = new ArrayList<Repository>();
        JsonArrayRequest jsonReq = new JsonArrayRequest(link, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    userRepository = getUserRepoList(response, imgURL);
                    passIntent();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonReq);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

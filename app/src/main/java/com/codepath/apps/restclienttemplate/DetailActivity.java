package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.content.res.Configuration;
        import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
        import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;

import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {

    ImageView ivProfileImage;
    TextView tvscreenName;
    ImageView ivMedia;
    TextView tvBody;
    TextView tvTime;
   // private String MenuItem;
    //TwitterClient Client;
    Menu MenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Tweet"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // getSupportActionBar().setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setLogo(R.drawable.ic_twitter);
       // getSupportActionBar().setDisplayUseLogoEnabled(true);


        String body = getIntent().getStringExtra("body");
        String screenName = getIntent().getStringExtra("screenName");
        String image = getIntent().getStringExtra("image");
        String time = getIntent().getStringExtra("time");
        String media = getIntent().getStringExtra("media");

        //Client = TwitterApp.getRestClient(this);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvscreenName = findViewById(R.id.tvscreenName);
        tvBody = findViewById(R.id.tvBody);
        tvTime = findViewById(R.id.tvTime);
        ivMedia = findViewById(R.id.ivMedia);

        tvBody.setText(body);
        tvscreenName.setText(screenName);
        boolean isPortrait = this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        int placeholder = isPortrait ? R.drawable.ph : R.drawable.ph;
        int radius = 20; // corner radius, higher value = more rounded
        int margin = 0; // crop margin, set to 0 for corners with no crop
        Glide.with(this).load(image).transform(new RoundedCornersTransformation(radius, margin)).placeholder(placeholder).error(placeholder).into(ivProfileImage);
        tvTime.setText(time);
        if(media != null) {
            int placeholder2 = isPortrait ? R.drawable.ph : R.drawable.ph;
            Glide.with(this).load(media).transform(new RoundedCornersTransformation(20, 0)).placeholder(placeholder2).error(placeholder2).into(ivMedia);
        }
    }

    @Override
    public boolean onOptionsItemSelected (android.view.MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnRply)
     public void reply(){
        Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
     }
}

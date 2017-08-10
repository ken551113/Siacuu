package com.kenlee.siacuu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kenlee.siacuu.R;
import com.squareup.picasso.Picasso;

public class BlogSingleActivity extends AppCompatActivity {

    private String mPostKey = null;

    private DatabaseReference mDatabase;

    private ImageView mBlogSingleImage;
    private TextView mBlogSingleTitle, mBlogSingleDesc;
    private Button mBlogsingleDelet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_single);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");

        mPostKey = getIntent().getExtras().getString("blog_id");


        mBlogSingleTitle = (TextView) findViewById(R.id.singleBlogTitle);
        mBlogSingleDesc = (TextView) findViewById(R.id.singleBlogDesc);
        mBlogSingleImage = (ImageView) findViewById(R.id.singleBlogImage);
        mBlogsingleDelet = (Button) findViewById(R.id.deletBtn);
        mBlogsingleDelet.setVisibility(View.GONE);
        mDatabase.child(mPostKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String post_title = (String)dataSnapshot.child("title").getValue();
                String post_desc = (String)dataSnapshot.child("desc").getValue();
                String post_image = (String)dataSnapshot.child("image").getValue();

                mBlogSingleTitle.setText(post_title);
                mBlogSingleDesc.setText(post_desc);

                Picasso.with(BlogSingleActivity.this).load(post_image).into(mBlogSingleImage);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mBlogsingleDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(mPostKey).removeValue();

                startActivity(new Intent(BlogSingleActivity.this, MainActivity.class));

            }
        });

    }
}

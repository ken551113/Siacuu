package com.kenlee.siacuu.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kenlee.siacuu.model.Blog;
import com.kenlee.siacuu.activity.BlogSingleActivity;
import com.kenlee.siacuu.R;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mBlogList = (RecyclerView) getView().findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String Post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setOrganization(model.getOrganization());
                viewHolder.setTime(model.getTime());
                viewHolder.setLocation(model.getLocation());


                viewHolder.setImage(getContext().getApplicationContext(), model.getImage());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent singleBlogIntent = new Intent(getActivity(), BlogSingleActivity.class);
                        singleBlogIntent.putExtra("blog_id", Post_key);
//                        Toast.makeText(getActivity(), Post_key, Toast.LENGTH_LONG).show();
                        startActivity(singleBlogIntent);

                    }
                });

            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        View mview;

        public BlogViewHolder(View itemView) {

            super(itemView);
            mview = itemView;
        }

        public void setTitle(String title){

            TextView post_title = (TextView) mview.findViewById(R.id.post_title);
            post_title.setText(title);

        }

        public void setOrganization(String organization){

            TextView post_organization = (TextView) mview.findViewById(R.id.post_organization);
            post_organization.setText(organization);

        }

        public void setTime(String time){

            TextView post_time = (TextView) mview.findViewById(R.id.post_time);
            post_time.setText(time);

        }

        public void setLocation(String location){

            TextView post_location = (TextView) mview.findViewById(R.id.post_location);
            post_location.setText(location);

        }

        public void setImage(Context ctx, String image){

            ImageView post_image = (ImageView) mview.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }
    }
    }


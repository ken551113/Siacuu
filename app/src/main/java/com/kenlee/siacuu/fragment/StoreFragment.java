package com.kenlee.siacuu.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;




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
import com.kenlee.siacuu.R;
import com.kenlee.siacuu.model.Store;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {

    private RecyclerView mStorelist;
    private DatabaseReference mDatabase;


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_store, container, false);
        return  v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Store");
        mStorelist = (RecyclerView) getView().findViewById(R.id.store_list);
        mStorelist.setHasFixedSize(true);
        mStorelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerAdapter<Store, StoreViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Store, StoreViewHolder>(
                Store.class,
                R.layout.store_row,
                StoreViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(StoreViewHolder viewHolder, Store model, int position) {

                final String Post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setTime(model.getTime());
                viewHolder.setImage(getContext().getApplicationContext(), model.getImage());
            }
        };

        mStorelist.setAdapter(firebaseRecyclerAdapter);

    }


    public static class StoreViewHolder extends RecyclerView.ViewHolder{

        View mview;

        public StoreViewHolder(View itemView) {
            super(itemView);
            mview = itemView;

        }
        public void setTitle(String title){

            TextView post_title = (TextView) mview.findViewById(R.id.post_title);
            post_title.setText(title);

        }

        public void setTime(String time){

            TextView post_time = (TextView) mview.findViewById(R.id.post_time);
            post_time.setText(time);

        }

        public void setImage(Context ctx, String image){

            ImageView post_image = (ImageView) mview.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }
    }
}
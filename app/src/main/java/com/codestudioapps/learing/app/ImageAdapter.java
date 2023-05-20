package com.codestudioapps.learing.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<String> mImageUrls;
    private Context mContext;

    public ImageAdapter(Context context, List<String> imageUrls) {
        mContext = context;
        mImageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mImageUrls.get(position))
                .apply(RequestOptions.centerCropTransform())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }

    public void addImages(List<String> imageUrls) {
        mImageUrls.addAll(imageUrls);
        notifyDataSetChanged();
    }

    public void clearImages() {
        mImageUrls.clear();
        notifyDataSetChanged();
    }

    public void setImages(List<String> imageUrls) {
        mImageUrls = new ArrayList<>(imageUrls);
        notifyDataSetChanged();
    }
}


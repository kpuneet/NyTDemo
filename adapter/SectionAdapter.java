package com.puneet.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.puneet.android.R;
import com.puneet.android.databinding.ListItemNewsCardBinding;
import com.puneet.android.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.CardViewHolder> {
    private List<Entry> mNewsList;
    private Context mContext;

    public SectionAdapter(Context context, List<Entry> news) {
        this.mNewsList = news;
        this.mContext = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_news_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        final Entry currentItem = mNewsList.get(position);
        holder.getBinding().setEntry(currentItem);
        if (currentItem.getImages() != null && currentItem.getImages().size() > 0 && !currentItem.getImages().get(0).getUrl().isEmpty() && currentItem.getImages().get(0).getUrl() != null) {
            holder.getBinding().setEntryUrl(currentItem.getImages().get(0).getUrl());
        }
        holder.getBinding().storyCard.setOnClickListener(v -> {
            Uri webpage = Uri.parse(currentItem.getUrl());
            Intent storyOnWebIntent = new Intent(Intent.ACTION_VIEW, webpage);
            if (storyOnWebIntent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(storyOnWebIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mNewsList == null) {
            return 0;
        }
        return mNewsList.size();
    }

    public void clear() {
        // Initialize new empty list, clearing out old data
        mNewsList = new ArrayList<>();
    }

    void addAll(List<Entry> data) {
        for (int i = 0; i < data.size(); i++) {
            Entry newsStory = data.get(i);
            mNewsList.add(newsStory);
            notifyDataSetChanged();
        }
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding dataBinding;
        ImageView newsThumbnail;
        TextView newsTitleTextView;
        TextView sectionNameTextView;
        TextView datePublishedTextView;
        CardView storyCard;

        CardViewHolder(View itemView) {
            super(itemView);
            dataBinding = DataBindingUtil.bind(itemView);
            newsThumbnail = getBinding().storyImage;
            newsTitleTextView = getBinding().newsTitleText;
            sectionNameTextView = getBinding().sectionNameText;
            datePublishedTextView = getBinding().datePublishedText;
            storyCard = getBinding().storyCard;
        }

        public ListItemNewsCardBinding getBinding() {
            return ((ListItemNewsCardBinding) dataBinding);
        }
    }

    public void update(List<Entry> entries) {
        boolean empty = mNewsList == null;
        mNewsList = entries;
        if (empty) {
            notifyItemRangeInserted(0, mNewsList.size());
        } else {
            notifyDataSetChanged();
        }
    }
}


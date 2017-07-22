package com.example.xyzreader.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.ui.detail.view_holder.ArticleDetailViewHolder;
import com.example.xyzreader.ui.detail.view_holder.ArticleDetailViewHolderByLine;
import com.example.xyzreader.ui.detail.view_holder.ArticleDetailViewHolderParagraph;
import com.example.xyzreader.ui.detail.view_holder.ArticleDetailViewHolderTitle;

import java.util.List;

public class ArticleDetailAdapter extends RecyclerView.Adapter<ArticleDetailViewHolder> {

    private List<ArticleViewModel> model;

    public void updateModel(List<ArticleViewModel> model) {
        this.model = model;
        notifyDataSetChanged();
    }

    @Override
    public ArticleDetailViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return model.get(i)
                .when(title -> {
                    View view = inflater.inflate(R.layout.list_item_title, null, false);
                    return new ArticleDetailViewHolderTitle(view);
                }, byLine -> {
                    View view = inflater.inflate(R.layout.list_item_byline, null, false);
                    return new ArticleDetailViewHolderByLine(view);
                }, paragraph -> {
                    View view = inflater.inflate(R.layout.list_item_body, null, false);
                    return new ArticleDetailViewHolderParagraph(view);
                });
    }

    @Override
    public void onBindViewHolder(ArticleDetailViewHolder articleDetailViewHolder, int i) {
        ArticleViewModel genericModel = this.model.get(i);
        genericModel.when(
                title -> {
                    articleDetailViewHolder.when(viewHolder -> viewHolder.bind(title),
                                                 ignored -> {},
                                                 ignored -> {});
                    return 0;
                }, byLine -> {
                    articleDetailViewHolder.when(ignored -> {},
                                                 viewHolder -> viewHolder.bind(byLine),
                                                 ignored -> {});
                    return 0;
                }, paragraph -> {
                    articleDetailViewHolder.when(ignored -> {},
                                                 ignored -> {},
                                                 viewHolder -> viewHolder.bind(paragraph));
                    return 0;
                });
    }

    @Override
    public int getItemViewType(int position) {
        return model.get(position)
                .when(title -> 0,
                      byLine -> 1,
                      paragraph -> 2);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}

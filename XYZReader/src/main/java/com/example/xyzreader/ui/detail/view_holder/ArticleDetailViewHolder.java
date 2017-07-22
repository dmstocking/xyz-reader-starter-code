package com.example.xyzreader.ui.detail.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.xyzreader.util.function.Action1;

public abstract class ArticleDetailViewHolder extends RecyclerView.ViewHolder {

    public ArticleDetailViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void when(Action1<ArticleDetailViewHolderTitle> title,
                              Action1<ArticleDetailViewHolderByLine> byLine,
                              Action1<ArticleDetailViewHolderParagraph> paragraph);
}

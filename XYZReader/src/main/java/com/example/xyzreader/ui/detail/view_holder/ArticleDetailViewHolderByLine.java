package com.example.xyzreader.ui.detail.view_holder;

import android.view.View;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.ui.detail.ArticleViewModel;
import com.example.xyzreader.util.function.Action1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailViewHolderByLine
        extends ArticleDetailViewHolder {

    @BindView(R.id.article_byline) TextView byLine;

    public ArticleDetailViewHolderByLine(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ArticleViewModel.ByLine model) {
        byLine.setText(model.byLine());
    }

    @Override
    public void when(Action1<ArticleDetailViewHolderTitle> title,
                     Action1<ArticleDetailViewHolderByLine> byLine,
                     Action1<ArticleDetailViewHolderParagraph> paragraph) {
        byLine.call(this);
    }
}

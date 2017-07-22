package com.example.xyzreader.ui.detail.view_holder;

import android.view.View;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.ui.detail.ArticleViewModel;
import com.example.xyzreader.util.function.Action1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailViewHolderParagraph
        extends ArticleDetailViewHolder {

    @BindView(R.id.article_body) TextView paragraph;

    public ArticleDetailViewHolderParagraph(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ArticleViewModel.Paragraph model) {
        paragraph.setText(model.paragraph());
    }

    @Override
    public void when(Action1<ArticleDetailViewHolderTitle> title,
                     Action1<ArticleDetailViewHolderByLine> byLine,
                     Action1<ArticleDetailViewHolderParagraph> paragraph) {
        paragraph.call(this);
    }
}

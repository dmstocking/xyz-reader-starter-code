package com.example.xyzreader.ui.detail;

import android.support.annotation.NonNull;

import com.example.xyzreader.util.function.Func2;
import com.google.auto.value.AutoValue;

public abstract class ArticleViewModel {

    public abstract <T> T when(Func2<Title, T> title,
                               Func2<ByLine, T> byLine,
                               Func2<Paragraph, T> paragraph);

    @AutoValue
    public abstract static class Title extends ArticleViewModel {

        public static Title create(@NonNull String title,
                                   @NonNull String photo, float aspectRatio) {
            return new AutoValue_ArticleViewModel_Title(title, photo, aspectRatio);
        }

        public abstract String title();
        public abstract String photo();
        public abstract float aspectRatio();

        @Override
        public <T> T when(Func2<Title, T> title,
                          Func2<ByLine, T> byLine,
                          Func2<Paragraph, T> paragraph) {
            return title.apply(this);
        }
    }

    @AutoValue
    public abstract static class ByLine extends ArticleViewModel {

        public static ByLine create(@NonNull String byLine) {
            return new AutoValue_ArticleViewModel_ByLine(byLine);
        }

        public abstract String byLine();

        @Override
        public <T> T when(Func2<Title, T> title,
                          Func2<ByLine, T> byLine,
                          Func2<Paragraph, T> paragraph) {
            return byLine.apply(this);
        }
    }

    @AutoValue
    public abstract static class Paragraph extends ArticleViewModel {

        public static Paragraph create(@NonNull String paragraph) {
            return new AutoValue_ArticleViewModel_Paragraph(paragraph);
        }

        public abstract String paragraph();

        @Override
        public <T> T when(Func2<Title, T> title,
                          Func2<ByLine, T> byLine,
                          Func2<Paragraph, T> paragraph) {
            return paragraph.apply(this);
        }
    }
}

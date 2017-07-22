package com.example.xyzreader.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.xyzreader.ui.detail.ArticleViewModel;

import io.reactivex.Observable;

public class ArticleViewModelRepository {

    @NonNull private final ContentResolver contentResolver;

    public ArticleViewModelRepository(@NonNull ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public Observable<ArticleViewModel> query(int itemId) {
        return Observable.create(emitter -> {
            Cursor query = contentResolver.query(ItemsContract.Items.buildItemUri(itemId),
                                                 null,
                                                 null,
                                                 null,
                                                 null);
            if (query == null) {
                emitter.onComplete();
                return;
            }

            while (query.moveToNext()) {
                String title = query.getString(query.getColumnIndex(ItemsContract.ItemsColumns.TITLE));
                String photo = query.getString(query.getColumnIndex(ItemsContract.ItemsColumns.PHOTO_URL));
                float aspectRatio = query.getFloat(query.getColumnIndex(ItemsContract.ItemsColumns.ASPECT_RATIO));
                String by = query.getString(query.getColumnIndex(ItemsContract.ItemsColumns.AUTHOR));
                String body = query.getString(query.getColumnIndex(ItemsContract.ItemsColumns.BODY));
                emitter.onNext(ArticleViewModel.Title.create(title, photo, aspectRatio));
                emitter.onNext(ArticleViewModel.ByLine.create(by));
                body = body.replace("\r\n\r\n", "\n\n");
                body = body.replace("\r\n", " ");
                for (String paragraph : body.split("\\n") ) {
                    emitter.onNext(ArticleViewModel.Paragraph.create(paragraph));
                }
            }
            emitter.onComplete();
        });
    }
}

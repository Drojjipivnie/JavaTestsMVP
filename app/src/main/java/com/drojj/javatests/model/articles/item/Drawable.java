package com.drojj.javatests.model.articles.item;

import android.content.Context;
import android.view.View;

public interface Drawable {
    View getView(Context context);

    int getPosition();
}

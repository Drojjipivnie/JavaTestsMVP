package com.drojj.javatests.model.articles.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

class Code extends Html implements Drawable {

    @Override
    public View getView(Context context) {
        TextView textView = (TextView) super.getView(context);
        HorizontalScrollView codeView = new HorizontalScrollView(context);

        codeView.addView(textView);
        codeView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        codeView.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        codeView.setSmoothScrollingEnabled(true);
        codeView.setHorizontalScrollBarEnabled(false);

        return codeView;
    }
}

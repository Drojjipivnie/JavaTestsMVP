package com.drojj.javatests.model.articles.item;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

class Image implements Drawable {
    private String position;

    private String value;

    public int getPosition() {
        return Integer.valueOf(position);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassPojo [position = " + position + ", value = " + value + "]";
    }

    @Override
    public View getView(Context context) {
        ImageView imageView = new ImageView(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);

        imageView.setLayoutParams(layoutParams);
        try {
            imageView.setImageDrawable(android.graphics.drawable.Drawable.createFromStream(context.getAssets().open(value), null));
        } catch (IOException e) {
            throw new RuntimeException("Error reading image from path " + value, e);
        }

        return imageView;
    }
}

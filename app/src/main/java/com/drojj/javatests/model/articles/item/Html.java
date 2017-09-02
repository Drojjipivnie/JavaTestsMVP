package com.drojj.javatests.model.articles.item;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

class Html implements Drawable {
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
        TextView v = new TextView(context);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            v.setText(android.text.Html.fromHtml(value, android.text.Html.FROM_HTML_MODE_LEGACY));
        } else {
            v.setText(android.text.Html.fromHtml(value));
        }
        v.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        //TODO: CalligraphyUtils.applyFontToTextView(v, mParent.getRegularFont());
        return v;
    }
}

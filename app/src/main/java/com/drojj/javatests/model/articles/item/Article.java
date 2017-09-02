package com.drojj.javatests.model.articles.item;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private List<Drawable> drawablesArray;

    private Html[] html;

    private Image[] image;

    private Code[] code;

    public Html[] getHtml() {
        return html;
    }

    public void setHtml(Html[] html) {
        this.html = html;
    }

    public Image[] getImage() {
        return image;
    }

    public void setImage(Image[] image) {
        this.image = image;
    }

    public Code[] getCode() {
        return code;
    }

    public void setCode(Code[] code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Article [html = " + html + ", image = " + image + ", code = " + code + "]";
    }


    public List<Drawable> getDrawables() {
        if (drawablesArray != null) {
            return drawablesArray;
        }

        SparseArray<Drawable> drawables = new SparseArray<>(html.length + image.length + code.length);

        parseDrawableArray(drawables, html);
        parseDrawableArray(drawables, image);
        parseDrawableArray(drawables, code);

        List<Drawable> drawablesArray = new ArrayList<>(drawables.size());
        for (int i = 1; i <= drawables.size(); i++) {
            drawablesArray.add(drawables.get(i));
        }

        return drawablesArray;
    }

    private void parseDrawableArray(SparseArray<Drawable> drawables, Drawable[] array) {
        for (Drawable drawable : array) {
            drawables.put(drawable.getPosition(), drawable);
        }
    }
}

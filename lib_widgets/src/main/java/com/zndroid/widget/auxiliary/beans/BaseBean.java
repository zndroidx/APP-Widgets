package com.zndroid.widget.auxiliary.beans;

import android.os.Build;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Created by lzy on 2020/8/17.
 * entity should extends this when use {@link com.zndroid.widget.expand.AnimatedExpandableListView}
 */
public class BaseBean<T> {
    private boolean isChecked = false;
    private long id;
    private List<T> childList;

    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        this.childList = childList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public T getChildItem(int index) {
        return childList == null ? null : childList.get(index);
    }

    public void toggle() {
        this.isChecked = !this.isChecked;
    }

    public int getChildCount() {
        return childList.size();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof BaseBean)) return false;
        return this.id == ((BaseBean) obj).getId();
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(id);
        }

        int hash = 5;
        hash = 67 * hash + (int)(this.id ^ (this.id >>> 32));

        return hash;
    }
}

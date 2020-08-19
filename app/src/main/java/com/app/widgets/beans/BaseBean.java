package com.app.widgets.beans;

import android.os.Build;

import com.zndroid.widget.auxiliary.beans.IExpandBean;

import java.util.List;
import java.util.Objects;

/**
 * Created by lzy on 2020/8/17.
 */
public class BaseBean<T> implements IExpandBean<T> {
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BaseBean<?> baseBean = (BaseBean<?>) object;
        return id == baseBean.id;
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

package com.zndroid.widget.auxiliary.beans;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by lzy on 2020/8/19.
 */
public interface IExpandBean<T> {
    boolean isChecked();
    void setChecked(boolean checked);

    long getId();
    void setId(long id);

    List<T> getChildList();
    void setChildList(List<T> childList);

    T getChildItem(int index);
    int getChildCount();

    void toggle();

    //只需要判断id即可
    @Override
    boolean equals(@Nullable Object obj);

    //只需要hashCode id即可
    @Override
    int hashCode();
}

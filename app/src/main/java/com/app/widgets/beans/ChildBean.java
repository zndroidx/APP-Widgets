package com.app.widgets.beans;

import com.zndroid.widget.auxiliary.beans.BaseBean;

/**
 * Created by lzy on 2020/8/17.
 */
public class ChildBean extends BaseBean<Void> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getChildCount() {
        return 0;
    }
}

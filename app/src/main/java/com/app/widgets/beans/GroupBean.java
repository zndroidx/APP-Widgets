package com.app.widgets.beans;

import com.zndroid.widget.auxiliary.beans.BaseBean;

/**
 * Created by lzy on 2020/8/17.
 */
public class GroupBean extends BaseBean<ChildBean> {
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

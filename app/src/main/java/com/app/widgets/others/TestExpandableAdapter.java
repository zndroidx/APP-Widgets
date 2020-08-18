package com.app.widgets.others;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.app.widgets.R;
import com.app.widgets.beans.ChildBean;
import com.app.widgets.beans.GroupBean;
import com.zndroid.widget.auxiliary.CommonCheckExpandableAdapter;

import java.util.List;

/**
 * Created by lzy on 2020/8/18.
 */
public class TestExpandableAdapter extends CommonCheckExpandableAdapter<GroupBean, ChildBean, GroupVH, ChildVH> {
    public TestExpandableAdapter(Context context, List<GroupBean> groups) {
        super(context, groups);
    }

    @Override
    protected GroupVH getGroupViewHolder(View itemView, int type, ViewGroup parent) {
        return new GroupVH(itemView);
    }

    @Override
    protected int getBindGroupItemViewId() {
        return R.layout.group_layout;
    }

    @Override
    protected void onBindGroupData(GroupVH holder, GroupBean group, final int groupPosition) {
        holder.textView.setText(group.getGroupName());
        holder.checkBox.setChecked(group.isChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGroupCheck(groupPosition);
            }
        });
    }

    @Override
    protected ChildVH getChildViewHolder(View itemView, int type, ViewGroup parent) {
        return new ChildVH(itemView);
    }

    @Override
    protected int getBindChildItemViewId() {
        return R.layout.child_layout;
    }

    @Override
    protected void onBindChildData(ChildVH holder, ChildBean child, final int groupPosition, final int childPosition) {
        holder.textView.setText(child.getName());
        holder.checkBox.setChecked(child.isChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChildCheck(childPosition, groupPosition);
            }
        });

        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(groupPosition, childPosition);
            }
        });
    }
}

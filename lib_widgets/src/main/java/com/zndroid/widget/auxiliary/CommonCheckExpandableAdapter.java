package com.zndroid.widget.auxiliary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zndroid.widget.auxiliary.beans.BaseBean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lzy on 2020/8/18.
 */
public abstract class CommonCheckExpandableAdapter<G extends BaseBean<C>, C extends BaseBean, VH_G extends BaseExpandableAdapter.ExpandableViewHolder, VH_C extends BaseExpandableAdapter.ExpandableViewHolder> extends BaseExpandableAdapter<G, C, VH_G, VH_C> {
    private List<C> selectedList = new CopyOnWriteArrayList<>();
    private ItemSelectChangedListener<C> itemSelectChangedListener;

    public void setSelectedList(List<C> selectedList) {
        this.selectedList.clear();
        this.selectedList = selectedList;
        selectedChildrenList.clear();
        selectedChildrenList.addAll(this.selectedList);

        for (G g : groups) {
            for (C c : g.getChildList()) {
                if (selectedList.contains(c))
                    c.setChecked(true);
            }
        }//child contains

        for (G g : groups) {
            if (selectedList.containsAll(g.getChildList()))
                g.setChecked(true);
        }//group contains

        notifyDataSetChanged();
    }

    public void unSelectAll() {
        if (null != selectedList)
            this.selectedList.clear();

        selectedChildrenList.clear();

        for (G g : groups) {
            g.setChecked(false);
            for (C c : g.getChildList()) {
                c.setChecked(false);
            }
        }

        notifyDataSetChanged();
    }

    public interface ItemSelectChangedListener<T> {
        void onItemSelectChanged(List<T> select);
    }

    public void setItemSelectChangedListener(ItemSelectChangedListener<C> itemSelectChangedListener) {
        this.itemSelectChangedListener = itemSelectChangedListener;
    }

    public CommonCheckExpandableAdapter(Context context, List<G> groups) {
        super(context, groups);
    }

    @Override
    protected abstract VH_G getGroupViewHolder(View itemView, int type, ViewGroup parent);

    @Override
    protected abstract int getBindGroupItemViewId();

    @Override
    protected abstract void onBindGroupData(VH_G holder, G group, int groupPosition);

    @Override
    protected abstract VH_C getChildViewHolder(View itemView, int type, ViewGroup parent);

    @Override
    protected abstract int getBindChildItemViewId();

    @Override
    protected abstract void onBindChildData(VH_C holder, C child, int groupPosition, int childPosition);

    @Override
    protected void onSelectChanged(List<C> childList) {
        this.itemSelectChangedListener.onItemSelectChanged(childList);
    }
}

package com.zndroid.widget.auxiliary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import com.zndroid.widget.auxiliary.beans.IExpandBean;
import com.zndroid.widget.expand.AnimatedExpandableListView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by lzy on 2020/8/17.
 */
public abstract class BaseExpandableAdapter<G extends IExpandBean<C>, C extends IExpandBean,
        VH_G extends BaseExpandableAdapter.ExpandableViewHolder,
        VH_C extends BaseExpandableAdapter.ExpandableViewHolder>
        extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private Context context;
    protected List<G> groups = new CopyOnWriteArrayList<>();
    protected List<C> selectedChildrenList = new CopyOnWriteArrayList<>();

    protected ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClicked(int groupPosition, int childPosition);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public BaseExpandableAdapter(Context context) {
        this.context = context;
        this.selectedChildrenList = new CopyOnWriteArrayList<>();
    }

    public void setNewData(List<G> groups) {
        this.groups = groups;
        notifyDataSetChanged();
    }

    public List<C> getAllChildrenList() {
        List<C> temp = new CopyOnWriteArrayList<>();
        for (G g : groups) {
            temp.addAll(g.getChildList());
        }

        return temp;
    }

    protected abstract VH_G getGroupViewHolder(View itemView, int type, ViewGroup parent);
    protected abstract @LayoutRes int getBindGroupItemViewId();
    protected abstract void onBindGroupData(VH_G holder, G group, int groupPosition);

    protected abstract VH_C getChildViewHolder(View itemView, int type, ViewGroup parent);
    protected abstract @LayoutRes int getBindChildItemViewId();
    protected abstract void onBindChildData(VH_C holder, C child, int groupPosition, int childPosition);

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        C child = groups.get(groupPosition).getChildItem(childPosition);
        VH_C holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(getBindChildItemViewId(), null);

            holder = getChildViewHolder(convertView, getRealChildType(groupPosition, childPosition), parent);
            convertView.setTag(holder);
        } else {
           holder = (VH_C) convertView.getTag();
        }

        onBindChildData(holder, child, groupPosition, childPosition);

        return convertView;
    }

    public void handleChildCheck(int childPosition, int groupPosition) {
        groups.get(groupPosition).getChildItem(childPosition).toggle();

        // 檢查 Child CheckBox 是否有全部勾選，以控制 Group CheckBox
        int childrenCount = groups.get(groupPosition).getChildCount();
        boolean childrenAllIsChecked = true;
        for (int i = 0; i < childrenCount; i++) {
            if (!groups.get(groupPosition).getChildItem(i).isChecked())
                childrenAllIsChecked = false;
        }

        groups.get(groupPosition).setChecked(childrenAllIsChecked);

        notifyDataSetChanged();
        setSelectedItems();
    }

    public void handleGroupCheck(int groupPosition) {
        groups.get(groupPosition).toggle();

        int childrenCount = groups.get(groupPosition).getChildCount();
        boolean groupIsChecked = groups.get(groupPosition).isChecked();
        for (int i = 0; i < childrenCount; i++)
            groups.get(groupPosition).getChildItem(i).setChecked(groupIsChecked);

        notifyDataSetChanged();
        setSelectedItems();
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChildCount();
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChildItem(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groups.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChildItem(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        G group = groups.get(groupPosition);
        VH_G holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(getBindGroupItemViewId(), null);

            holder = getGroupViewHolder(convertView, getGroupType(groupPosition), parent);
            convertView.setTag(holder);
        } else {
            holder = (VH_G) convertView.getTag();
        }
        onBindGroupData(holder, group, groupPosition);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private void setSelectedItems() {
        selectedChildrenList.clear();
        for (G g : groups) {
            for (C c : g.getChildList()) {
                if (c.isChecked())
                    selectedChildrenList.add(c);
            }
        }

        onSelectChanged(selectedChildrenList);
    }

    protected void onSelectChanged(List<C> childList) {

    }

    public List<C> getSelectedChildrenList() {
        return selectedChildrenList;
    }

    public static class ExpandableViewHolder {
        public ExpandableViewHolder(View itemView) {
            this.itemView = itemView;
        }

        private View itemView;

        public void setItemView(View itemView) {
            this.itemView = itemView;
        }

        public View getItemView() {
            return itemView;
        }
    }
}

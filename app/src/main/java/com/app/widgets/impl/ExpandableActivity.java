package com.app.widgets.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.widgets.R;
import com.app.widgets.beans.ChildBean;
import com.app.widgets.beans.GroupBean;
import com.app.widgets.others.TestExpandableAdapter;
import com.zndroid.widget.auxiliary.BaseExpandableAdapter;
import com.zndroid.widget.auxiliary.CommonCheckExpandableAdapter;
import com.zndroid.widget.expand.AnimatedExpandableListView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExpandableActivity extends AppCompatActivity {
    private AnimatedExpandableListView expandableListView;
    private List<GroupBean> list = new CopyOnWriteArrayList<>();
    private TestExpandableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        expandableListView = findViewById(R.id.expand_list_view);

        initData();

        adapter = new TestExpandableAdapter(this);
        adapter.setNewData(list);

        ChildBean childBean21 = new ChildBean();
        childBean21.setId(21);
        childBean21.setName("2-1");

        ChildBean childBean22 = new ChildBean();
        childBean22.setId(22);
        childBean22.setName("2-2");

        ChildBean childBean23 = new ChildBean();
        childBean23.setId(23);
        childBean23.setName("2-3");

        ChildBean childBean12 = new ChildBean();
        childBean12.setId(12);
        childBean12.setName("1-2");

        List<ChildBean> list2 = new CopyOnWriteArrayList<>();
        list2.add(childBean21);
        list2.add(childBean22);
        list2.add(childBean23);
        list2.add(childBean12);
        adapter.setSelectedList(list2);

        adapter.setItemClickListener(new BaseExpandableAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(int groupPosition, int childPosition) {
                Toast.makeText(ExpandableActivity.this, groupPosition + " " + childPosition, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setItemSelectChangedListener(new CommonCheckExpandableAdapter.ItemSelectChangedListener<ChildBean>() {
            @Override
            public void onItemSelectChanged(List<ChildBean> select) {

            }
        });
        expandableListView.setAdapter(adapter);
    }

    private void initData() {
        ChildBean childBean11 = new ChildBean();
        childBean11.setId(11);
        childBean11.setName("1-1");

        ChildBean childBean12 = new ChildBean();
        childBean12.setId(12);
        childBean12.setName("1-2");

        ChildBean childBean13 = new ChildBean();
        childBean13.setId(13);
        childBean13.setName("1-3");

        List<ChildBean> list1 = new CopyOnWriteArrayList<>();
        list1.add(childBean11);
        list1.add(childBean12);
        list1.add(childBean13);

        GroupBean groupBean1 = new GroupBean();
        groupBean1.setId(111);
        groupBean1.setGroupName("111");
        groupBean1.setChildList(list1);

        ///////

        ChildBean childBean21 = new ChildBean();
        childBean21.setId(21);
        childBean21.setName("2-1");

        ChildBean childBean22 = new ChildBean();
        childBean22.setId(22);
        childBean22.setName("2-2");

        ChildBean childBean23 = new ChildBean();
        childBean23.setId(23);
        childBean23.setName("2-3");

        List<ChildBean> list2 = new CopyOnWriteArrayList<>();
        list2.add(childBean21);
        list2.add(childBean22);
        list2.add(childBean23);

        GroupBean groupBean2 = new GroupBean();
        groupBean2.setId(222);
        groupBean2.setGroupName("222");
        groupBean2.setChildList(list2);

        list.add(groupBean1);
        list.add(groupBean2);
    }

    public void selectAll(View view) {
        List<ChildBean> childBeanList = new CopyOnWriteArrayList<>();
        for (GroupBean g: list) {
            childBeanList.addAll(g.getChildList());
        }
        adapter.setSelectedList(childBeanList);
    }

    public void unSelectAll(View view) {

        adapter.unSelectAll();

        List<ChildBean> list = adapter.getSelectedChildrenList();

        Log.i("ll", list.size() + "");
        for (ChildBean bean : list)
            Log.i("ll", bean.getName());
    }

    public void get(View view) {
        List<ChildBean> list = adapter.getSelectedChildrenList();

        Log.i("ll", list.size() + "");
        for (ChildBean bean : list)
            Log.i("ll", bean.getName());
    }
}

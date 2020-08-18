package com.app.widgets.others;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.app.widgets.R;
import com.zndroid.widget.auxiliary.BaseExpandableAdapter;

/**
 * Created by lzy on 2020/8/18.
 */
public class GroupVH extends BaseExpandableAdapter.ExpandableViewHolder {
    public TextView textView;
    public CheckBox checkBox;

    public GroupVH(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.tvGroup);
        checkBox = itemView.findViewById(R.id.chbGroup);
    }
}

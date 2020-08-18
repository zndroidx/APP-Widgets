package com.app.widgets.others;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.app.widgets.R;
import com.zndroid.widget.auxiliary.BaseExpandableAdapter;

/**
 * Created by lzy on 2020/8/18.
 */
public class ChildVH extends BaseExpandableAdapter.ExpandableViewHolder {
    public TextView textView;
    public CheckBox checkBox;

    public ChildVH(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.tvChild);
        checkBox = itemView.findViewById(R.id.chbChild);
    }
}

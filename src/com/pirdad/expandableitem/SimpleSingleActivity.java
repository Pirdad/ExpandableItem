package com.pirdad.expandableitem;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.pirdad.expandableitem.adapter.SimpleListAdapter;
import com.pirdad.expandableitem.view.ExpandableItem;


public class SimpleSingleActivity extends Activity implements ExpandableItem.ItemOpenListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        View view_visible = inflater.inflate(R.layout.item_visible, null);
        View view_hidden = inflater.inflate(R.layout.item_hidden, null);

        ExpandableItem item = new ExpandableItem(this);
        item.setup.center_layout = view_visible;
        item.setup.bottom_layout = view_hidden;
        item.setup.drag_gesture = ExpandableItem.DRAG_GESTURE.ONE_FINGER;
        item.setup.open_listener = this;

        setContentView(item);
    }

    @Override
    public void onItemOpened(int id, ExpandableItem view) {

    }

    @Override
    public void onItemClosed(int id, ExpandableItem view) {

    }
}

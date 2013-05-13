package com.pirdad.expandableitem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.pirdad.expandableitem.R;
import com.pirdad.expandableitem.view.ExpandableItem;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: pirdod
 * Date: 4/26/13
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleListAdapter extends BaseAdapter implements ExpandableItem.ItemOpenListener {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<TestItem> items;

    public SimpleListAdapter(Context context) {

        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items = getDummyData();
    }

    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public Object getItem(int position) {

        return items.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final TestItem tst_item = items.get(position);

        View view_visible = tst_item.visible_view;
        View view_hidden = tst_item.hidden_view;

        if (view_visible == null) {
            view_visible = inflater.inflate(R.layout.item_visible, null);
            tst_item.visible_view = view_visible;
        }
        if (view_hidden == null) {
            view_hidden = inflater.inflate(R.layout.item_hidden, null);
            tst_item.hidden_view = view_hidden;
        }

        final ExpandableItem view = new ExpandableItem(context);
        view.setup.id = tst_item.id;
        view.setup.center_layout = view_visible;
        view.setup.bottom_layout = view_hidden;
        view.setup.drag_gesture = ExpandableItem.DRAG_GESTURE.TWO_FINGER;
        view.setup.long_press_delay = 1000;
        view.setup.open_listener = this;
        view.setup.extra_params.put("ITEM_REF", tst_item);
        view.setup.is_expanded = tst_item.is_open;

        TextView txt_visible = (TextView) view.setup.center_layout.findViewById(R.id.txt_visible);
        TextView txt_hidden = (TextView) view.setup.bottom_layout.findViewById(R.id.txt_hidden);

        txt_visible.setText(tst_item.title);
        txt_hidden.setText(tst_item.detail);

        return view;
    }

    @Override
    public void onItemOpened(int id, ExpandableItem view) {

        TestItem item = (TestItem) view.setup.extra_params.get("ITEM_REF");
        item.is_open = true;
        Toast.makeText(context, "item: " + id + " is now open", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClosed(int id, ExpandableItem view) {

        TestItem item = (TestItem) view.setup.extra_params.get("ITEM_REF");
        item.is_open = false;
        Toast.makeText(context, "item: " + id + " is now closed", Toast.LENGTH_SHORT).show();
    }

    public class TestItem {

        public int id;

        public String title;
        public String detail;
        public boolean is_open = false;
        public View hidden_view;
        public View visible_view;
        public TestItem(int id, String title, String detail) {

            this.id = id;
            this.title = title;
            this.detail = detail;
        }
    }

    private ArrayList<TestItem> getDummyData() {

        ArrayList<TestItem> items = new ArrayList<TestItem>();

        items.add(new TestItem(12, "First", "Wow ........................ \n ............................. \n ........................... \n ........................... \n ........................... \n ........................... \n ........................... \n ........................... \n .................................\n ..."));
        items.add(new TestItem(13, "Second ........................ \n" +
                " ............................. \n" +  " ............................. \n" +" ............................. \n" +" ............................. \n" +
                " ....", "Nice"));
        items.add(new TestItem(14, "Third", "Ahhh"));
        items.add(new TestItem(15, "Fourth", "Cheyaaa"));
        items.add(new TestItem(16, "Fifth", "Weeeee"));
        items.add(new TestItem(17, "First", "Wow"));
        items.add(new TestItem(18, "Second", "Nice"));
        items.add(new TestItem(19, "Third", "Ahhh"));
        items.add(new TestItem(20, "Fourth", "Cheyaaa"));
        items.add(new TestItem(21, "Fifth", "Weeeee"));
        items.add(new TestItem(22, "First", "Wow"));
        items.add(new TestItem(23, "Second", "Nice"));
        items.add(new TestItem(24, "Third", "Ahhh"));
        items.add(new TestItem(25, "Fourth", "Cheyaaa"));
        items.add(new TestItem(26, "Fifth", "Weeeee"));
        items.add(new TestItem(12, "First", "Wow ........................ \n ............................. \n ........................... \n .................................\n ..."));
        items.add(new TestItem(13, "Second ........................ \n" +
                " ............................. \n" +
                " ....", "Nice"));
        items.add(new TestItem(14, "Third", "Ahhh"));
        items.add(new TestItem(15, "Fourth", "Cheyaaa"));
        items.add(new TestItem(16, "Fifth", "Weeeee"));
        items.add(new TestItem(17, "First", "Wow"));
        items.add(new TestItem(18, "Second", "Nice"));
        items.add(new TestItem(19, "Third", "Ahhh"));
        items.add(new TestItem(20, "Fourth", "Cheyaaa"));
        items.add(new TestItem(21, "Fifth", "Weeeee"));
        items.add(new TestItem(22, "First", "Wow"));
        items.add(new TestItem(23, "Second", "Nice"));
        items.add(new TestItem(24, "Third", "Ahhh"));
        items.add(new TestItem(25, "Fourth", "Cheyaaa"));
        items.add(new TestItem(26, "Fifth", "Weeeee"));
        items.add(new TestItem(12, "First", "Wow ........................ \n ............................. \n ........................... \n .................................\n ..."));
        items.add(new TestItem(13, "Second ........................ \n" +
                " ............................. \n" +
                " ....", "Nice"));
        items.add(new TestItem(14, "Third", "Ahhh"));
        items.add(new TestItem(15, "Fourth", "Cheyaaa"));
        items.add(new TestItem(16, "Fifth", "Weeeee"));
        items.add(new TestItem(17, "First", "Wow"));
        items.add(new TestItem(18, "Second", "Nice"));
        items.add(new TestItem(19, "Third", "Ahhh"));
        items.add(new TestItem(20, "Fourth", "Cheyaaa"));
        items.add(new TestItem(21, "Fifth", "Weeeee"));
        items.add(new TestItem(22, "First", "Wow"));
        items.add(new TestItem(23, "Second", "Nice"));
        items.add(new TestItem(24, "Third", "Ahhh"));
        items.add(new TestItem(25, "Fourth", "Cheyaaa"));
        items.add(new TestItem(26, "Fifth", "Weeeee"));
        items.add(new TestItem(12, "First", "Wow ........................ \n ............................. \n ........................... \n .................................\n ..."));
        items.add(new TestItem(13, "Second ........................ \n" +
                " ............................. \n" +
                " ....", "Nice"));
        items.add(new TestItem(14, "Third", "Ahhh"));
        items.add(new TestItem(15, "Fourth", "Cheyaaa"));
        items.add(new TestItem(16, "Fifth", "Weeeee"));
        items.add(new TestItem(17, "First", "Wow"));
        items.add(new TestItem(18, "Second", "Nice"));
        items.add(new TestItem(19, "Third", "Ahhh"));
        items.add(new TestItem(20, "Fourth", "Cheyaaa"));
        items.add(new TestItem(21, "Fifth", "Weeeee"));
        items.add(new TestItem(22, "First", "Wow"));
        items.add(new TestItem(23, "Second", "Nice"));
        items.add(new TestItem(24, "Third", "Ahhh"));
        items.add(new TestItem(25, "Fourth", "Cheyaaa"));
        items.add(new TestItem(26, "Fifth", "Weeeee"));
        items.add(new TestItem(12, "First", "Wow ........................ \n ............................. \n ........................... \n .................................\n ..."));
        items.add(new TestItem(13, "Second ........................ \n" +
                " ............................. \n" +
                " ....", "Nice"));
        items.add(new TestItem(14, "Third", "Ahhh"));
        items.add(new TestItem(15, "Fourth", "Cheyaaa"));
        items.add(new TestItem(16, "Fifth", "Weeeee"));
        items.add(new TestItem(17, "First", "Wow"));
        items.add(new TestItem(18, "Second", "Nice"));
        items.add(new TestItem(19, "Third", "Ahhh"));
        items.add(new TestItem(20, "Fourth", "Cheyaaa"));
        items.add(new TestItem(21, "Fifth", "Weeeee"));
        items.add(new TestItem(22, "First", "Wow"));
        items.add(new TestItem(23, "Second", "Nice"));
        items.add(new TestItem(24, "Third", "Ahhh"));
        items.add(new TestItem(25, "Fourth", "Cheyaaa"));
        items.add(new TestItem(26, "Fifth", "Weeeee"));
        items.add(new TestItem(12, "First", "Wow ........................ \n ............................. \n ........................... \n .................................\n ..."));
        items.add(new TestItem(13, "Second ........................ \n" +
                " ............................. \n" +
                " ....", "Nice"));
        items.add(new TestItem(14, "Third", "Ahhh"));
        items.add(new TestItem(15, "Fourth", "Cheyaaa"));
        items.add(new TestItem(16, "Fifth", "Weeeee"));
        items.add(new TestItem(17, "First", "Wow"));
        items.add(new TestItem(18, "Second", "Nice"));
        items.add(new TestItem(19, "Third", "Ahhh"));
        items.add(new TestItem(20, "Fourth", "Cheyaaa"));
        items.add(new TestItem(21, "Fifth", "Weeeee"));
        items.add(new TestItem(22, "First", "Wow"));
        items.add(new TestItem(23, "Second", "Nice"));
        items.add(new TestItem(24, "Third", "Ahhh"));
        items.add(new TestItem(25, "Fourth", "Cheyaaa"));
        items.add(new TestItem(26, "Fifth", "Weeeee"));
        items.add(new TestItem(12, "First", "Wow ........................ \n ............................. \n ........................... \n .................................\n ..."));
        items.add(new TestItem(13, "Second ........................ \n" +
                " ............................. \n" +
                " ....", "Nice"));
        items.add(new TestItem(14, "Third", "Ahhh"));
        items.add(new TestItem(15, "Fourth", "Cheyaaa"));
        items.add(new TestItem(16, "Fifth", "Weeeee"));
        items.add(new TestItem(17, "First", "Wow"));
        items.add(new TestItem(18, "Second", "Nice"));
        items.add(new TestItem(19, "Third", "Ahhh"));
        items.add(new TestItem(20, "Fourth", "Cheyaaa"));
        items.add(new TestItem(21, "Fifth", "Weeeee"));
        items.add(new TestItem(22, "First", "Wow"));
        items.add(new TestItem(23, "Second", "Nice"));
        items.add(new TestItem(24, "Third", "Ahhh"));
        items.add(new TestItem(25, "Fourth", "Cheyaaa"));
        items.add(new TestItem(26, "Fifth", "Weeeee"));

        return items;
    }
}

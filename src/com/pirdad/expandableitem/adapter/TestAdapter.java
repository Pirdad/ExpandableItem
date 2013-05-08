package com.pirdad.expandableitem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.pirdad.expandableitem.R;
import com.pirdad.expandableitem.view.ExpandableListItem;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: pirdod
 * Date: 4/26/13
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAdapter extends BaseAdapter implements ExpandableListItem.ItemOpenListener {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<TestItem> items;

    public TestAdapter(Context context) {

        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items = new ArrayList<TestItem>();
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

        ExpandableListItem view = (ExpandableListItem) convertView;
        TestItem tst_item = items.get(position);

        if (view == null || tst_item.hidden_view == null) {

            View view_visible = inflater.inflate(R.layout.item_visible, null);
            View view_hidden = inflater.inflate(R.layout.item_hidden, null);

            view = new ExpandableListItem(context);
            view.setup = view.new SetupInfo();
            view.setup.center_layout = view_visible;
            view.setup.bottom_layout = view_hidden;
            view.setup.long_press_delay = 1000;
            view.setup.open_listener = this;
            tst_item.hidden_view = view_hidden;

        } else if (tst_item.hidden_view != null) {

            view.setup.bottom_layout = tst_item.hidden_view;
            view.reset();
        }

        view.setup.id = tst_item.id;
        view.setup.two_finger_drag = true;
        view.setup.extra_params.put("ITEM_REF", tst_item);
        if (tst_item.is_open) view.open();
        else view.close();

        TextView txt_visible = (TextView) view.setup.center_layout.findViewById(R.id.txt_visible);
        TextView txt_hidden = (TextView) view.setup.bottom_layout.findViewById(R.id.txt_hidden);

        txt_visible.setText(tst_item.title);
        txt_hidden.setText(tst_item.detail);

        return view;
    }

    @Override
    public void onItemOpened(int id, ExpandableListItem view) {

        TestItem item = (TestItem) view.setup.extra_params.get("ITEM_REF");
        item.is_open = true;
        Toast.makeText(context, "item: " + id + " is now open", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClosed(int id, ExpandableListItem view) {

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

        public TestItem(int id, String title, String detail) {

            this.id = id;
            this.title = title;
            this.detail = detail;
        }
    }
}

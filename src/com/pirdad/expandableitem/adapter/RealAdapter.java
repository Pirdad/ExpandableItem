package com.pirdad.expandableitem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.pirdad.expandableitem.R;
import com.pirdad.expandableitem.object.Feed;
import com.pirdad.expandableitem.view.ExpandableItem;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: pirdod
 * Date: 5/7/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealAdapter extends BaseAdapter implements ExpandableItem.ItemOpenListener {

    private Context context;
    private LayoutInflater inflater;
    public ArrayList<Feed> items;

    public RealAdapter(Context context) {

        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items = new ArrayList<Feed>();
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

        ExpandableItem view = (ExpandableItem) convertView;
        Feed feed = items.get(position);

        if (view == null || feed.hidden_view == null) {

            View view_visible = inflater.inflate(R.layout.item_visible, null);
            View view_hidden = inflater.inflate(R.layout.item_hidden, null);

            view = new ExpandableItem(context);
            view.setup.center_layout = view_visible;
            view.setup.bottom_layout = view_hidden;
            view.setup.long_press_delay = 1000;
            view.setup.open_listener = this;
            feed.hidden_view = view_hidden;

        } else if (feed.hidden_view != null) {

            view.setup.bottom_layout = feed.hidden_view;
            view.reset();
        }

        view.setup.extra_params.put("ITEM_REF", feed);
        if (feed.is_open) view.open(false);
        else view.close(false);

        TextView txt_visible = (TextView) view.setup.center_layout.findViewById(R.id.txt_visible);
        TextView txt_hidden = (TextView) view.setup.bottom_layout.findViewById(R.id.txt_hidden);

        txt_visible.setText(feed.title);
        txt_hidden.setText(feed.contentSnippet);

        return view;
    }

    @Override
    public void onItemOpened(int id, ExpandableItem view) {

        Feed item = (Feed) view.setup.extra_params.get("ITEM_REF");
        item.is_open = true;
        Toast.makeText(context, "item: " + id + " is now open", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClosed(int id, ExpandableItem view) {

        Feed item = (Feed) view.setup.extra_params.get("ITEM_REF");
        item.is_open = false;
        Toast.makeText(context, "item: " + id + " is now closed", Toast.LENGTH_SHORT).show();
    }
}

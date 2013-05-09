package com.pirdad.expandableitem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.pirdad.expandableitem.adapter.SimpleAdapter;


public class SimpleActivity extends Activity {

    private ListView listview_test;
    private SimpleAdapter adapter_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        LayoutInflater inflater = getLayoutInflater();
//        View view_visible = inflater.inflate(R.layout.item_visible, null);
//        View item_hidden = inflater.inflate(R.layout.item_hidden, null);
//        ExpandableItem item = new ExpandableItem(this, view_visible, item_hidden);
//
//        setContentView(item);

        setContentView(R.layout.list);

        listview_test = (ListView) findViewById(R.id.listview_test);
        adapter_test = new SimpleAdapter(this);
        listview_test.setAdapter(adapter_test);
    }
}

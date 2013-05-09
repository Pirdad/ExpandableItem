package com.pirdad.expandableitem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.pirdad.expandableitem.adapter.SimpleListAdapter;


public class SimpleListActivity extends Activity {

    private ListView listview_test;
    private SimpleListAdapter adapter_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list);

        listview_test = (ListView) findViewById(R.id.listview_test);
        adapter_test = new SimpleListAdapter(this);
        listview_test.setAdapter(adapter_test);
    }
}

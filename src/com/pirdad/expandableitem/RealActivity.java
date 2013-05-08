package com.pirdad.expandableitem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.reflect.TypeToken;
import com.pirdad.expandableitem.adapter.RealAdapter;
import com.pirdad.expandableitem.datacraver.*;
import com.pirdad.expandableitem.object.ExampleObject;
import com.pirdad.expandableitem.object.Feed;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: pirdod
 * Date: 5/7/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealActivity extends Activity implements DataCraver.CraveListener {

    private ArrayList<Feed> list;
    private ListView listview_test;
    private RealAdapter adapter_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list);

        list = new ArrayList<Feed>();

        listview_test = (ListView) findViewById(R.id.listview_test);
        adapter_test = new RealAdapter(this);
        listview_test.setAdapter(adapter_test);

        executeExampleRequest();
    }

    public void executeExampleRequest() {

        DataCraver craver = new DataCraver();
        craver.crave(Craving.getInstance(this, "google_feed", 24345)
                .setUrl("https://ajax.googleapis.com/ajax/services/feed/find")
                .setMethod(Craving.CRAVEMETHOD.GET)
                .addGetParameter("v", "1.0")
                .addGetParameter("q", "Official Google Blogs")
                .setTimeout(Craving.CRAVETIMEOUT.ONEMIN, Craving.CRAVETIMEOUT.ONEMIN),
                this);
    }

    @Override
    public void craveAvailable(String string_id, int int_id, Data data) {

        parseExampleResponse((String) data.getResponse());
    }

    @Override
    public void craveUnavailable(String string_id, int int_id, Data data) {

    }

    private void parseExampleResponse(String response) {

        Log.d("Response", response);
        JsonParser<ExampleObject> parser = new JsonParser<ExampleObject>();
        parser.parse(response, new TypeToken<ExampleObject>() {}.getType(), new ParseCallback<ExampleObject>() {

            @Override
            public void onComplete(ExampleObject data) {

                Log.d("Response", "oncomplete");
                showResult(data);
            }

            @Override
            public void onFailure() {
                Log.d("Response", "oncomplete");
            }
        });
    }

    private void showResult(ExampleObject data) {

        list = data.responseData.entries;
        adapter_test.items = list;
        listview_test.setAdapter(adapter_test);
    }
}

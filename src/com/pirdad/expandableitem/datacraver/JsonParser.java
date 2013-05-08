package com.pirdad.expandableitem.datacraver;

import android.os.AsyncTask;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class JsonParser<T> {

    public void parse(String json, Type type, ParseCallback callback) {

        new ParseTask(json, type, callback).execute();

    }
    private class ParseTask extends AsyncTask<Void, Integer, Boolean> {

        private String json;
        private Type type;
        private ParseCallback callback;
        private T data;

        public ParseTask(String json, Type type, ParseCallback callback) {

            this.type = type;
            this.json = json;
            this.callback = callback;

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            if (json != null && type != null) {
                try {
                    data = GsonInstance.getInstance().getParser().fromJson(json, type);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (callback != null) {
                if (result == true && data != null) {
                    callback.onComplete(data);
                } else {
                    callback.onFailure();
                }
            }

        }
    }

}

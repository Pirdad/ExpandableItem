package com.pirdad.expandableitem.datacraver;


public interface ParseCallback<T> {

    public void onComplete(T data);
    public void onFailure();

}

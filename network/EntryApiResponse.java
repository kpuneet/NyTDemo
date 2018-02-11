package com.puneet.android.network;

import com.puneet.android.model.Entry;

import java.io.Serializable;
import java.util.List;

public class EntryApiResponse implements Serializable {
    public List<Entry> entryList;
    public boolean failure;
    public String category;
    public EntryApiResponse(List<Entry> entryList) {
        this.entryList = entryList;
        if (entryList == null || entryList.isEmpty()) {
            failure = true;
        }
    }
}

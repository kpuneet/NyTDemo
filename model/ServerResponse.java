
package com.puneet.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("section")
    private String section;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("num_results")
    private Integer numResults;

    @SerializedName("results")
    private List<Entry> results = null;

    public List<Entry> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status='" + status + '\'' +
                ", copyright='" + copyright + '\'' +
                ", section='" + section + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", numResults=" + numResults +
                ", results=" + results +
                '}';
    }


}

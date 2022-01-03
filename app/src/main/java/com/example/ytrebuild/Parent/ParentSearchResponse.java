package com.example.ytrebuild.Parent;

import java.util.List;


import com.example.ytrebuild.Response.PageInfo;
import com.example.ytrebuild.Response.YTSearchResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ParentSearchResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("items")
    @Expose
    private List<YTSearchResponse> items = null;
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;
    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;
    @SerializedName("regionCode")
    @Expose
    private String regionCode;

    /**
     * No args constructor for use in serialization
     *
     */
    public ParentSearchResponse() {
    }

    /**
     * @param regionCode
     * @param kind
     * @param nextPageToken
     * @param pageInfo
     * @param etag
     * @param items
     */
    public ParentSearchResponse(String kind, String etag, String nextPageToken, String regionCode, PageInfo pageInfo, List<YTSearchResponse> items) {
        super();
        this.kind = kind;
        this.etag = etag;
        this.nextPageToken = nextPageToken;
        this.regionCode = regionCode;
        this.pageInfo = pageInfo;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<YTSearchResponse> getItems() {
        return items;
    }

    public void setItems(List<YTSearchResponse> items) {
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}


package com.puneet.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Entry {

    @SerializedName("section")
    private String section;

    @SerializedName("title")
    private String title;

    @SerializedName("subsection")
    private String subsection;


    @SerializedName("abstract")
    private String _abstract;

    @SerializedName("url")
    private String url;

    @SerializedName("byline")
    private String byline;

    @SerializedName("item_type")
    private String itemType;

    @SerializedName("updated_date")
    private transient String updatedDate;

    @SerializedName("created_date")
    private transient String createdDate;

    @SerializedName("published_date")
    private transient String publishedDate;

    @SerializedName("material_type_facet")
    private String materialTypeFacet;

    @SerializedName("kicker")
    private String kicker;

    @SerializedName("des_facet")
    private List<String> desFacet = null;

    @SerializedName("org_facet")
    private List<String> orgFacet = null;

    @SerializedName("per_facet")
    private List<Object> perFacet = null;

    @SerializedName("geo_facet")
    private List<Object> geoFacet = null;

    @SerializedName("multimedia")
    private List<Multimedia> images = null;

    @SerializedName("short_url")
    private String shortUrl;

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedia> getImages() {
        return images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (section != null ? !section.equals(entry.section) : entry.section != null) return false;
        if (title != null ? !title.equals(entry.title) : entry.title != null) return false;
        if (subsection != null ? !subsection.equals(entry.subsection) : entry.subsection != null)
            return false;
        if (_abstract != null ? !_abstract.equals(entry._abstract) : entry._abstract != null)
            return false;
        if (url != null ? !url.equals(entry.url) : entry.url != null) return false;
        if (byline != null ? !byline.equals(entry.byline) : entry.byline != null) return false;
        if (itemType != null ? !itemType.equals(entry.itemType) : entry.itemType != null)
            return false;
        if (updatedDate != null ? !updatedDate.equals(entry.updatedDate) : entry.updatedDate != null)
            return false;
        if (createdDate != null ? !createdDate.equals(entry.createdDate) : entry.createdDate != null)
            return false;
        if (publishedDate != null ? !publishedDate.equals(entry.publishedDate) : entry.publishedDate != null)
            return false;
        if (materialTypeFacet != null ? !materialTypeFacet.equals(entry.materialTypeFacet) : entry.materialTypeFacet != null)
            return false;
        if (kicker != null ? !kicker.equals(entry.kicker) : entry.kicker != null) return false;
        if (desFacet != null ? !desFacet.equals(entry.desFacet) : entry.desFacet != null)
            return false;
        if (orgFacet != null ? !orgFacet.equals(entry.orgFacet) : entry.orgFacet != null)
            return false;
        if (perFacet != null ? !perFacet.equals(entry.perFacet) : entry.perFacet != null)
            return false;
        if (geoFacet != null ? !geoFacet.equals(entry.geoFacet) : entry.geoFacet != null)
            return false;
        if (images != null ? !images.equals(entry.images) : entry.images != null) return false;
        return shortUrl != null ? shortUrl.equals(entry.shortUrl) : entry.shortUrl == null;
    }

    @Override
    public int hashCode() {
        int result = section != null ? section.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (subsection != null ? subsection.hashCode() : 0);
        result = 31 * result + (_abstract != null ? _abstract.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (byline != null ? byline.hashCode() : 0);
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (publishedDate != null ? publishedDate.hashCode() : 0);
        result = 31 * result + (materialTypeFacet != null ? materialTypeFacet.hashCode() : 0);
        result = 31 * result + (kicker != null ? kicker.hashCode() : 0);
        result = 31 * result + (desFacet != null ? desFacet.hashCode() : 0);
        result = 31 * result + (orgFacet != null ? orgFacet.hashCode() : 0);
        result = 31 * result + (perFacet != null ? perFacet.hashCode() : 0);
        result = 31 * result + (geoFacet != null ? geoFacet.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (shortUrl != null ? shortUrl.hashCode() : 0);
        return result;
    }
}

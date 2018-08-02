package com.example.excellekitio.stillwaterscamps.MEDIA;

/**
 * Created by excelle kitio on 15/11/2017.
 */
public class DataObjectEssam {
    private String mText1;
    private String mText2;
    private String imageUrl;
    private int  id;
    private String type ;

    public DataObjectEssam(String text1, String text2, String imageUrl){
        mText1 = text1;
        mText2 = text2;
        this.imageUrl=imageUrl;
    }

    public DataObjectEssam(String mText1, String mText2, String imageUrl, int id) {
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public DataObjectEssam(String mText1, String mText2, String imageUrl, int id, String type) {
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.imageUrl = imageUrl;
        this.id = id;
        this.type = type;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmText1() {
        return mText1;
    }
    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getImageUrl() {
        return imageUrl;

    }

    public String getmText2() {
        return mText2;
    }
    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
}

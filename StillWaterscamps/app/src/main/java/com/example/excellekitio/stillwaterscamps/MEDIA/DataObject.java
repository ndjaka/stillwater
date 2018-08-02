package com.example.excellekitio.stillwaterscamps.MEDIA;

/**
 * Created by excelle kitio on 15/11/2017.
 */
public class DataObject {
    private String mText1;
    private String mText2;
    private int imageUrl;

    public DataObject(String text1, String text2, int imageUrl){
        mText1 = text1;
        mText2 = text2;
        setImageUrl(imageUrl);
    }
    public String getmText1() {
        return mText1;
    }
    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageUrl() {
        return imageUrl;

    }

    public String getmText2() {
        return mText2;
    }
    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
}

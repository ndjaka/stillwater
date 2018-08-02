package com.example.excellekitio.stillwaterscamps.MEDIA;

/**
 * Created by excelle kitio on 30/11/2017.
 */
public class Datagallerie {
    private String mText1;
    private int mText2;
    private int imageUrl;

    Datagallerie (String text1, int text2, int imageUrl){
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

    public int getmText2() {
        return mText2;
    }
    public void setmText2(int mText2) {
        this.mText2 = mText2;
    }
}

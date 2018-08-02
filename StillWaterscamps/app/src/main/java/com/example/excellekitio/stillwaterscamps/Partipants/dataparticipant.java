package com.example.excellekitio.stillwaterscamps.Partipants;

/**
 * Created by excelle kitio on 11/12/2017.
 */
public class dataparticipant {

    private String member_name;
    private int member_pic_id;
    //private String status;
    // private String contactType;

    public dataparticipant(String member_name, int member_pic_id) {

        this.member_name = member_name;
        this.member_pic_id = member_pic_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public int getMember_pic_id() {
        return member_pic_id;
    }

    public void setMember_pic_id(int member_pic_id) {
        this.member_pic_id = member_pic_id;
    }


}

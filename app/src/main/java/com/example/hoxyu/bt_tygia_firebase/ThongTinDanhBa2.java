package com.example.hoxyu.bt_tygia_firebase;

public class ThongTinDanhBa2 {
    private String key;
    private String img_contact;
    private String name_contact;
    private String number_contact;
    private String nickname_contact;

    public ThongTinDanhBa2() {
    }

    public ThongTinDanhBa2(String key, String img_contact, String name_contact, String number_contact, String nickname_contact) {
        this.key = key;
        this.img_contact = img_contact;
        this.name_contact = name_contact;
        this.number_contact = number_contact;
        this.nickname_contact = nickname_contact;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImg_contact() {
        return img_contact;
    }

    public void setImg_contact(String img_contact) {
        this.img_contact = img_contact;
    }

    public String getName_contact() {
        return name_contact;
    }

    public void setName_contact(String name_contact) {
        this.name_contact = name_contact;
    }

    public String getNumber_contact() {
        return number_contact;
    }

    public void setNumber_contact(String number_contact) {
        this.number_contact = number_contact;
    }

    public String getNickname_contact() {
        return nickname_contact;
    }

    public void setNickname_contact(String nickname_contact) {
        this.nickname_contact = nickname_contact;
    }

    @Override
    public String toString() {
        return "ThongTinDanhBa{" +
                "img_contact='" + img_contact + '\'' +
                ", name_contact='" + name_contact + '\'' +
                ", number_contact='" + number_contact + '\'' +
                ", nickname_contact='" + nickname_contact + '\'' +
                '}';
    }
}

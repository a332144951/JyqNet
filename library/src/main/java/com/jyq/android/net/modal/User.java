package com.jyq.android.net.modal;
/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
           佛祖保佑       永无BUG
 */


import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.gson.UserRoleAdapter;
import com.jyq.android.net.upload.image.UploadImageUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/31.
 */
public class User implements Serializable {
    public enum UserType {
        UNKNOWN("unknown"),
        MASTER("master"),
        TEACHER("teacher"),
        PARENT("parent"),;
        private String type;

        UserType(String parent) {
            this.type = parent;
        }

        public String getType() {
            return type;
        }

       public static UserType TypeOf(String typeName) {
            UserType[] types = values();
            for (UserType type : types) {
                if (type.type.equals(typeName)) {
                    return type;
                }
            }
            return UNKNOWN;
        }
    }


    public User() {
    }
    @SerializedName(value = "user_role_id", alternate = {"id"})
    public int logicId;
    @JsonAdapter(UserRoleAdapter.class)
    @SerializedName(value = "role", alternate = {"user_role"})
    public UserType role;
    @SerializedName("score")
    public int score;
    @SerializedName("name")
    public String name;
    @SerializedName("py")
    public String py;
    @SerializedName("description")
    public String description;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName(value = "mobile", alternate = {"login_id"})
    public String mobile;
    @SerializedName("biye_school")
    public String biye_school;

    /*1:可删除;0:不可山粗*/
    @SerializedName("can_unbind")
    private int deleteble;



    ///////////////////////////////////////////////////////////////////////////
    // 位置信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("distance")
    private double distance;
    @SerializedName("lng")
    private double longitude;
    @SerializedName("lat")
    private double latitude;
    public Near getLocation(){
        return new Near.Builder()
                .distance(distance)
                .lat(latitude)
                .lng(longitude)
                .build();
    }


    ///////////////////////////////////////////////////////////////////////////
    // 宝宝信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName(value = "baby_name")
    private String babyName;
    @SerializedName(value = "baby_id")
    private int babyId;
    @SerializedName(value = "baby_avatar")
    private String babyAvatar;

    public Baby getBaby() {
        return new Baby.Builder()
                .name(babyName)
                .id(babyId)
                .avatar(babyAvatar)
                .build();
    }

    ///////////////////////////////////////////////////////////////////////////
    // 班级信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("class_id")
    private int classId;
    @SerializedName("class_name")
    private String className;

    public Grade getGrade() {
        return new Grade.Builder()
                .id(classId)
                .name(className)
                .build();
    }


    ///////////////////////////////////////////////////////////////////////////
    // 园所信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("school_id")
    private int schoolId;
    @SerializedName("school_name")
    private String schoolName;

    public School getSchool() {
        return new School.Builder()
                .id(schoolId)
                .name(schoolName)
                .build();
    }


    ///////////////////////////////////////////////////////////////////////////
    // 用户称谓
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("title_id")
    private int titleId;
    @SerializedName(value = "title", alternate = {"title_name", "text"})
    private String titleName;

    public UserTitle getTitle() {
        return new UserTitle.Builder()
                .titleId(titleId)
                .titleName(titleName)
                .build();
    }


    ///////////////////////////////////////////////////////////////////////////
    // 即时通信信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("yx_token")
    private String IMToken;
    @SerializedName(value = "yx_accid")
    private String IMAccid;

    public ImInfo getImInfo() {
        return new ImInfo.Builder()
                .account(IMAccid)
                .accessToken(IMToken)
                .build();
    }

    ///////////////////////////////////////////////////////////////////////////
    // 地址信息
    ///////////////////////////////////////////////////////////////////////////

    @SerializedName("street")
    private String street;
    @SerializedName("addr_text")
    private String addr_text;
    @SerializedName("province")
    private int province;
    @SerializedName("city")
    private int city;
    @SerializedName(value = "area",alternate = {"area_name"})
    private int area;


    public Address getAddress() {
        return new Address.Builder()
                .fullAddress(addr_text)
                .areaId(area)
                .cityId(city)
                .provinceId(province)
                .street(street)
                .build();
    }


    public String getAvatar() {
        return UploadImageUtils.contactFullImageUrl(HttpCache.getInstance().getImageHost(), avatar);
    }

    public String getAvatarKey() {
        return avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return logicId == user.logicId;

    }

    @Override
    public int hashCode() {
        return logicId;
    }

    private User(Builder builder) {
        logicId = builder.logicId;
        schoolId = builder.schoolId;
        classId = builder.classId;
        role = builder.role;
        titleId = builder.titleId;
        titleName = builder.titleName;
        IMToken = builder.IMToken;
        IMAccid = builder.IMAccid;
        score = builder.score;
        className = builder.className;
        schoolName = builder.schoolName;
        name = builder.name;
        py = builder.py;
        description = builder.description;
        avatar = builder.avatar;
        distance = builder.distance;
        longitude = builder.longitude;
        latitude = builder.latitude;
        mobile = builder.mobile;
        street = builder.street;
        addr_text = builder.addr_text;
        biye_school = builder.biye_school;
        province = builder.province;
        city = builder.city;
        area = builder.area;
    }

    public static class Builder {
        private int logicId;
        private int schoolId;
        private int classId;
        private UserType role;
        private int titleId;
        private String titleName;
        private String IMToken;
        private String IMAccid;
        private int score;
        private String className;
        private String schoolName;
        private String name;
        private String py;
        private String description;
        private String avatar;
        private double distance;
        private double longitude;
        private double latitude;
        private String mobile;
        private String street;
        private String addr_text;
        private String biye_school;
        private int province;
        private int city;
        private int area;

        public Builder(User user) {

            logicId(user.logicId)
                    .schoolId(user.schoolId)
                    .classId(user.classId)
                    .role(user.role)
                    .titleId(user.titleId)
                    .titleName(user.titleName)
                    .IMToken(user.IMToken)
                    .IMAccid(user.IMAccid)
                    .score(user.score)
                    .className(user.className)
                    .schoolName(user.schoolName)
                    .name(user.name)
                    .py(user.py)
                    .description(user.description)
                    .avatar(user.avatar)
                    .distance(user.distance)
                    .longitude(user.longitude)
                    .latitude(user.latitude)
                    .mobile(user.mobile)
                    .street(user.street)
                    .addr_text(user.addr_text)
                    .biye_school(user.biye_school)
                    .province(user.province)
                    .city(user.city)
                    .area(user.area)
            ;
        }

        public Builder() {
        }

        public Builder logicId(int val) {
            logicId = val;
            return this;
        }

        public Builder schoolId(int val) {
            schoolId = val;
            return this;
        }

        public Builder classId(int val) {
            classId = val;
            return this;
        }

        public Builder role(UserType val) {
            role = val;
            return this;
        }

        public Builder titleId(int val) {
            titleId = val;
            return this;
        }

        public Builder titleName(String val) {
            titleName = val;
            return this;
        }

        public Builder IMToken(String val) {
            IMToken = val;
            return this;
        }

        public Builder IMAccid(String val) {
            IMAccid = val;
            return this;
        }

        public Builder score(int val) {
            score = val;
            return this;
        }

        public Builder className(String val) {
            className = val;
            return this;
        }

        public Builder schoolName(String val) {
            schoolName = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder py(String val) {
            py = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder avatar(String val) {
            avatar = val;
            return this;
        }

        public Builder distance(double val) {
            distance = val;
            return this;
        }

        public Builder longitude(double val) {
            longitude = val;
            return this;
        }

        public Builder latitude(double val) {
            latitude = val;
            return this;
        }

        public Builder mobile(String val) {
            mobile = val;
            return this;
        }


        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder addr_text(String val) {
            addr_text = val;
            return this;
        }

        public Builder biye_school(String val) {
            biye_school = val;
            return this;
        }

        public Builder province(int val) {
            province = val;
            return this;
        }

        public Builder city(int val) {
            city = val;
            return this;
        }

        public Builder area(int val) {
            area = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "logicId=" + logicId +
                ", schoolId=" + schoolId +
                ", classId=" + classId +
                ", role='" + role + '\'' +
                ", titleId=" + titleId +
                ", titleName='" + titleName + '\'' +
                ", IMToken='" + IMToken + '\'' +
                ", IMAccid='" + IMAccid + '\'' +
                ", score=" + score +
                ", className='" + className + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", name='" + name + '\'' +
                ", py='" + py + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", distance=" + distance +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", mobile='" + mobile + '\'' +
                ", street='" + street + '\'' +
                ", addr_text='" + addr_text + '\'' +
                ", biye_school='" + biye_school + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", area=" + area +
                '}';
    }
}

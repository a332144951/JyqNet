package com.jyq.android.net.modal;
import com.google.gson.annotations.SerializedName;

/**
* 
*@author GuoL
*created at 2016-10-25 14:26
*/
public class Syllabus {
    //课程内容id
    @SerializedName(value="id",alternate = {"course_id"})
    public int id;
    //班级id
    @SerializedName("class_id")
    public int classId;
    //周id
    @SerializedName("week")
    public int week;
    //类型id
    @SerializedName("type")
    public int type;
    //节id
    @SerializedName("section")
    public int section;
    //课程名称
    @SerializedName("text")
    public String text;
    //更新时间
    @SerializedName("update_time")
    public String update_time;

    @Override
    public String toString() {
        return "Syllabus{" +
                "id=" + id +
                ", classId=" + classId +
                ", week=" + week +
                ", type=" + type +
                ", section=" + section +
                ", text='" + text + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}

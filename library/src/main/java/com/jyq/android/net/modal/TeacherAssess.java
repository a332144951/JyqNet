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

import com.google.gson.annotations.SerializedName;

public class TeacherAssess {
    //新增的评价内容id
    @SerializedName("rate_id")
    public int rate_id;
    //发教师评价获得的积分数量
    @SerializedName("score")
    public int score;
    //总分 (包含该月的积分获得总数和评价分数)
    @SerializedName("sum")
    public int sum;
    //该月的积分获得总数
    @SerializedName("score_sum")
    public int score_sum;
    //该月的园长评价总分
    @SerializedName("master_rate_sum")
    public int master_rate_sum;
    //该月的家长评价总分
    @SerializedName("parent_rate_sum")
    public int parent_rate_sum;
    //该月家长提交的评价平均值
    @SerializedName("rate_avg")
    public int rate_avg;
    //该月家长提交的孩子喜爱度总分
    @SerializedName("rate1_sum")
    public int rate1_sum;
    //该月家长提交的沟通用心度总分
    @SerializedName("rate2_sum")
    public int rate2_sum;
    //该月家长提交的班级活动总分
    @SerializedName("rate3_sum")
    public int rate3_sum;
}

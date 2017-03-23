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

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */

public class Dynamic extends BaseComment {
    @SerializedName("type")
    public int type;
    @SerializedName("reply_count")
    public int replyCount;
    @SerializedName("has_more")
    private int hasMore;
    @SerializedName("replys")
    public List<Reply> replyList;
    @SerializedName("audit_time")
    private long auditTime;

    @SerializedName("nice_list")
    private List<User> likeList;
    @SerializedName("has_nice")
    private int like;


    public String getType() {
        switch (type) {
            case 1:
                return "动态";
            case 2:
                return "通知";
            case 3:
                return "食谱";
            case 4:
                return "亲子共习";
        }
        return "全部";
    }

    //
//
    public long getAuditTime() {
        return auditTime * 1000;
    }

    public boolean enableLoadMore() {
        return hasMore == 1;
    }

    public void replyLoad(List<Reply> replys) {
        this.hasMore = 0;
        this.replyList.addAll(replys);
    }

    public void deleteReply(Reply reply) {
        List<Reply> delete = new ArrayList<>();
        delete.addAll(findRefer(replyList, reply.id));
        replyList.removeAll(delete);
        replyList.remove(reply);
    }

    private List<Reply> findRefer(List<Reply> replies, int replyId) {
        List<Reply> delete = new ArrayList<>();
        for (Reply r : replies) {
            if (r.referId == replyId) {
                delete.add(r);
                List<Reply> item = Lists.newArrayList(replies);
                item.remove(r);
                delete.addAll(findRefer(item, r.id));
            }
        }
        return delete;
    }

}

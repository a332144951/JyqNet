package com.jyq.android.net.modal;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : GuoL
 * Create at 2016-11-21 15:39
 */

public class AllRedFlowerList {
    private List<RedFlower> todayFlower=new ArrayList<>();
    private List<RedFlower> historyFlower=new ArrayList<>();
    public List<RedFlower> getTodayFlower() {
        return todayFlower;
    }

    public void setTodayFlower(List<RedFlower> todayFlower) {
        this.todayFlower = todayFlower;
    }

    public List<RedFlower> getHistoryFlower() {
        return historyFlower;
    }

    public void setHistoryFlower(List<RedFlower> historyFlower) {
        this.historyFlower = historyFlower;
    }

}

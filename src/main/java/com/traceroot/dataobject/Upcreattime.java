package com.traceroot.dataobject;

import lombok.Data;

//测试
@Data
public class Upcreattime {
    private String updatetime;
    private String creattime;

    public Upcreattime(String updatetime, String creattime) {
        this.updatetime = updatetime;
        this.creattime = creattime;
    }
}

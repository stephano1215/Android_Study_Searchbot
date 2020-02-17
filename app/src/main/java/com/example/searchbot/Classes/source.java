package com.example.searchbot.Classes;

import java.util.ArrayList;
import java.util.List;

public class source {

    public int type;
    public String src1;
    public String src2;
    public String src3;
    public List<ImageData.items> src4;

    public source(int type, String src1, String src2, String src3) {
        this.type = type;
        this.src1 = src1;
        this.src2 = src2;
        this.src3 = src3;
    }

    public source(int type, String src1, String src2, String src3, List<ImageData.items> src4) {
        this.type = type;
        this.src1 = src1;
        this.src2 = src2;
        this.src3 = src3;
        this.src4 = src4;
    }

    public source(int type, String src1) {
        this.type = type;
        this.src1 = src1;
        this.src2 = null;
        this.src3 = null;
    }
}

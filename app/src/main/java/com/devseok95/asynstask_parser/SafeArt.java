package com.devseok95.asynstask_parser;

import java.io.Serializable;

public class SafeArt implements Serializable {
    String content;
    String countryEnName;
    String countryName;
    String id;
    String title;
    String wrtDt;

    public SafeArt() {
        content = "";
        countryEnName = "";
        countryName = "";
        id = "";
        title = "";
        wrtDt = "";
    }

    public SafeArt(SafeArt a) {
        this.content = a.content;
        this.countryEnName = a.countryEnName;
        this.countryName = a.countryName;
        this.id = a.id;
        this.title = a.title;
        this.wrtDt = a.wrtDt;
    }

}

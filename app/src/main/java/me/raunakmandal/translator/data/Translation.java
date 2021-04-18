package me.raunakmandal.translator.data;

import com.google.gson.annotations.SerializedName;

public class Translation {
    @SerializedName("translatedText")
    private String translated;

    public Translation(String translated) {
        this.translated = translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public String getTranslated() {
        return translated;
    }
}

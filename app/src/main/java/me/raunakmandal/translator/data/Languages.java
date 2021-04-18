package me.raunakmandal.translator.data;

public class Languages {
    private String langCode;
    private String langName;

    public Languages(String code, String lang) {
        this.langCode = code;
        this.langName = lang;
    }

    public String getLangCode() {
        return langCode;
    }

    public String getLangName() {
        return langName;
    }
}

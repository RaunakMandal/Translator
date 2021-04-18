package me.raunakmandal.translator.global;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.raunakmandal.translator.data.Languages;

public class Global {
    public static final String BASE_URL = "https://libretranslate.com/";

    public static List<Languages> getList() throws JSONException {
        String langsArray = "[{\"code\":\"en\",\"name\":\"English\"},{\"code\":\"ar\",\"name\":\"Arabic\"},{\"code\":\"zh\",\"name\":\"Chinese\"},{\"code\":\"fr\",\"name\":\"French\"},{\"code\":\"de\",\"name\":\"German\"},{\"code\":\"hi\",\"name\":\"Hindi\"},{\"code\":\"ga\",\"name\":\"Irish\"},{\"code\":\"it\",\"name\":\"Italian\"},{\"code\":\"ja\",\"name\":\"Japanese\"},{\"code\":\"ko\",\"name\":\"Korean\"},{\"code\":\"pt\",\"name\":\"Portuguese\"},{\"code\":\"ru\",\"name\":\"Russian\"},{\"code\":\"es\",\"name\":\"Spanish\"}]";
        JSONArray jsonArray = new JSONArray(langsArray);
        List<Languages> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            list.add(new Languages(obj.getString("code"), obj.getString("name")));
        }
        return list;
    }
}
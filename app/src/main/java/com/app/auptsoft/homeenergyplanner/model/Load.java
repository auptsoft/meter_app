package com.app.auptsoft.homeenergyplanner.model;

import android.content.Context;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 12/8/2018.
 */

public class Load extends Content {
    private int id;
    private String name;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String others;

    private final String TABLE_NAME = "loads";

    public Load() {
        this.id = 0;
        this.name = "";
        this.startHour = 0;
        this.startMinute = 0;
        this.endHour = 0;
        this.endMinute = 0;
    }

    public Load(int id, String name, int startHour, int startMinute, int endHour, int endMinute, String others) {
        this.id = id;
        this.name = name;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.others = others;
    }

    public static ArrayList<String> getLoadNames(ArrayList<Load> loads) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (Load l : loads) {
            stringArrayList.add(l.getName());
        }
        return stringArrayList;
    }

    @Nullable
    public static Load get(Context context, int id) {
        Load load = new Load();
        ArrayList<HashMap<String, String>> hashMaps = load.get(context, "id=?", new String[]{id + ""});
        if (hashMaps.size() > 0) {
            return new Load(Integer.parseInt(hashMaps.get(0).get("id")),
                    hashMaps.get(0).get("name"),
                    Integer.parseInt(hashMaps.get(0).get("startHour")),
                    Integer.parseInt(hashMaps.get(0).get("startMinute")),
                    Integer.parseInt(hashMaps.get(0).get("endHour")),
                    Integer.parseInt(hashMaps.get(0).get("endMinute")),
                    hashMaps.get(0).get("others"));
        }
        return null;
    }

    public static ArrayList<Load> getAll(Context context) {
        Load load = new Load();
        ArrayList<HashMap<String, String>> hashMaps = load.get(context, "", new String[]{});

        ArrayList<Load> arrayList = new ArrayList<>();
        if (hashMaps != null) {
            for (HashMap<String, String> item : hashMaps) {
                Load l = new Load(Integer.parseInt(item.get("id")),
                        item.get("name"),
                        Integer.parseInt(item.get("startHour")),
                        Integer.parseInt(item.get("startMinute")),
                        Integer.parseInt(item.get("endHour")),
                        Integer.parseInt(item.get("endMinute")),
                        item.get("others"));
                arrayList.add(l);
            }
        }
        return arrayList;
    }

    public static String insert(Context context, Load load) {
        Load l = new Load();
        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
        hashMaps.add(load.toHashMap());
        return l.insert(context, hashMaps);
    }

    public static int update(Context context, Load load) {
        return new Load().update(context, load.toHashMap(), "id=?",
                new String[]{load.getId() + ""});
    }

    public static int delete(Context context, Load load) {
        return new Load().delete(context, "id=?",
                new String[]{load.id + ""});
    }

    public String getFormatedTime() {
        return "" + ((char)getStartHour()) + ((char)getEndHour()) + ((char) getStartMinute()) + ((char) getEndMinute());
    }

    public static String getFormatedTime(ArrayList<Load> loads) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            try {
                stringBuilder.append(loads.get(i).getFormatedTime());
            } catch (Exception e) {
                stringBuilder.append("0000");
            }
        }

        return stringBuilder.toString();
    }

    public HashMap<String, String> toHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", this.name);
        hashMap.put("startHour", this.startHour + "");
        hashMap.put("startMinute", this.startMinute + "");
        hashMap.put("endHour", this.endHour + "");
        hashMap.put("endMinute", this.endHour + "");
        hashMap.put("others", this.others);
        return hashMap;
    }

    @Nullable
    public static String stringify(Load load) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", load.id);
            jsonObject.put("name", load.name);
            jsonObject.put("startHour", load.startHour);
            jsonObject.put("startMinute", load.startMinute);
            jsonObject.put("endHour", load.endHour);
            jsonObject.put("endMinute", load.endMinute);
            jsonObject.put("others", load.others);
            return jsonObject.toString();
        } catch (JSONException je) {
            return null;
        }
    }

    @Nullable
    public static Load parseJSON(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return new Load(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getInt("startHour"),
                    jsonObject.getInt("startMinute"),
                    jsonObject.getInt("endHour"),
                    jsonObject.getInt("endMinute"),
                    jsonObject.getString("others")
            );
        } catch (JSONException je) {
            return null;
        }
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                "id",
                "name",
                "startHour",
                "startMinute",
                "endHour",
                "endMinute",
                "others"
        };
    }

    @Override
    protected String[] getColumnsType() {
        return new String[]{
                "integer primary key autoincrement",
                "text",
                "text",
                "text",
                "text",
                "text",
                "text"
        };
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
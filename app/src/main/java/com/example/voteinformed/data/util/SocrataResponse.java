package com.example.voteinformed.data.util;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SocrataResponse {

    @SerializedName("data")
    private List<List<Object>> data;

    public List<List<Object>> getData() {
        return data;
    }
}
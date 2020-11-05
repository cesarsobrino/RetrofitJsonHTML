/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.m001.jsonretrofit;

/**
 *
 * @author FP Mañana A
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Covid {

@SerializedName("data")
@Expose
private List<Data> data = null;

public List<Data> getData() {
return data;
}

public void setData(List<Data> data) {
this.data = data;
}

    @Override
    public String toString() {
        return "Covid{" + "data=" + data + '}';
    }


}
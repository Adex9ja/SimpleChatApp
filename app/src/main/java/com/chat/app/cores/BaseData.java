package com.chat.app.cores;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseData<T> {

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private int total;
    private int page;
    private int limit;
    private int offset;


    @SerializedName("data")
    @Expose
    private T data = null;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

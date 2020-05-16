package com.example.people.img;

public class ImagEntity {


    private String path;
    private String title;
    private long size;

    public ImagEntity(String path, String title, long size) {
        this.path = path;
        this.title = title;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ImagEntity{" +
                "path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}

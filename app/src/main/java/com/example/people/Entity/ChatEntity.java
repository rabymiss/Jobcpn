package com.example.people.Entity;

public class ChatEntity   {
    private String People;
    private String Content;

    @Override
    public String toString() {
        return "ChatEntity{" +
                "People='" + People + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }

    public String getPeople() {
        return People;
    }

    public void setPeople(String people) {
        People = people;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

package com.ua.sutty.utils;

import java.util.ArrayList;
import java.util.List;

public class AvailableUrl {

    private List<String> allUrl;
    private List<String> urlForUser;
    private List<String> urlForGuest;

    public List<String> getAllUrl() {
        return allUrl;
    }

    public List<String> getUrlForUser() {
        return urlForUser;
    }

    public List<String> getUrlForGuest() {
        return urlForGuest;
    }

    {
        allUrl = new ArrayList<>();
        allUrl.add("/");
        allUrl.add("/home");
        allUrl.add("/add");
        allUrl.add("/edit");
        allUrl.add("/login");
        allUrl.add("/delete");
        allUrl.add("/logout");

        urlForUser = new ArrayList<>();
        urlForUser.add("/");
        urlForUser.add("/home");
        urlForUser.add("/login");
        urlForUser.add("/logout");

        urlForGuest = new ArrayList<>();
        urlForGuest.add("/");
        urlForGuest.add("/login");

    }


}

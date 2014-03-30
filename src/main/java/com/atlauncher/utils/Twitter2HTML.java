package com.atlauncher.utils;

import twitter4j.Status;

public final class Twitter2HTML{
    private Twitter2HTML(){}

    public static String toHTML(Status status){
        StringBuilder builder = new StringBuilder();

        builder.append("<h1>").append(status.getUser().getName()).append("</h1>");
        builder.append("<hr>");
        builder.append("<p>").append(status.getText()).append("</p>");
        builder.append("<hr>");

        return builder.toString();
    }
}
package com.tinqinacademy.comments.rest.controller;

public class RestApiPaths {
    private static final String repeatedPart = "comment";
    public static final String EDIT_A_COMMENT = "/hotel/"+repeatedPart+"/{commentId}";
    public static final String DELETE_A_COMMENT = "/system/"+repeatedPart+"/{commentId}";
    public static final String EDIT_A_COMMENT_BY_ADMIN = "/system/"+repeatedPart+"/{commentId}";
    public static final String LEAVE_A_COMMENT = "/hotel/{roomId}/"+repeatedPart+"";
    public static final String GET_ALL_COMMENTS = "/hotel/{roomId}/"+repeatedPart+"";
}

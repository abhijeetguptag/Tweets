package com.herokuapp.data.remote;

public class ApiConstants {

    public static final String BASE_URL = "https://sym-json-server.herokuapp.com/";
    public static final long CONNECT_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;
    public static final int USER_FETCH_LIMIT = 40;
    public static final int POSTS_FETCH_LIMIT = 10;
    public static final int COMMENTS_FETCH_LIMIT = 10;


    private ApiConstants() {
        // Private constructor to hide the implicit one
    }

}

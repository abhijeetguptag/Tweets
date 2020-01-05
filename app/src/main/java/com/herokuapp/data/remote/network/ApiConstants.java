package com.herokuapp.data.remote.network;

public class ApiConstants {

    public static final String BASE_URL = "https://sym-json-server.herokuapp.com/";
    public static final long CONNECT_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;
    public static final int AUTHOR_NETWORK_FETCH_LIMIT = 20;
    public static final int POSTS_NETWORK_FETCH_LIMIT = 20;
    public static final int COMMENTS_NETWORK_FETCH_LIMIT = 20;

    public static final int AUTHOR_DB_FETCH_LIMIT = 20;
    public static final int POSTS_DB_LIMIT = 20;
    public static final int COMMENTS_DB_FETCH_LIMIT = 20;


    private ApiConstants() {
        // Private constructor to hide the implicit one
    }

}

package com.herokuapp;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.mockwebserver.MockWebServer;

public  class MockServer {
//public class MockServer extends UiThreadTestRule {

//    private MockWebServer mServer;
//
//    public static final int MOCK_WEBSERVER_PORT = 8000;
//
//    @Override
//    public Statement apply(final Statement base, Description description) {
//        return new Statement() {
//            @Override
//            public void evaluate() throws Throwable {
//                startServer();
//                try {
//                    base.evaluate();
//                } finally {
//                    stopServer();
//                }
//            }
//        };
//    }
//
//    /**
//     * Returns the started web server instance
//     *
//     * @return mock server
//     */
//    public MockWebServer server() {
//        return mServer;
//    }
//
//    public void startServer() throws IOException, NoSuchAlgorithmException {
//        mServer = new MockWebServer();
//        try {
//            mServer(MOCK_WEBSERVER_PORT);
//        } catch (IOException e) {
//            throw new IllegalStateException("mock server start issue",e);
//        }
//    }
//
//    public void stopServer() {
//        try {
//            mServer.shutdown();
//        } catch (IOException e) {
//
//        }
//    }
}

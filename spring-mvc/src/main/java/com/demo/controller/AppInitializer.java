package com.demo.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.demo.AI.ServerAI;

public class AppInitializer implements ServletContextListener {

    private Thread serverThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        serverThread = new Thread(() -> ServerAI.main(new String[]{}));
        serverThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (serverThread != null && serverThread.isAlive()) {
            serverThread.interrupt();
        }
    }
}

package com.iditgraber.trc.server;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class TrcApplication {

	public static void main(String[] args) throws Exception{
		HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
		server.createContext("", new MyHttpHandler());
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		server.start();
	}

}

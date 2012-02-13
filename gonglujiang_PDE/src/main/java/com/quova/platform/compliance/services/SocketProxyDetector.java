package com.quova.platform.compliance.services;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketProxyDetector {

  // TODO: make these configurable
  private static int numThreads = 10;
  private static int portNum = 9876;
  private static int backLog = 100;

  private ServerSocket serverSocket;
  boolean listening = true;

  public SocketProxyDetector() {

    try {
      serverSocket = new ServerSocket(4444);
    } catch (IOException e) {
      System.out.println("Could not listen on port: "+portNum);
      System.exit(-1);
    }

    // TODO: make this a pool & limit number of SocketWorkers
    while (listening) {
      try { 
        new SocketWorker(serverSocket.accept()).start();
      } catch( IOException ioe ) {
        ioe.printStackTrace();
      }
    }

  }

  public static void main( String[] args ) {
    SocketProxyDetector spd = new SocketProxyDetector();
  }
}

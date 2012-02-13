package com.quova.platform.compliance.services;

import java.net.*;
import java.io.*;

public class SocketWorker extends Thread {
    private Socket socket = null;
    
    public SocketWorker( Socket socket ) {
      super( "SocketWorker" );
      this.socket = socket;
    }
    
    public void run() {
    
      try {
        PrintWriter out = new PrintWriter( socket.getOutputStream(), true );
        BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
    
        String inputLine, outputLine;
    
        while ((inputLine = in.readLine()) != null) {
          // TODO: need better security so malicious user can't simply return "thanks" to spoof this response
          out.println( "thanks" );
        }
        out.close();
        in.close();
        socket.close();
    
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
}

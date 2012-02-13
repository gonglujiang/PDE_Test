package com.quova.platform.compliance.applet;
import java.applet.Applet;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RMISecurityManager;

public class PDEApplet extends Applet {

	private static final long serialVersionUID = -8283423853498083672L;

	private boolean debug;
	private TextArea text;

	private String host;
	private int httpPort;
	private int socketPort;

	private String sessionId;
	private boolean parfiglioProxy;
	private boolean socketProxy;
	private String operatingSystem;
	private String localIp;
	private int localPort;
	private String lastMileIp;
	private int lastMilePort;

	public static void main(String[] args) {
		PDEApplet pdeApplet = new PDEApplet();
		pdeApplet.proxyDetect();
	}

	public PDEApplet() {
		debug = true;
		text = new TextArea(30, 80);
		add(text);
		text.append(" Applet is initializing...\n");

		System.setSecurityManager (new RMISecurityManager() {
			public void checkConnect (String host, int port) {}
			public void checkConnect (String host, int port, Object context) {}
		});
		
		try {
			localIp = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			text.append("\n !!!\tGet local ip exception.\t!!!");
			text.append("\n"+e.toString()+"\n");
		}
		
//		Debug as application/appletViewer.
//		host = "localhost";
//		httpPort = 8080;
//		socketPort = 4444;
//		sessionId = "1234";
//		proxyDetect();
	}

	public void initialize(String host, String httpPort, String socketPort,
			String sessionId) {
		text.append("\n Parameters after NEW SESSION JS is called:");
		text.append("\n host:" + host);
		text.append("\n httpPort:" + httpPort);
		text.append("\n socketPort:" + socketPort);
		text.append("\n sessionId:" + sessionId);
		text.append("\n");

		this.host = host;
		this.httpPort = Integer.valueOf(httpPort).intValue();
		this.socketPort = Integer.valueOf(socketPort).intValue();
		this.sessionId = sessionId;
	}

	public void proxyDetect() {
		parfiglioProxy = parfiglioCheck();
		socketProxy = socketCheck();
		String xml = buildXML().toString();
		text.append("\n XML Posted to REST Service:");
		text.append("\n" + xml + "\n");
		sendXML(xml);
		text.append("\nI am done.");
	}

	private boolean parfiglioCheck() {
		try {
			text.append("\n ParfiglioProxy Check Requested Address: ");
			text.append("\n " + host + ":" + httpPort + "\n");
			Socket socket = new Socket(host, httpPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out.println("GET /quova-compliance/parfiglio/generate HTTP/1.1");
			out.println("host: google.com");
			out.println("Connection: Close");
			out.println();
			out.flush();
			String readLine;
			String lastLine = null;
			while ((readLine = in.readLine()) != null) {
				lastLine = readLine;
				log(readLine);
			}
			socket.close();
			if (lastLine != null
					&& lastLine.length() > 0
					&& lastLine
							.indexOf("<qParfiglio><parfiglioId>666</parfiglioId></qParfiglio>") > -1) {
				return true;
			}
		} catch (Exception e) {
			log(e.toString());
			text.append("\n !!!\tparfiglio error\t!!!");
			text.append("\n"+e.toString()+"\n");
		}
		return false;
	}

	private boolean socketCheck() {
		try {
			text.append("\n SocketProxy Check Requested Address:");
			text.append("\n " + host + ":" + socketPort + "\n");
			Socket socket = new Socket(host, socketPort);
			localPort = socket.getLocalPort();
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println();
			out.flush();
			socket.close();
		} catch (Exception e) {
			text.append("\n !!!\tsocket error\t!!!");
			text.append("\n"+e.toString()+"\n");
			log(e.toString());
			return false;
		}
		return true;
	}

	private StringBuffer buildXML() {
		return new StringBuffer()
				.append("<qProxyData>")
				.append("\n<parfiglioProxy>" + parfiglioProxy
						+ "</parfiglioProxy>")
				.append("\n<socketProxy>" + socketProxy + "</socketProxy>")
				.append("\n<localIp>" + localIp + "</localIp>")
				.append("\n<localPort>" + localPort + "</localPort>")
				.append("\n</qProxyData>");
	}

	private void sendXML(String xml) {
		try {
			URL url = new URL("http://" + host + ":" + httpPort
					+ "/quova-compliance/data/" + sessionId);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-type", "application/xml");

			Writer output = new OutputStreamWriter(connection.getOutputStream());
			output.write(xml);
			output.close();

			BufferedReader input = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			input.close();
		} catch (Exception e) {
			text.append("\n !!!\tsendXML error\t!!!");
			text.append("\n" + e.toString() + "\n");
		}
	}

	private boolean isDebug() {
		return debug;
	}

	private void log(String s) {
		if (isDebug())
			System.out.println(s);
	}

	private void log(String s, Throwable throwable) {
		if (isDebug()) {
			System.err.println(s);
			throwable.printStackTrace();
		}
	}

}

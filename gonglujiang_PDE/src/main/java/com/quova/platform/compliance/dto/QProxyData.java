package com.quova.platform.compliance.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class QProxyData {

    private QSession session;

    /* whether the user passed the parfiglio test; true means this test flagged it as a proxy */
    private boolean parfiglioProxy;

    /* whether a connection can be made through a socket connection; true means this test flagged it as a proxy */
    private boolean socketProxy;

    /* * whether the user passed the requester's OS as guessed using jpcap */
    private String operatingSystem;

    private String localIp;
    private String localPort;
    private String lastMileIp;
    private String lastMilePort;

    /**
     * Determines if this instance is parfiglioProxy.
     * 
     * @return The parfiglioProxy.
     */
    public boolean getParfiglioProxy() {
        return this.parfiglioProxy;
    }

    /**
     * Sets whether or not this instance is parfiglioProxy.
     * 
     * @param parfiglioProxy
     *            The parfiglioProxy.
     */
    public void setParfiglioProxy(boolean parfiglioProxy) {
        this.parfiglioProxy = parfiglioProxy;
    }

    /**
     * Determines if this instance is socketProxy.
     *
     * @return The socketProxy.
     */
    public boolean getSocketProxy()
    {
        return this.socketProxy;
    }

    /**
     * Sets whether or not this instance is socketProxy.
     *
     * @param socketProxy The socketProxy.
     */
    public void setSocketProxy(boolean socketProxy)
    {
        this.socketProxy = socketProxy;
    }

    /**
     * Gets the operatingSystem for this instance.
     * 
     * @return The operatingSystem.
     */
    public String getOperatingSystem() {
        return this.operatingSystem;
    }

    /**
     * Sets the operatingSystem for this instance.
     * 
     * @param operatingSystem
     *            The operatingSystem.
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * Gets the localIp for this instance.
     * 
     * @return The localIp.
     */
    public String getLocalIp() {
        return this.localIp;
    }

    /**
     * Sets the localIp for this instance.
     * 
     * @param localIp
     *            The localIp.
     */
    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    /**
     * Gets the localPort for this instance.
     * 
     * @return The localPort.
     */
    public String getLocalPort() {
        return this.localPort;
    }

    /**
     * Sets the localPort for this instance.
     * 
     * @param localPort
     *            The localPort.
     */
    public void setLocalPort(String localPort) {
        this.localPort = localPort;
    }

    /**
     * Gets the lastMileIp for this instance.
     * 
     * @return The lastMileIp.
     */
    public String getLastMileIp() {
        return this.lastMileIp;
    }

    /**
     * Sets the lastMileIp for this instance.
     * 
     * @param lastMileIp
     *            The lastMileIp.
     */
    public void setLastMileIp(String lastMileIp) {
        this.lastMileIp = lastMileIp;
    }

    /**
     * Gets the lastMilePort for this instance.
     * 
     * @return The lastMilePort.
     */
    public String getLastMilePort() {
        return this.lastMilePort;
    }

    /**
     * Sets the lastMilePort for this instance.
     * 
     * @param lastMilePort
     *            The lastMilePort.
     */
    public void setLastMilePort(String lastMilePort) {
        this.lastMilePort = lastMilePort;
    }

}

package com.quova.platform.compliance.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QSession {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}

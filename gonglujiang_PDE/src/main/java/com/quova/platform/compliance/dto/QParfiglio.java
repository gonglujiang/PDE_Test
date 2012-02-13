package com.quova.platform.compliance.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QParfiglio {

    private String parfiglioId;

    public String getParfiglioId() {
        return parfiglioId;
    }

    public void setParfiglioId(String parfiglioId) {
        this.parfiglioId = parfiglioId;
    }
}

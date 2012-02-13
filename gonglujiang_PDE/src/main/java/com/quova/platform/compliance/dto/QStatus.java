package com.quova.platform.compliance.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: skhoubyari
 * Date: 3/23/11
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class QStatus {
    private int statusCode;
    private String subCode;
    private String description;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

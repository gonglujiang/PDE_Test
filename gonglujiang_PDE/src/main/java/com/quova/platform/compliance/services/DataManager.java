package com.quova.platform.compliance.services;

import com.quova.platform.compliance.dto.QProxyData;
import com.quova.platform.compliance.dto.QSession;

public interface DataManager {

    public void storeData(QSession session, QProxyData proxyData);

    public QProxyData retrieveData(QSession sessionId);
}

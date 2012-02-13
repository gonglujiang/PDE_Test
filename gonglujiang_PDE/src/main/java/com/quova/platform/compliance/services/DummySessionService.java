package com.quova.platform.compliance.services;

import com.quova.platform.compliance.dto.QSession;

public class DummySessionService implements SessionService {

  public QSession createSession() {
    QSession session = new QSession();
    session.setSessionId( "1234" );
    return session;
  }
}

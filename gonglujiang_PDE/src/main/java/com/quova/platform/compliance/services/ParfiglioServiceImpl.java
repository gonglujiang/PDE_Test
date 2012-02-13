package com.quova.platform.compliance.services;

import com.quova.platform.compliance.dto.QParfiglio;

public class ParfiglioServiceImpl implements ParfiglioService {

  public QParfiglio getParfiglio() {

    QParfiglio parfiglio = new QParfiglio();
    parfiglio.setParfiglioId( "666" );
    return parfiglio;
  }
}

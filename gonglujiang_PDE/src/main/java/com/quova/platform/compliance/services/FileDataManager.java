package com.quova.platform.compliance.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.quova.platform.compliance.dto.QProxyData;
import com.quova.platform.compliance.dto.QSession;

public class FileDataManager implements DataManager {

    private static final Logger logger = Logger.getLogger(FileDataManager.class);

    // TODO: set this using the properties file
    private String fileDir;

    public FileDataManager() {
        fileDir = "/Users/mrjake2/projects/quova-compliance/data_files";
    }

    public void storeData(QSession session, QProxyData proxyData) {

        try {
	        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter( fileDir+"/"+session.getSessionId()+"_proxy_data.txt")));
	        JAXBContext context = JAXBContext.newInstance(QProxyData.class);
	        Marshaller m = context.createMarshaller();
	        m.marshal(proxyData, out);
	        out.close();
        } catch( IOException ioe ) {
            logger.error( ioe );
        } catch( JAXBException je ) {
            logger.error( je );
        }

        return;
    }

    public QProxyData retrieveData(QSession sessionId) {
        return new QProxyData();
    }

}

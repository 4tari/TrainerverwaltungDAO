package dataLayer.settings;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class SettingsManager {
    private static SettingsManager instance;
    private PersistenceSettings persistenceSettings;


    private SettingsManager() {
    }


    public static SettingsManager getInstance() {
        if(instance == null) {
            instance = new SettingsManager();
        }

        return instance;
    }


    public PersistenceSettings getPersistenceSettings() {
        return readPersistenceSettings();
    }


    private PersistenceSettings readPersistenceSettings() {
        // todo: was muss hier getan werden?

        PersistenceSettings ps = new PersistenceSettings();

        try {
            File fXmlFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("persistence_type");
            String persistenceType = nList.item(0).getTextContent();

            if (persistenceType.equals("xml")){
                ps.setPersistenceType("xml");
            }
            else if (persistenceType.equals("sqlite")){
                ps.setPersistenceType("sqlite");
            }

            else {
                throw new Exception("wrong persistence_type");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        this.persistenceSettings = ps;

        return persistenceSettings;
    }

}

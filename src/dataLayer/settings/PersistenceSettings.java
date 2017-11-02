package dataLayer.settings;

public class PersistenceSettings {
    private String type;

    public PersistenceSettings() {
    }

    public String getPersistenceType() {
        return type;
    }

    public void setPersistenceType(String type) {
        this.type = type;
    }
}

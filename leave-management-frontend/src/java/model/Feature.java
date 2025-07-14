package model;

public class Feature {
    private int featureID;
    private String featureName;

    public Feature() {
    }

    public Feature(int featureID, String featureName) {
        this.featureID = featureID;
        this.featureName = featureName;
    }

    public int getFeatureID() {
        return featureID;
    }

    public void setFeatureID(int featureID) {
        this.featureID = featureID;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "featureID=" + featureID +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}

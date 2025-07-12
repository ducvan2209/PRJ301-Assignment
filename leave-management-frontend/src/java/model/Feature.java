/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class Feature {
    private int FeatureID;
    private String FeatureName;

    public Feature() {
    }

    public Feature(int FeatureID, String FeatureName) {
        this.FeatureID = FeatureID;
        this.FeatureName = FeatureName;
    }

    public int getFeatureID() {
        return FeatureID;
    }

    public void setFeatureID(int FeatureID) {
        this.FeatureID = FeatureID;
    }

    public String getFeatureName() {
        return FeatureName;
    }

    public void setFeatureName(String FeatureName) {
        this.FeatureName = FeatureName;
    }

    @Override
    public String toString() {
        return "Features{" + "FeatureID=" + FeatureID + ", FeatureName=" + FeatureName + '}';
    }
    
    
}

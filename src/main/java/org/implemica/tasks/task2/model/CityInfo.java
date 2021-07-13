package org.implemica.tasks.task2.model;

import java.util.List;
import java.util.Map;

public class CityInfo {

    // information about cities
    private Map<Integer, Map<String, List<List<Integer>>>> inputs;
    // cities
    private List<List<String>> cities;

    // getters and setters
    // returning data with information about cities
    public Map<Integer, Map<String, List<List<Integer>>>> getInputs() {
        return inputs;
    }

    public void setInputs(Map<Integer, Map<String, List<List<Integer>>>> inputs) {
        this.inputs = inputs;
    }

    public List<List<String>> getCities() {
        return cities;
    }

    public void setCities(List<List<String>> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "inputs=" + inputs +
                ", cities=" + cities +
                '}';
    }

}

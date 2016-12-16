package com.example.pratik.retrofit1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pratik on 29-Nov-16.
 */

public class Country {
    private List<Worldpopulation> worldpopulation = new ArrayList<Worldpopulation>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The worldpopulation
     */
    public List<Worldpopulation> getWorldpopulation() {
        return worldpopulation;
    }

    /**
     *
     * @param worldpopulation
     * The worldpopulation
     */
    public void setWorldpopulation(List<Worldpopulation> worldpopulation) {
        this.worldpopulation = worldpopulation;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

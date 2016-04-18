/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author yuezhao
 */
public class MyResultItems {

    private Map<String, Review> fields = new LinkedHashMap();

    private Request request;

    private boolean skip;

    public <T> T get(String key) {
        Object o = fields.get(key);
        if (o == null) {
            return null;
        }
        return (T) fields.get(key);
    }

    public Map<String, Review> getAll() {
        return fields;
    }

    public <T> MyResultItems put(String key, Review value) {
        fields.put(key, value);
        return this;
    }

    public Request getRequest() {
        return request;
    }

    public MyResultItems setRequest(Request request) {
        this.request = request;
        return this;
    }

    /**
     * Whether to skip the result.<br>
     * Result which is skipped will not be processed by Pipeline.
     *
     * @return whether to skip the result
     */
    public boolean isSkip() {
        return skip;
    }


    /**
     * Set whether to skip the result.<br>
     * Result which is skipped will not be processed by Pipeline.
     *
     * @param skip whether to skip the result
     * @return this
     */
    public MyResultItems setSkip(boolean skip) {
        this.skip = skip;
        return this;
    }

    @Override
    public String toString() {
        
        return "MyResultItems{" +
                "fields=" + fields +
                ", request=" + request +
                ", skip=" + skip +
                '}';
    }
}
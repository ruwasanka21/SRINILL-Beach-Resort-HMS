/**
 * GENIUS SOFT CONFIDENTIAL
 *
 * [2015] - [2015] Genius Soft (PVT) LTD
 *
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of Genius Soft (PVT) LTD and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Genius Soft (PVT) LTD and its
 * suppliers and may be covered by Sri Lanka and Foreign Patents, patents in process, and are protected by trade secret
 * or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Genius Soft (PVT) LTD.
 */
package model;

import com.google.gson.JsonObject;

public class DataObject {

    JsonObject jo;
    private final String attribute;

    public DataObject(String defaultAttribute) {
        jo = new JsonObject();
        attribute = defaultAttribute;
    }

    public void addProperty(String property, String value) {
        jo.addProperty(property, value);
    }

    public String get(String property) {
        return jo.get(property).getAsString();
    }

    @Override
    public String toString() {
        return get(attribute);
    }

}

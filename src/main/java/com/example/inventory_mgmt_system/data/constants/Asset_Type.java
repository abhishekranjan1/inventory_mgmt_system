package com.example.inventory_mgmt_system.data.constants;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Asset_Type {
     storage,
    network,
    compute;

   // private String value;

  // @JsonValue
//    @Override
//    public String toString() {
//        return this.value;
//    }

//     Asset_Type(String value) {
//        this.value = value;
//   }
//
//    public static Asset_Type fromValue(String value) {
//        if (value != null) {
//            for (Asset_Type type : values()) {
//                if (type.value.equals(value)) {
//                    return type;
//                }
//            }
//        }
//        return getDefault();
//    }
//
//    private static Asset_Type getDefault() {
//        return UNKNOWN;
//    }
//
//    public String toValue() {
//            return this.value;
//        }

//    private static Map<String, Asset_Type> typeMap = new HashMap<>(3);
//    static {
//        typeMap.put("storage", STORAGE);
//        typeMap.put("network", NETWORK);
//        typeMap.put("compute", COMPUTE);
//    }
//
//
//    @JsonCreator
//    public static Asset_Type forValue(String value) {
//        return typeMap.get(StringUtils.lowerCase(value));
//    }
//
//    @JsonValue
//    public String toValue() {
//        for (Map.Entry<String, Asset_Type> entry : typeMap.entrySet()) {
//            if (entry.getValue() == this)
//                return entry.getKey();
//        }
//        return null; // or fail
//    }
}

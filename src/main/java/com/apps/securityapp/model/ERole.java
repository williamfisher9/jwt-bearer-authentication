package com.apps.securityapp.model;

import java.util.HashMap;
import java.util.Map;

public enum ERole {
    ROLE_ADMIN("ADMIN", 10, "ROLE_ADMIN"),
    ROLE_USER("USER", 20, "ROLE_USER");

    private String code;
    private int numCode;
    private String name;

    private static final Map<String, ERole> BY_CODE = new HashMap<>();
    private static final Map<Integer, ERole> BY_NUM_CODE = new HashMap<>();
    private static final Map<String, ERole> BY_NAME = new HashMap<>();

    static {
        for(ERole eRole:values()){
            BY_CODE.put(eRole.code, eRole);
            BY_NAME.put(eRole.name, eRole);
            BY_NUM_CODE.put(eRole.numCode, eRole);
        }
    }

    ERole(String code, int numCode, String name) {
        this.code = code;
        this.numCode = numCode;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public int getNumCode() {
        return numCode;
    }

    public String getName() {
        return name;
    }

    public static ERole getByCode(String code){
        return BY_CODE.get(code);
    }

    public static ERole getByName(String name){
        return BY_NAME.get(name);
    }

    public static ERole getByNumCode(int numCode){
        return BY_NUM_CODE.get(numCode);
    }
}

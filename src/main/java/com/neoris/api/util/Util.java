package com.neoris.api.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class Util {
    private static final ObjectMapper mapper = new ObjectMapper();

    private Util() {
    }

    public static <T> T converterObject(Object objet, Class<T> c) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(objet, c);
    }

    public static void mergeObjects(Object requestObject, Object databaseObject) {
        BeanUtils.copyProperties(requestObject, databaseObject, getNullPropertyNames(requestObject));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String hashPassword(String password) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}

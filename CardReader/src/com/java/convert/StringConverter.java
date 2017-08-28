
package com.java.convert;

public class StringConverter implements Converter {

    public Object toObject(byte[] src) {
        return new String(src, TIS620).trim();
    }
}

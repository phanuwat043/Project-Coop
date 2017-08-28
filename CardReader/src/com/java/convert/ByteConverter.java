
package com.java.convert;

public class ByteConverter implements Converter {

    public Object toObject(byte[] src) {
        return Byte.valueOf(src[0]);
    }
}

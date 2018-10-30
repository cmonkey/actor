package org.github.cmonkey.actor.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTool {

    public static Unsafe getUnsafe()throws Exception{

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe)field.get(null);
    }
}

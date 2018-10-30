package org.github.cmonkey.actor.test.unsafe;

import org.github.cmonkey.actor.unsafe.SuperArray;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class TestSuperArray {

    public static void main(String[] args) throws Exception{
        long size = Integer.MAX_VALUE;

        SuperArray array = new SuperArray(size);

        out.println("array size = " + array.getSize());

        int num = 1000000;
        IntStream.range(1, 100).forEach(i -> {
            try {
                array.set(num + i, (byte)3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AtomicInteger sum = new AtomicInteger();

        IntStream.range(1, 100).forEach(i -> {
            try {
                sum.addAndGet(array.get(num + i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        out.print("sum = " + sum);
    }
}

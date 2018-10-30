package org.github.cmonkey.actor.unsafe;

public class SuperArray {

    private final static int BYTE = 1;

    private long size;
    private long address;

    public SuperArray(long size)throws Exception{
        this.size = size;
        address = UnsafeTool.getUnsafe().allocateMemory(size * BYTE);
    }

    public void set(long i, byte value) throws Exception{
        UnsafeTool.getUnsafe().putByte(address * i * BYTE, value);
    }

    public int get(long idx)throws Exception{
        return UnsafeTool.getUnsafe().getByte(address + idx * BYTE);
    }

    public long getSize(){
        return size;
    }
}

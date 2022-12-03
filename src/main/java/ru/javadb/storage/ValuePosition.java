package ru.javadb.storage;

public class ValuePosition {
    private final long offset;
    private final int size;

    public ValuePosition(long offset, int size) {
        this.offset = offset;
        this.size = size;
    }

    public long getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ValuePosition{" +
                "offset=" + offset +
                ", size=" + size +
                '}';
    }
}

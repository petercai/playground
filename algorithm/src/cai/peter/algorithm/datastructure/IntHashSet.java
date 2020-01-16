package cai.peter.algorithm.datastructure;

import cai.peter.algorithm.list.Node;

import java.util.NoSuchElementException;

public class IntHashSet {
    private static final int INIT_CAP = 1 << 6;
    private Node[] buckets;
    private int size;

    public IntHashSet(final int cap){
        this.buckets = new Node[cap];
        this.size  = 0;
    }

    public IntHashSet(){
        this(INIT_CAP);
    }

    public void add(int t){
        int index = Integer.valueOf(t).hashCode()%buckets.length;
        Node bucket = buckets[index];
        if( bucket==null) throw new NoSuchElementException("Invalid element");

//        if( bucket.getValue())

    }
}


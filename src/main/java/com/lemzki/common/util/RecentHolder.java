package com.lemzki.common.util;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//This is designed to hold recent items by adding items to a set in order.
// If set become more than initialized limit, it will remove the last one before adding the new entry
//needs imporovement.. threadsafety plus resource too much. scope must be session based so it can hold the recent data
//from the same sessin
public abstract class RecentHolder<E>  {
    private LinkedHashSet<E> theSet = new LinkedHashSet<>();
    private List<E> theList = new ArrayList<>();
    private int limit = 10;


    public RecentHolder(){
        this(10);
    }

    public RecentHolder(int limit){
        this.limit = limit;
    }

    public void add(E e){
        if(theList.contains(e)){
            theList.remove(e); //remove it from list and add it again so it goes top
        }

        if(theList.size() > limit){
            theList.remove(0); // remove oldest record
        }

        theList.add(e);
    }

    public List<E> getAll(){
        //reverse the list so the last one added is number 1
        List<E> defensiveCopy = new ArrayList<>(theList);
        Collections.reverse(defensiveCopy);

        return defensiveCopy;
    }

}
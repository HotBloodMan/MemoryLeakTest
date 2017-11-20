package com.ljt.memoryleaktest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${JT.L} on 2017/11/20.
 */

public class TestManager {

    private List<OnDataArrivedListener> mlisteners=new ArrayList();

    private static class SingletonHolder{
        public static final TestManager instance=new TestManager();
    }
    public static TestManager getInstance(){
        return SingletonHolder.instance;
    }
    public synchronized void registerListener(OnDataArrivedListener listener){
        if(!mlisteners.contains(listener)){
            mlisteners.add(listener);
        }
    }
    public synchronized void unregisterListener(OnDataArrivedListener listener) {
        mlisteners.remove(listener);
    }

    public interface OnDataArrivedListener{
        public void onDataArrived(Object data);
    }

}

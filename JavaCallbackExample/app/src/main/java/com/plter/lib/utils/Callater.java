package com.plter.lib.utils;

import android.os.AsyncTask;

/**
 * Created by plter on 8/24/15.
 */
public class Callater<T> {

    private long delay;
    private Func func;
    private Object[] args;

    @SafeVarargs
    static public <T> void setTimeout(Func<T> func,long delay,T ... bindArgs){
        new Callater<T>(func,delay,bindArgs).start();
    }


    private Callater(Func<T> func,long delay,T[] args) {
        this.func = func;
        this.delay = delay;
        this.args = args;
    }

    private void start(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if (func!=null){
                    func.execute(args);
                }else {
                    throw new RuntimeException("func can't be null");
                }
            }
        }.execute();
    }

    public interface Func<T>{
        void execute(T ... args);
    }

}

package com.example.nitishsharma.servicesunderstand;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {
    public MyService() {
    }

    public int getmRandomNumber() {
        return mRandomNumber;
    }


    private IBinder mBinder =new MyService1();
    private  int mRandomNumber ;
    private  boolean mRandmGeneratorOn ; // to switch on or switch off the random number
    private final int  Max= 10;
    private  final int MIN = -100 ;

    ////  3 important methods onstartcommand ,


    ///// used when we use bind service
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.


        Log.d("msg","service on bind demo");
        return  mBinder ;
        //throw new UnsupportedOperationException("Not yet implemented");
    }



    private void startRandomNumberGenerator(){
while (mRandmGeneratorOn){
    try {
Thread.sleep(1000);

        if (mRandmGeneratorOn){
            mRandomNumber = new Random().nextInt(Max)+MIN ;
            Log.d("msg","srandom number generated is"+Thread.currentThread().getId()+mRandomNumber   );
        }
    }
    catch(InterruptedException i){
        Log.d("msg","" +"exception occured" );
    }
}
    }

    private void stopRandomNumberGenerator(){
        mRandmGeneratorOn = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("msg","service destroyed");
        mRandmGeneratorOn = false ;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("msg","service started" + Thread.currentThread().getId());
/// do long running tasks here
       // stopSelf();;


        // sevice runs on main thread , so we dnt want it run on main thread and block ui //
        // so seprate thread


        mRandmGeneratorOn = true
                ;

        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
        return  START_STICKY;
    }

    // binder is a abstarct class which extends from ibbinder class


    class MyService1 extends Binder{
public MyService getService(){
    return MyService
            .this ;
}
    }
}

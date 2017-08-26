package com.example.nitishsharma.servicesunderstand;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView text ;
    private Intent serviceIntent ;

    private MyService myService ;
    private boolean isBound ;
    private ServiceConnection serviceConnection ;



    // if you are running long running task , then do it on seprate thread to don't make application hanging
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.textView);
        Log.d("msg","activity started" + Thread.currentThread().getId());

        serviceIntent = new Intent(getApplicationContext(),MyService.class);
    }






    public  void getRandom1(View v){

        setRandomNumber();
        // once the service is staeted lifecycle of service has to be managed in serviceitself
    }


    public  void start(View v){

        startService(serviceIntent);

        // once the service is staeted lifecycle of service has to be managed in serviceitself
    }

    public  void stop(View v){

        stopService(serviceIntent);

        // once the service is staeted lifecycle of service has to be managed in serviceitself
    }

    public  void bind(View v){

if (serviceConnection == null){
    serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            MyService.MyService1 myServiceBinder = (  MyService.MyService1)  iBinder ;
            myService = myServiceBinder.getService() ;
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
isBound = false ;
        }
    };

}
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
        // once the service is staeted lifecycle of service has to be managed in serviceitself


    }
    public  void unbind(View v){

if (isBound){
    unbindService(serviceConnection);
    isBound = false ;
}
        // once the service is staeted lifecycle of service has to be managed in serviceitself
    }

    public void setRandomNumber(){
        if (isBound){
            text.setText(myService.getmRandomNumber() + "");
        }
        else{
            text.setText("service is not bound");
        }
    }


    //Behaviour of service  ////when resource crucnh situation , android may decide to kill my app ,, in case my service is running,,

    // then android give high priority may or may not kill it
    //

    // now in case it is killed , what should happen to service which is killed , that is determined
    // by the value that you have written on on start command

   // / whether you want to restart the service or whether the restarted service has intent
    // //value popultated so that it can continue work
    ///START_STICK --AUTORESTART -TRUE BUT NULL INTENT
    ///START_NON_STICKY --NO AUTOMATCALLY START WE HAVE TO TRIGGER THE INTENT
    //START_REDELVER INTENT --AUTOSTART -YES , AND ALSO GET INTENT WHICH IT HAD GOT BEFORE GETTING KILLED


    ///START-STICKY ---SERVICES AR EXPLICITLY MANAGED AND LONG RUNNNG
    // NO NEED TO REMEBER STATE AT KILL TIME
    // LONG RUNNING MUSIC  // EXAMPLE MUSIC SERVICE   /////WE DONT WANT MUSIC BLASTING AT UNEXPECTED TIME


    //START_NON_STICKY ALARM SERVICE PERODICALY ,POLLING DATA FROM SERVER PERODICALLY // IF ITS KILLED ,, NO NEED O RETSART BECAUSE WILL START PERODICALLY


    ////FILE DOWNLOAD --GETS KILLED THEN WILL START FROM WHERE IT HAS BEEN STOPPED .



///// INTO TO BOUND SERVICES


    // APP GETS A DEDICATED PROCESS IN ANDROID --------


    /// AN ACTIVITY MAY WANT TO BIND TO SERVCE TO GET SOME STATUS UPDATE ,,, IT NOOD NEEDTO BE ACTIVITY , MAY BE A SERVICE
    // EITHERIN CASE ,, ACTIVITY IS TRYING TO BIND A SERVICE ,,, SERVICE CALLED BOUND SERVICE .


    /// PART OF SAME APP CALLED LOCAL BINDING


    //// BUT MAYBE ONE ACTIVITY WANTS TO CONNET TO SERVICE OF SOME OTHER APP - REMOTE BINDIING , INTER PROCESSS COMMUNICATION



    // LB IMPLEMENTED USING IBINDER INERFACE
    //RB USING AIDL---DONT USE --(MULTI THREADE) , MESSENGER  (NON MULTITHREADED SCENARIO  -)






    //////////LOCAL BINDING UING IBINDER INTERFACE
    // LET US SAY SERVICE CONTINUSOLY GENERATES RANDON MUMBER IN BACKROUND  INFINITE LOOOP
    //// ACTIVITY NNEDS TO DISPLAY RANDOM NUMBER N ACTIVITY WHENEVER IT NEEDS
    ///// FOR THAT ACTIVITY NEED TO BIND TO THAT SERVICE
    //AFTER SUCCESFULLY BINDING , IT WILLL ASK FOR RANDOM NUMBER FROM SERVICE AND IF IT IS PERFECTLY BOUND
    //THEN SERVICE WILL RETURN THAT GENERATED RANDOM NUMBER
    // WILL SHOW ON UI
    // SERVICE NNEDS TO IMPLMENT IBINDER AND RETUNR IBIDER INSTANCE
    //AN ACTIVITY USE SERVICE CONNECTION REQUEST API TO CONNECT TO SERVICE



    ////// you can bind a s ervice which has not yet started
    ///// a bound service canot be stopped unless and until all the components which are connected to it are unbound
    // then only the srvice can be destroyed
    ////onbind gets executed only once
    //

    // Component can bind to start unstarted or stopped service
    //any number can bind to service
    // a bound service ca not be desroyed // only at resrce crucnh
    //if stop service has called while service was bound it will be automatically stop when
    // all the compnents from the service are unbound

    // components which an bind to service are activities //service and content provider
    // cannot use broadcast receiver to connect t receiver//


}


/////


// Remote Binding activity nneds to communicate to service which is in another app

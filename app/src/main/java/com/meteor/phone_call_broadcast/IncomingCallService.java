package com.meteor.phone_call_broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallService extends Service {

    /**
     * 记录上一个电话状态
     */
    private int lastCallState = TelephonyManager.CALL_STATE_IDLE;

    /**
     * 电话服务管理器
     */
    private TelephonyManager telephonyManager;

    /**
     * 电话状态监听器
     */
    private MyPhoneStateListener myPhoneStateListener;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        // 获取来电号码
        Log.d("state", "开启服务");
        getIncomingCall();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 不获取来电号码
        Log.d("state", "关闭服务");
        getIncomingCallCancel();
    }

    /**
     * 获取来电号码
     */
    private void getIncomingCall() {
        // 获取电话系统服务
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        myPhoneStateListener = new MyPhoneStateListener();
        telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    /**
     * 不获取来电号码
     */
    private void getIncomingCallCancel() {
        telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_NONE);
    }

    /**
     * 电话状态监听器
     */
    class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            Log.d("MyPhoneStateListener", incomingNumber + "");

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING://响铃

                    //自定义来电界面
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK://通话状态

                    //呼入电话
                    if (lastCallState == TelephonyManager.CALL_STATE_RINGING) {

                        //自定义呼入界面

                    }
                    //呼出电话
                    else {

                        //自定义呼出界面

                    }
                    lastCallState = TelephonyManager.CALL_STATE_OFFHOOK;

                    break;

                case TelephonyManager.CALL_STATE_IDLE://无状态
                    //无通话
                    if (lastCallState == TelephonyManager.CALL_STATE_IDLE) {

                    }
                    //通话挂断
                    else {
                        lastCallState = TelephonyManager.CALL_STATE_IDLE;
                        //自定义通话挂断界面
                    }
                    break;

                default:
                    break;
            }

        }
    }
}

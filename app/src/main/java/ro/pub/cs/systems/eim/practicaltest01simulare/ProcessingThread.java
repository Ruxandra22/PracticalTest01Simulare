package ro.pub.cs.systems.eim.practicaltest01simulare;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.Date;
import java.util.Random;

/**
 * Created by Ruxandra on 3/29/2018.
 */

class ProcessingThread extends Thread {

    Context context = null;
    double arithmeticMean, geometricMean;
    private boolean isRunning = true;

    public ProcessingThread(Context context, int firstNumber, int secondNumber) {

        this.context = context;
        arithmeticMean = (firstNumber + secondNumber) / 2;
        geometricMean = Math.sqrt(firstNumber * secondNumber);
    }
    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while(isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {

        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_STRING);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + arithmeticMean + " " + geometricMean);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}

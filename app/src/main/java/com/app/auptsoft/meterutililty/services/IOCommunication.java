package com.app.auptsoft.meterutililty.services;

import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Andrew on 08/04/2018.
 */

public class IOCommunication {
    private InputStream inputStream;
    private OutputStream outputStream;

    private volatile String received = "";

    private boolean listening = false;

    public enum ErrorType{TIMEOUT, READ_ERROR};

    public interface OperationInterface {
        void onReceive(String message);
        void onError(ErrorType errorType, String errorMessage);
        void onReceiveZeroLength();
    }

    @FunctionalInterface
    public interface SendListener {
        void sent(boolean sent, String errorMessage);
    }

    public IOCommunication(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void listenForMessage(final int timeout, final OperationInterface operationInterface) {
        listening = false;
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean readSuccess = false;
                try {
                    for (int i=0; i<timeout; i++){
                        Thread.sleep(1);
                        if (inputStream.available() > 0) {
                            Thread.sleep(50);
                            String inStr = "";
                            while (true) {
                                int a = inputStream.read();
                                inStr += (char)a;
                                if(inputStream.available() == 0){
                                    break;
                                }
                            }
                            received = inStr;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    operationInterface.onReceive(received);
                                    if(received.length() > 0) {
                                    } else {
                                        operationInterface.onReceiveZeroLength();
                                    }
                                }
                            });
                            readSuccess = true;
                            break;
                        }
                    }
                    if (!readSuccess) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                operationInterface.onError(ErrorType.TIMEOUT, "Timeout error occurred");
                            }
                        });
                    }
                } catch (final Exception ioe) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            operationInterface.onError(ErrorType.READ_ERROR, ioe.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    public void listenForMessage(final OperationInterface operationInterface) {
        listening = false;
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (inputStream.available() > 0) {
                            Thread.sleep(50);
                            /*while (inputStream.available()>0){
                                received += (char)inputStream.read();
                            } */
                            //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            //int in = -1;
                            String inStr = "";
                            while (true) {
                                int a = inputStream.read();
                                inStr += (char)a;
                                Thread.sleep(10);
                                if(inputStream.available() == 0){
                                    break;
                                }
                                //break;
                                //char inChar = (char)in;
                                //inStr += inChar;
                            }
                            received = inStr;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    operationInterface.onReceive(received);
                                    if(received.length() > 0) {
                                        /*if (received.charAt(0) == 'A') {
                                            String stateString = received.substring(1);
                                            operationInterface.onStateChanged(new State(stateString));
                                        } else if (received.charAt(0) == 'C') {
                                            operationInterface.onCurrentValuesReceived(received.charAt(1), received.charAt(2));
                                        } */
                                    } else {
                                        operationInterface.onReceiveZeroLength();
                                    }
                                }
                            });
                        }
                        //received="";
                    }
                    /*Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            operationInterface.onReceive(received, true);
                            //listenForMessage(operationInterface);
                        }
                    }); */
                } catch (final Exception ioe) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            operationInterface.onError(ErrorType.READ_ERROR, ioe.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    void write(int in, SendListener sendListener) {
        try {
            outputStream.write(in);
            sendListener.sent(true, null);
        } catch (IOException ioe) {
            sendListener.sent(false, ioe.getMessage());
        }
    }

    void write(byte[] in, SendListener sendListener) {
        try {
            outputStream.write(in);
            sendListener.sent(true, null);
        } catch (IOException ioe) {
            sendListener.sent(false, ioe.getMessage());
        }
    }

    void write(byte[] in, int off, int len, SendListener sendListener) {
        try {
            outputStream.write(in, off, len);
            sendListener.sent(true, null);
        } catch (IOException ioe) {
            sendListener.sent(false, ioe.getMessage());
        }
    }

    void writeString(String string, SendListener sendListener) {
        byte[] stringBytes = string.getBytes();
        write(stringBytes, sendListener);
    }

    public boolean canSend() {
        try {
            outputStream.write(65);
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
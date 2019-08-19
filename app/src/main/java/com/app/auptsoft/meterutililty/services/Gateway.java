package com.app.auptsoft.meterutililty.services;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.model.Load;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gateway {



    public static void turnOnLoad(Load load, final OnOperationCompleteListener onOperationCompleteListener) {
        final String planerIp = "http://"+AppState.planerIp+"/";

        String loadId = Utility.appendZero(load.getId(), 1);

        final String urlPath = "cmd/onload"+loadId;

        RESTUtil.get(planerIp, urlPath, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.message().equals("OK")) {
                    onOperationCompleteListener.onOperationComplete(planerIp+urlPath,"Done");
                } else
                onOperationCompleteListener.onError(planerIp+urlPath,"...");
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onOperationCompleteListener.onError(planerIp+urlPath, t.getMessage());
            }
        });
    }

    public static void getMeterStatus(final OnOperationCompleteListener onOperationCompleteListener) {
        final String planerIp = "http://"+AppState.meterIp+"/";


        final String urlPath = "/getData";

        RESTUtil.get(planerIp, urlPath, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.message().equals("OK")) {
                    try {
                        String msg = response.body().string();
                        onOperationCompleteListener.onOperationComplete(planerIp+urlPath,msg);
                    } catch (Exception e) {
                        onOperationCompleteListener.onError(planerIp+urlPath, e.getMessage());
                    }
                } else
                onOperationCompleteListener.onError(planerIp+urlPath,"...");
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onOperationCompleteListener.onError(planerIp+urlPath, t.getMessage());
            }
        });
    }

    public static void turnOffLoad(Load load, final OnOperationCompleteListener onOperationCompleteListener) {
        final String planerIp = "http://"+AppState.planerIp+"/";

        String loadId = Utility.appendZero(load.getId(), 1);

        final String urlPath = "cmd/offload"+loadId;

        RESTUtil.get(planerIp, urlPath, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.message().equals("OK")) {
                    onOperationCompleteListener.onOperationComplete(planerIp+urlPath,"Done");
                } else
                onOperationCompleteListener.onError(planerIp+urlPath,"...");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onOperationCompleteListener.onError(planerIp+urlPath, t.getMessage());
            }
        });
    }

    public static void setPlanerTime(int hour, int minute, int seconds, final OnOperationCompleteListener onOperationCompleteListener) {
        final String planerIp ="http://"+AppState.planerIp+"/";

        String hourS = Utility.appendZero(hour, 2);
        String minuteS = Utility.appendZero(minute, 2);
        String secondsS = Utility.appendZero(seconds, 2);

        final String urlPath = "stm/"+hourS+"/"+minuteS+"/"+secondsS;

        RESTUtil.get(planerIp, urlPath, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.message().equals("OK")) {
                    onOperationCompleteListener.onOperationComplete(planerIp+urlPath,"Done");
                } else
                onOperationCompleteListener.onError(planerIp+urlPath,"...");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onOperationCompleteListener.onError(planerIp+urlPath, t.getMessage());
            }
        });
    }

    public static void setTiming(ArrayList<Load> loads, final OnOperationCompleteListener onOperationCompleteListener) {
        final String planerIp = "http://"+AppState.planerIp+"/";

        String states = "";

        for (Load load : loads) {
            if(load.isUseTiming()) {
                states += "1";
            } else {
                states += "0";
            }
        }


        final String urlPath = "etm/"+states;

        RESTUtil.get(planerIp, urlPath, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.message().equals("OK")) {
                    onOperationCompleteListener.onOperationComplete(planerIp+urlPath,"Done");
                } else
                onOperationCompleteListener.onError(planerIp+urlPath,response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onOperationCompleteListener.onError(planerIp+urlPath, t.getMessage());
            }
        });
    }

    public static void setLoadOffTime(Load load, final OnOperationCompleteListener onOperationCompleteListener) {
        final String planerIp = "http://"+AppState.planerIp+"/";

        String hour = Utility.appendZero(load.getEndHour(), 2);
        String minute = Utility.appendZero(load.getEndMinute(), 2);
        String seconds = Utility.appendZero(load.getEndSecond(), 2);
        String loadId = Utility.appendZero(load.getId(), 1);

        final String urlPath = "sdt/l"+loadId+"oft/"+hour+"/"+minute+"/"+seconds;

        RESTUtil.get(planerIp, urlPath, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.message().equals("OK")) {
                    onOperationCompleteListener.onOperationComplete(planerIp+urlPath,"Done");
                } else
                onOperationCompleteListener.onError(planerIp+urlPath,"...");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onOperationCompleteListener.onError(planerIp+urlPath, t.getMessage());
            }
        });
    }

    public static void setLoadOnTime(Load load, final OnOperationCompleteListener onOperationCompleteListener) {
        final String planerIp = "http://"+AppState.planerIp+"/";

        String hour = Utility.appendZero(load.getStartHour(), 2);
        String minute = Utility.appendZero(load.getStartMinute(), 2);
        String seconds = Utility.appendZero(load.getStartSecond(), 2);
        String loadId = Utility.appendZero(load.getId(), 1);

        final String urlPath = "sdt/l"+loadId+"ont/"+hour+"/"+minute+"/"+seconds;

        RESTUtil.get(planerIp, urlPath, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.message().equals("OK")) {
                    onOperationCompleteListener.onOperationComplete(planerIp+urlPath,"Done");
                } else
                onOperationCompleteListener.onError(planerIp+urlPath,"...");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onOperationCompleteListener.onError(planerIp+urlPath, t.getMessage());
            }
        });
    }



    public interface OnOperationCompleteListener {
        void onOperationComplete(String url, String msg);
        void onError(String url, String msg);
    }
}

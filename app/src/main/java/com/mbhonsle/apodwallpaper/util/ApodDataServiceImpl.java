package com.mbhonsle.apodwallpaper.util;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ApodDataServiceImpl implements ApodDataService {

    @Override
    public ApodData getJsonData(String url) {
        try {
            return new DataTask(url).execute().get();
        } catch (Exception e) {
            Log.e("APODDataTask", e.getMessage(), e);
        }

        return ApodData.getDefault();
    }

    public class DataTask extends AsyncTask<Void, Void, ApodData> {

        private String serviceEndpoint = "";
        DataTask(String endPoint) {
            this.serviceEndpoint = endPoint;
        }

        @Override
        protected ApodData doInBackground(Void... voids) {
            try {
                if (this.serviceEndpoint.isEmpty()) {
                    Log.e("APODDataTask", "Service endpoint is empty");
                    return null;
                }
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                return restTemplate.getForObject(this.serviceEndpoint, ApodData.class);
            } catch (Exception e) {
                Log.e("APODDataTask", e.getMessage(), e);
            }
            return ApodData.getDefault();
        }
    }
}

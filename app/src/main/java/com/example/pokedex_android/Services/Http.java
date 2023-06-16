package com.example.pokedex_android.Services;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedex_android.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.Consumer;

import javax.security.auth.callback.Callback;

public class Http {
    private Context ctx;

    public interface VolleyCallBack {
        void onSuccess(JSONObject jsonObject);
        void onError(VolleyError error);
    }

    public Http(Context ctx){
        this.ctx = ctx;
    }

    private String getPath(String path) {
        String fullPath;
        if (path.contains("http") || path.contains("https")) fullPath = path;
        else fullPath = BuildConfig.BASE_URL + path;

        return fullPath;
    }

    public void get(String path, final VolleyCallBack callback) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                getPath(path),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                    }
                });

        Volley.newRequestQueue(ctx).add(stringRequest);
    }
}
package com.dailyneeds.vugido.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dailyneeds.vugido.dialogs.ProgressDialog;
import java.util.Map;



public class NetworkQueries {

    private Context context;
    private String URL;
    private ProgressDialog progressDialog;
    private FragmentActivity fragmentActivity;
    private NetworkQueryInterface networkQueryInterface;
    private Map<String,String> params;
    private NetworkQueryInterfaceWithCode networkQueryInterfaceWithCode;
    private int code;



    public NetworkQueries(Context context,NetworkQueryInterfaceWithCode networkQueryInterfaceWithCode,String URL,Map<String,String> params,int code){
        this.context=context;
        this.URL=URL;
        fragmentActivity= (FragmentActivity) context;
        this.networkQueryInterfaceWithCode=networkQueryInterfaceWithCode;
        this.params=params;
        this.code=code;
    }

    public NetworkQueries(Context context,String URL,Map<String,String> params){
        this.context=context;
        this.URL=URL;
        fragmentActivity= (FragmentActivity) context;
        networkQueryInterface= (NetworkQueryInterface) context;
        this.params=params;
    }
    public NetworkQueries(Context context,String URL,Map<String,String> params,int Code){
        this.context=context;
        this.URL=URL;
        fragmentActivity= (FragmentActivity) context;
        networkQueryInterfaceWithCode= (NetworkQueryInterfaceWithCode) context;
        this.params=params;
        code=Code;
    }
    public NetworkQueries(Context context,NetworkQueryInterface networkQueryInterface,String URL,Map<String,String> params){
        this.context=context;
        this.URL=URL;
        fragmentActivity= (FragmentActivity) context;
        this.networkQueryInterface=networkQueryInterface ;
        this.params=params;
    }

    public void sendNormalRequest(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("res",response);


                        networkQueryInterface.networkQueryInterface(response);

                        // from here get the data and then

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("err",error.toString());
                        networkQueryInterface.networkQueryError(volleyErrorStatus(error));


                    }
                }){


            @Override
            protected Map<String, String> getParams()  {

                return params;
            }

        };

        stringRequest.setShouldRetryServerErrors(true);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);



    }

    public void sendRequest(String Message) {

        Log.e("ex","yes");
        addProgressDialog(Message);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("res",response);


                        removeProgressDialog();
                        networkQueryInterface.networkQueryInterface(response);

                        // from here get the data and then

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("err",error.toString());
                        removeProgressDialog();
                        networkQueryInterface.networkQueryError(volleyErrorStatus(error));


                    }
                }){


            @Override
            protected Map<String, String> getParams()  {

                return params;
            }

        };

        stringRequest.setShouldRetryServerErrors(true);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);



    }

    public void sendRequestCode(String Message) {

        Log.e("ex","yes");
        addProgressDialog(Message);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("res",response);


                        removeProgressDialog();
                        networkQueryInterfaceWithCode.networkQueryInterface(response,code);

                        // from here get the data and then

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("err",error.toString());
                        removeProgressDialog();
                        networkQueryInterfaceWithCode.networkQueryError(volleyErrorStatus(error),code);
                       // networkQueryInterface.networkQueryError(volleyErrorStatus(error));


                    }
                }){


            @Override
            protected Map<String, String> getParams()  {

                return params;
            }

        };

        stringRequest.setShouldRetryServerErrors(true);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);



    }



    private void removeProgressDialog() {

        if(progressDialog!=null){

            progressDialog.dismiss();
            progressDialog=null;

        }

    }

    private void addProgressDialog(String message) {

        progressDialog=new ProgressDialog();
        Bundle bundle=new Bundle();
        bundle.putString("MSG",message);
        progressDialog.setArguments(bundle);
        progressDialog.show(fragmentActivity.getSupportFragmentManager(),"PROGRESS");

    }

    public void sendNormalRequestCode() {

        Log.e("ex","yes");
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("res",response);


                        networkQueryInterfaceWithCode.networkQueryInterface(response,code);

                        // from here get the data and then

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("err",error.toString());

                        networkQueryInterfaceWithCode.networkQueryError(volleyErrorStatus(error),code);
                        // networkQueryInterface.networkQueryError(volleyErrorStatus(error));


                    }
                }){


            @Override
            protected Map<String, String> getParams()  {

                return params;
            }

        };

        stringRequest.setShouldRetryServerErrors(true);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);



    }

 /*   public void sendJSONRequest(String placing_order, JSONObject buildParams) {


        Log.e("ex","yes");
        addProgressDialog(placing_order);


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,ConfigVariables.PLACE_ORDER, buildParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                Log.e("data",response.toString());

                removeProgressDialog();
               // networkQueryInterface.networkQueryInterface(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error", error.toString());

                removeProgressDialog();
              //  networkQueryInterface.networkQueryError(volleyErrorStatus(error));

            }
        });




        jsonObjectRequest.setShouldRetryServerErrors(true);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);





    }*/

    // public interface

    public interface NetworkQueryInterface{

        void networkQueryInterface(String Response);
        void networkQueryError(String error);

    }
    public interface NetworkQueryInterfaceWithCode{

        void networkQueryInterface(String Response,int code);
        void networkQueryError(String error,int code);
    }

    private String volleyErrorStatus(VolleyError volleyError){
        String message;
        if (volleyError instanceof NetworkError) {
            message = "Can't connect to Internet...Please check your connection!";
        } else if (volleyError instanceof ServerError) {
            message = "Server not found. Please try again after some time!!";
        } else if (volleyError instanceof AuthFailureError) {
            message = "Can't connect to Internet...Please check your connection!";
        } else if (volleyError instanceof ParseError) {
            message = "Parsing error! Please try again after some time!!";
        } else if (volleyError instanceof TimeoutError) {
            message = "Connection TimeOut! Please check your internet connection.";
        }
        else{
            message = "Can't connect to Internet...Please check your connection!";
        }

        return message;
    }


}

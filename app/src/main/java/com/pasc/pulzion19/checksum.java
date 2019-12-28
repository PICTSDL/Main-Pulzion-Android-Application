package com.pasc.pulzion19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class checksum extends AppCompatActivity implements PaytmPaymentTransactionCallback {
    String custid = "", orderId = "", mid = "", amount = "";
    int temp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Intent intent = getIntent();
        orderId = intent.getExtras().getString("orderid");
        custid = intent.getExtras().getString("custid");
        temp = intent.getIntExtra("final_amount", 0);
        mid = "axESEi44386194598879"; /// your merchant id
        sendUserDetailTOServerdd dl = new sendUserDetailTOServerdd();
        dl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    public class sendUserDetailTOServerdd extends AsyncTask<ArrayList<String>, Void, String> {
        private ProgressDialog dialog = new ProgressDialog(checksum.this);
        //private String orderId , mid, custid, amt;
        String url = "https://pascpayment.000webhostapp.com/generateChecksum.php";
        String verifyurl = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=" + orderId;

        String CHECKSUMHASH ="";
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
        protected String doInBackground(ArrayList<String>... alldata) {
            JSONParser jsonParser = new JSONParser(checksum.this);
            String param=
                    "MID="+mid+
                            "&ORDER_ID=" + orderId+
                            "&CUST_ID="+custid+
                            "&CHANNEL_ID=WAP&TXN_AMOUNT=" + temp + "&WEBSITE=PRODUCTION" +
                            "&CALLBACK_URL=" + verifyurl + "&INDUSTRY_TYPE_ID=Retail";
            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);

            if(jsonObject != null){
                Log.e("CheckSum result >>",jsonObject.toString());
                try {
                    CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
                    Log.e("CheckSum result >>",CHECKSUMHASH);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            PaytmPGService Service = PaytmPGService.getProductionService();

            HashMap<String, String> paramMap = new HashMap<String, String>();
            //these are mandatory parameters
            paramMap.put("MID", mid); //MID provided by paytm
            paramMap.put("ORDER_ID", orderId);
            paramMap.put("CUST_ID", custid);
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", String.valueOf(temp));
            paramMap.put("WEBSITE", "PRODUCTION");
            paramMap.put("CALLBACK_URL", verifyurl);
            //paramMap.put( "EMAIL" , "abc@gmail.com");   // no need
            // paramMap.put( "MOBILE_NO" , "9144040888");  // no need
            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);
            //paramMap.put("PAYMENT_TYPE_ID" ,"CC");    // no need
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.i("checksum ", "param "+ paramMap.toString());
            Service.initialize(Order,null);
            // start payment service call here
            Service.startPaymentTransaction(checksum.this, true, true, new PaytmPaymentTransactionCallback() {
                /*Call Backs*/
                public void someUIErrorOccurred(String inErrorMessage) {
                    Intent i = new Intent(checksum.this,EventRegistrationActivity.class);
                    i.putExtra("TXN_RESPONSE", "TXN_FAILURE");
                    setResult(RESULT_OK,i);
                    finish();
                }

                public void onTransactionResponse(Bundle inResponse) {
                    Log.e("Response", inResponse.toString());
                    Intent i = new Intent(checksum.this,EventRegistrationActivity.class);
                    i.putExtra("TXN_RESPONSE", "TXN_SUCCESS");
                    setResult(RESULT_OK,i);
                    finish();
                }

                public void networkNotAvailable() {
                    Toast.makeText(checksum.this, "Check your Network", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(checksum.this,EventRegistrationActivity.class);
                    i.putExtra("TXN_RESPONSE", "TXN_FAILURE");
                    setResult(RESULT_OK,i);
                    finish();
                }

                public void clientAuthenticationFailed(String inErrorMessage) {
                }

                public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                }

                public void onBackPressedCancelTransaction() {
                    Log.e("checksum ", " cancel call back respon  ");
                    Toast.makeText(getApplicationContext(),"Payment Cancelled", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), EventRegistrationActivity.class);
                    startActivity(i);
                    finish();


                }

                public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

                }
            });
        }
    }
    @Override
    public void onTransactionResponse(Bundle inResponse) {
        /*Display the message as below */
        Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
        Intent i = new Intent(checksum.this,EventRegistrationActivity.class);
        i.putExtra("TXN_RESPONSE", "TXN_SUCCESS");
        setResult(RESULT_OK,i);
        finish();

    }
    @Override
    public void networkNotAvailable() {
        Toast.makeText(this, "Network=null", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void clientAuthenticationFailed(String s) {
    }
    @Override
    public void someUIErrorOccurred(String s) {
        Log.e("checksum ", " ui fail respon  "+ s );
    }
    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Log.e("checksum ", " error loading pagerespon true "+ s + "  s1 " + s1);
    }
    @Override
    public void onBackPressedCancelTransaction() {
        Log.e("checksum ", " cancel call back respon  " );
        Toast.makeText(getApplicationContext(), "Payment Transaction response ", Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(), Success.class);
        startActivity(i);
        finish();

    }
    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Log.e("checksum ", "  transaction cancel " );
    }


}

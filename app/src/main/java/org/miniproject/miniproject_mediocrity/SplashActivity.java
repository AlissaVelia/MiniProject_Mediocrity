package org.miniproject.miniproject_mediocrity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import net.khirr.android.privacypolicy.PrivacyPolicyDialog;

public class SplashActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    private Button BtnLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        BtnLoad = findViewById(R.id.btnLoad);

        SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);
        if(firstRun==false)//if running for first time
        //Splash will load for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.commit();
            privacyCheck();
        }
        else
        {


            StartAppSDK.init(this, "203281927", true);
            StartAppSDK.setUserConsent (this,
                    "pas",
                    System.currentTimeMillis(),
                    true);
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(i);

//            Intent a=new Intent(this,MainActivity.class);


        }



    }

    private void privacyCheck() {
        PrivacyPolicyDialog dialog = new PrivacyPolicyDialog(this,
                "https://localhost/terms",
                "https://localhost/privacy");

        // Dialog
        dialog.addPoliceLine("This application uses a unique user identifier for advertising purposes, it is shared with third-party companies.");
        dialog.addPoliceLine("This application sends error reports, installation and send it to a server of the Fabric.io company to analyze and process it.");
        dialog.addPoliceLine("This application requires internet access and must collect the following information: Installed applications and history of installed applications, ip address, unique installation id, token to send notifications, version of the application, time zone and information about the language of the device.");
        dialog.addPoliceLine("All details about the use of data are available in our Privacy Policies, as well as all Terms of Service links below.");

        // Title Dialog
        dialog.setTitle("Terms Of Services");

        // Terms
        dialog.setTermsOfServiceSubtitle("If you click on (accept), you acknowledge that is makes the bla bla");
        // Dialog set
        dialog.setTitleTextColor(Color.parseColor("#222222"));
        dialog.setAcceptButtonColor(ContextCompat.getColor(this, R.color.colorAccent));
        dialog.show();

        final Intent intent = new Intent(this, MainActivity.class);

        dialog.setOnClickListener(new PrivacyPolicyDialog.OnClickListener() {
            @Override
            public void onAccept(boolean b) {
                Log.e("SplashActivity", "Policies accepted");
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                Log.e("SplashActivity", "Policies not accepted");
                finish();
            }
        });
    }

    public void Load(View view) {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
    }
}

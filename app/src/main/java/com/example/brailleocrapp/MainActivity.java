package com.example.brailleocrapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Button testButton, scanButton;
    TextView wifiNetView;

    List<ScanResult> wifiList;
    WifiManager wifiManager;
    StringBuilder ssids = new StringBuilder();
    WifiReceiver wifiReceiver;

    String networkSSID = "Be Happy";
    String networkPass = "anitya123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, CustomTextSend.class);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiNetView = findViewById(R.id.wifiNetView);
        testButton = findViewById(R.id.testButton);
        scanButton = findViewById(R.id.scanButton);

        if (!wifiManager.isWifiEnabled()) {
            wifiNetView.setText("WiFi OFF. Turning ON...");
            wifiManager.setWifiEnabled(true);
        }

        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        wifiNetView.setText("Starting Scan");

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiNetView.setText(ssids);
            }
        });
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\""+ networkPass +"\"";
        wifiManager.addNetwork(conf);

        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();

                break;
            }
        }

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(wifiReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(wifiReceiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            ssids = new StringBuilder();
            wifiList = wifiManager.getScanResults();
            ssids.append("\nNumber of Connections: "+ wifiList.size());

            for (int i=0; i<wifiList.size(); i++) {
                ssids.append(wifiList.get(i).toString());
                ssids.append('\n');
            }
        }
    }

}


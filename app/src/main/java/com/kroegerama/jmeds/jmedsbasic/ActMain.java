package com.kroegerama.jmeds.jmedsbasic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.kroegerama.jmeds.jmedsbasic.attachment.DocuExampleAttachmentService;
import com.kroegerama.jmeds.jmedsbasic.device.MyDevice;
import com.kroegerama.jmeds.jmedsbasic.device.MyService;

import org.ws4d.java.JMEDSFramework;
import org.ws4d.java.communication.DPWSCommunicationManager;
import org.ws4d.java.service.DefaultDevice;

import java.io.IOException;


public class ActMain extends AppCompatActivity {

    private Button btnStart;

    private Button btnStop;

    private Button btnTest;

    private ListView debug;

    private ArrayAdapter<String> debugAdapter;

    public void debug(String message) {
        debugAdapter.insert(message, 0);
        Log.d("JMEDS", message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.primary);

        getViews();
        initViews();

        debug("App started.");
    }

    private void getViews() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnTest = (Button) findViewById(R.id.btnTest);

        debug = (ListView) findViewById(R.id.debug);
    }

    private void initViews() {
        debugAdapter = new ArrayAdapter<String>(this, R.layout.list_item);
        debug.setAdapter(debugAdapter);

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                btnStart();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                btnStop();
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // doAttachmentTest();
                btnTest();
            }
        });
    }

    public void btnStart() {
        JMEDSFramework.start(null);
        debug("<ANDROID> FRAMEWORK STARTED.");

        MyDevice device;
        MyService service;

        try {
            device = new MyDevice();
            service = new MyService();

            debug("Add Service...");
            device.addService(service);

            debug("Start Device...");
            device.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnStop() {
        JMEDSFramework.stop();
    }

    public void btnTest() {
        if (!JMEDSFramework.isRunning()) {
            Toast.makeText(this, R.string.toast_framework_not_running, Toast.LENGTH_LONG).show();
            return;
        }

        DefaultDevice device = new DefaultDevice(DPWSCommunicationManager.COMMUNICATION_MANAGER_ID);
        DocuExampleAttachmentService service = new DocuExampleAttachmentService();

        device.addService(service);

        debug("EPR: " + device.getEndpointReference());

        try {
            device.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.act_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

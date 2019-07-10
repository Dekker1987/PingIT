package com.pingit.dekker.pingit;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.pingit.dekker.pingit.activities.PingPropertiesActivity;
import com.pingit.dekker.pingit.fragments.PingGraphFragment;
import com.pingit.dekker.pingit.helpers.DBhelper;
import com.pingit.dekker.pingit.helpers.HandlerHelper;
import com.pingit.dekker.pingit.helpers.PingHelper;
import com.pingit.dekker.pingit.models.PointModel;

public class MainActivity extends AppCompatActivity{

    private int backPressCounter;
    private ToggleButton btn_ping_start;
    private BottomSheetBehavior bottomSheetBehavior;
    private HandlerHelper handlerHelper;

    private MenuItem mi_stat_expand;
    private MenuItem mi_pause_ping;
    private MenuItem mi_continue_ping;

    private TextView tv_from_bottom_sheet;
    private PingHelper pingHelper;
    private EditText ed_url_to_ping;
    private PingGraphFragment pingGraphFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPinghelper();

        initUI();
        ed_url_to_ping.setText("www.youtube.com"); // for test
    }

    private void initPinghelper(){
        handlerHelper = new HandlerHelper(this);
        pingHelper = new PingHelper(getApplicationContext());
        pingHelper.setHandler(handlerHelper);
    }

    public void showPingRes(PointModel pointModel) {
        pingGraphFragment.showPingResult(pointModel.toString());
    }

    private void initUI(){
        initNhideActionBar();
        pingGraphFragment = (PingGraphFragment)getSupportFragmentManager()
                                                            .findFragmentById(R.id.PingGraphFragment);

        ed_url_to_ping = findViewById(R.id.ed_url_to_ping);

        btn_ping_start = findViewById(R.id.btn_ping_start);
        btn_ping_start.setOnCheckedChangeListener(onStartPingAndOnStopListener);

        initBottomSheet();
    }

    private void initBottomSheet(){
        View llBottomSheet = findViewById(R.id.bottomSheet);
        tv_from_bottom_sheet = llBottomSheet.findViewById(R.id.tv_ping_stat_send);
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void initNhideActionBar(){
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }

    ToggleButton.OnCheckedChangeListener onStartPingAndOnStopListener = new ToggleButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                executePing();
            } else {
                interruptPing();
            }
        }
    };

    private void executePing(){
        if(pingHelper == null){
            initPinghelper();
        }

        if(!ed_url_to_ping.equals("")){
            pingHelper.initHostToPing(ed_url_to_ping.getText().toString());
            pingHelper.start();
        }
    }

    private void interruptPing(){
        pingHelper.interruptPing();
        pingHelper = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        mi_stat_expand = menu.findItem(R.id.mi_expand);
        mi_pause_ping = menu.findItem(R.id.mi_pause_ping);
        mi_continue_ping = menu.findItem(R.id.mi_continue_ping);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mi_expand:
                expandStatisticLayout();
                break;

            case R.id.mi_ping_prop:
                startPingPropActivity();
                break;

            case R.id.mi_pause_ping:
                pausePing();
                break;

            case R.id.mi_continue_ping:
                continuePing();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void pausePing(){
        if(pingHelper!=null){
            pingHelper.suspend();
        }
    }

    private void continuePing(){
        if(pingHelper!=null){
            pingHelper.resume();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        changeMiStatExpandVisibility(keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed(){
        backPressCounter++;
        switch(backPressCounter){
            case 1:
                Toast.makeText(getApplicationContext(),"Press again to exit...",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                finish();
                break;
            default:
                break;
        }
    }

    private void expandStatisticLayout(){
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void startPingPropActivity(){
        startActivity(new Intent(this, PingPropertiesActivity.class));
    }

    private void changeMiStatExpandVisibility(int keyCode) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                switch (bottomSheetBehavior.getState()) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        mi_stat_expand.setVisible(true);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        mi_stat_expand.setVisible(false);
                        break;
                    default:
                        break;
                }
            }
    }

    protected void onDestroy(){
        super.onDestroy();
        closedDBconnection();
    }

    private void closedDBconnection(){
        DBhelper.getInstance().closeDB();
    }
}

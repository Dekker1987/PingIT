package com.pingit.dekker.pingit.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.pingit.dekker.pingit.R;
import com.pingit.dekker.pingit.enums.DefaultPingProperties;
import com.pingit.dekker.pingit.helpers.DBhelper;
import com.pingit.dekker.pingit.models.PropertyModel;
import com.pingit.dekker.pingit.utils.PingPropertiesValidatorUtil;

/**
 * Created by Sergii on 13.08.2018.
 */

public class PingPropertiesActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout txt_inp_lay_prop_timeout;
    private TextInputLayout txt_inp_lay_prop_ttl;
    private TextInputLayout txt_inp_lay_prop_packet_size;
    private TextInputLayout txt_inp_lay_prop_tries;
    private TextInputLayout txt_inp_lay_prop_delay;

    private TextInputEditText ed_prop_timeout;
    private TextInputEditText ed_prop_ttl;
    private TextInputEditText ed_prop_packet_size;
    private TextInputEditText ed_prop_tries;
    private TextInputEditText ed_prop_delay;

    private Button btn_clear_values;
    private Button btn_save_values;
    private Button btn_set_default_values;

    private ToggleButton tb_is_vibrate;
    private ExpandableRelativeLayout expandableLayout;

    private RadioGroup rg_vibro_at;
    private RadioButton rb_vibro_on_success;
    private RadioButton rb_vibro_on_fail;

    private boolean isVibrate;
    private boolean isVibrateOnSuccess;
    private boolean isVibrateOnFailure;

    private PropertyModel propertyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ping_properties_activity);

        initPropertiesModel();
        initUI();

    }

    private void initPropertiesModel()  {
        propertyModel = DBhelper.getInstance().getPropertiesModel();

        isVibrate = propertyModel.isVibrate();
        isVibrateOnSuccess = propertyModel.isVibrateOnSuccess();
        isVibrateOnFailure = propertyModel.isVibrateOnFailure();
    }

    private void initUI(){
        initViewsId();
        initViewsListeners();
        initViewStates();
     }

     private void initViewsId(){
         txt_inp_lay_prop_timeout = findViewById(R.id.txt_inp_lay_prop_timeout);
         txt_inp_lay_prop_ttl = findViewById(R.id.txt_inp_lay_prop_ttl);
         txt_inp_lay_prop_packet_size = findViewById(R.id.txt_inp_lay_prop_packet_size);
         txt_inp_lay_prop_tries = findViewById(R.id.txt_inp_lay_prop_tries);
         txt_inp_lay_prop_delay = findViewById(R.id.txt_inp_lay_prop_delay);
         ed_prop_timeout = findViewById(R.id.ed_prop_timeout);
         ed_prop_ttl = findViewById(R.id.ed_prop_ttl);
         ed_prop_packet_size = findViewById(R.id.ed_prop_packet_size);
         ed_prop_tries = findViewById(R.id.ed_prop_tries);
         ed_prop_delay = findViewById(R.id.ed_prop_delay);
         tb_is_vibrate = findViewById(R.id.tb_is_vibrate);
         btn_set_default_values = findViewById(R.id.btn_set_default_values);
         btn_clear_values = findViewById(R.id.btn_clear_values);
         btn_save_values = findViewById(R.id.btn_save_values);
         rg_vibro_at = findViewById(R.id.rg_vibro_at);
         rb_vibro_on_success = findViewById(R.id.rb_vibro_on_success);
         rb_vibro_on_fail = findViewById(R.id.rb_vibro_on_fail);
         expandableLayout = findViewById(R.id.expandableLayout);
         initExpandableLayout();
     }

     private void initViewsListeners(){
         tb_is_vibrate.setOnCheckedChangeListener(isVibrateOnCheckedChangeListener);
         rg_vibro_at.setOnCheckedChangeListener(radioGroupOnClickListener);
         btn_set_default_values.setOnClickListener(this);
         btn_clear_values.setOnClickListener(this);
         btn_save_values.setOnClickListener(this);
     }

     private void initViewStates(){
         initNhideActionBar();
         setBackgroundForPropEditText(android.R.color.transparent); // need set programmatically for keeping original size of EditText
         initHints();
         initRadioGroupState();
     }

     private void initRadioGroupState(){
         tb_is_vibrate.setChecked(propertyModel.isVibrate());
         rb_vibro_on_success.setChecked(propertyModel.isVibrateOnSuccess());
         rb_vibro_on_fail.setChecked(propertyModel.isVibrateOnFailure());
     }

     private void initHints(){
        if(propertyModel!=null){
            txt_inp_lay_prop_timeout.setHint("Timeout(ms) Current value is: " + propertyModel.getTimeout());
            txt_inp_lay_prop_ttl.setHint("TTL(ms) Current value is: " + propertyModel.getTtl());
            txt_inp_lay_prop_packet_size.setHint("Packet Size(bytes) Current value is: " + propertyModel.getPacketSize());
            txt_inp_lay_prop_tries.setHint("Tries Current value is: " + propertyModel.getTries());
            txt_inp_lay_prop_delay.setHint("Delay Current value is: " + propertyModel.getDelay());
        } else {
            txt_inp_lay_prop_timeout.setHint("Timeout(ms) Current value is: " + DefaultPingProperties.TIMEOUT.getDefaultValue());
            txt_inp_lay_prop_ttl.setHint("TTL(ms) Current value is: " + DefaultPingProperties.TTL.getDefaultValue());
            txt_inp_lay_prop_packet_size.setHint("Packet Size(bytes) Current value is: " + DefaultPingProperties.PACKET_SIZE.getDefaultValue());
            txt_inp_lay_prop_tries.setHint("Tries Current value is: " + DefaultPingProperties.TRIES.getDefaultValue());
            txt_inp_lay_prop_delay.setHint("Delay Current value is: " + DefaultPingProperties.DELAY.getDefaultValue());
        }
     }

     private void initExpandableLayout(){
         expandableLayout.toggle();
         expandableLayout.moveChild(0);
         expandableLayout.move(500);
         expandableLayout.setClosePosition(0);
    }

     ToggleButton.OnCheckedChangeListener isVibrateOnCheckedChangeListener = new ToggleButton.OnCheckedChangeListener(){

         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             if (isChecked) {
                 isVibrate=true;
                 expandableLayout.expand();
             } else {
                 isVibrate=false;
                 isVibrateOnSuccess=false;
                 isVibrateOnFailure=false;

                 expandableLayout.setClosePosition(0);
                 expandableLayout.setExpanded(false);
                 expandableLayout.collapse();
             }
         }
     };

    RadioGroup.OnCheckedChangeListener radioGroupOnClickListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId) {
                case R.id.rb_vibro_on_success:
                    isVibrateOnSuccess=true;
                    isVibrateOnFailure=false;
                    break;
                case R.id.rb_vibro_on_fail:
                    isVibrateOnSuccess=false;
                    isVibrateOnFailure=true;
                    break;
                default:
                    break;
            }
        }
    };

    private void setBackgroundForPropEditText(int backgroundColorCode){
        ed_prop_timeout.setBackgroundResource(backgroundColorCode);
        ed_prop_ttl.setBackgroundResource(backgroundColorCode);
        ed_prop_packet_size.setBackgroundResource(backgroundColorCode);
        ed_prop_tries.setBackgroundResource(backgroundColorCode);
        ed_prop_delay.setBackgroundResource(backgroundColorCode);
    }

    private void initNhideActionBar(){
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_set_default_values:
                setDefaultPingProperties();
                break;

            case R.id.btn_clear_values:
                clearAllValues();
                break;

            case R.id.btn_save_values:
                savePropertiesToDB();
                break;

            default:
                break;
        }
    }

    private void setDefaultPingProperties(){
        DBhelper.getInstance().saveProperties(new PropertyModel()
        {{
            setPacketSize(DefaultPingProperties.PACKET_SIZE.getDefaultValue());
            setTimeout(DefaultPingProperties.TIMEOUT.getDefaultValue());
            setTtl(DefaultPingProperties.TTL.getDefaultValue());
            setDelay(DefaultPingProperties.DELAY.getDefaultValue());
            setTries(DefaultPingProperties.TRIES.getDefaultValue());
            setVibrate(false);
            setVibrateOnFailure(false);
            setVibrateOnSuccess(false);
        }});

        initPropertiesModel();
        initHints();
        clearAllValues();
    }

    private void savePropertiesToDB(){
        DBhelper.getInstance().saveProperties(new PropertyModel()
                                              {{
                                                setPacketSize(
                                                        Integer.parseInt(
                                                                String.valueOf(
                                                                        PingPropertiesValidatorUtil.getInstance().getValidatedPacketSize(
                                                                                ed_prop_packet_size.getText().toString()))));

                                                setTimeout(
                                                        Integer.parseInt(
                                                                String.valueOf(
                                                                        PingPropertiesValidatorUtil.getInstance().getValidatedTimeout(
                                                                                ed_prop_timeout.getText().toString()))));

                                                setTtl(
                                                        Integer.parseInt(
                                                                String.valueOf(
                                                                        PingPropertiesValidatorUtil.getInstance().getValidatedTTL(
                                                                                ed_prop_ttl.getText().toString()))));

                                                setDelay(
                                                        Integer.parseInt(
                                                                String.valueOf(
                                                                        PingPropertiesValidatorUtil.getInstance().getValidatedDelay(
                                                                                ed_prop_delay.getText().toString()))));

                                                setTries(
                                                        Integer.parseInt(
                                                                String.valueOf(
                                                                        PingPropertiesValidatorUtil.getInstance().getValidatedTries(
                                                                                ed_prop_tries.getText().toString()))));

                                                setVibrate(isVibrate);
                                                setVibrateOnFailure(isVibrateOnFailure);
                                                setVibrateOnSuccess(isVibrateOnSuccess);
                                              }});

        initPropertiesModel();
        initHints();
        clearAllValues();
    }

    private void clearAllValues(){
        ed_prop_timeout.setText("");
        ed_prop_ttl.setText("");
        ed_prop_packet_size.setText("");
        ed_prop_tries.setText("");
        ed_prop_delay.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    private void finishActivity(){
        finish();
    }
}
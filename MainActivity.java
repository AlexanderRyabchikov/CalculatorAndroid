package com.example.alexander.calculator;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

enum Operation
{
    PLUS,
    SUB,
    MUL,
    DIV
}

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, SensorEventListener{
    private EditText editText;
    private Button  b_clean, b_polar, b_percent, b_div,
                    b_seven, b_eigth, b_nine, b_mul,
                    b_four, b_five, b_six, b_sub,
                    b_one, b_two, b_three, b_plus,
                    b_zero, b_point, b_result;
    private double db_Result, first_param, second_param;
    private String inputNumber;
    private Operation operation;
    private int state_div = 0;
    private int state_mul = 0;
    private int state_sub = 0;
    private int state_plus = 0;
    private int state_point = 0;
    private SensorManager sensorManager;
    private Sensor sensorLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL);

        editText = (EditText)findViewById(R.id.editText);
        editText.setCursorVisible(false);
        b_clean = (Button)findViewById(R.id.clear_button);
        b_polar = (Button)findViewById(R.id.polar_button);
        b_percent = (Button)findViewById(R.id.percent_button);
        b_div = (Button)findViewById(R.id.div_button);
        b_seven = (Button)findViewById(R.id.seven_button);
        b_eigth = (Button)findViewById(R.id.eith_button);
        b_nine = (Button)findViewById(R.id.nine_button);
        b_mul = (Button)findViewById(R.id.mul_button);
        b_four = (Button)findViewById(R.id.four_button);
        b_five = (Button)findViewById(R.id.five_button);
        b_six = (Button)findViewById(R.id.six_button);
        b_sub = (Button)findViewById(R.id.sub_button);
        b_one = (Button)findViewById(R.id.one_button);
        b_two = (Button)findViewById(R.id.two_button);
        b_three = (Button)findViewById(R.id.three_button);
        b_plus = (Button)findViewById(R.id.plus_button);
        b_zero = (Button)findViewById(R.id.zero_button);
        b_point = (Button)findViewById(R.id.point_button);
        b_result = (Button)findViewById(R.id.result_button);

        db_Result = 0;
        first_param = 0;
        second_param = 0;
        inputNumber = "";

        b_clean.setOnClickListener(this);
        b_polar.setOnClickListener(this);
        b_percent.setOnClickListener(this);
        b_div.setOnClickListener(this);
        b_seven.setOnClickListener(this);
        b_eigth.setOnClickListener(this);
        b_nine.setOnClickListener(this);
        b_mul.setOnClickListener(this);
        b_four.setOnClickListener(this);
        b_five.setOnClickListener(this);
        b_six.setOnClickListener(this);
        b_sub.setOnClickListener(this);
        b_one.setOnClickListener(this);
        b_two.setOnClickListener(this);
        b_three.setOnClickListener(this);
        b_plus.setOnClickListener(this);
        b_zero.setOnClickListener(this);
        b_point.setOnClickListener(this);
        b_result.setOnClickListener(this);

        getSupportActionBar().hide();
        print_text("0");


    }

    private void print_text(String str)
    {
        editText.setText(str);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this, sensorLight);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.clear_button:
                db_Result = 0;
                print_text("0");
                inputNumber = "";
                state_point = 0;
                break;
            case R.id.polar_button:
                String tmp_number = editText.getText().toString();
                if(tmp_number != "") {
                    if(tmp_number.charAt(0) == '-')
                    {
                        tmp_number = tmp_number.substring(1);
                        print_text(tmp_number);
                    }
                    else
                    {
                        tmp_number = "-" + tmp_number;
                        print_text(tmp_number);
                    }

                }
                break;
            case R.id.percent_button:
                double tmp = Double.parseDouble(editText.getText().toString()) / 100;
                inputNumber = "";
                print_text(String.valueOf(tmp));
                break;
            case R.id.div_button:
                 if (state_div == 1) {
                    double tmp_result = 0;
                    second_param = Double.parseDouble(editText.getText().toString());
                    tmp_result = first_param / second_param;
                    first_param = tmp_result;
                    print_text(String.valueOf(tmp_result));
                    inputNumber = "";
                 }
                 else {
                    first_param = Double.parseDouble(editText.getText().toString());
                    inputNumber = "";
                    print_text("");
                    operation = Operation.DIV;
                    state_div = 1;
                 }
                state_point = 0;
                break;
            case R.id.seven_button:
                if(inputNumber == "")
                    inputNumber = "7";
                else
                    inputNumber += "7";
                print_text(inputNumber);
                break;
            case R.id.eith_button:
                if(inputNumber == "")
                    inputNumber = "8";
                else
                    inputNumber += "8";
                print_text(inputNumber);
                break;
            case R.id.nine_button:
                if(inputNumber == "")
                    inputNumber = "9";
                else
                    inputNumber += "9";
                print_text(inputNumber);
                break;
            case R.id.mul_button:

                if(state_mul == 1) {

                    double tmp_result = 0;
                    second_param = Double.parseDouble(editText.getText().toString());
                    tmp_result = first_param * second_param;
                    first_param = tmp_result;
                    print_text(String.valueOf(tmp_result));
                    inputNumber = "";
                }
                else {
                    first_param = Double.parseDouble(editText.getText().toString());
                    inputNumber = "";
                    print_text("");
                    operation = Operation.MUL;
                    state_mul = 1;
                }
                state_point = 0;
                break;
            case R.id.four_button:
                if(inputNumber == "")
                    inputNumber = "4";
                else
                    inputNumber += "4";
                print_text(inputNumber);
                break;
            case R.id.five_button:
                if(inputNumber == "")
                    inputNumber = "5";
                else
                    inputNumber += "5";
                print_text(inputNumber);
                break;
            case R.id.six_button:
                if(inputNumber == "")
                    inputNumber = "6";
                else
                    inputNumber += "6";
                print_text(inputNumber);
                break;
            case R.id.sub_button:
                if(state_sub == 1) {
                    double tmp_result = 0;
                    second_param = Double.parseDouble(editText.getText().toString());
                    tmp_result = first_param - second_param;
                    first_param = tmp_result;
                    print_text(String.valueOf(tmp_result));
                    inputNumber = "";
                }
                else {
                    first_param = Double.parseDouble(editText.getText().toString());
                    inputNumber = "";
                    print_text("");
                    operation = Operation.SUB;
                    state_sub = 1;
                }
                state_point = 0;
                break;
            case R.id.one_button:
                if(inputNumber == "")
                    inputNumber = "1";
                else
                    inputNumber += "1";
                print_text(inputNumber);
                break;
            case R.id.two_button:
                if(inputNumber == "")
                    inputNumber = "2";
                else
                    inputNumber += "2";
                print_text(inputNumber);
                break;
            case R.id.three_button:
                if(inputNumber == "")
                    inputNumber = "3";
                else
                    inputNumber += "3";
                print_text(inputNumber);
                break;
            case R.id.plus_button:
                if(state_plus == 1) {
                    double tmp_result = 0;
                    second_param = Double.parseDouble(editText.getText().toString());
                    tmp_result = first_param + second_param;
                    first_param = tmp_result;
                    print_text(String.valueOf(tmp_result));
                    inputNumber = "";
                }
                else {
                    first_param = Double.parseDouble(editText.getText().toString());
                    inputNumber = "";
                    print_text("");
                    operation = Operation.PLUS;
                    state_plus = 1;
                }
                state_point = 0;
                break;
            case R.id.zero_button:
                if(inputNumber == "")
                    inputNumber = "0";
                else
                    inputNumber += "0";
                print_text(inputNumber);
                break;
            case R.id.point_button:
                if (state_point != 1) {
                    if (inputNumber == "")
                        inputNumber = "0.";
                    else
                        inputNumber += ".";
                    state_point = 1;
                    print_text(inputNumber);
                }
                break;
            case R.id.result_button:
                second_param = Double.parseDouble(editText.getText().toString());
                switch (operation) {
                    case PLUS:
                        db_Result = first_param + second_param;
                        state_plus = 0;
                        break;
                    case MUL:
                        db_Result = first_param * second_param;
                        state_mul = 0;
                        break;
                    case SUB:
                        db_Result = first_param - second_param;
                        state_sub = 0;
                        break;
                    case DIV:
                        db_Result = first_param / second_param;
                        state_div = 0;
                        break;
                }
                print_text(String.valueOf(db_Result));
                inputNumber="";
                break;
        }
    }

    private boolean setColorPressButton(Button button, int Color, MotionEvent motionEvent)
    {

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            button.setDrawingCacheBackgroundColor(Color);
            return true;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            button.setBackgroundColor(Color);
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean result = false;
        switch (view.getId())
        {
            case R.id.clear_button:
                result = setColorPressButton(b_clean, Color.WHITE, motionEvent);
                break;
            case R.id.polar_button:
                result = setColorPressButton(b_polar, Color.WHITE, motionEvent);
                break;
            case R.id.percent_button:
                result = setColorPressButton(b_percent, Color.WHITE, motionEvent);
                break;
            case R.id.div_button:
                result = setColorPressButton(b_div, Color.YELLOW, motionEvent);
                break;
            case R.id.seven_button:
                result = setColorPressButton(b_seven, Color.DKGRAY, motionEvent);
                break;
            case R.id.eith_button:
                result = setColorPressButton(b_eigth, Color.DKGRAY, motionEvent);
                break;
            case R.id.nine_button:
                result = setColorPressButton(b_nine, Color.DKGRAY, motionEvent);
                break;
            case R.id.mul_button:
                result = setColorPressButton(b_mul, Color.YELLOW, motionEvent);
                break;
            case R.id.four_button:
                result = setColorPressButton(b_four, Color.DKGRAY, motionEvent);
                break;
            case R.id.five_button:
                result = setColorPressButton(b_five, Color.DKGRAY, motionEvent);
                break;
            case R.id.six_button:
                result = setColorPressButton(b_six, Color.DKGRAY, motionEvent);
                break;
            case R.id.sub_button:
                result = setColorPressButton(b_sub, Color.YELLOW, motionEvent);
                break;
            case R.id.one_button:
                result = setColorPressButton(b_one, Color.DKGRAY, motionEvent);
                break;
            case R.id.two_button:
                result = setColorPressButton(b_two, Color.DKGRAY, motionEvent);
                break;
            case R.id.three_button:
                result = setColorPressButton(b_three, Color.DKGRAY, motionEvent);
                break;
            case R.id.plus_button:
                result = setColorPressButton(b_plus, Color.YELLOW, motionEvent);
                break;
            case R.id.zero_button:
                result = setColorPressButton(b_zero, Color.DKGRAY, motionEvent);
                break;
            case R.id.point_button:
                result = setColorPressButton(b_point, Color.DKGRAY, motionEvent);
                break;
            case R.id.result_button:
                result = setColorPressButton(b_result, Color.YELLOW, motionEvent);
                break;
        }
        return result;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT)
            if (sensorEvent.values[0] > 10000) {
                editText.setText("Every light");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            else if (sensorEvent.values[0] < 10000){
                editText.setText("");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

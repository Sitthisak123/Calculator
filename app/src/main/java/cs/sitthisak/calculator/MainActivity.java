package cs.sitthisak.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    public Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_dot,
                    btn_addition,btn_subtraction,btn_multiply,btn_divide,
                    btn_equal,btn_ac,btn_percent,btn_positive_or_negative;
    public TextView tv_result, tv_temp1, tv_op;
    public final String addition = "+",
                        subtraction = "-",
                        multiply = "×",
                        divide = "÷";
    public String temp = "",
                  temp2 = "",
                  operator = "";
    public float result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matchView();

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zero_pressed();
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pressed("9");
            }
        });


        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        btn_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op_pressed(addition);
            }
        });

        btn_subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op_pressed(subtraction);
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op_pressed(multiply);
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op_pressed(divide);
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        btn_positive_or_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invertNumber();
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot_pressed();
            }
        });

        btn_percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                percent_pressed();
            }
        });
    }

    private void num_pressed(String num) {
        if(operator.length() > 0){
            temp2 = temp2+num;
            tv_result.setText(temp2);
        }else{
            temp = temp+num;
            tv_result.setText(temp);
        }
    }
    private void zero_pressed() {
        if(operator.length() > 0){
            if(!isDuplicateZero(temp2)){
                temp2 = temp2+"0";
                tv_result.setText(temp2);
            }
        }else{
            if(!isDuplicateZero(temp)){
                temp = temp+"0";
                tv_result.setText(temp);
            }
        }
    }
    private void dot_pressed() {
        if(operator.length() > 0){
            if(!isExistDot(temp2)){
                temp2 = temp2+".";
                tv_result.setText(temp2);
            }
        }else{
            if(!isExistDot(temp)){
                temp = temp+".";
                tv_result.setText(temp);
            }
        }
    }
    public void percent_pressed(){
        float num = Float.parseFloat(temp);
        clear();
        num /= 100;
        temp = formatFloat(num);
        tv_result.setText(temp);
    }


    private void tvClear() {
        tv_result.setText("");
        tv_op.setText(" ");
        tv_temp1.setText("");
    }
    private void clear(){
        temp = "";
        temp2 = "";
        operator = "";
        tv_op.setText(" ");
        tv_temp1.setText("");
        tv_result.setText("0");
    }

    private void calculate() {
        if(temp.length() < 1|| temp2.length() < 1 || operator.length() < 1){
            return;
        }
        float a = 0, b = 0;
        a = Float.parseFloat(temp);
        b = Float.parseFloat(temp2);
        switch (operator){
            case addition:
                result = a+b;
                break;
            case subtraction:
                result = a-b;
                break;
            case multiply:
                result = a*b;
                break;
            case divide:
                result = a/b;
                break;
        }
        clear();
        temp = formatFloat(result);
        tv_result.setText(temp);
    }

    public void op_pressed(String op){
        if(temp.length() < 1){
            return;
        }
        if(temp2.length() < 1){
            tv_result.setText("");
        }
        operator = op;
        tv_op.setText(op);
        tv_temp1.setText(temp);
    }

    private void matchView() {
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_dot = findViewById(R.id.btn_dot);

        btn_addition = findViewById(R.id.btn_addition);
        btn_subtraction = findViewById(R.id.btn_subtraction);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);

        btn_equal = findViewById(R.id.btn_equal);
        btn_ac = findViewById(R.id.btn_ac);
        btn_percent = findViewById(R.id.btn_percent);
        btn_positive_or_negative = findViewById(R.id.btn_positive_or_negative);

        tv_result = findViewById(R.id.tv_result);
        tv_temp1 = findViewById(R.id.tv_temp1);
        tv_op = findViewById(R.id.tv_op);

//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv_temp1.getLayoutParams();
//        params.weight = 0.8f;
//        tv_temp1.setLayoutParams(params);
    }

    public static boolean isInteger(double number) {
        return (int) number == number;
    }

    public static String swapPositiveOrNegative(String value) {
        float floatValue = Float.parseFloat(value);
        if (floatValue > 0) {
            return ("-" + value);
        } else if (floatValue < 0) {
            return value.substring(1);
        }
        return value;
    }

    public void invertNumber(){
        String numberPattern = "^[0-9]*\\.?[0-9]+$";
        if(operator.length() > 0){
            if(temp2.length() > 0 && Pattern.matches(numberPattern, temp2)){
                temp2 = swapPositiveOrNegative(temp2);
                tv_result.setText(temp2);
            }
        }else{
            if(temp.length() > 0 && Pattern.matches(numberPattern, temp)){
                temp = swapPositiveOrNegative(temp);
                tv_result.setText(temp);
            }
        }
    }
    public static String formatFloat(float value) {
        if (isInteger(value)) {
            return Integer.toString((int) value);
        } else {
            return Float.toString(value);
        }
    }

    public static boolean isExistDot(String str){
        if (str.indexOf(".") != -1) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isDuplicateZero(String str){
        if(str.length() == 1){
            if(str.charAt(0) == '0'){
                return true;
            }
        }
        return false;
    }

}
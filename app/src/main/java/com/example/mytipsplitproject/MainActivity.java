package com.example.mytipsplitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText initBillAmount, noOfpeople;
    TextView tipAmount, totalWithTipAmount, billPerPerson;
    RadioGroup radioGroup_tip_selection;
    int tip_percent,people;
    float totalAmountWithTip,initBillAmountVar,calctipAmount,splitamount;
    String radiotext;
    Button SplitCalc,clear;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initBillAmount = (EditText)(findViewById(R.id.bill_total_amount));
        noOfpeople = (EditText)(findViewById(R.id.people));

        tipAmount = (TextView) (findViewById(R.id.tip_amount));
        totalWithTipAmount = (TextView) (findViewById(R.id.total_with_tip_amount));
        billPerPerson = (TextView) (findViewById(R.id.bill_per_person));

        radioGroup_tip_selection = (RadioGroup) (findViewById(R.id.radioGroup));

        radioGroup_tip_selection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton checkedButton = (RadioButton) radioGroup.findViewById(i);
                    if (checkedButton != null) {

                        radiotext = String.valueOf(checkedButton.getText());

                        switch (radiotext) {
                            case "20%":
                                tip_percent = 20;
                                break;
                            case "18%":
                                tip_percent = 18;
                                break;
                            case "15%":
                                tip_percent = 15;
                                break;
                            case "12%":
                                tip_percent = 12;
                                break;
                        }


                        if(TextUtils.isEmpty(initBillAmount.getText())==false){
                            initBillAmountVar = Float.valueOf(String.valueOf(initBillAmount.getText()).trim());
                            calctipAmount = (initBillAmountVar * tip_percent) / 100;
                            totalAmountWithTip = initBillAmountVar + calctipAmount;

                            tipAmount.setText("$"+String.valueOf(calctipAmount));
                            totalWithTipAmount.setText("$"+String.valueOf(totalAmountWithTip));
                        }
                        else{
                            radioGroup_tip_selection.clearCheck();
                        }
                    }
                }
        });

        SplitCalc = findViewById(R.id.Split_Calc_Button);


        SplitCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(noOfpeople.getText())){
                    noOfpeople.setError("Please enter no of people");
                }
                else {
                    people = Integer.valueOf(noOfpeople.getText().toString().trim());

                    if (people > 0) {
                        splitamount = totalAmountWithTip / people;
                        DecimalFormat f = new DecimalFormat("##.00");
                        billPerPerson.setText("$"+String.valueOf(f.format(splitamount)));
                    } else {
                        Toast.makeText(getApplicationContext(), "People should be > 0", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        clear = findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup_tip_selection.clearCheck();
                initBillAmount.setText(null);
                noOfpeople.setText(null);
                tipAmount.setText(" ");
                totalWithTipAmount.setText(" ");
                billPerPerson.setText(" ");
            }
        });

    }
}
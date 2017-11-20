package com.manfee.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declare variables
    EditText mPurchasePrice;
    TextView mDownPayment;
    EditText mLoanTerm;
    Button mCalculate;
    TextView mMonthlyRepay;
    SeekBar mSeekbar;
    EditText mPercentage;
    TextView mShowInterestRate;

    double purchaseprice;
    double downpayment;
    double annualinterest;
    double month;
    double amount;
    double operation;
    double discount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPurchasePrice = (EditText) findViewById(R.id.editText_PurchasePrice);
        mDownPayment = (EditText) findViewById(R.id.editText_DownPayment);
        mLoanTerm = (EditText) findViewById(R.id.editText_LoanTerm);
        mCalculate = (Button) findViewById(R.id.button_Calculate);
        mMonthlyRepay = (TextView) findViewById(R.id.textView_MonthlyRepay);
        mSeekbar = (SeekBar) findViewById(R.id.seekBar_InterestRate);
        mPercentage = (EditText) findViewById(R.id.editText_Percentage);
        mShowInterestRate = (TextView) findViewById(R.id.textView_ShowInterestRate);

        //Onprogress changed
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar mSeekBar, int i, boolean b) {

                float value = (float) (4+(i * 0.1));
                mShowInterestRate.setText(value+" %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar mSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar mSeekBar) {

            }
        });

        //Onclick Button
        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchaseprice = Double.parseDouble(mPurchasePrice.getText().toString());
                downpayment = Double.parseDouble(mDownPayment.getText().toString());
                amount = purchaseprice - downpayment;
                double percentage = Double.parseDouble(mPercentage.getText().toString());
                annualinterest = (percentage / 100) / 12;
                double year = Double.parseDouble(mLoanTerm.getText().toString());
                operation = Math.pow(1 + annualinterest, (year * 12));
                discount = (operation - 1) / (annualinterest * operation);
                double monthlyPayment = amount / discount;
                mMonthlyRepay.setText("RM " + monthlyPayment);

            }
        });


    }}

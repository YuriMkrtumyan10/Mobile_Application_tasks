package com.example.hw2_engtoint;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    private EditText mInputEditText;
    private TextView mResultTextView;

    public static final String ERROR_MESSAGE = "The input number should be from (-1, 999.999.999]";
    public static final String ERROR_MESSAGE2 = "Please enter a number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting IDs of UI components
        mInputEditText = findViewById(R.id.text_input);
        mResultTextView = findViewById(R.id.message_textview);
        Button mConvertButton = findViewById(R.id.button_convert);

        //OnClick
        mConvertButton.setOnClickListener(view -> {
            //Check for empty input
            if (mInputEditText.getText().toString().isEmpty()){
                mResultTextView.setText("");
                showErrorSnackbar(ERROR_MESSAGE2);
                return;
            }

            BigInteger input = new BigInteger(mInputEditText.getText().toString());

            //Check the range
            if (input.compareTo(BigInteger.ZERO) > -1 &&
                    input.compareTo(BigInteger.valueOf(99999999)) < 1) {
                String result = IntToEng.numberToWords(input.intValue());
                //textView design
                textViewAnimation(result);
            } else {
                mResultTextView.setText("");
                showErrorSnackbar(ERROR_MESSAGE);

            }
        });
    }
    private void showErrorSnackbar(String errorMessage) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.error_red));
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.setAction("Dismiss", v -> snackbar.dismiss());
        snackbar.show();
    }

    private void textViewAnimation(String result){
        mResultTextView.setText(result);
        mResultTextView.setVisibility(View.INVISIBLE);
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);
        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);
        mResultTextView.startAnimation(out);
        mResultTextView.setVisibility(View.VISIBLE);
        mResultTextView.startAnimation(in);
        mResultTextView.setTextColor(ContextCompat.getColor(this, R.color.result_text_color));
        mResultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        mResultTextView.setTypeface(null, Typeface.BOLD);
        mResultTextView.setGravity(Gravity.CENTER);
    }
}

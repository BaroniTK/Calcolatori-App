package com.example.calcolatoriapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputBinaryFixedPoint;
    private EditText inputNumber,inputIEEE754;
    private EditText inputNumber2;
    private TextView tvResult;
    private int selectedBits = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        inputNumber = findViewById(R.id.inputNumber);
        inputNumber2 = findViewById(R.id.inputNumber2);
        inputBinaryFixedPoint = findViewById(R.id.inputBinaryFixedPoint);
        inputIEEE754=findViewById(R.id.inputIEEE754);
        tvResult = findViewById(R.id.tvResult);

        Button btnDecimalToBinary = findViewById(R.id.btnDecimalToBinary);
        Button btnBinaryToDecimal = findViewById(R.id.btnBinaryToDecimal);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnComplement2 = findViewById(R.id.btnComplement2);
        Button btnFixedPointToDecimal = findViewById(R.id.btnFixedPointToDecimal);
        Button btnToComplement2 = findViewById(R.id.btnToComplement2);
        Button btnAddComplement2 = findViewById(R.id.btnAddComplement2);
        Button btnSubtractComplement2 = findViewById(R.id.btnSubtractComplement2);
        Button btnDecodeIEEE754 = findViewById(R.id.btnDecodeIEEE754);
        Spinner spinnerBits = findViewById(R.id.spinnerBits);

        spinnerBits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        selectedBits = 4;
                        break;
                    case 1:
                        selectedBits = 8;
                        break;
                    case 2:
                        selectedBits = 12;
                        break;
                    case 3:
                        selectedBits = 16;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnDecimalToBinary.setOnClickListener(view -> {
            String inputStr = inputNumber.getText().toString();
            if (inputStr.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    double number = Double.parseDouble(inputStr);
                    String binary = decimalToBinary(number);
                    tvResult.setText("Binary: " + binary);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnBinaryToDecimal.setOnClickListener(view -> {
            String binary = inputNumber.getText().toString();
            if (binary.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    double decimal = binaryToDecimal(binary);
                    tvResult.setText("Decimal: " + decimal);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnAdd.setOnClickListener(view -> {
            String inputStr1 = inputNumber.getText().toString();
            String inputStr2 = inputNumber2.getText().toString();
            if (inputStr1.isEmpty() || inputStr2.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    long number1 = Long.parseLong(inputStr1, 2);
                    long number2 = Long.parseLong(inputStr2, 2);
                    long result = number1 + number2;
                    tvResult.setText("Result: " + Long.toBinaryString(result));
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnSubtract.setOnClickListener(view -> {
            String inputStr1 = inputNumber.getText().toString();
            String inputStr2 = inputNumber2.getText().toString();
            if (inputStr1.isEmpty() || inputStr2.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    long number1 = Long.parseLong(inputStr1, 2);
                    long number2 = Long.parseLong(inputStr2, 2);
                    long result = number1 - number2;
                    tvResult.setText("Result: " + Long.toBinaryString(result));
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnMultiply.setOnClickListener(view -> {
            String inputStr1 = inputNumber.getText().toString();
            String inputStr2 = inputNumber2.getText().toString();
            if (inputStr1.isEmpty() || inputStr2.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    long number1 = Long.parseLong(inputStr1, 2);
                    long number2 = Long.parseLong(inputStr2, 2);
                    long result = number1 * number2;
                    tvResult.setText("Result: " + Long.toBinaryString(result));
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnComplement2.setOnClickListener(view -> {
            String inputStr1 = inputNumber.getText().toString();
            String inputStr2 = inputNumber2.getText().toString();
            if (inputStr1.isEmpty() || inputStr2.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    long number1 = Long.parseLong(inputStr1, 2);
                    long number2 = Long.parseLong(inputStr2, 2);
                    String result = complement2Operation(number1, number2, selectedBits);
                    tvResult.setText("Complemento a 2 Result: " + result);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnFixedPointToDecimal.setOnClickListener(view -> {
            String binary = inputBinaryFixedPoint.getText().toString();
            if (binary.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    double decimal = binaryFixedPointToDecimal(binary);
                    tvResult.setText("Fixed Point Decimal: " + decimal);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnToComplement2.setOnClickListener(view -> {
            String inputStr = inputNumber.getText().toString().replace(" ", "");
            if (inputStr.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    long number = Long.parseLong(inputStr, 2);
                    String result = toComplement2(number, selectedBits);
                    tvResult.setText("Complemento a 2: " + result);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnAddComplement2.setOnClickListener(view -> {
            String inputStr1 = inputNumber.getText().toString().replace(" ", "");
            String inputStr2 = inputNumber2.getText().toString().replace(" ", "");
            if (inputStr1.isEmpty() || inputStr2.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    long number1 = parseBinaryString(inputStr1, selectedBits);
                    long number2 = parseBinaryString(inputStr2, selectedBits);
                    long result = addComplement2(number1, number2, selectedBits);
                    tvResult.setText("Result: " + result);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnSubtractComplement2.setOnClickListener(view -> {
            String inputStr1 = inputNumber.getText().toString().replace(" ", "");
            String inputStr2 = inputNumber2.getText().toString().replace(" ", "");
            if (inputStr1.isEmpty() || inputStr2.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else {
                try {
                    long number1 = parseBinaryString(inputStr1, selectedBits);
                    long number2 = parseBinaryString(inputStr2, selectedBits);
                    long result = subtractComplement2(number1, number2, selectedBits);
                    tvResult.setText("Subtract Complemento a 2 Result: " + result);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });

        btnDecodeIEEE754.setOnClickListener(view -> {
            String ieee754Binary = inputIEEE754.getText().toString().replace(" ", "");
            if (ieee754Binary.isEmpty()) {
                tvResult.setText("Error: Input is empty");
            } else if (ieee754Binary.length() != 32) {
                tvResult.setText("Error: Input must be 32 bits long");
            } else {
                try {
                    // Parse sign bit
                    int s = Integer.parseInt(ieee754Binary.substring(0, 1));

                    // Parse exponent bits
                    int e = Integer.parseInt(ieee754Binary.substring(1, 9), 2);

                    // Parse mantissa bits
                    String mantissaBinary = ieee754Binary.substring(9);
                    double m = 1.0; // The implicit leading 1 in normalized IEEE 754 format
                    for (int i = 0; i < mantissaBinary.length(); i++) {
                        if (mantissaBinary.charAt(i) == '1') {
                            m += Math.pow(2, -(i + 1));
                        }
                    }

                    // Calculate the value
                    double value = Math.pow(-1, s) * m * Math.pow(2, e - 127);

                    // Display the result
                    tvResult.setText("Decimal value: " + value);
                } catch (NumberFormatException e) {
                    tvResult.setText("Error: Invalid input");
                }
            }
        });
    }

    private String decimalToBinary(double number) {
        long intPart = (long) number;
        double fracPart = number - intPart;

        String intPartStr = Long.toBinaryString(intPart);
        StringBuilder fracPartStr = new StringBuilder();

        while (fracPart > 0) {
            fracPart *= 2;
            if (fracPart >= 1) {
                fracPartStr.append("1");
                fracPart -= 1;
            } else {
                fracPartStr.append("0");
            }
        }

        return fracPartStr.length() > 0 ? intPartStr + "." + fracPartStr : intPartStr;
    }

    private double binaryToDecimal(String binary) {
        if (binary.contains(".")) {
            String[] parts = binary.split("\\.");
            int intPart = Integer.parseInt(parts[0], 2);

            double fracPart = 0.0;
            for (int i = 0; i < parts[1].length(); i++) {
                if (parts[1].charAt(i) == '1') {
                    fracPart += Math.pow(2, -(i + 1));
                }
            }

            return intPart + fracPart;
        } else {
            return Integer.parseInt(binary, 2);
        }
    }

    private long parseBinaryString(String binaryStr, int bits) {
        long number = Long.parseLong(binaryStr, 2);
        if ((number & (1L << (bits - 1))) != 0) {
            number = number - (1L << bits);
        }
        return number;
    }

    private long addComplement2(long num1, long num2, int bits) {
        long mask = (1L << bits) - 1;
        long result = (num1 + num2) & mask;
        if ((result & (1L << (bits - 1))) != 0) {
            result = result - (1L << bits);
        }
        return result;
    }

    private long subtractComplement2(long num1, long num2, int bits) {
        long mask = (1L << bits) - 1;
        long result = (num1 - num2) & mask;
        if ((result & (1L << (bits - 1))) != 0) {
            result = result - (1L << bits);
        }
        return result;
    }

    private String toComplement2(long number, int bits) {
        if (number >= 0) {
            return String.format("%" + bits + "s", Long.toBinaryString(number)).replace(' ', '0');
        } else {
            long mask = (1L << bits) - 1;
            long complement = (number & mask);
            return String.format("%" + bits + "s", Long.toBinaryString(complement)).replace(' ', '0');
        }
    }

    private String complement2Operation(long num1, long num2, int bits) {
        String binNum1 = Long.toBinaryString(num1);
        String binNum2 = Long.toBinaryString(num2);

        String bin1 = String.format("%" + bits + "s", binNum1).replace(' ', '0');
        String bin2 = String.format("%" + bits + "s", binNum2).replace(' ', '0');

        StringBuilder twosComplementBin2 = new StringBuilder();
        for (char bit : bin2.toCharArray()) {
            twosComplementBin2.append(bit == '0' ? '1' : '0');
        }
        long bin2Complement = Long.parseLong(twosComplementBin2.toString(), 2) + 1;

        long result = Long.parseLong(bin1, 2) + bin2Complement;

        return Long.toBinaryString(result);
    }

    private double binaryFixedPointToDecimal(String binary) {
        String[] parts = binary.split("\\.");
        int integerPart = Integer.parseInt(parts[0], 2);

        double fractionalPart = 0.0;
        if (parts.length > 1) {
            char[] fractionBits = parts[1].toCharArray();
            for (int i = 0; i < fractionBits.length; i++) {
                if (fractionBits[i] == '1') {
                    fractionalPart += Math.pow(2, -(i + 1));
                }
            }
        }

        return integerPart + fractionalPart;
    }
}

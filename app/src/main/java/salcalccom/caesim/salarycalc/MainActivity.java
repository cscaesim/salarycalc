package salcalccom.caesim.salarycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText mHours;
    EditText mPayRate;

    TextView mBeforeTaxes;
    TextView mAfterTaxes;

    Button calcButton;

    double hourlyRate;
    double hours;
    double beforeTaxes;
    double afterTaxes;
    double taxRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcButton = (Button) findViewById(R.id.button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    mHours = (EditText) findViewById(R.id.amountHours);
                    String sHours = mHours.getText().toString();
                    hours = Double.parseDouble(sHours);
                } catch (NumberFormatException ex) {

                }

                mPayRate = (EditText) findViewById(R.id.hourlyRate);
                String hourRate = mPayRate.getText().toString();
                hourlyRate = Double.parseDouble(hourRate);

                beforeTaxes = hourlyRate * hours * 52;

                taxRate = calculateTax(beforeTaxes);

                afterTaxes = beforeTaxes - taxRate;

                String sBeforeTaxes;
                sBeforeTaxes = '$' + String.valueOf(beforeTaxes);
                mBeforeTaxes = (TextView) findViewById(R.id.beforeTaxes);
                mBeforeTaxes.setText(sBeforeTaxes);

                mAfterTaxes = (TextView) findViewById(R.id.afterTaxes);
                String sAfterTaxes;
                sAfterTaxes = '$' + String.valueOf(afterTaxes);
                mAfterTaxes.setText(sAfterTaxes);

            }

        });

    }

    public double calculateTax(double yearlyRate) {
        double income = yearlyRate;
        double tax = 0;
        if (income >= 0 && income <= 1000) {
            tax = (0 * income);
        } else if (income > 1000 && income <= 10000) {
            tax = (0.1 * (income - 1000));
        } else if (income > 10000 && income <= 20200) {
            tax = ((0.1 * (10000 - 1000)) + (0.15 * (income - 10000)));
        } else if (income > 20200 && income <= 30750) {
            tax = ((0.1 * (10000 - 1000)) + (0.15 * (20200 - 10000)) + (0.2 * (income - 20200)));
        } else if (income > 30750 && income <= 50000) {

        } else if (income > 50000) {
            tax = ((0.1 * (10000 - 1000)) + (0.15 * (20200 - 10000)) + (0.2 * (30750 - 20200)) + (0.25 * (50000 - 30750)) + (
                    0.3 * (income - 50000)));
        }

        return tax;
    }
}

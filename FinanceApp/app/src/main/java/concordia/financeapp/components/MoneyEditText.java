package concordia.financeapp.components;

import android.content.Context;
import android.util.AttributeSet;

import com.blackcat.currencyedittext.CurrencyEditText;

/**
 * Classe que representa um valor monetário
 * Created by erico on 02/07/16.
 */
public class MoneyEditText extends CurrencyEditText {

    public MoneyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Double getValue() {
        return getRawValue() / (double) getCentsMaxValue();
    }

    public void setValue(Double value) {
        if (value == null) {
            value = 0d;
        }
        String formattedValue = formatCurrency(Long.toString(
                (long) (value * getCentsMaxValue())));
        setText(formattedValue);
    }

    public int getCentsMaxValue() {
        return (int) Math.pow(10, getCurrency().getDefaultFractionDigits());
    }
}

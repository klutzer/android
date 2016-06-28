package concordia.financeapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import concordia.financeapp.business.Conta;

public class ContaSpinnerAdapter extends ArrayAdapter<Conta> {

    public ContaSpinnerAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_item);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}

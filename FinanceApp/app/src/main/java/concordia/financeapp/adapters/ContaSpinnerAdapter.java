package concordia.financeapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import concordia.financeapp.business.Conta;

public class ContaSpinnerAdapter extends ArrayAdapter<Conta> {

    public ContaSpinnerAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_item);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void addDefaultObject() {
        Conta conta = new Conta("Criar nova conta...");
        insert(conta, getCount());
    }

    public void setData(List<Conta> contas) {
        clear();
        addAll(contas);
        addDefaultObject();
    }

}

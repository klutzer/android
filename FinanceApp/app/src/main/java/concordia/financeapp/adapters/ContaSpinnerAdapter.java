package concordia.financeapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import concordia.financeapp.R;
import concordia.financeapp.business.Conta;

public class ContaSpinnerAdapter extends ArrayAdapter<Conta> {

    public ContaSpinnerAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_item);
        setDropDownViewResource(R.layout.conta_spinner_item);

    }

    public void addDefaultObject() {
        Conta conta = new Conta("(+) Criar nova conta");
        insert(conta, getCount());
    }

    public void setData(List<Conta> contas) {
        clear();
        addAll(contas);
        addDefaultObject();
    }

    @Override
    public void clear() {
        super.clear();
        Conta c = new Conta();
        c.setNome("Selecione uma conta...");
        c.setId(-1L);
        add(c);
    }

    @Override
    public boolean isEnabled(int position) {
        return position > 0;
    }

    @Override
    public View getDropDownView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.conta_spinner_item, parent, false);
        ImageView img = (ImageView) view.findViewById(R.id.conta_spinner_item_btnExcluir);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getItem(position).getNome());
        configureView(textView, position);

        if (isEnabled(position) && position != getCount()-1) {
            img.setVisibility(View.VISIBLE);
        }else {
            img.setVisibility(View.GONE);
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Conta contaSelecionada = getItem(position);
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setMessage("Deseja remover a conta "+contaSelecionada.getNome()+"?")
                        .setTitle("Excluir")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (contaSelecionada.delete()) {
                                    remove(contaSelecionada);
                                }
                            }
                        })
                        .setNegativeButton("Não", null)
                        .create();
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        configureView(view, position);
        return view;
    }

    private void configureView(TextView view, int position) {
        if (!isEnabled(position)) {
            view.setTextColor(Color.GRAY);
        }else {
            view.setTextColor(Color.BLACK);
        }
    }
}

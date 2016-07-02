package concordia.financeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import concordia.financeapp.business.Conta;
import concordia.financeapp.components.MoneyEditText;

/**
 * Referências: https://developer.android.com/guide/topics/ui/dialogs.html?hl=pt-br
 * Created by erico on 01/07/16.
 */
public class NovaContaDialog extends DialogFragment {

    private NovaContaDialogListener listener;
    private EditText edtDescricao;
    private MoneyEditText edtSaldoInicial;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_criar_conta, null);

        edtDescricao = (EditText) dialogView.findViewById(R.id.dialog_criar_conta_edtNome);
        edtSaldoInicial = (MoneyEditText) dialogView.findViewById(R.id.dialog_criar_conta_edtSaldoInicial);

        return new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setMessage("Criar nova conta")
                .setView(dialogView)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        criarConta();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onFechamentoDoDialogo();
                    }
                })
                .create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (NovaContaDialogListener) activity;
    }

    public void criarConta() {
        Conta conta = new Conta();
        conta.setNome(edtDescricao.getText().toString());
        conta.setSaldoInicial(edtSaldoInicial.getValue());
        conta.save();
        listener.onContaCriada(conta);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        listener.onFechamentoDoDialogo();
    }

    public interface NovaContaDialogListener {
        void onContaCriada(Conta conta);
        void onFechamentoDoDialogo();
    }
}

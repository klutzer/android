package concordia.financeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import concordia.financeapp.adapters.ContaSpinnerAdapter;
import concordia.financeapp.business.Conta;

public class MainActivity extends AppCompatActivity implements NovaContaDialog.NovaContaDialogListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private CurrencyEditText edtNumber;
    private Spinner spConta;
    private ContaSpinnerAdapter contaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Aplicação Financeira");
        setSupportActionBar(toolbar);

        edtNumber = (CurrencyEditText) findViewById(R.id.edtNumber);
        spConta = (Spinner) findViewById(R.id.spConta);
        contaAdapter = new ContaSpinnerAdapter(this);
        spConta.setAdapter(contaAdapter);
        spConta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecionaConta(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        carregarContas(null);
    }

    private void selecionaConta(int position) {
        if (spConta.getSelectedItemPosition() != position) {
            spConta.setSelection(position);
            return;
        }
        Conta contaSelecionada = contaAdapter.getItem(position);
        if (contaSelecionada.getId() == null) {
            criarDialogoCadastrarConta();
        }else {
            edtNumber.setValue(contaSelecionada.getSaldoInicial());
        }
    }

    private void carregarContas(final Conta selected) {
        List<Conta> contas = Conta.listAll(Conta.class, "nome");
        if (contas.isEmpty()) {
            Conta carteira = new Conta("Carteira");
            carteira.save();
            contas.add(carteira);
        }
        contaAdapter.setData(contas);
        if (selected == null) {
            selecionaConta(0);
        }else {
            int position = contaAdapter.getPosition(selected);
            selecionaConta(position);
        }
    }

    private void criarDialogoCadastrarConta() {
        NovaContaDialog contaDialog = new NovaContaDialog();
        contaDialog.show(getFragmentManager(), "ContaDialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings: {
                double valor = edtNumber.getRawValue()/100d;
                Toast.makeText(this, "Preferências selecionada "+
                        valor+" - "+edtNumber.getText(), Toast.LENGTH_SHORT).show();
                return true;
            }
            default: return false;
        }
    }

    @Override
    public void onContaCriada(Conta conta) {
        Toast.makeText(this, "Conta "+conta.getNome()+" criada!", Toast.LENGTH_SHORT).show();
        carregarContas(conta);
    }

    @Override
    public void onFechamentoDoDialogo() {
        Toast.makeText(this, "Operação cancelada", Toast.LENGTH_SHORT).show();
        carregarContas(null);
    }
}

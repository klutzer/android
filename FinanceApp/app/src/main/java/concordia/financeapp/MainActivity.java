package concordia.financeapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import concordia.financeapp.adapters.ContaSpinnerAdapter;
import concordia.financeapp.business.Conta;

public class MainActivity extends AppCompatActivity implements NovaContaDialog.NovaContaDialogListener {

    private Toolbar toolbar;
    private CurrencyEditText edtNumber;
    private MaterialSearchView searchView;
    private Spinner spConta;
    private ContaSpinnerAdapter contaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtNumber = (CurrencyEditText) findViewById(R.id.edtNumber);
        edtNumber.setEnabled(false);
        toolbar.setTitle("Aplicação Financeira");
        setSupportActionBar(toolbar);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        spConta = (Spinner) findViewById(R.id.spConta);
        contaAdapter = new ContaSpinnerAdapter(this);
        spConta.setAdapter(contaAdapter);
        spConta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Conta contaSelecionada = contaAdapter.getItem(position);
                if (contaSelecionada.getId() == null) {
                    criarDialogoCadastrarConta();
                }else {
                    if (contaSelecionada.getSaldoInicial() == null || contaSelecionada.getSaldoInicial() == 0) {
                        edtNumber.setText("0");
                    }else {
                        String formattedValue = edtNumber.formatCurrency(Long.toString(
                                (long) (contaSelecionada.getSaldoInicial() * 100)));
                        edtNumber.setText(formattedValue);
                    }
                    Toast.makeText(MainActivity.this, "Conta " + contaSelecionada + " selecionada!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        carregarContas();
    }

    private void carregarContas() {
        List<Conta> contas = Conta.listAll(Conta.class);
        if (contas.isEmpty()) {
            Conta carteira = new Conta("Carteira");
            carteira.save();
            contas.add(carteira);
        }
        contaAdapter.setData(contas);
        spConta.setSelection(0);
    }

    private void criarDialogoCadastrarConta() {
        NovaContaDialog contaDialog = new NovaContaDialog();
        contaDialog.show(getFragmentManager(), "ContaDialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);

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
            case R.id.menu_search: {

                return true;
            }
            default: return false;
        }
    }

    @Override
    public void onContaCriada(Conta conta) {
        Toast.makeText(this, "Conta "+conta.getNome()+" criada!", Toast.LENGTH_SHORT).show();
        carregarContas();
    }

    @Override
    public void onFechamentoDoDialogo() {
        carregarContas();
    }
}

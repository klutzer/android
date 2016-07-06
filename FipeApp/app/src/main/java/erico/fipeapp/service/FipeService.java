package erico.fipeapp.service;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import erico.fipeapp.business.Marca;

/**
 * Classe responsável por fazer a busca de registros no
 * web service da tabela fipe
 */
public class FipeService {

    private static final String TAG = "FIPE";
    private static final String BASE_URL = "http://fipeapi.appspot.com/api/1/";

    public static List<Marca> getMarcas() {
        List<Marca> marcas = new LinkedList<>();
        marcas.add(new Marca(1, "Fiat"));
        marcas.add(new Marca(2, "BMW"));
        marcas.add(new Marca(198, "Rolls Royce"));
        marcas.add(new Marca(3, "Chevrolet"));
        marcas.add(new Marca(4, "Ford"));
        marcas.add(new Marca(49, "Audi"));
        return marcas;
    }

    public void getMarcas(final ServiceCallback<List<Marca>> callback) {
        new AsyncTask<Void, Void, List<Marca>>() {

            @Override
            protected List<Marca> doInBackground(Void... params) {
                try {
                    JSONArray result = new JSONArray(getStringFromUrl(BASE_URL + "carros/marcas.json"));
                    JSONObject obj;
                    List<Marca> marcas = new ArrayList<Marca>(result.length());
                    for (int i=0; i<result.length(); i++) {
                        obj = result.getJSONObject(i);
                        marcas.add(new Marca(obj.getInt("id"), obj.getString("name")));
                    }
                    return marcas;
                }catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Marca> marcas) {
                callback.onSuccess(marcas);
            }
        }.execute();
    }

    private String getStringFromUrl(String urlStr) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");

        StringBuilder result = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream(), Charset.forName("utf-8")));
        try {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }finally {
            if (rd != null) {
                rd.close();
            }
        }
        return result.toString();
    }
}

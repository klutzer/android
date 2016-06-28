package erico.financeapp.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import erico.financeapp.business.Marca;

/**
 * Classe responsável por fazer a busca de registros no
 * web service da tabela fipe
 */
public class FipeService {

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

    private String getStringFromUrl(String urlStr) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();


        return null;
    }
}

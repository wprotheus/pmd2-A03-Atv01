package com.wprotheus.pmd2a03atv01.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wprotheus.pmd2a03atv01.R;
import com.wprotheus.pmd2a03atv01.model.Estudante;
import com.wprotheus.pmd2a03atv01.model.Nota;
import com.wprotheus.pmd2a03atv01.util.AdapterList;
import com.wprotheus.pmd2a03atv01.util.Auxiliar;
import com.wprotheus.pmd2a03atv01.util.Conexao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private final String URL = "https://my-json-server.typicode.com/wprotheus/pmd2-A03-Atv01/estudante";
    private ListView lvDados;
    private AdapterList adapterList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDados = findViewById(R.id.lvDados);
        executarApp();
    }

    private void executarApp() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        service.execute(() -> {
            try {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterHTTP(URL);
                Auxiliar auxiliar = new Auxiliar();
                String textoFromURL = auxiliar.conversor(inputStream);

                if (!textoFromURL.isEmpty()) {
                    JSONArray jsonArray = new JSONArray(textoFromURL);
                    List<Estudante> estudantes = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject estudanteJson = jsonArray.getJSONObject(i);
                        String nome = estudanteJson.getString("nome");
                        int anoNascimento = estudanteJson.getInt("ano_nascimento");
                        JSONObject notasJson = estudanteJson.getJSONObject("notas");
                        int n1 = notasJson.getInt("n1");
                        int n2 = notasJson.getInt("n2");
                        int n3 = notasJson.getInt("n3");

                        Nota notas = new Nota(n1, n2, n3);
                        Estudante estudante = new Estudante(nome, anoNascimento, notas);
                        estudantes.add(estudante);
                        handler.post(() -> {
                            adapterList = new AdapterList(this, estudantes);
                            lvDados.setAdapter(adapterList);
                        });
                    }
                } else {
                    handler.post(() -> Toast.makeText(getApplicationContext(),
                            "Não foi possível obter os dados.", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> Toast.makeText(getApplicationContext(),
                        "URL inválida.", Toast.LENGTH_LONG).show());
            }
        });
    }
}
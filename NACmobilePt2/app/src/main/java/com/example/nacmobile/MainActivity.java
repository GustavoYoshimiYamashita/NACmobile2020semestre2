package com.example.nacmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private String fileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtndo o caminho de gravacao dos arquivos
        this.fileName = getApplicationContext().getFilesDir().getPath().toString() + "/fiap.txt";


        //Chamada dos elementos de tela em objetos
        final EditText contentBox = findViewById(R.id.contextBox);
        Button btLimpar = findViewById(R.id.btLimpar);
        Button btSalvar = findViewById(R.id.btSalvar);
        Button btRecuperar = findViewById(R.id.btRecuperar);

        //Listeners de tocar no botao para disparar eventos
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentBox.setText("");
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = contentBox.getText().toString();

                MainActivity.this.gravaDadosArquivo(fileName, content);
            }
        });

        btRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = recuperarDadosArquivo(fileName);
                contentBox.setText(content);
            }
        });
    }

    public void gravaDadosArquivo(String fileName,String data ){
        try{
            OutputStreamWriter bufferSaida = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
            bufferSaida.write(data);
            bufferSaida.close();
        }
        catch (FileNotFoundException e){
            Toast.makeText(this, "Falha na abertura do arquivo", Toast.LENGTH_SHORT).show();
        }
        catch (UnsupportedEncodingException e){
            Toast.makeText(this, "Falha no codificador", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this, "falha na escrita", Toast.LENGTH_SHORT).show();
        }
    }

    public String recuperarDadosArquivo(String fileName){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

            StringBuilder sb = new StringBuilder();

            String linha = bufferedReader.readLine();

            while(linha != null){
                sb.append(linha);
                sb.append("\n");
                linha = bufferedReader.readLine();
            }

            return sb.toString();

        }
        catch (FileNotFoundException e){
            Toast.makeText(this, "Falha na abertura do arquivo", Toast.LENGTH_SHORT).show();
        }
        catch (UnsupportedEncodingException e){
            Toast.makeText(this, "Falha no codificador", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this, "falha na leitura", Toast.LENGTH_SHORT).show();
        }


        return "";
    }


}
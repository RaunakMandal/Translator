package me.raunakmandal.translator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.raunakmandal.translator.R;
import me.raunakmandal.translator.adapter.SpinnerAdapter;
import me.raunakmandal.translator.api.ApiInterface;
import me.raunakmandal.translator.data.Languages;
import me.raunakmandal.translator.data.Translation;
import me.raunakmandal.translator.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceDrop, targetDrop;
    private EditText sourceText;
    private TextView targetText;
    private Button copyButton;
    private List<Languages> langsList;

    private String source, target, srcEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        try {
            langsList = Global.getList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setupSpinner();
    }

    private void setupSpinner() {
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(MainActivity.this, langsList);

        sourceDrop.setAdapter(spinnerAdapter);
        targetDrop.setAdapter(spinnerAdapter);

        sourceDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Languages lang = (Languages) parent.getItemAtPosition(position);
                source = lang.getLangCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        targetDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Languages lang = (Languages) parent.getItemAtPosition(position);
                target = lang.getLangCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void init() {
        sourceDrop = (Spinner) findViewById(R.id.src_lang);
        targetDrop = (Spinner) findViewById(R.id.target_lang);
        sourceText = (EditText) findViewById(R.id.src);
        srcEdit = sourceText.getText().toString();
        targetText = (TextView) findViewById(R.id.res);
        copyButton = (Button) findViewById(R.id.copy_btn);

        sourceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fetchData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void fetchData(String s) {
        Map<String, String> mp = new HashMap<>();
        mp.put("q", s);
        mp.put("source", source);
        mp.put("target", target);

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(Global.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Translation> call = apiInterface.getTranslation(mp);
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                if (response.body() != null) {
                    targetText.setText(response.body().getTranslated());
                }
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
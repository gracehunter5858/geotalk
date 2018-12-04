package beamteam.geotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class TranslateFragment extends Fragment {

    String API_KEY = "trnsl.1.1.20181129T011924Z.decd2856686aa882.fbeb4537e22c61892dcaa42e7605cc2a310eae51";
    TextView output;
    String sourceLang;
    String targetLang;
    String sourceLetter;
    String targetLetter;
    HashMap<String, String> getLetter;

    public TranslateFragment() {
        // Required empty public constructor
    }
    public static TranslateFragment newInstance() {
        TranslateFragment fragment = new TranslateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /** Refer to https://tech.yandex.com/translate/doc/dg/reference/translate-docpage/ for documentation */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        // Deal with the Spinners
        getLetter = new HashMap<>();
        setLanguages();

        final Spinner sourceSpinner = (Spinner) view.findViewById(R.id.fromlangs);
        final Spinner targetSpinner = (Spinner) view.findViewById(R.id.tolangs);

        sourceSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceLang = sourceSpinner.getSelectedItem().toString();
                sourceLetter = getLetter.get(sourceLang);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        targetSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                targetLang = targetSpinner.getSelectedItem().toString();
                targetLetter = getLetter.get(targetLang);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Get button to switch languages
        Button switchButton = (Button) view.findViewById(R.id.switchButton);

        switchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempSource = sourceLang;
                sourceSpinner.setSelection(getIndex(sourceSpinner, targetLang));
                targetSpinner.setSelection(getIndex(targetSpinner, tempSource));
            }
        });

        // Get text input and output views
        TextView translateView = (TextView) view.findViewById(R.id.output);
        final EditText input = (EditText) view.findViewById(R.id.input);

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String phrase = input.getText().toString();
                    translate(phrase, sourceLetter, targetLetter);
                    return true;
                }
                return false;
            }
        });

        output = translateView;


        return view;
    }

    private int getIndex(Spinner spinner, String lang){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(lang)){
                index = i;
            }
        }
        return index;
    }

    void setLanguages() {
        getLetter.put("English", "en");
        getLetter.put("Spanish", "es");
        getLetter.put("Japanese", "ja");
        getLetter.put("Vietnamese", "vi");
        getLetter.put("Chinese", "zh");
        getLetter.put("Korean", "ko");
        getLetter.put("French", "fr");
        getLetter.put("Italian", "it");
        getLetter.put("Russian", "ru");
    }

    void translate(String phrase, String sourceLang, String targetLang) {
        System.out.println("translating phrase");
        String requestUrl = String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                        "key=%s&text=%s&lang=%s-%s", API_KEY, phrase, sourceLang, targetLang);
        System.out.println(requestUrl);
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("request successful");
                setText(response, output);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("request failed");
            }
        };
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, listener, errorListener);
        queue.add(jsonObjectRequest);
    }
    private void setText(JSONObject response, TextView test) {
        try {
            JSONArray translated = response.getJSONArray("text");
            String text = translated.getString(0);
            test.setText(text);
        } catch (JSONException e) {
            System.out.println("JSON error");
        }
    }

}

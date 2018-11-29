package beamteam.geotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class TranslateFragment extends Fragment {

    String API_KEY = "trnsl.1.1.20181129T011924Z.decd2856686aa882.fbeb4537e22c61892dcaa42e7605cc2a310eae51";
    TextView test;


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

        TextView textView = (TextView) view.findViewById(R.id.translatetest);
        // TODO: Move copy and paste components from activity_translate to fragment_translate
//        Spinner sourceSpinner = (Spinner) view.findViewById(R.id.fromlangs);
//        Spinner targetSpinner = (Spinner) view.findViewById(R.id.tolangs);
//
//        String sourceLang = sourceSpinner.getSelectedItem().toString();
//        String targetlang = targetSpinner.getSelectedItem().toString();
//
        String phrase = textView.getText().toString();
//
        test = textView;

        translate(phrase, "en", "es");

        return view;
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
                setText(response, test);
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

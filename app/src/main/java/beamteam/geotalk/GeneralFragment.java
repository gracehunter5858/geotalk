package beamteam.geotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.SavedPhrase;
import beamteam.geotalk.db.SavedPhraseDAO;

public class GeneralFragment extends Fragment {
    private SavedPhraseDAO savedPhraseDAO;

    public GeneralFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment GeneralFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralFragment newInstance() {
        GeneralFragment fragment = new GeneralFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedPhraseDAO = AppDatabase.getInMemoryDatabase(this.getContext()).getSavedPhraseDAO();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_general, container, false);


        List<String> phrases = savedPhraseDAO.getSourcePhrases(1, 1);
        if (phrases.contains(((TextView) view.findViewById(R.id.bathroom)).getText().toString())) {
            ((CheckBox) view.findViewById(R.id.savestar1)).setChecked(true);
        } else {
            ((CheckBox) view.findViewById(R.id.savestar1)).setChecked(false);
        }
        if (phrases.contains(((TextView) view.findViewById(R.id.terminal)).getText().toString())) {
            ((CheckBox) view.findViewById(R.id.savestar2)).setChecked(true);
        } else {
            ((CheckBox) view.findViewById(R.id.savestar2)).setChecked(false);
        }


        ((CheckBox) view.findViewById(R.id.savestar1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String source = ((TextView) view.findViewById(R.id.bathroom)).getText().toString();
                String target = "¿Dónde está el baño?";
                if (isChecked) {
                    if (!isDuplicate(source)) {
                        savedPhraseDAO.insert(new SavedPhrase(1, 1, 1, source, target));
                    }

                } else {
                    savedPhraseDAO.delete(new SavedPhrase(1, 1, 1, source, target));
                }
            }
        });
        ((CheckBox) view.findViewById(R.id.savestar2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String source = ((TextView) view.findViewById(R.id.terminal)).getText().toString();
                String target = "¿Dónde está la terminal?";
                if (isChecked) {
                    if (!isDuplicate(source)) {
                        savedPhraseDAO.insert(new SavedPhrase(1, 1, 1, source, target));
                    }

                } else {
                    savedPhraseDAO.delete(new SavedPhrase(1, 1, 1, source, target));
                }
            }
        });
        return view;
    }

    public boolean isDuplicate(String phrase) {
        List<String> phrases = savedPhraseDAO.getSourcePhrases(1, 1);
        return phrases.contains(phrase);
    }



}

package beamteam.geotalk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import static android.support.design.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED;

public class BottomNav extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment GeneralFrag;
    Fragment ContextFrag;
    Fragment TranslateFrag;
    Fragment ProfileFrag;
    Fragment SavedFrag;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            String tag = "temp";
            switch (item.getItemId()) {
                case R.id.General:
                    if (GeneralFrag == null) {
                        GeneralFrag = GeneralFragment.newInstance();
                    }
                    tag = "General";
                    selectedFragment = GeneralFrag;
                    break;
                case R.id.Context:
                    if (ContextFrag == null) {
                        ContextFrag = ContextFragment.newInstance();
                    }
                    tag = "Context";
                    selectedFragment = ContextFrag;
                    break;
                case R.id.Translate:
                    if (TranslateFrag == null) {
                        TranslateFrag = TranslateFragment.newInstance();
                    }
                    tag = "Translate";
                    selectedFragment = TranslateFrag;
                    break;
                case R.id.Profile:
                    if (ProfileFrag == null) {
                        ProfileFrag = ProfileFragment.newInstance();
                    }
                    tag = "Profile";
                    selectedFragment = ProfileFrag;
                    break;
                case R.id.Saved:
                    if (SavedFrag == null) {
                        SavedFrag = SavedFragment.newInstance();
                    }
                    tag = "Saved";
                    selectedFragment = SavedFrag;
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_layout, selectedFragment).addToBackStack(tag);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setLabelVisibilityMode(LABEL_VISIBILITY_LABELED);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_layout, GeneralFragment.newInstance());
        transaction.commit();
    }

}

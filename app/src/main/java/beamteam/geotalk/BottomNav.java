package beamteam.geotalk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import beamteam.geotalk.Fragments.ContextFragment;
import beamteam.geotalk.Fragments.GeneralFragment;
import beamteam.geotalk.Fragments.ProfileFragment;
import beamteam.geotalk.Fragments.SavedFragment;
import beamteam.geotalk.Fragments.TranslateFragment;

import static android.support.design.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED;

public class BottomNav extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.General:
                    selectedFragment = GeneralFragment.newInstance();
                    break;
                case R.id.Context:
                    selectedFragment = ContextFragment.newInstance();
                    break;
                case R.id.Translate:
                    selectedFragment = TranslateFragment.newInstance();
                    break;
                case R.id.Profile:
                    selectedFragment = ProfileFragment.newInstance();
                    break;
                case R.id.Saved:
                    selectedFragment = SavedFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_layout, selectedFragment);
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

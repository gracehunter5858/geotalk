package beamteam.geotalk;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView photo = (ImageView) findViewById(R.id.photo);
        Picasso.get()
                .load(R.drawable.no_image)
                .into(photo);

        System.out.println("Photo set");

        Spinner sourceSpinner = (Spinner) findViewById(R.id.sourcelangs);
        Spinner targetSpinner = (Spinner) findViewById(R.id.targetlangs);
        ArrayAdapter<CharSequence> sourceAdapter = ArrayAdapter.createFromResource(this,
                R.array.fromlanguages, R.layout.spinner_item);
        ArrayAdapter<CharSequence> targetAdapter = ArrayAdapter.createFromResource(this,
                R.array.tolanguages, R.layout.spinner_item);

        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        targetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(sourceAdapter);
        targetSpinner.setAdapter(targetAdapter);
    }

}

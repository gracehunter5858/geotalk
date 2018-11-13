package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Language {

    @PrimaryKey
    @NonNull
    public String name;

    public Language(String name) {
        this.name = name;
    }

}


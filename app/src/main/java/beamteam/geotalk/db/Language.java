package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Language {

    @PrimaryKey
    @NonNull
    private String name;

    public Language(String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

}


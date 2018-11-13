package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity
public class LocationCategory {

    @PrimaryKey
    @NonNull
    private String name;

    public LocationCategory(String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

}

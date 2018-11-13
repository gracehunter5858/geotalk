package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Location {

    @PrimaryKey
    @NonNull
    public String name;

    public Location(String name) {
        this.name = name;
    }

}

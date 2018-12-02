package beamteam.geotalk;


import android.arch.persistence.room.ColumnInfo;

public class PhraseTuple {
    @ColumnInfo(name = "source")
    public String source;

    @ColumnInfo(name = "target")
    public String target;
}
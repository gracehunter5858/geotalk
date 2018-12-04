package beamteam.geotalk;

import android.arch.persistence.room.ColumnInfo;

public class CategoryTuple {
    @ColumnInfo(name = "catName")
    public String cat;

    @ColumnInfo(name = "subCatName")
    public String subcat;
}

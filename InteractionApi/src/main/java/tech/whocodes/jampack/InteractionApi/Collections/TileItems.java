package tech.whocodes.jampack.InteractionApi.Collections;

import tech.whocodes.jampack.InteractionApi.Collections.Queries.TileItemQuery;

import java.util.ArrayList;
import java.util.List;

public class TileItems {
    public static List<ETileItem> tileItems = new ArrayList<>();

    public static TileItemQuery search() {
        return new TileItemQuery(tileItems);
    }
}

package com.sun.hotspot.igv.data.services;

import com.sun.hotspot.igv.data.Group;
import com.sun.hotspot.igv.data.Pair;
import java.util.List;

public interface GroupOrganizer {

    public String getName();

    public List<Pair<String, List<Group>>> organize(List<String> subFolders, List<Group> groups);
}

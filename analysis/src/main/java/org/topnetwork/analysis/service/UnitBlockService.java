package org.topnetwork.analysis.service;

import org.topnetwork.grpclib.pojo.stream.UnitsBlockMap;

import java.util.HashMap;

public interface UnitBlockService {
    void saveUnitBlock(HashMap<String, UnitsBlockMap> units);
}

package org.topnetwork.analysis.service;

import org.topnetwork.grpclib.pojo.stream.ReturnValue;

public interface TableBlockService {
    void queryTxByHash(ReturnValue message);
}

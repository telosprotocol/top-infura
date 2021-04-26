package org.topnetwork.analysis.service;

import org.topnetwork.grpclib.pojo.stream.TableBlockResult;

public interface TableBlockService {
    void queryTxByHash(TableBlockResult message);
}

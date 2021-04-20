package org.topnetwork.analysis.service;

import org.topnetwork.grpclib.pojo.stream.TableBlockResp;

public interface TableBlockService {
    void queryTxByHash(TableBlockResp message);
}

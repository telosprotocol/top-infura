package org.topnetwork.analysis.service;

import org.topnetwork.grpclib.pojo.account.AccountValue;
import org.topnetwork.grpclib.pojo.transaction.TransactionResult;

import java.util.HashMap;

public interface TransactionService {
    Boolean saveTxToDb(TransactionResult transactionResult, HashMap<String, AccountValue> shardIds, String tx_hash);

    void syncFormChain(String txHash);
}

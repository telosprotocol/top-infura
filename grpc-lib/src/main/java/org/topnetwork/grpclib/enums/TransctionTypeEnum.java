package org.topnetwork.grpclib.enums;

public enum TransctionTypeEnum {
	
	create_user_account,
    create_contract_account,
    run_contract,
    transfer,
    alias_name,
    set_account_keys,
    lock_token,
    unlock_token,
    create_sub_account,
    vote,
    abolish_vote,
    pledge_token_tgas,//暂不实现
    redeem_token_tgas,//暂不实现
    pledge_token_disk,//暂不实现
    redeem_token_disk,//暂不实现
    pledge_token_vote,//暂不实现
    redeem_token_vote;//暂不实现

}

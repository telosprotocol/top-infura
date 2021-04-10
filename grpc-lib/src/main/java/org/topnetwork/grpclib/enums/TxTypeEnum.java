package org.topnetwork.grpclib.enums;

import java.util.HashMap;

public enum TxTypeEnum {
    create_user_account(0,"create_user_account"),
    create_contract_account(1,"create_contract_account"),
    run_contract(3,"run_contract"),
    transfer(4,"transfer"),
    alias_name(6,"alias_name"),
    set_account_keys(11,"set_account_keys"),
    lock_token(12,"lock_token"),
    unlock_token(13,"unlock_token"),
    create_sub_account(16,"create_sub_account"),
    vote(20,"vote"),
    abolish_vote(21,"abolish_vote"),
    pledge_token_tgas(22,"pledge_token_tgas"),
    redeem_token_tgas(23,"redeem_token_tgas"),
    pledge_token_disk(24,"pledge_token_disk"),
    redeem_token_disk(25,"redeem_token_disk"),
    pledge_token_vote(27,"pledge_token_vote"),
    redeem_token_vote(28,"redeem_token_vote");
    private int type;
    private String des;


    private TxTypeEnum(int type, String des) {
        this.type=type;
        this.des=des;
    }

    public int getType() {
        return type;
    }

    public String getDes() {
        return des;
    }

    private static HashMap<Integer, TxTypeEnum> map = new HashMap<>();

    static {
        for (TxTypeEnum txTypeEnum : TxTypeEnum.values()) {
            map.put(txTypeEnum.type,txTypeEnum);
        }
    }

    public static TxTypeEnum parse(Integer type){
        if (map.containsKey(type)){
            return map.get(type);
        }
        return null;
    }


}

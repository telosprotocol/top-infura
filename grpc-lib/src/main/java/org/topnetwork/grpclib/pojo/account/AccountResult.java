package org.topnetwork.grpclib.pojo.account;

/**
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AccountResult {

    private AccountValue value;

    public void setValue(AccountValue value) {
        this.value = value;
    }

    public AccountValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AccountResult{" +
                "value=" + value +
                '}';
    }
}
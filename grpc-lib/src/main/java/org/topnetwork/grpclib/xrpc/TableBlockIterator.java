package org.topnetwork.grpclib.xrpc;

import com.alibaba.fastjson.JSON;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;

import java.util.Iterator;

/**
 * @author CasonCai
 * @since 2021/4/21 上午10:31
 **/
public class TableBlockIterator implements Iterator<TableBlockResult> {
    Iterator<xrpc_reply> xrpc_replyIterator = null;

    public TableBlockIterator(Iterator<xrpc_reply> xrpc_replyIterator) {
        this.xrpc_replyIterator = xrpc_replyIterator;
    }

    @Override
    public boolean hasNext() {
        return xrpc_replyIterator.hasNext();
    }

    @Override
    public TableBlockResult next() {
        xrpc_reply reply = xrpc_replyIterator.next();
        if(reply == null){
            return null;
        }

        String body = reply.getBody();
        return JSON.parseObject(body, TableBlockResult.class);
    }
}

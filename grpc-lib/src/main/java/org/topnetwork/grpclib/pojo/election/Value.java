package org.topnetwork.grpclib.pojo.election;

import java.math.BigInteger;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Value {

    private Body body;
    private String hash;
    private Header header;
    private BigInteger height;
    private String owner;
    private String prev_hash;
    private long timestamp;
    public void setBody(Body body) {
         this.body = body;
     }
     public Body getBody() {
         return body;
     }

    public void setHash(String hash) {
         this.hash = hash;
     }
     public String getHash() {
         return hash;
     }

    public void setHeader(Header header) {
         this.header = header;
     }
     public Header getHeader() {
         return header;
     }

    public void setHeight(BigInteger height) {
         this.height = height;
     }
     public BigInteger getHeight() {
         return height;
     }

    public void setOwner(String owner) {
         this.owner = owner;
     }
     public String getOwner() {
         return owner;
     }

    public void setPrev_hash(String prev_hash) {
         this.prev_hash = prev_hash;
     }
     public String getPrev_hash() {
         return prev_hash;
     }

    public void setTimestamp(long timestamp) {
         this.timestamp = timestamp;
     }
     public long getTimestamp() {
         return timestamp;
     }

}
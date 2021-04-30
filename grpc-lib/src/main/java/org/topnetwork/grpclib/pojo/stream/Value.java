package org.topnetwork.grpclib.pojo.stream;

import java.io.Serializable;

/**
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Value implements Serializable {

    private Body body;
    private int cluster_id;
    private String hash;
    private Header header;
    private Long height;
    private String owner;
    private String prev_hash;
    private int shard_id;
    private int table_id;
    private long timestamp;
    private int zone_id;
    public void setBody(Body body) {
         this.body = body;
     }
     public Body getBody() {
         return body;
     }

    public void setCluster_id(int cluster_id) {
         this.cluster_id = cluster_id;
     }
     public int getCluster_id() {
         return cluster_id;
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

    public void setHeight(Long height) {
         this.height = height;
     }
     public Long getHeight() {
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

    public void setShard_id(int shard_id) {
         this.shard_id = shard_id;
     }
     public int getShard_id() {
         return shard_id;
     }

    public void setTable_id(int table_id) {
         this.table_id = table_id;
     }
     public int getTable_id() {
         return table_id;
     }

    public void setTimestamp(long timestamp) {
         this.timestamp = timestamp;
     }
     public long getTimestamp() {
         return timestamp;
     }

    public void setZone_id(int zone_id) {
         this.zone_id = zone_id;
     }
     public int getZone_id() {
         return zone_id;
     }

    @Override
    public String toString() {
        return "Value{" +
                "body=" + body +
                ", cluster_id=" + cluster_id +
                ", hash='" + hash + '\'' +
                ", header=" + header +
                ", height=" + height +
                ", owner='" + owner + '\'' +
                ", prev_hash='" + prev_hash + '\'' +
                ", shard_id=" + shard_id +
                ", table_id=" + table_id +
                ", timestamp=" + timestamp +
                ", zone_id=" + zone_id +
                '}';
    }
}
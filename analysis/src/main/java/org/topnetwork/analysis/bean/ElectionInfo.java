package org.topnetwork.analysis.bean;

import java.math.BigInteger;

public class ElectionInfo {

    private Integer edge;

    private Integer archive;

    private Integer root_beacon;

    private Integer sub_beacon;

    private Integer auditor;

    private Integer validator;

    private BigInteger edgeHeight;

    private BigInteger archiveHeight;

    private BigInteger root_beaconHeight;

    private BigInteger sub_beaconHeight;

    private BigInteger auditorHeight;

    private BigInteger validatorHeight;

    public Integer getEdge() {
        return edge;
    }

    public void setEdge(Integer edge) {
        this.edge = edge;
    }

    public Integer getArchive() {
        return archive;
    }

    public void setArchive(Integer archive) {
        this.archive = archive;
    }

    public Integer getRoot_beacon() {
        return root_beacon;
    }

    public void setRoot_beacon(Integer root_beacon) {
        this.root_beacon = root_beacon;
    }

    public Integer getSub_beacon() {
        return sub_beacon;
    }

    public void setSub_beacon(Integer sub_beacon) {
        this.sub_beacon = sub_beacon;
    }

    public Integer getAuditor() {
        return auditor;
    }

    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    public Integer getValidator() {
        return validator;
    }

    public void setValidator(Integer validator) {
        this.validator = validator;
    }

    public BigInteger getEdgeHeight() {
        return edgeHeight;
    }

    public void setEdgeHeight(BigInteger edgeHeight) {
        this.edgeHeight = edgeHeight;
    }

    public BigInteger getArchiveHeight() {
        return archiveHeight;
    }

    public void setArchiveHeight(BigInteger archiveHeight) {
        this.archiveHeight = archiveHeight;
    }

    public BigInteger getRoot_beaconHeight() {
        return root_beaconHeight;
    }

    public void setRoot_beaconHeight(BigInteger root_beaconHeight) {
        this.root_beaconHeight = root_beaconHeight;
    }

    public BigInteger getSub_beaconHeight() {
        return sub_beaconHeight;
    }

    public void setSub_beaconHeight(BigInteger sub_beaconHeight) {
        this.sub_beaconHeight = sub_beaconHeight;
    }

    public BigInteger getAuditorHeight() {
        return auditorHeight;
    }

    public void setAuditorHeight(BigInteger auditorHeight) {
        this.auditorHeight = auditorHeight;
    }

    public BigInteger getValidatorHeight() {
        return validatorHeight;
    }

    public void setValidatorHeight(BigInteger validatorHeight) {
        this.validatorHeight = validatorHeight;
    }

    @Override
    public String toString() {
        return "ElectionInfo{" +
                "edge=" + edge +
                ", archive=" + archive +
                ", root_beacon=" + root_beacon +
                ", sub_beacon=" + sub_beacon +
                ", auditor=" + auditor +
                ", validator=" + validator +
                ", edgeHeight=" + edgeHeight +
                ", archiveHeight=" + archiveHeight +
                ", root_beaconHeight=" + root_beaconHeight +
                ", sub_beaconHeight=" + sub_beaconHeight +
                ", auditorHeight=" + auditorHeight +
                ", validatorHeight=" + validatorHeight +
                '}';
    }
}

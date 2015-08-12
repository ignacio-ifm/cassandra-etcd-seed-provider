package org.zalando.cassandra.locator.etcd;

public abstract class AbstractNode {
    private String key;
    private int modifiedIndex;
    private int createdIndex;

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public int getModifiedIndex() {
        return modifiedIndex;
    }

    public void setModifiedIndex(final int modifiedIndex) {
        this.modifiedIndex = modifiedIndex;
    }

    public int getCreatedIndex() {
        return createdIndex;
    }

    public void setCreatedIndex(final int createdIndex) {
        this.createdIndex = createdIndex;
    }
}

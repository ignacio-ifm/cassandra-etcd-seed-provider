package org.zalando.cassandra.locator.etcd;

public class KeyNode extends AbstractNode {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}

package org.zalando.cassandra.locator.etcd;

import java.util.List;

public class DirNode extends AbstractNode {
    private boolean dir;
    private List<KeyNode> nodes;

    public boolean isDir() {
        return dir;
    }

    public void setDir(final boolean dir) {
        this.dir = dir;
    }

    public List<KeyNode> getNodes() {
        return nodes;
    }

    public void setNodes(final List<KeyNode> nodes) {
        this.nodes = nodes;
    }
}

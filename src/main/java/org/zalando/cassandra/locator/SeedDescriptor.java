package org.zalando.cassandra.locator;

public class SeedDescriptor {
    private String host;
    private String availabilityZone;

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(final String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }
}

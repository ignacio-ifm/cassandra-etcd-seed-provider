package org.zalando.cassandra.locator;

import org.apache.cassandra.config.DatabaseDescriptor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class EtcdSeedProviderTest {

    @Before
    public void setUp() {
        System.setProperty("cassandra.storagedir", System.getProperty("java.io.tmpdir"));
    }

    @Test
    public void testStandardSetup() {
        System.setProperty("cassandra.config", EtcdSeedProvider.class.getResource("/cassandra.yaml").toString());
        final Set<InetAddress> seeds = DatabaseDescriptor.getSeeds();
        assertThat(seeds, is(not(empty())));
    }

    @Test
    public void testStandardSetupWithInvalidUrl() {
        System.setProperty("cassandra.config", EtcdSeedProvider.class.getResource("/cassandra-with-invalid-url.yaml").toString());
        DatabaseDescriptor.getSeeds();
    }

    @Test
    public void testSetupWithoutSpecificHostUsingEnvironment() {
        DatabaseDescriptor.getSeeds();
        System.setProperty("cassandra.config", EtcdSeedProvider.class.getResource("/cassandra-without-etcd-url.yaml").toString());
        final Set<InetAddress> seeds = DatabaseDescriptor.getSeeds();
        assertThat(seeds, is(not(empty())));
    }
}
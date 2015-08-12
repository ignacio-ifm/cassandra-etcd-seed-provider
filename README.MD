# Cassandra Seed Providers

Cassandra gets its list of seed nodes from an implementation of the ``SeedProvider``
interface. The most common implementation is bundled with Cassandra, the 
``SimpleSeedProvider``. The ``SimpleSeedProvider`` reads the list of seed nodes
from the **cassandra.yaml** configuration file.

If you're deploying Cassandra as an immutable stack, changing the list of seed
nodes at runtime can be challenging. If you need to deploy it on your AWS VPC
using only dynamic IP addresses it becomes even more complicated.

If you find yourself in any of these circumstances this project may help you.

# Etcd Seed Provider

The ``EtcdSeedProvider`` addresses those challenges by fetching the 
list of seed nodes from an external Etcd cluster which can be dynamically
updated.

It's up to the process that maintains the list of seed nodes to delete dead
entries and replace them properly by new ones. Such process should store the
entries in a specific format the ``EtcdSeedProvider`` understands.

The seeds nodes keys should be stored under the ``url`` parameter setting. The key
name itself it not relevant and the value should be the IP address or hostname.

## Etcd format

Assuming your cluster configuration root is under
``http://my-etcd-cluster.domain.com/v2/keys/cassandra/my-cluster-name/seeds``
the expected format would be:

    | Key                                                          | Value     |
    |--------------------------------------------------------------|-----------|
    | /v2/keys/cassandra/my-cluster-name/seeds/meaninglesskeyname1 | 127.0.1.1 |
    | /v2/keys/cassandra/my-cluster-name/seeds/meaninglesskeyname2 | 127.0.1.2 |
    | /v2/keys/cassandra/my-cluster-name/seeds/meaninglesskeyname3 | 127.0.1.3 |


The above content on your Etcd would use **127.0.1.1**, **127.0.1.2** and 
**127.0.1.3** as the seed nodes for your cluster.

The key names holding the ip address or hostname for each seed node is effectively
meaningless. The ``EtcdSeedProvider`` just gets the values for all the child nodes under
the ``url`` setting.
    
## Howto

First you have to copy the latest jar file for this library to your cassandra/lib sub-folder to
make sure the ``EtcdSeedProvider`` is available. You can build it yourself or check the Download
section below.

You then have to change the seed provider ``class_name`` configuration and set the 
``url`` parameter in your **cassandra.yaml** configuration file.

For example:

    seed_provider:
        - class_name: org.zalando.cassandra.locator.EtcdSeedProvider
        parameters:
            - url: http://my-etcd-cluster.domain.com/v2/keys/cassandra/my-cluster-name/seeds

## Download

You can download the latest released version from bintray [ ![Download](https://api.bintray.com/packages/lmineiro/maven/cassandra-etcd-seed-provider/images/download.svg) ](https://bintray.com/lmineiro/maven/cassandra-etcd-seed-provider/_latestVersion)

## Known limitations and To Do list

- Only the IP addresses are stored, without any further metadata like AZ


# Microstream CDI extension

The Microstream CDI extension is an open-source project to integrate the Jakarta EE/MicroProfile world with the Microstream persistence solution.

This project has two minimum requirements:

* A [CDI](https://jakarta.ee/specifications/cdi/) 2.0 implementation or higher
* An [Eclipse MicroProfile Config](https://github.com/eclipse/microprofile-config) 2.0 implementation or higher

## Features

### StorageManager

You're enabled to inject the ```StorageManager``` easily using MicroProfile Config to read the properties.

```java
@Inject
private StorageManager manager;
```

The CDI will create an instance application-scoped, and it will close automatically.

### UpdateRoot

Do you want to force um update in the root? But You don't want to put it on your code explicitly? Don't worry; 
we have ```UpdateRoot``` annotation to handle it for us!

```java
@UpdateRoot
public void update(T entity) {
  this.root.add(entity);
}
```

### Storage

The Storage annotation allows injecting an entity from Microstream.

```java
@Storage
public class NameStorage {
    //...
}
```

It will create/load this annotation using CDI.

```java
@Inject
private NameStorage nameStorage;
```

### Cache

You can use Microsctream as a cache as well, thanks to the ```StorageCache``` annotation.

```java
@Inject
@StorageCache
private Cache<String, Integer> counter;
```

You also have the option to inject both ```CachingProvider``` and ```CacheManager``` using CDI.

```java
@Inject
@StorageCache
private CachingProvider provider;

@Inject
@StorageCache
private CacheManager cacheManager;
```

## Eclipse MicroProfile Configuration

The integration allows receiving all information from the Eclipse MicroProfile Config instead of either a programmatic
configuration or a single file.
Thus, you can overwrite any properties following the good practices in the Market, such as [the Twelve-Factor App](https://12factor.net/).

### Cache

The relation with the properties from [Microstream docs](https://docs.microstream.one/manual/cache/configuration/properties.html):

There is a list of properties in the ```CacheProperties``` enum.

The primary purpose of this configuration is to allow you to explore the Configuration of Cache through Eclipse MicroProfile.
     
* ```microstream.cache.loader.factory```: cacheLoaderFactory - A CacheLoader should be configured for "Read Through" caches to load values when a cache miss occurs.
* ```microstream.cache.writer.factory```: cacheWriterFactory - A CacheWriter is used for write-through to an external resource.
* ```microstream.cache.expires.factory```: expiryPolicyFactory - Determines when cache entries will expire based on creation, access and modification operations.
* ```microstream.cache.read.through```: readThrough - When in "read-through" mode, cache misses that occur due to cache entries not existing as a result of performing a "get" will appropriately cause the configured CacheLoader to be invoked.
* ```microstream.cache.write.through```: writeThrough - When in "write-through" mode, cache updates that occur as a result of performing "put" operations will appropriately cause the configured CacheWriter to be invoked. 
* ```microstream.cache.store.value```: storeByValue - When a cache is storeByValue, any mutation to the key or value does not affect the key of value stored in the cache.
* ```microstream.cache.statistics```: statisticsEnabled - Checks whether statistics collection is enabled in this cache. 
* ```microstream.cache.management```: managementEnabled - Checks whether management is enabled on this cache. 

## Microstream

MicroStream Data-Store is a native Java object graph storage engine. From a technical point of view it serves one purpose only:

**To fully or partially persist and restore a Java object graph in the simplest way possible for the user.**

## Links

* [Website](https://microstream.one/)
* [Documentation](https://docs.microstream.one/manual/intro/welcome.html)
* [Videos](https://www.youtube.com/c/MicroStream)



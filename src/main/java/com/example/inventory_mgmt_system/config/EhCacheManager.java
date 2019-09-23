package com.example.inventory_mgmt_system.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EhCacheManager {
    private static final Logger LOG = Logger.getLogger(EhCacheManager.class.getName());

    private static Cache<String, Object> localCache;

    private static CacheManager cacheManager;


    private synchronized static Cache<String, Object> getLocalCache() {
        try {
            if (localCache == null) {
                LOG.log(Level.FINE, "Creating cache manager via XML resource");
                Configuration xmlConfig = new XmlConfiguration(EhCacheManager.class.getResource("/ehcache.xml"));
                cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
                cacheManager.init();
                localCache = cacheManager.getCache("ehCache", String.class, Object.class);
            }
            return localCache;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Exception while initializing ehCache ", ex);
            return null;
        }
    }

    public static void putCache(String key, Object value) {
        try {
            LOG.log(Level.INFO, "ehCache putting key : " + key);
            localCache = getLocalCache();
            if (localCache != null && value != null) localCache.put(key, value);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Exception while putting value to ehCache : " + key, ex);
        }
    }

    public static Object getCache(String key) {
        LOG.log(Level.INFO, "ehCache : in getCache func, key : " + key);
        localCache = getLocalCache();
        if (localCache != null && localCache.containsKey(key)) {
            LOG.log(Level.INFO, "returning key from ehCache : " + localCache.get(key).toString());
            return localCache.get(key);
        } else {
            LOG.log(Level.INFO, "Could not get value from ehCache");
            return null;
        }
    }

    private static void removeALlCache() {
        localCache = getLocalCache();
        if (localCache != null) localCache.clear();
    }

}

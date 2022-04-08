package org.bonn.ooka.buchungssystem.ss2022.caching;

import java.util.List;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class CachingProxy implements Caching {

	private final Caching cachingImp;

	public CachingProxy(Caching cachingImp) {
		this.cachingImp = cachingImp;
	}

	@Override
	public void cacheResult(String key, List<Object> value) {
		if (cachingImp != null)
			cachingImp.cacheResult(key, value);
	}

	@Override
	public List<Object> fetchResult(String key) {
		if (cachingImp != null)
			return cachingImp.fetchResult(key);
		else
			return null;
	}
}

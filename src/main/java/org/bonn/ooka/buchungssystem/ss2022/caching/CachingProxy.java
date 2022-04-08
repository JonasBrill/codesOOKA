package org.bonn.ooka.buchungssystem.ss2022.caching;

import java.util.List;

public class CachingProxy implements Caching {

	private final CachingImp cachingImp;

	public CachingProxy(CachingImp cachingImp) {
		this.cachingImp = cachingImp;
	}

	@Override
	public void cacheResult(String key, List<Object> value) {
		cachingImp.cacheResult(key, value);
	}

	@Override
	public List<Object> fetchResult(String key) {
		return cachingImp.fetchResult(key);
	}
}

package org.bonn.ooka.buchungssystem.ss2022.caching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class CachingImp implements Caching {
	Map<String, List<Object>> values;

	public CachingImp() {
		this.values = new HashMap<>();
	}

	public void cacheResult(String key, List<Object> value) {
		this.values.put(key, value);
	}

	public List<Object> fetchResult(String key) {
		return this.values.getOrDefault(key, null);
	}
}

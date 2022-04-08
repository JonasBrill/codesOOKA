package org.bonn.ooka.buchungssystem.ss2022.caching;

import java.util.List;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public interface Caching {
	void cacheResult(String key, List<Object> value);

	List<Object> fetchResult(String key);
}

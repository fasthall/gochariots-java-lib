package edu.ucsb.gochariots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Record {
	private Map<String, String> tags;
	private List<Long> hash;
	private long seed;

	public Record(long seed) {
		this.tags = new HashMap<String, String>();
		this.hash = new ArrayList<Long>();
		this.seed = seed;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void add(String key, String value) {
		tags.put(key, value);
	}

	public void addHash(long hash) {
		this.hash.add(hash);
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public String toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("tags", tags);
			json.put("seed", seed);
			json.put("prehash", hash);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json.toString();
	}
}

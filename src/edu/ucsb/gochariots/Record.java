package edu.ucsb.gochariots;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Record {
	private Map<String, String> tags;
	private long hash;
	private long seed;

	public Record(long seed) {
		this.tags = new HashMap<String, String>();
		this.seed = seed;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void add(String key, String value) {
		tags.put(key, value);
	}

	public long getHash() {
		return hash;
	}

	public void setHash(long hash) {
		this.hash = hash;
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

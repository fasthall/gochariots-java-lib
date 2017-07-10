package edu.ucsb.gochariots;

public class FNV {

	public static long hash64(String str) {
		long h = 0xcbf29ce484222325L;
		for (int i = 0; i < str.length(); i++) {
			h ^= str.charAt(i);
			h *= 0x100000001b3L;
		}
		return h;
	}
}

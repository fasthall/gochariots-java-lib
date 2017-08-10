package edu.ucsb.gochariots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Gochariots {
	private String host;

	public Gochariots() {

	}

	public Gochariots(String host) {
		setHost(host);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		if (!host.startsWith("http://")) {
			host = "http://" + host;
		}
		if (host.endsWith("/")) {
			host = host.substring(0, host.length() - 1);
		}
		this.host = host;
	}

	public void post(Record record) {
		String body = record.toJSON();
		String url = host + "/record";

		try {
			URL object = new URL(url);
			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("POST");

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(body);
			wr.flush();

			StringBuilder sb = new StringBuilder();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Long> getHash(Record record) {
		ArrayList<Long> result = new ArrayList<Long>();
		for (Map.Entry<String, String> entry : record.getTags().entrySet()) {
			result.add(FNV.hash64(entry.getKey() + ":" + entry.getValue()));
		}
		return result;
	}
}


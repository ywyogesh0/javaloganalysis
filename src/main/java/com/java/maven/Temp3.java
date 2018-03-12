package com.java.maven;

import java.io.Serializable;

public class Temp3 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ipHost;
	private String date;
	private String url;
	private Integer count;

	public Temp3(String ipHost, String date, String url, Integer count) {
		super();
		this.ipHost = ipHost;
		this.date = date;
		this.url = url;
		this.count = count;
	}

	public Temp3() {
	}

	public String getIpHost() {
		return ipHost;
	}

	public void setIpHost(String ipHost) {
		this.ipHost = ipHost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Temp3 [ipHost=" + ipHost + ", date=" + date + ", url=" + url + ", count=" + count + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((ipHost == null) ? 0 : ipHost.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Temp3 other = (Temp3) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (ipHost == null) {
			if (other.ipHost != null)
				return false;
		} else if (!ipHost.equals(other.ipHost))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}

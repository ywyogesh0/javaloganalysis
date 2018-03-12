package com.java.maven;

import java.io.Serializable;

public class ApacheLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uuid;
	private String ipHost;
	private String date;
	private String time;
	private String method;
	private String url;
	private String protocol;
	private String version;
	private Integer code;
	private Integer sixHourSlot;

	public ApacheLog() {
	}

	public ApacheLog(String uuid, String ipHost, String date, String time, String method, String url, String protocol,
			String version, Integer code, Integer sixHourSlot) {
		super();
		this.uuid = uuid;
		this.ipHost = ipHost;
		this.date = date;
		this.time = time;
		this.method = method;
		this.url = url;
		this.protocol = protocol;
		this.version = version;
		this.code = code;
		this.sixHourSlot = sixHourSlot;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getSixHourSlot() {
		return sixHourSlot;
	}

	public void setSixHourSlot(Integer sixHourSlot) {
		this.sixHourSlot = sixHourSlot;
	}

	@Override
	public String toString() {
		return "ApacheLog [uuid=" + uuid + ", ipHost=" + ipHost + ", date=" + date + ", time=" + time + ", method="
				+ method + ", url=" + url + ", protocol=" + protocol + ", version=" + version + ", code=" + code
				+ ", sixHourSlot=" + sixHourSlot + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((ipHost == null) ? 0 : ipHost.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result + ((sixHourSlot == null) ? 0 : sixHourSlot.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		ApacheLog other = (ApacheLog) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (protocol == null) {
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		if (sixHourSlot == null) {
			if (other.sixHourSlot != null)
				return false;
		} else if (!sixHourSlot.equals(other.sixHourSlot))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}

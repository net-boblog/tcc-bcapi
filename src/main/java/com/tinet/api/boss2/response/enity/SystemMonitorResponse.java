package com.tinet.api.boss2.response.enity;

import com.tinet.api.boss2.model.SystemMonitor;


public class SystemMonitorResponse extends CommonResponse {
	private static final long serialVersionUID = 8187188521354899820L;
	private SystemMonitor systemMonitor;

	public SystemMonitor getSystemMonitor() {
		return systemMonitor;
	}
	public void setSystemMonitor(SystemMonitor systemMonitor) {
		this.systemMonitor = systemMonitor;
	}
}

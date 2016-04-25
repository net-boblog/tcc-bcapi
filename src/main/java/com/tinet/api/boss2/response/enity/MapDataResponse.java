package com.tinet.api.boss2.response.enity;

import java.util.Map;


@SuppressWarnings("rawtypes")
public class MapDataResponse extends CommonResponse{
	
	private static final long serialVersionUID = -8886275684232385520L;
	private Map data;

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

}

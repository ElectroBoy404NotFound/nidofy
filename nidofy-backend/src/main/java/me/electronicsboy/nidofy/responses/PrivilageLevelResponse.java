package me.electronicsboy.nidofy.responses;

import me.electronicsboy.nidofy.data.PrivilegeLevel;

public class PrivilageLevelResponse {
    private PrivilegeLevel privilageLevel;

	public PrivilegeLevel getPrivilageLevel() {
		return privilageLevel;
	}

	public PrivilageLevelResponse setPrivilageLevel(PrivilegeLevel privilageLevel) {
		this.privilageLevel = privilageLevel;
		return this;
	}
}
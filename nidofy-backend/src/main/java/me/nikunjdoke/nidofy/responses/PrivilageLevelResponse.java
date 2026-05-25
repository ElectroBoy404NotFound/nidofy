package me.nikunjdoke.nidofy.responses;

import me.nikunjdoke.nidofy.data.PrivilegeLevel;

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
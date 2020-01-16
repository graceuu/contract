package com.uprism.contract.application.value;

import lombok.Getter;
import lombok.Setter;

public class MemberCommand {

	@Getter
	@Setter
	public static class Addition {
		private String id;
		private String name;
		private String password;
	}
}

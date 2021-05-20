package com.revature.K.serialization;

public enum SimpleOperator implements Operator{

	PLUS{
		public int apply(int a, int b) {
			return a + b;
		}
	},
	MINUS {
		public int apply(int a, int b) {
			return a - b;
		}
	}

}

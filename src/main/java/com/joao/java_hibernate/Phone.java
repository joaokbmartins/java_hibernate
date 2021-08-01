package com.joao.java_hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Phone {

	@Column(length = 2)
	private int countryCode;
	@Column(length = 2)
	private int areaCode;
	@Column(length = 5)
	private int phonePrefix;
	@Column(length = 4)
	private int lineNumber;

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getPhonePrefix() {
		return phonePrefix;
	}

	public void setPhonePrefix(int phonePrefix) {
		this.phonePrefix = phonePrefix;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public String toString() {
		return "Phone [countryCode=" + countryCode + ", areaCode=" + areaCode + ", phonePrefix=" + phonePrefix
		    + ", lineNumber=" + lineNumber + "]";
	}

}

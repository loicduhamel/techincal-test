package fr.whitedev.technicaltest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Geo {
		private String lat;
		private String lng;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Address {
		private String street;
		private String suite;
		private String city;
		private String zipcode;
		private Geo geo;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Company {
		private String name;
		private String catchPhrase;
		private String bs;
	}

	private int id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private String phone;
	private String website;
	private Company company;
}

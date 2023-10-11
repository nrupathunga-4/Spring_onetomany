package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="aadhar")
public class AadharCard {
	@Id
	@Size(min=5,max=12,message = "Size is maximun 12 only")
	private String adharnumber;
	
	@OneToOne(mappedBy = "aadharCard")
	private Person person;
	
	
	public String getAdharnumber() {
		return adharnumber;
	}

	public void setAdharnumber(String adharnumber) {
		this.adharnumber = adharnumber;
	}
}

package com.example.airlineTicket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seat_class")
public class SeatClass {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int classId;

	    private String className;

	    private int baggageAllowance;

	    private double priceMultiplier;

		public SeatClass(int classId, String className, int baggageAllowance, double priceMultiplier) {
			super();
			this.classId = classId;
			this.className = className;
			this.baggageAllowance = baggageAllowance;
			this.priceMultiplier = priceMultiplier;
		}

		public SeatClass() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getClassId() {
			return classId;
		}

		public void setClassId(int classId) {
			this.classId = classId;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public int getBaggageAllowance() {
			return baggageAllowance;
		}

		public void setBaggageAllowance(int baggageAllowance) {
			this.baggageAllowance = baggageAllowance;
		}

		public double getPriceMultiplier() {
			return priceMultiplier;
		}

		public void setPriceMultiplier(double priceMultiplier) {
			this.priceMultiplier = priceMultiplier;
		}
	    
	    
}

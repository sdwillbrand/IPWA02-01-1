package model;

import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Named
@ViewScoped
@Entity
public class Emission implements Serializable, Comparable<Emission> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emission_id")
	private Integer id;

	@Column(name = "emission_year", nullable = false)
	private int year;

	@Column(name = "emission_amount", nullable = false)
	private float amount;

	@Column(name = "emission_editable", nullable = false)
	private boolean editable;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "author_id")
	private Credential author;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "country_id")
	private Country country;

	public Emission() {
	}

	public Emission(int year, float amount, boolean editable, Country country, Credential author) {
		this.year = year;
		this.amount = amount;
		this.editable = editable;
		this.country = country;
		this.author = author;
	}

	public Integer getId() {
		return this.id;
	}

	public String getOwner() {
		return this.author.getUsername();
	}

	@Override
	public String toString() {
		return String.format("%d; %f; %b", this.year, this.amount, this.editable);
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public int compareTo(Emission o) {
		return this.year - o.year;
	}
}
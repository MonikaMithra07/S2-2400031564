package com.klef.fsad.exam;
import jakarta.persistence.*;

@Entity
@Table(name="delivery")
public class Delivery {
    @Id
    private int id;
    private String name;
    private String date;
    private String status;
    // ... include your Getters and Setters here ...
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
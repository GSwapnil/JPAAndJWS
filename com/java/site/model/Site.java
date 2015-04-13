package com.java.site.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Site
 *
 */
@Entity
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
public class Site implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String latitude;
	private String longitude;
	private String name;

	//One-to-many association to Tower
	@OneToMany(mappedBy="site")
	private List<Tower> towers;

	public Site() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	
	public Tower addTower(Tower tower) {
		getTowers().add(tower);
		tower.setSite(this);
		return tower;
	}

	public Tower removeTower(Tower tower) {
		getTowers().remove(tower);
		tower.setSite(null);
		return tower;
	}
   
}

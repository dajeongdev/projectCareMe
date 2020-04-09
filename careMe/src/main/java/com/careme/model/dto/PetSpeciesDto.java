package com.careme.model.dto;

public class PetSpeciesDto {
	private int pet_species_idx;
	private int pet_species_level;
	private String pet_species_name;
	private int pet_species_ancestor;
	
	public int getPet_species_idx() {
		return pet_species_idx;
	}
	public void setPet_species_idx(int pet_species_idx) {
		this.pet_species_idx = pet_species_idx;
	}
	public int getPet_species_level() {
		return pet_species_level;
	}
	public void setPet_species_level(int pet_species_level) {
		this.pet_species_level = pet_species_level;
	}
	public String getPet_species_name() {
		return pet_species_name;
	}
	public void setPet_species_name(String pet_species_name) {
		this.pet_species_name = pet_species_name;
	}
	public int getPet_species_ancestor() {
		return pet_species_ancestor;
	}
	public void setPet_species_ancestor(int pet_species_ancestor) {
		this.pet_species_ancestor = pet_species_ancestor;
	}
	
	@Override
	public String toString() {
		return "PetSpeciesDto [pet_species_idx=" + pet_species_idx + ", pet_species_level=" + pet_species_level
				+ ", pet_species_name=" + pet_species_name + ", pet_species_ancestor=" + pet_species_ancestor + "]";
	}
	

}

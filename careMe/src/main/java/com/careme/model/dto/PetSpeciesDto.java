package com.careme.model.dto;

public class PetSpeciesDto {
	private int pet_species_idx;
	private int code_level;
	private String pet_species_name;
	private int pet_species_ancestor;
	
	public int getPet_species_idx() {
		return pet_species_idx;
	}
	public void setPet_species_idx(int pet_species_idx) {
		this.pet_species_idx = pet_species_idx;
	}
	public int getCode_level() {
		return code_level;
	}
	public void setCode_level(int code_level) {
		this.code_level = code_level;
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
		return "PetSpecies [pet_species_idx=" + pet_species_idx + ", code_level=" + code_level + ", pet_species_name="
				+ pet_species_name + ", pet_species_ancestor=" + pet_species_ancestor + "]";
	}

}

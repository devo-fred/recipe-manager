package com.devoteam.demoassignments.recipemanager.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devoteam.demoassignments.recipemanager.enums.RoleType;

@Entity
@Table(name = "roles")
public class Role {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private RoleType name;

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return RoleTypes return the name
     */
    public RoleType getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(RoleType name) {
        this.name = name;
    }

}
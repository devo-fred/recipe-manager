package com.devoteam.demoassignments.recipemanager.repository;

import java.util.Optional;

import com.devoteam.demoassignments.recipemanager.entity.Role;
import com.devoteam.demoassignments.recipemanager.enums.RoleType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleType name);
}
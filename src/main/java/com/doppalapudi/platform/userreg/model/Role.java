package com.doppalapudi.platform.userreg.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@NoArgsConstructor

public class Role {
	
	@Id
    @Column(name = "ROLE_NAME")
    @NotEmpty
    @Getter
    @Setter
    private String roleName;
	
	@ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<User> users;
}

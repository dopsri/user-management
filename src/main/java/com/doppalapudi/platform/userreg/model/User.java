package com.doppalapudi.platform.userreg.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER")
@NoArgsConstructor
public class User {

    /*@Id
    @GeneratedValue
    @Column(name = "USER_ID")
    @Setter(AccessLevel.PROTECTED)
    private Integer id;*/

    @Id
    @Column(name = "USER_NAME")
    @NotEmpty
    @Getter @Setter
    private String username;

    @Column(name = "PASSWORD")
    @Getter @Setter
    private String password;

    @Column(name = "LAST_NAME")
    @NotEmpty
    @Getter @Setter
    private String lastName;

    @Column(name = "FIRST_NAME")
    @NotEmpty
    @Getter @Setter
    private String firstName;

    @Column(name = "EMAIL_ID")
    @Email
    @NotEmpty
    @Getter @Setter
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", 
		joinColumns={
				@JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME") }, 
		inverseJoinColumns = {
				@JoinColumn(name = "ROLE_NAME", referencedColumnName = "ROLE_NAME") })
    @Getter @Setter
    private Set<Role> roles;
}

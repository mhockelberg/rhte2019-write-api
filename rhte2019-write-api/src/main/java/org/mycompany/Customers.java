package org.mycompany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Customer entity
 *
 */
@ApiModel(description = "Represents a customer")
@Entity // makes a table out of this class
public class Customers {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(value = "The ID of the customer", required = true)
    private Integer id;

    @ApiModelProperty(value = "The first name of the customer", required = true)
    private String firstName;

    @ApiModelProperty(value = "The last name of the customer", required = true)
    private String lastName;

    @ApiModelProperty(value = "The email of the customer", required = true)
    private String email;

    
    public Customers() {
    }

    public Customers(Integer id, String firstName, String lastName, String email ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
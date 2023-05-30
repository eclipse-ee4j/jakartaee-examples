/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakartaee.examples.jpa.joinedInheritance;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @Inheritance with Joined strategy
 * 
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Named("animal")
@RequestScoped
public class AnimalBean implements Serializable {

    /**
     * Stores the EJB.
     */    
    @Inject
    private AnimalEjb animalEjb;
    
    private List<Cat> cats;
    private List<Dog> dogs;
    
    /**
     * Saves information of two animals of type cat and dog
     */
    public void saveAndLoad(){
        animalEjb.persist();
        
        cats = animalEjb.findAllCats();
        dogs = animalEjb.findAllDogs();
    }

    public List<Cat> getCats() {
        return cats;
    }

    public List<Dog> getDogs() {
        return dogs;
    }
    
}

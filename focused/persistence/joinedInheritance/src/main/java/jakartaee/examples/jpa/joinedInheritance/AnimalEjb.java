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

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * A simple EJB that persist animal data in database
 * 
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Stateless
public class AnimalEjb {

    /**
     * Stores the persistence context.
     */        
    @PersistenceContext(unitName = "inheritancePU")
    private EntityManager em;
    
    /**
     * Persist information of a cat and a dog in database     
     */
    public void persist(){                       

        Cat cat = new Cat(10);
        cat.setName("A Cat");
        Dog dog = new Dog(20);        
        dog.setName("A Dog");
        
        em.persist(cat);
        em.persist(dog);
        
    }
    
    public List<Cat> findAllCats(){
        return em.createQuery("FROM Cat c").getResultList();
    }

    public List<Dog> findAllDogs() {
        return em.createQuery("FROM Dog r").getResultList();
    }
    
}

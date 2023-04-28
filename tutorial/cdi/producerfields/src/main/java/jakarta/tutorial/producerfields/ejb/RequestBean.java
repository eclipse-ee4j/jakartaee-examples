/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.producerfields.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.tutorial.producerfields.db.UserDatabase;
import jakarta.tutorial.producerfields.entity.ToDo;

@ConversationScoped
@Stateful
public class RequestBean {

    @Inject
    @UserDatabase
    EntityManager em;

    public ToDo createToDo(String inputString) {
        ToDo toDo;
        Date currentTime = Calendar.getInstance().getTime();

        try {
            toDo = new ToDo();
            toDo.setTaskText(inputString);
            toDo.setTimeCreated(currentTime);
            em.persist(toDo);
            return toDo;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<ToDo> getToDos() {
        try {
            List<ToDo> toDos =
                    (List<ToDo>) em.createQuery(
                    "SELECT t FROM ToDo t ORDER BY t.timeCreated")
                    .getResultList();
            return toDos;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}

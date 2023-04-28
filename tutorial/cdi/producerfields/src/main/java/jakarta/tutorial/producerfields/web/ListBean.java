/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.producerfields.web;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Named;
import jakarta.tutorial.producerfields.ejb.RequestBean;
import jakarta.tutorial.producerfields.entity.ToDo;
import jakarta.validation.constraints.NotNull;

@Named
@ConversationScoped
public class ListBean implements Serializable {
    
    private static final long serialVersionUID = 8751711591138727525L;
    
    @EJB
    private RequestBean request;
    @NotNull
    private String inputString;
    private ToDo toDo;
    private List<ToDo> toDos;

    public void createTask() {
        this.toDo = request.createToDo(inputString);
    }
    
    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }

    public List<ToDo> getToDos() {
        return request.getToDos();
    }

    public void setToDos(List<ToDo> toDos) {
        this.toDos = toDos;
    }
}

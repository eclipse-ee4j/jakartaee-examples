/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package jakarta.tutorial.dukestutoring.web.util;

import java.util.HashMap;
import java.util.Map;

import jakarta.faces.context.FacesContext;

/**
 *
 * @author ievans
 */
public class EntityConverter {

    private static final String key = "dukestutoring.web.util.EntityConverter";

    public EntityConverter() {
    }

    protected Map<String, Object> getViewMap(FacesContext context) {
        Map<String, Object> viewMap = context.getViewRoot().getViewMap();
        @SuppressWarnings({"unchecked", "rawtypes"})
        Map<String, Object> idMap = (Map) viewMap.get(key);
        if (idMap == null) {
            idMap = new HashMap<>();
            viewMap.put(key, idMap);
        }
        return idMap;
    }
}

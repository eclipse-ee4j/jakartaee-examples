/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukesbookstore.listeners;

import jakarta.faces.event.ActionEvent;
import jakarta.tutorial.dukesbookstore.components.AreaComponent;
import jakarta.tutorial.dukesbookstore.components.MapComponent;

/**
 * <p>An {@link ActionEvent} indicating that the specified {@link AreaComponent}
 * has just become the currently selected hotspot within the source
 * {@link MapComponent}.</p>
 */
public class AreaSelectedEvent extends ActionEvent {

    private static final long serialVersionUID = 5030325260451465110L;

    /**
     * <p>Construct a new {@link AreaSelectedEvent} from the specified source
     * map.</p>
     *
     * @param map The {@link MapComponent} originating this event
     */
    public AreaSelectedEvent(MapComponent map) {
        super(map);
    }

    /**
     * @return the {@link MapComponent} of the map for which an area was
     * selected.
     */
    public MapComponent getMapComponent() {
        return ((MapComponent) getComponent());
    }
}

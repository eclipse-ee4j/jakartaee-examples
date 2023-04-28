/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukesbookstore.model;

import java.io.Serializable;

/**
 * <p>{@link ImageArea} is a JavaBeans component that represents a hotspot in an
 * image map. Within a particular image map, no two hotspots may have the same
 * alternate text, because this is treated as a key.</p>
 */
public final class ImageArea implements Serializable {

    private static final long serialVersionUID = -2382340811618738146L;
    private String alt = null;
    private String coords = null;
    private String shape = null;

    /**
     * <p>Construct an uninitialized {@link ImageArea} instance.</p>
     */
    public ImageArea() {
    }

    /**
     * <p>Construct an {@link ImageArea} initialized with the specified property
     * values.</p>
     *
     * @param alt Alternate text for this hotspot
     * @param coords Coordinate positions for this hotspot
     * @param shape Shape of this hotspot (default, rect, circle, poly)
     */
    public ImageArea(String alt, String coords, String shape) {
        setAlt(alt);
        setCoords(coords);
        setShape(shape);
    }

    /**
     * @return the alternate text for this hotspot
     */
    public String getAlt() {
        return (this.alt);
    }

    /**
     * <p>Set the alternate text for this hotspot.</p>
     *
     * @param alt The new alternate text
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * @return the coordinate positions for this hotspot
     */
    public String getCoords() {
        return (this.coords);
    }

    /**
     * <p>Set the coordinate positions for this hotspot.</p>
     *
     * @param coords The new coordinate positions
     */
    public void setCoords(String coords) {
        this.coords = coords;
    }

    /**
     * @return the shape for this hotspot
     */
    public String getShape() {
        return (this.shape);
    }

    /**
     * <p>Set the shape for this hotspot.</p>
     *
     * @param shape The new shape
     */
    public void setShape(String shape) {
        this.shape = shape;
    }
}

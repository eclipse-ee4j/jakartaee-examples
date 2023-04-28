/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="PERSISTENCE_ORDER_VENDOR_PART",
       uniqueConstraints=
           @UniqueConstraint(columnNames={"PARTNUMBER", "PARTREVISION"})
)
@NamedQueries({
    @NamedQuery(
        name="findAverageVendorPartPrice",
        query="SELECT AVG(vp.price) " +
              "FROM VendorPart vp"
    ),
    @NamedQuery(
        name="findTotalVendorPartPricePerVendor",
        query="SELECT SUM(vp.price) " +
              "FROM VendorPart vp " +
              "WHERE vp.vendor.vendorId = :id"
    ),
    @NamedQuery(
        name="findAllVendorParts",
        query="SELECT vp FROM VendorPart vp ORDER BY vp.vendorPartNumber"
    )
})
public class VendorPart implements java.io.Serializable {
    private static final long serialVersionUID = 4685631589912848921L;
    private Long vendorPartNumber;
    private String description;
    private double price;
    private Part part;
    private Vendor vendor;
    
    public VendorPart() {}
    
    public VendorPart(String description, double price, Part part) {
        this.description = description;
        this.price = price;
        this.part = part;
        part.setVendorPart(this);
    }

    @TableGenerator(
        name="vendorPartGen",
        table="PERSISTENCE_ORDER_SEQUENCE_GENERATOR",
        pkColumnName="GEN_KEY",
        valueColumnName="GEN_VALUE",
        pkColumnValue="VENDOR_PART_ID",
        allocationSize=10)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="vendorPartGen")
    public Long getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(Long vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @OneToOne
    @JoinColumns({
        @JoinColumn(name="PARTNUMBER", referencedColumnName="PARTNUMBER"),
        @JoinColumn(name="PARTREVISION", referencedColumnName="REVISION")
    })
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @JoinColumn(name="VENDORID")
    @ManyToOne
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
}

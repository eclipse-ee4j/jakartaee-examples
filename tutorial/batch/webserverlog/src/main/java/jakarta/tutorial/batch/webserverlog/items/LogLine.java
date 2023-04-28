/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.batch.webserverlog.items;

/* Represents a line in the input log file
   Used as input items in the ItemReader implementation */ 
public class LogLine {

    private final String datetime;
    private final String ipaddr;
    private final String browser;
    private final String url;

    /* Construct the line from individual items */
    public LogLine(String datetime, String ipaddr,
        String browser, String url) {
        this.datetime = datetime;
        this.ipaddr = ipaddr;
        this.browser = browser;
        this.url = url;
    }

    /* Construct an item from a log line */
    public LogLine(String line) {
        //System.out.println(line);
        String[] result = line.split(", ");
        this.datetime = result[0];
        this.ipaddr = result[1];
        this.browser = result[2];
        this.url = result[3];
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return datetime + " " + ipaddr + " " + browser + " " + url;
    }

    /* Getters and setters */
    public String getDatetime() {
        return datetime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public String getBrowser() {
        return browser;
    }

    public String getUrl() {
        return url;
    }
}

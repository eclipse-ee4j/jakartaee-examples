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

/* Represents a log line in the filtered log file
 * Used as output items in the ItemWriter implementation */
public class LogFilteredLine {
	private final String ipaddr;
	private final String url;
	
        /* Construct from an input log line */
	public LogFilteredLine(LogLine ll) {
		this.ipaddr = ll.getIpaddr();
		this.url = ll.getUrl();
	}
        
        /* Construct from an output log line */
        public LogFilteredLine(String line) {
		String[] result = line.split(", ");
		this.ipaddr = result[0];
		this.url = result[1];
	}
	
	@Override
	public String toString() {
		return ipaddr + ", " + url;
	}
}

/*
Copyright (c) 2013 J. L. Canales Gasco
 
This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.
 
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
 
You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA}]
*/
package org.paneoplatform.core.model.domain;

import java.util.UUID;


/**
 * Implement the Transaction Object definition.
 * This POJO define the attributes of a Paneo Transaction
 * @author J.L. Canales
 *
 */
public class Transaction {

	/**
	 * Transaction unique ID
	 */
	private UUID transac_id;
	
	/**
	 * Global Correlation ID
	 */
	private String correlation_id;
	
	/**
	 * Conversation Correlation ID
	 */
	private String breadcrumb_id;
	
	/**
	 * Transaction source login
	 */
	private String origin;
	
	/**
	 * Transaction destination login
	 */
	private String destination;
	
	/**
	 * Origin geolocation x coordinate
	 */
	private double x_coord_from;
	
	/**
	 * Origin geolocation y coordinate
	 */
	private double y_coord_from;
	
	/**
	 * Transaction body
	 */
	private String body;

	public UUID getTransac_id() {
		return transac_id;
	}

	public void setTransac_id(UUID transac_id) {
		this.transac_id = transac_id;
	}

	public String getCorrelation_id() {
		return correlation_id;
	}

	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}

	public String getBreadcrumb_id() {
		return breadcrumb_id;
	}

	public void setBreadcrumb_id(String breadcrumb_id) {
		this.breadcrumb_id = breadcrumb_id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getX_coord_from() {
		return x_coord_from;
	}

	public void setX_coord_from(double x_coord_from) {
		this.x_coord_from = x_coord_from;
	}

	public double getY_coord_from() {
		return y_coord_from;
	}

	public void setY_coord_from(double y_coord_from) {
		this.y_coord_from = y_coord_from;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}

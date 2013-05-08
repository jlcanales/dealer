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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ ((breadcrumb_id == null) ? 0 : breadcrumb_id.hashCode());
		result = prime * result
				+ ((correlation_id == null) ? 0 : correlation_id.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result
				+ ((transac_id == null) ? 0 : transac_id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x_coord_from);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y_coord_from);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (breadcrumb_id == null) {
			if (other.breadcrumb_id != null)
				return false;
		} else if (!breadcrumb_id.equals(other.breadcrumb_id))
			return false;
		if (correlation_id == null) {
			if (other.correlation_id != null)
				return false;
		} else if (!correlation_id.equals(other.correlation_id))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (transac_id == null) {
			if (other.transac_id != null)
				return false;
		} else if (!transac_id.equals(other.transac_id))
			return false;
		if (Double.doubleToLongBits(x_coord_from) != Double
				.doubleToLongBits(other.x_coord_from))
			return false;
		if (Double.doubleToLongBits(y_coord_from) != Double
				.doubleToLongBits(other.y_coord_from))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [transac_id=" + transac_id + ", correlation_id="
				+ correlation_id + ", breadcrumb_id=" + breadcrumb_id
				+ ", origin=" + origin + ", destination=" + destination
				+ ", x_coord_from=" + x_coord_from + ", y_coord_from="
				+ y_coord_from + ", body=" + body + "]";
	}
	
	
	
}

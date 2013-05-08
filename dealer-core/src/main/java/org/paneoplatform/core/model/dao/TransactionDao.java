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
package org.paneoplatform.core.model.dao;

import org.paneoplatform.core.model.domain.Transaction;

/**
 * Dao Definition for transaction operations over the
 * Paneo Platform Dealer System 

 * @author J.L. Canales
 *
 */
public interface TransactionDao {
	
	/**
	 * Save a transaction in the Dealer persistence system
	 * @param transaction Transaction object to be persisted
	 */
  public void save(Transaction transaction);
  
  /**
   * Search for a Transaction object identified by its
   * transaction_id
   * @param transaction_id Transaction ID for the object to be searched
   * @return Transaction found or 'null' if Transaction does not exits
   */
  public Transaction find(String transaction_id);
  
  /**
   * Delete the Transaction object identified by its
   * transaction_id
   * @param transaction_id Transaction Id for the object to be deleted
   */
  public void delete(String transaction_id);
}

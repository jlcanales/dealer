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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.prettyprint.hector.api.Keyspace;

/**
 * Implements TransactionDao interface using Hector API to access
 * to Cassandra.
 * @author J. L. Canales Gasco
 *
 */
public class TransactionDaoImpl extends
		AbstractColumnFamilyDao<String, Transaction> implements
		TransactionDao {

	private static Logger  log = LoggerFactory.getLogger(TransactionDaoImpl.class);
	
	private static final String TRANSACTIONS_COLUMN_FAMILY = "transactions";
	
	public TransactionDaoImpl(Keyspace keySpace) {
		super(keySpace, String.class, Transaction.class, TRANSACTIONS_COLUMN_FAMILY);
	}

	@Override
	public void save(Transaction transaction) {
		super.save(transaction.getTransac_id(), transaction);

	}
}

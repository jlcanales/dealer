/*
 * This object is based on  Sanjay Acharya code publish in his blog to 
 * ilustrate Hector API capabilities and work procedures.
 * It can be consult at 
 * http://sleeplessinslc.blogspot.com.es/2010/11/apache-cassandra-with-hector-example.html
 */
package org.paneoplatform.core.model.dao;

import me.prettyprint.cassandra.serializers.BytesArraySerializer;
import me.prettyprint.cassandra.serializers.SerializerTypeInferer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;

/**
 * Abstract class to implement cassandra DAO using Hector for generic Object
 * type.
 * 
 * @author jdeveloper
 * 
 * @param <KeyType>
 *            Object Type used for Key in the Column Family
 * @param <T>
 *            Object Type to store row data
 */
public abstract class AbstractColumnFamilyDao<KeyType, T> {

	/**
	 * Class Type for POJO object that represents a Row in the Column Family
	 */
	private final Class<T> persistentClass;

	/**
	 * Class Type for Key used in the Column Family
	 */
	private final Class<KeyType> keyTypeClass;

	/**
	 * Hector Keyspace Object to work with Cassandra Keyspace
	 */
	protected final Keyspace keySpace;

	/**
	 * Column Family Name witch is accessed by the concrete Dao
	 */
	protected final String columnFamilyName;

	/**
	 * List of Column Names extracted form persistentClass attributes
	 */
	private final String[] allColumnNames;

	/**
	 * Constructor
	 * @param keyspace Keyspace witch contains the Column Family accessed by DAO
	 * @param keyTypeClass Class Type for Key used in the Column Family
	 * @param persistentClass Class Type for POJO object that represents a Row in the Column Family
	 * @param columnFamilyName Column Family Name witch is accessed by the concrete DAO
	 */
	public AbstractColumnFamilyDao(Keyspace keyspace,
			Class<KeyType> keyTypeClass, Class<T> persistentClass,
			String columnFamilyName) {
		this.keySpace = keyspace;
		this.keyTypeClass = keyTypeClass;
		this.persistentClass = persistentClass;
		this.columnFamilyName = columnFamilyName;
		this.allColumnNames = DaoHelper.getAllColumnNames(persistentClass);
	}

	/**
	 * Store a object as a row in the Column Family
	 * @param key   Key Object Type for the Column Family
	 * @param model POJO that contains row information to be stored
	 */
	public void save(KeyType key, T model) {

		Mutator<Object> mutator = HFactory.createMutator(keySpace,
				SerializerTypeInferer.getSerializer(keyTypeClass));
		for (HColumn<?, ?> column : DaoHelper.getColumns(model)) {
			mutator.addInsertion(key, columnFamilyName, column);
		}

		mutator.execute();
	}

	/**
	 * Search for a row identified by Key
	 * @param key Key that identify row to be search
	 * @return Object that store row content.
	 */
	public T find(KeyType key) {
		SliceQuery<Object, String, byte[]> query = HFactory.createSliceQuery(
				keySpace, SerializerTypeInferer.getSerializer(keyTypeClass),
				StringSerializer.get(), BytesArraySerializer.get());

		QueryResult<ColumnSlice<String, byte[]>> result = query
				.setColumnFamily(columnFamilyName).setKey(key)
				.setColumnNames(allColumnNames).execute();

		if (result.get().getColumns().size() == 0) {
			return null;
		}

		try {
			T t = persistentClass.newInstance();
			DaoHelper.populateEntity(t, result);
			return t;
		} catch (Exception e) {
			throw new RuntimeException("Error creating persistent class", e);
		}
	}

	/**
	 * Delete a row identified by Key
	 * @param key Key that identify row to be deleted
	 */
	public void delete(KeyType key) {
		Mutator<Object> mutator = HFactory.createMutator(keySpace,
				SerializerTypeInferer.getSerializer(keyTypeClass));
		mutator.delete(key, columnFamilyName, null,
				SerializerTypeInferer.getSerializer(keyTypeClass));
	}
}

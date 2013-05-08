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

/*
 * This object is based on  Sanjay Acharya code publish in his blog to 
 * ilustrate Hector API capabilities and work procedures.
 * It can be consult at 
 * http://sleeplessinslc.blogspot.com.es/2010/11/apache-cassandra-with-hector-example.html
 */
package org.paneoplatform.core.model.dao;

import me.prettyprint.cassandra.serializers.SerializerTypeInferer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils Class for DAO implementations.
 * It wrap some transformations a common operations with Hector
 * in order to make Hector work easier
 * @author jdeveloper
 *
 */
public final class DaoHelper {

  private DaoHelper() {}

  /**
   * Create a java.util.UUID based on com.eaio.uuid.UUID.
   * com.eaio.uuid.UUID is the native cassandra object to manage UUID. To abstract
   * the core application from it using java.util.UUID is used this method.
   * 
   * @return java.util.UUID
   */
  public static java.util.UUID getTimeUUID() {
    return java.util.UUID.fromString(new com.eaio.uuid.UUID().toString());
  }

  public static byte[] asByteArray(java.util.UUID uuid) {
    long msb = uuid.getMostSignificantBits();
    long lsb = uuid.getLeastSignificantBits();
    byte[] buffer = new byte[16];

    for (int i = 0; i < 8; i++) {
      buffer[i] = (byte) (msb >>> 8 * (7 - i));
    }
    for (int i = 8; i < 16; i++) {
      buffer[i] = (byte) (lsb >>> 8 * (7 - i));
    }

    return buffer;
  }

  public static <T> List<HColumn<String, ?>> getColumns(T entity) {
    try {
      List<HColumn<String, ?>> columns = new ArrayList<HColumn<String, ?>>();
      Field[] fields = entity.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        Object value = field.get(entity);
        
        if (value == null) {
          // Field has no value so nothing to store
          continue;
        }
        
        String name = field.getName();
        
        HColumn<String, ?> column = HFactory.createColumn(name, value, StringSerializer.get(),
          SerializerTypeInferer.getSerializer(value));

        columns.add(column);
      }
      return columns;
    }
    catch (Exception e) {
      throw new RuntimeException("Reflecting yeee haw exception", e);
    }
  }

  public static <T> List<HColumn<String, String>> getStringCols(T entity) {
    try {
      List<HColumn<String, ?>> cols = getColumns(entity);
      List<HColumn<String, String>> retCols = new ArrayList<HColumn<String, String>>();

      for (HColumn<String, ?> col : cols) {
        retCols.add(HFactory.createStringColumn(col.getName(), col.getValue().toString()));
      }

      return retCols;
    }
    catch (Exception e) {
      throw new RuntimeException("Reflecting away", e);
    }
  }

  public static <T> void populateEntity(T entity, QueryResult<ColumnSlice<String, byte[]>> result) {
    try {
      Field[] fields = entity.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        String name = field.getName();
        HColumn<String, byte[]> col = result.get().getColumnByName(name);
        if (col == null || col.getValue() == null || col.getValue().length == 0) {
          // No data for this col
          continue;
        }

        Object val = SerializerTypeInferer.getSerializer(field.getType()).fromBytes(col.getValue());
        field.set(entity, val);
      }
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException("Error Reflecting", e);
    }
  }

  public static <T> Field getFieldForPropertyName(T entity, String name) {
    try {
      return entity.getClass().getDeclaredField(name);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void populateEntityFromCols(Object entity, List<HColumn<String, String>> cols) {

    for (HColumn<String, ?> col : cols) {
      Field f = getFieldForPropertyName(entity, col.getName());
      try {
        f.setAccessible(true);
        f.set(entity, col.getValue());
      }
      catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static String[] getAllColumnNames(Class<?> entityClass) {
    List<String> columnNames = new ArrayList<String>();
    Field[] fields = entityClass.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      String name = field.getName();
      columnNames.add(name);
    }

    return columnNames.toArray(new String[] {});
  }
}

/**
 *    Copyright 2009-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package mybatis.framework;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

import java.sql.Connection;

/**
 * Creates an {@link SqlSession} out of a connection or a DataSource
 *
 * @author Clinton Begin
 */
public interface MySqlSessionFactory {

  MySqlSession openSession();

  MySqlSession openSession(boolean autoCommit);
  MySqlSession openSession(Connection connection);
  MySqlSession openSession(TransactionIsolationLevel level);

  MySqlSession openSession(ExecutorType execType);
  MySqlSession openSession(ExecutorType execType, boolean autoCommit);
  MySqlSession openSession(ExecutorType execType, TransactionIsolationLevel level);
  MySqlSession openSession(ExecutorType execType, Connection connection);

  MyConfiguration getConfiguration();

}

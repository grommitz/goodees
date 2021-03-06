package io.github.goodees.ese.store.jdbc;

/*-
 * #%L
 * ese
 * %%
 * Copyright (C) 2017 Patrik Duditš
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.github.goodees.ese.core.Event;
import io.github.goodees.ese.core.store.SnapshotMetadata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class JdbcSchema {

    protected abstract PreparedStatement selectEntityVersion(Connection connection, String entityId)
            throws SQLException;

    protected abstract PreparedStatement createEntityVersion(Connection connection, String entityId, long startVersion)
            throws SQLException;

    protected abstract long readEntityVersion(ResultSet rs) throws SQLException;

    protected abstract PreparedStatement insertEvent(Connection connection, String entityId) throws SQLException;

    protected abstract PreparedStatement updateEventVersion(Connection connection, String entityId, long startVersion,
            long endVersion) throws SQLException;

    protected abstract void prepareInsert(PreparedStatement insertEvent, Event event, int payloadVersion, String payload)
            throws SQLException;

    protected abstract PreparedStatement selectEvents(Connection connection, String entityId, long afterVersion)
            throws SQLException;

    protected abstract String readEventType(ResultSet rs) throws SQLException;

    protected abstract int readEventPayloadVersion(ResultSet rs) throws SQLException;

    protected abstract String readEventPayload(ResultSet rs) throws SQLException;

    protected abstract PreparedStatement selectSnapshot(Connection connection, String entityId) throws SQLException;

    protected abstract PreparedStatement updateSnapshot(Connection connection, String entityId, long stateVersion,
            int payloadVersion, String payload) throws SQLException;

    protected abstract PreparedStatement insertSnapshot(Connection connection, String entityId, long stateVersion,
            int payloadVersion, String payload) throws SQLException;

    protected abstract SnapshotMetadata readSnapshotMetadata(ResultSet rs) throws SQLException;

    protected abstract String readSnapshotPayload(ResultSet rs) throws SQLException;
}

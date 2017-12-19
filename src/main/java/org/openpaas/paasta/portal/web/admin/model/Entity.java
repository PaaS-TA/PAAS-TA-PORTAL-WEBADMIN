/*
 * Copyright 2009-2012 the original author or authors.
 *
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
 */

package org.openpaas.paasta.portal.web.admin.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 엔티티 모델
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.5.30 최초작성
 */
public class Entity {

    public Map<String, Object> map;
    private Meta meta;
    private String name;

    public Entity() {
        //empty
    }

    public Entity(Meta meta) {
        this(meta, null, new HashMap());
    }

    public Entity(Meta meta, String name, Map map) {
        if (meta != null) {
            this.meta = meta;
        } else {
            this.meta = Meta.defaultMeta();
        }
        this.name = name;
        this.map = map;
    }

    public Entity(Meta meta, String name) {
        if (meta != null) {
            this.meta = meta;
        } else {
            this.meta = Meta.defaultMeta();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": (" +
                (meta == null || meta.getGuid() == null ? "-" : meta.getGuid()) + ") " +
                getName();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public static class Meta {

        private UUID guid;
        private Date created;
        private Date updated;
        private String url;

        public Meta() {
        }

        public Meta(UUID guid, Date created, Date updated) {
            this.guid = guid;
            this.created = created;
            this.updated = updated;
        }

        public Meta(UUID guid, Date created, Date updated, String url) {
            this.guid = guid;
            this.created = created;
            this.updated = updated;
            this.url = url;
        }

        public static Meta defaultMeta() {
            return new Meta(null, null, null);
        }

        public UUID getGuid() {
            return guid;
        }

        public Date getCreated() {
            return created;
        }

        public Date getUpdated() {
            return updated;
        }

        public String getUrl() {
            return url;
        }
    }
}

/*
 * Copyright 2014 the original author or authors.
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
package com.rockagen.gnext.service.spring;

import java.io.Serializable;
import java.util.List;

import com.rockagen.commons.util.CommUtil;
import com.rockagen.gnext.dao.GenericDao;
import com.rockagen.gnext.dao.Hibernate4GenericDao;
import com.rockagen.gnext.qo.QueryObject;

/**
 * Implementation of the <code>ObjectQueryGenericServ</code> interface
 * 
 * @author RA
 * @see GenericServImpl
 */
public abstract class QueryObjectGenericServImpl<E, PK extends Serializable>
		extends GenericServImpl<E, QueryObject, PK> {

	protected abstract Hibernate4GenericDao<E, PK> getHibernate4GenericDao();

	@Override
	public GenericDao<E, PK> getGenericDao() {
		return getHibernate4GenericDao();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> find(final QueryObject queryObject) {
		if (queryObject == null) {
			return null;
		}
		Boolean limit = false;
		if (queryObject.getSize() > 0) {
			limit = true;
		}
		
		if (!CommUtil.isBlank(queryObject.getSql())) {
			if (limit) {
				if (queryObject.getMap() != null && !queryObject.getMap().isEmpty()) {
					return (List<E>) getGenericDao().query(queryObject.getSql(),
							queryObject.getIndex(), queryObject.getSize(), queryObject.getMap());
				} else {
					return (List<E>) getGenericDao().query(queryObject.getSql(),
							queryObject.getIndex(), queryObject.getSize(), queryObject.getArgs());
				}

			} else {
				if (queryObject.getMap() != null && !queryObject.getMap().isEmpty()) {
					return (List<E>) getGenericDao().query(queryObject.getSql(),
							queryObject.getMap());
				} else {
					return (List<E>) getGenericDao().query(queryObject.getSql(),
							queryObject.getArgs());
				}

			}
		} else if (queryObject.getDetachedCriteria() != null) {
			if (limit) {
				return (List<E>) getHibernate4GenericDao().queryByCriteria(
						queryObject.getDetachedCriteria(), queryObject.getIndex(), queryObject.getSize());
			} else {
				return (List<E>) getHibernate4GenericDao().queryByCriteria(
						queryObject.getDetachedCriteria());
			}
		} else {
			// XXX
			return null;
		}

	}


}

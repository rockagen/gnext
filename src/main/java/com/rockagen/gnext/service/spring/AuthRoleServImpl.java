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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rockagen.gnext.dao.Hibernate4GenericDao;
import com.rockagen.gnext.po.AuthRole;
import com.rockagen.gnext.service.AuthRoleServ;

/**
 * Implementation of the <code>AuthRoleServ</code> interface
 * @author RA
 */
@Service("authRoleServ")
public class AuthRoleServImpl extends QueryObjectGenericServImpl<AuthRole, Long> implements AuthRoleServ{

	private Hibernate4GenericDao<AuthRole, Long> authRoleDao;
	
	
	@Override
	protected Hibernate4GenericDao<AuthRole, Long> getHibernate4GenericDao() {
		return authRoleDao;
	}
	
	@Resource
	public void setAuthRoleDao(Hibernate4GenericDao<AuthRole, Long> authRoleDao) {
		this.authRoleDao = authRoleDao;
	}



	

}

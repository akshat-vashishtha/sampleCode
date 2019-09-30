package com.qualtech.crif.api.db.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.crif.api.db.dao.CriffDetailsDBDao;
import com.qualtech.crif.api.entity.CriffDetailLogs;
import com.qualtech.crif.api.request.CriffReqRes;

@Repository
@Transactional
public class CriffDetailsDBDaoImpl implements CriffDetailsDBDao {
	private static final Logger logger = Logger.getLogger(CriffDetailsDBDaoImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Long save(CriffDetailLogs crifResponseEntity) {
		Long Unique_Id = 0l;
		try {
			Unique_Id = Long.parseLong(getSession().save(crifResponseEntity).toString());

			logger.info("Total CriffDetailLogs Records Insertion Against ID -:- " + Unique_Id);

		} catch (Exception e) {
			logger.error("CriffDetailsDBDaoImpl || save() || Exception in inserting records crifResponseEntity : " + e);
		}

		return Unique_Id;
	}

	public void save(CriffReqRes crifreqres) {

		Long Unique_Id = 0l;
		try {
			Unique_Id = Long.parseLong(getSession().save(crifreqres).toString());
			logger.info("Criff request response inserted");

		}

		catch (Exception e) {
			logger.error("We are getting exception while inserting records: " + e);

		}

	}

}

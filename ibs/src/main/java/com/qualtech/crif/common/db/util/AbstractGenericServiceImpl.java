package com.qualtech.crif.common.db.util;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class AbstractGenericServiceImpl<DAO extends GenericDAO, T> {
	/**
	 * Method to return the domain dao
	 */
	protected abstract DAO getDAO();

	/**
	 * Method to set the domain dao
	 */
	protected abstract void setDAO(DAO dao);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public Long save(final T T) {
		return getDAO().save(T);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void update(final T T) {
		getDAO().update(T);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void merge(final T T) {
		getDAO().merge(T);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void delete(final T T) {
		getDAO().delete(T);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T load(final Long id) {
		return (T) getDAO().get(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T get(final Long id) {
		return (T) getDAO().get(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void saveOrUpdate(final T entity) {
		getDAO().saveOrUpdate(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteAll() {
		getDAO().deleteAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deleteById(final Long id) {
		getDAO().deleteById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public Long count() {
		return getDAO().count();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<T> findAll() {
		return getDAO().findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<T> findAllPaginatedAndSortedRaw(int page, int size,
			String sortBy, String sortOrder) {
		return getDAO().findAllPaginatedAndSortedRaw(page, size, sortBy,
				sortOrder);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public List<T> findAllSorted(String sortBy, String sortOrder) {
		return getDAO().findAllSorted(sortBy, sortOrder);
	}
}

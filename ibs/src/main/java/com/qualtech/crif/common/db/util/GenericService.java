package com.qualtech.crif.common.db.util;

import java.util.List;

public interface GenericService<DAO, T> {

	public T load(Long id);

	public Long save(T T);

	public void saveOrUpdate(T object);

	public void update(T T);

	public void merge(T T);

	public void delete(T T);

	public void deleteById(Long id);

	public T get(Long id);

	public void deleteAll();

	public Long count();

	public List<T> findAll();

	public List<T> findAllPaginatedAndSortedRaw(final int page,
			final int size, final String sortBy, final String sortOrder);

	public List<T> findAllSorted(final String sortBy,
			final String sortOrder);

}

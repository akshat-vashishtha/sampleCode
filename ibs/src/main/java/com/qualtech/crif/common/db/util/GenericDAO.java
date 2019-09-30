package com.qualtech.crif.common.db.util;

import java.util.List;

/**
 * The Interface GenericDAO.
 * 
 * @param <T>
 *            the generic type
 *            
 */
public interface GenericDAO<T> {

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the domain object
	 */
	public T load(Long id);

	/**
	 * Gets the.
	 * 
	 * @param id
	 *            the id
	 * @return the domain object
	 */
	public T get(Long id);

	/**
	 * Update.
	 * 
	 * @param object
	 *            the object
	 */
	public void update(T object);

	/**
	 * Merge.
	 * 
	 * @param object
	 *            the object
	 */
	public void merge(T object);

	/**
	 * Save.
	 * 
	 * @param object
	 *            the object
	 * @return the long
	 */
	public Long save(T object);

	/**
	 * Save or update.
	 * 
	 * @param object
	 *            the object
	 */
	public void saveOrUpdate(T object);

	/**
	 * Delete.
	 * 
	 * @param object
	 *            the object
	 */
	public void delete(T object);

	/**
	 * Delete by id.
	 * 
	 * @param id
	 *            the id
	 */
	public void deleteById(Long id);

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public List<T> findAll();

	/**
	 * Delete all.
	 */
	public void deleteAll();

	/**
	 * Count.
	 * 
	 * @return the long
	 */
	public Long count();

	/**
	 * Find all paginated and sorted raw.
	 * 
	 * @param page
	 *            the page
	 * @param size
	 *            the size
	 * @param sortBy
	 *            the sort by
	 * @param sortOrder
	 *            the sort order
	 * @return the list
	 */
	public List<T> findAllPaginatedAndSortedRaw(final int page,
			final int size, final String sortBy, final String sortOrder);

	/**
	 * Find all sorted.
	 * 
	 * @param sortBy
	 *            the sort by
	 * @param sortOrder
	 *            the sort order
	 * @return the list
	 */
	public List<T> findAllSorted(final String sortBy,
			final String sortOrder);

	/**
	 * Batch create.
	 * 
	 * @param entityList
	 *            the entity list
	 * @return the int
	 */
	public long batchCreate(final List<T> entityList, long batchSize);

	/**
	 * Truncate.
	 */
	public void truncate();
}

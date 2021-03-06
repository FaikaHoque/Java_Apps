package ca.jrvs.apps.twitter.dao;

public interface CrdDao <T, ID> {
    /**
     * create an entity to the underlying storage
     * @param entity to be created
     * @return created entity
     */
    T create(T entity);
    /**
     * Find an entity by its id
     *
     */
    T findById(ID id);
    /**
     * delete an entity by its ID
     * @param id of the entity to be deleted
     * @return deleted entity
     */

    T deleteById(ID id);

}

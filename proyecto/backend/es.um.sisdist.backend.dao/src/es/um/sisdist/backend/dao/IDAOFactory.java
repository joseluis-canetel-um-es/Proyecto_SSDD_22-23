/**
 *
 */
package es.um.sisdist.backend.dao;

import es.um.sisdist.backend.dao.user.IUserDAO;

/**
 * @author dsevilla
 *
 */
public interface IDAOFactory
{
    public IUserDAO createSQLUserDAO();

    public IUserDAO createMongoUserDAO();
    /** nuevos metodos -- kholoud */
    // añado los metodos de eliminacion de usuario
    
    public boolean removeSQLUserDAO();
    public boolean removeMongoUserDAO();
}

package DAO;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    void criar(T entity) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    void atualizar(T entity) throws SQLException;
    void deletar(int id) throws SQLException;
}

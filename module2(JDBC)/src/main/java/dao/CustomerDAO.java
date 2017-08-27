package dao;

import domain.Customer;

/**
 * Created by User on 11.06.2017.
 */
public interface CustomerDAO extends GeneralDAO<Long, Customer> {
    void setProjectDAO(ProjectDAO projectDAO);
    ProjectDAO getProjectDAO();
}

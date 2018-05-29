package com.jcloud.express.repository;

import com.jcloud.express.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role,Integer> {

    /**
     * Find existing role by it's name
     * @param role
     * @return
     */
    public Role findRoleByRole(String role);

    /**
     * Find existing role by it's id
     * @param role_id
     * @return
     */
    public Role findByRoleId(Integer role_id);
}

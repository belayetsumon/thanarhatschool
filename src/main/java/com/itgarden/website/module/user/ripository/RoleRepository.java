/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.website.module.user.ripository;


import com.itgarden.website.module.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}

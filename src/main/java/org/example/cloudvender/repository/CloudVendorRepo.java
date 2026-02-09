package org.example.cloudvender.repository;

import org.example.cloudvender.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudVendorRepo extends JpaRepository<CloudVendor, Long> {

}


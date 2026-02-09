package org.example.cloudvender.service;

import org.example.cloudvender.model.CloudVendor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CloudVendorServiceInter {
    CloudVendor createVendor(CloudVendor cloudVendor);

    CloudVendor getVendorById(Long id);

    List<CloudVendor> getAllVendors();

    String deleteVendor(Long id);

    String updateVendor(Long id, CloudVendor cloudVendor);
}

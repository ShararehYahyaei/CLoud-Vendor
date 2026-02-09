package org.example.cloudvender.service;

import org.example.cloudvender.exception.CloudVendorNotFoundException;
import org.example.cloudvender.model.CloudVendor;
import org.example.cloudvender.repository.CloudVendorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorService implements CloudVendorServiceInter {
    private final CloudVendorRepo cloudVendorRepo;

    public CloudVendorService(CloudVendorRepo cloudVendorRepo) {
        this.cloudVendorRepo = cloudVendorRepo;
    }

    @Override
    public CloudVendor createVendor(CloudVendor cloudVendor) {
        return cloudVendorRepo.save(cloudVendor);
    }

    @Override
    public CloudVendor getVendorById(Long id) {
        return cloudVendorRepo.findById(id).orElseThrow((
                () -> new CloudVendorNotFoundException("Can't find CLoudVendor")));

    }

    @Override
    public List<CloudVendor> getAllVendors() {
        return cloudVendorRepo.findAll();
    }

    @Override
    public String deleteVendor(Long id) {
        if (cloudVendorRepo.existsById(id)) {
            cloudVendorRepo.deleteById(id);
            return "Vendor deleted";
        }
        return "Vendor Not Found";
    }

    @Override
    public String updateVendor(Long id, CloudVendor cloudVendor) {
        if (cloudVendorRepo.findById(id).isPresent()) {
            CloudVendor existedCloudVendor = cloudVendorRepo.findById(id).get();
            existedCloudVendor.setName(cloudVendor.getName());
            existedCloudVendor.setAddress(cloudVendor.getAddress());
            existedCloudVendor.setPhoneNumber(cloudVendor.getPhoneNumber());
            cloudVendorRepo.save(existedCloudVendor);
            return "Vendor updated";
        }
        return "Vendor Not Found";
    }
}

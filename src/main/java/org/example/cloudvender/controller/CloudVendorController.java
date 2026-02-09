package org.example.cloudvender.controller;

import org.example.cloudvender.model.CloudVendor;
import org.example.cloudvender.service.CloudVendorServiceInter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvender")
public class CloudVendorController {
    private final CloudVendorServiceInter cloudVendorService;


    public CloudVendorController(CloudVendorServiceInter cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @PostMapping
    public CloudVendor create(@RequestBody CloudVendor cloudVendor) {

        return cloudVendorService.createVendor(cloudVendor);
    }

    @GetMapping("/{id}")
    public CloudVendor findById(@PathVariable Long id) {
        return cloudVendorService.getVendorById(id);
    }

    @PutMapping("/{id}")
    public String updateVendor(@PathVariable Long id,
                               @RequestBody CloudVendor cloudVendor) {
        return cloudVendorService.updateVendor(id, cloudVendor);
    }


    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {
        return cloudVendorService.deleteVendor(id);
    }

    @GetMapping("/getAllCloudVendors")
    public List<CloudVendor> findAll() {
        return cloudVendorService.getAllVendors();
    }
}

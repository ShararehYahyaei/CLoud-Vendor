package org.example.cloudvender.service;


import org.example.cloudvender.exception.CloudVendorNotFoundException;
import org.example.cloudvender.model.CloudVendor;
import org.example.cloudvender.repository.CloudVendorRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CloudVendorServiceTest {

    @Mock
    private CloudVendorRepo cloudVendorRepo;

    @InjectMocks
    private CloudVendorService cloudVendorService;

    @Test
    void shouldCreateCloudVendor() {
        CloudVendor cloudVendor = new CloudVendor(1L, "Shari", "Utrecht", "123");

        when(cloudVendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
        CloudVendor result = cloudVendorService.createVendor(cloudVendor);
        assertNotNull(result);
        assertEquals("Shari", result.getName());
        verify(cloudVendorRepo).save(cloudVendor);

    }

    @Test
    void shouldReturnVendorWhenIdExists() {
        CloudVendor cloudVendor = new CloudVendor(1L, "Shari", "Utrecht", "123");
        when(cloudVendorRepo.findById(1L)).thenReturn(Optional.of(cloudVendor));

        CloudVendor result = cloudVendorService.getVendorById(1L);
        assertNotNull(result);
        assertEquals("Shari", result.getName());


    }

    @Test
    void shouldReturnExceptionWhenIdDoesNotExist() {
        when(cloudVendorRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CloudVendorNotFoundException.class, () -> cloudVendorService.getVendorById(1L));
    }

    @Test
    void shouldDeleteVendorWhenIdExists() {
        when(cloudVendorRepo.existsById(1L)).thenReturn(true);
        cloudVendorService.deleteVendor(1L);
        verify(cloudVendorRepo).deleteById(1L);

    }

    @Test
    void shouldReturnNotFoundWhenDeletingNOnExistingVendor() {
        when(cloudVendorRepo.existsById(1L)).thenReturn(false);
        String result = cloudVendorService.deleteVendor(1L);
        assertEquals("Vendor Not Found", result);

    }

    @Test
    void shouldUpdateVendorWhenExisted() {
        CloudVendor existing = new CloudVendor(1L, "Shari", "Utrecht", "123");
        CloudVendor updated = new CloudVendor(1L, "ALi", "Amersfoort", "123");
        when(cloudVendorRepo.findById(1L)).thenReturn(Optional.of(existing));
        String result = cloudVendorService.updateVendor(1L, updated);
        assertEquals("Vendor updated", result);
        assertEquals("ALi", existing.getName());
        verify(cloudVendorRepo).save(existing);
    }

}

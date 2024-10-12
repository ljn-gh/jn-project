package com.jianan.demomodule.test.lock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BusinessServiceTest {

    @InjectMocks
    private BusinessService businessService;

    @Mock
    private ConcurrentHashMap<String, Lock> lockMap;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
        // Cleanup any resources if necessary
    }

    @Test
    public void testApproveWithSuccessfulLock() throws InterruptedException {
        String id = "testId";
        Lock lock = new ReentrantLock();
        when(lockMap.merge(eq(id), any(Lock.class), any())).thenReturn(lock);

        // Call the method under test
        businessService.approve(id);

        // Verify lock was acquired and released
        verify(lock, times(1)).tryLock(10, TimeUnit.SECONDS);
        verify(lock, times(1)).unlock();
        verify(lockMap, times(1)).remove(id);
    }

    @Test
    public void testApproveWithFailedLock() throws InterruptedException {
        String id = "testId";
        Lock lock = new ReentrantLock();
        when(lockMap.merge(eq(id), any(Lock.class), any())).thenReturn(lock);
        doReturn(false).when(lock).tryLock(10, TimeUnit.SECONDS);

        // Call the method under test
        businessService.approve(id);

        // Verify lock was attempted to be acquired and not released since it wasn't acquired
        verify(lock, times(1)).tryLock(10, TimeUnit.SECONDS);
        verify(lock, never()).unlock();
        verify(lockMap, never()).remove(id);
    }

}

package com.radixweb.trendingrepo.api;

import com.radixweb.trendingrepo.data.Resource;
import com.radixweb.trendingrepo.data.Status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ApiResponseTest {

    @Test
    public void exception() {
        Exception exception = new Exception("test");
        Resource<Exception> apiResponse = Resource.error(exception.getMessage(), exception);
        Assert.assertEquals("test", apiResponse.message);
        Assert.assertEquals(Status.ERROR, apiResponse.status);
    }

    @Test
    public void success() {
        Resource<String> resource = Resource.success("test");
        Assert.assertEquals("test", resource.data);
        Assert.assertEquals(Status.SUCCESS, resource.status);
    }
}

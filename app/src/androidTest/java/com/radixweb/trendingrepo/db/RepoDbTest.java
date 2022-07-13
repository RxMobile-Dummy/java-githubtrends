package com.radixweb.trendingrepo.db;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.util.MockTestUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class RepoDbTest extends DbTest {

    @Test
    public void insertAndReadPostsTest() {
        List<TrendRepoEntity> repositories = MockTestUtil.mockRepositories();
        db.githubDao().insertRepositories(repositories);

        List<TrendRepoEntity> storedPosts1 = db.githubDao().getRepositoriesByPage(1L);
        Assert.assertEquals(2L, storedPosts1.size());
        Assert.assertEquals(new Long(1), storedPosts1.get(0).getId());

        List<TrendRepoEntity> storedPosts2 = db.githubDao().getRepositoriesByPage(2L);
        Assert.assertEquals(1L, storedPosts2.size());
        Assert.assertEquals(new Long(3), storedPosts2.get(0).getId());
    }

    @Test
    public void emptyPostsTest() {
        List<TrendRepoEntity> repositories = MockTestUtil.mockRepositories();
        db.githubDao().insertRepositories(repositories);

        List<TrendRepoEntity> storedPosts = db.githubDao().getRepositoriesByPage(3L);
        Assert.assertEquals(0, storedPosts.size());
    }
}

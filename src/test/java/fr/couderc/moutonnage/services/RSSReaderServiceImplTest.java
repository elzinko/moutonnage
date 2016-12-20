package fr.couderc.moutonnage.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import fr.couderc.moutonnage.entity.RSS;
import fr.couderc.moutonnage.resources.RSSRepository;
import fr.couderc.moutonnage.services.RSSReaderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RSSReaderServiceImplTest {
    
    @Autowired
    private RSSReaderServiceImpl service;
    
    @Autowired
    private RSSRepository repo;
    
    @Test
    public void readRSS() throws Exception {
        
        List<RSS> result = repo.findAll();
        repo.deleteAll();
        
        Assert.assertTrue(repo.findAll().isEmpty());
        
        service.readRSS();
        
        Assert.assertFalse(repo.findAll().isEmpty());
    }

}
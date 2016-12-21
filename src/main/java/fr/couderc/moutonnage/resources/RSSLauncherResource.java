/*
 * Copyright (c) 2001-2016 Group JCDecaux. 
 * 17 rue Soyer, 92523 Neuilly Cedex, France.
 * All rights reserved. 
 * 
 * This software is the confidential and proprietary information
 * of Group JCDecaux ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you
 * entered into with Group JCDecaux.
 */

package fr.couderc.moutonnage.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.couderc.moutonnage.services.RSSReaderServiceImpl;

@RestController
@RequestMapping("/rss")
class BookmarkRestController {

    private final RSSReaderServiceImpl rssReaderService;

    @Autowired
    BookmarkRestController(RSSReaderServiceImpl rssReaderService) {
        this.rssReaderService = rssReaderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    ResponseEntity<?> updateRSS() {
        this.rssReaderService.readRSS();
        return ResponseEntity.noContent().build();
    }

}
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

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.couderc.moutonnage.entity.RSS;

@RepositoryRestResource(collectionResourceRel = "rss", path = "rss")
public interface RSSRepository extends MongoRepository<RSS, String> {

}

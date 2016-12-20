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

package fr.couderc.moutonnage.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@Document(collection = "rss")
public class RSS {

    @Id
    private String id;
    
    private String title;
    
    private String link;
    
    private String author;
    
    private LocalDateTime publisheddate;

    private LocalDateTime updateTime;
    
    private String description;
    
    private String category;
        
}

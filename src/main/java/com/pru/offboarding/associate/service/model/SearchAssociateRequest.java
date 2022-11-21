package com.pru.offboarding.associate.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchAssociateRequest {

	private String associateName;
    private String band;
    private String emailIbm;
    private String xid;
    
}

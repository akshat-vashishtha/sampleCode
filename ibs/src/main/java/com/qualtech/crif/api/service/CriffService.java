package com.qualtech.crif.api.service;

import com.qualtech.crif.api.request.CriffApiRequest;
import com.qualtech.crif.api.response.CriffApiResponse;

public interface CriffService 
{
    public CriffApiResponse criffService(CriffApiRequest criffApiRequest,String service);



}

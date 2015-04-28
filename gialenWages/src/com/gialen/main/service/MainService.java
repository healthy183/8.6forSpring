package com.gialen.main.service;

import com.gialen.model.Psnaccount;
import com.gialen.model.vo.LgnUsrVo;

public interface MainService {

	Psnaccount findPSNByPersonid(String personid);

	LgnUsrVo lgnByOA(String loginCode);

}

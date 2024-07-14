package com.abs.wfs.workman.interfaces.rest.flow;

import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.rtd.WfsDspWorkRepIvo;
import com.abs.wfs.workman.util.WorkManMessageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/flow/rtd/")
@RequiredArgsConstructor
public class RtdFlowController {


//    @Autowired
//    WfsDspWorkRepServiceImpl wfsDspWorkRepService;
//
//    @PostMapping(WorkManMessageList.WFS_DSP_WORK_REP)
//    public ApFlowProcessVo executeEvent(@RequestBody WfsDspWorkRepIvo wfsDspWorkRepIvo,
//                                        @RequestParam(value = "key") String trackingKey,
//                                        @RequestParam(value = "scenario") String scenarioType) throws Exception{
//
//        String cid = WorkManMessageList.WFS_DSP_WORK_REP;
//        ApFlowProcessVo apFlowProcessVo = this.wfsDspWorkRepService.initialize(cid,trackingKey,scenarioType,wfsDspWorkRepIvo.getHead().getTid());
//
//        return this.wfsDspWorkRepService.execute(apFlowProcessVo,wfsDspWorkRepIvo);
//    }
}

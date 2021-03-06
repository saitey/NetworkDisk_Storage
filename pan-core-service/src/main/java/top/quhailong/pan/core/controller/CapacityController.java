package top.quhailong.pan.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.quhailong.pan.core.provider.CapacityProvider;
import top.quhailong.pan.utils.RestAPIResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 容量服务
 *
 * @author: quhailong
 * @date: 2019/9/24
 */
@RestController
public class CapacityController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private HttpServletRequest httpServletRequest;
    @Autowired
    private CapacityProvider capacityProvider;

    /**
     * 查询使用容量
     *
     * @author: quhailong
     * @date: 2019/9/24
     */
    //@RequestMapping(value = "use", method = {RequestMethod.POST})
    @RequestMapping(value = "usecapacity", method = RequestMethod.GET)
    public RestAPIResult<String> useCapacity(String uid) {
        logger.info("查询使用容量请求URL：{}", httpServletRequest.getRequestURL());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        logger.info("查询使用容量数据处理开始,uid:{}", uid);
        RestAPIResult<String> result = capacityProvider.useCapacityHandle(uid);
        logger.info("查询使用容量数据处理结束,result:{}", result);
        stopWatch.stop();
        logger.info("查询使用容量调用时间,millies:{}", stopWatch.getTotalTimeMillis());
        return result;
    }

    /**
     * 初始化容量
     *
     * @author: quhailong
     * @date: 2019/9/25
     */
    @RequestMapping(value = "initcapacity", method = RequestMethod.POST)
    public RestAPIResult<Integer> initCapacity(@RequestParam("userId") String userId) {
        logger.info("初始化容量请求URL：{}", httpServletRequest.getRequestURL());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        logger.info("初始化容量数据处理开始,userId:{}", userId);
        RestAPIResult<Integer> result = capacityProvider.initCapacityHandle(userId);
        logger.info("初始化容量数据处理结束,result:{}", result);
        stopWatch.stop();
        logger.info("初始化容量调用时间,millies:{}", stopWatch.getTotalTimeMillis());
        return result;
    }

}

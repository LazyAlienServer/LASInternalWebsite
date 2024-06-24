package com.las.lasbackenduser3000.utils.verificationCode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackenduser3000.config.RabbitmqConfig;
import com.las.lasbackenduser3000.model.bot.Params;
import com.las.lasbackenduser3000.model.bot.SendPrivateMsg;
import com.las.lasbackenduser3000.service.db.redis.impl.RedisToolsImpl;
import com.las.lasbackenduser3000.websocket.MyWebSocketClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyinuo
 */
@Component
public class VerificationCode {

    @Resource
    private MyWebSocketClient webSocketClient;

    public final RabbitTemplate rabbitTemplate;
    public final RedisToolsImpl redisTools;

    public VerificationCode(RabbitTemplate rabbitTemplate, RedisToolsImpl redisTools) {
        this.rabbitTemplate = rabbitTemplate;
        this.redisTools = redisTools;
    }

    /**
     * 验证码
     * @param mqMessage mq
     */
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_BOT_VERIFICATION})
    public void verificationCode(Message mqMessage){
        //get text,qq
        JSONObject mqMessageJsonObject = JSONObject.parseObject(new String(mqMessage.getBody()));
        String text = (String) mqMessageJsonObject.get("message");
        String qq = (String) mqMessageJsonObject.get("qq");

        //CAPTCHA Generation
        Random vc = new Random();
        String vc1 = "1234567890abcdefghijk#lm$opqr$stuvwxwzA~!BCDEF^GHIJKL@MNO(PQRSTU=VWXYZ";
        String vc3 = "";
        for (int i = 0; i < 20; i++) {
            //随机生成数，提供索引位置
            int a = vc.nextInt(69);
            //用get 和提供的索引找到相应位置的数据给变量
            vc3 += vc1.charAt(a);
        }

        //存redis
        redisTools.insert(qq,vc3,5, TimeUnit.MINUTES);

        //封装
        String message = text + "\n\n\n" + "你的验证码为:" + vc3;
        Params params = new Params();
        params.setUser_id(Long.parseLong(qq));
        params.setMessage(message);
        SendPrivateMsg sendPrivateMsg = new SendPrivateMsg("send_private_msg",params);

        //send
        webSocketClient.send(JSON.toJSONString(sendPrivateMsg));
    }
}

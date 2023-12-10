package com.friend.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.friend.entity.AlipayBean;
import com.friend.service.goodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/alipay")
public class AlipayController {

    //获取配置文件中的配置信息
    //应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    @Value("${alipay.appId}")
    private String appId;

    //商户私钥 您的PKCS8格式RSA2私钥
    @Value("${alipay.appPrivateKey}")
    private String privateKey;

    //支付宝公钥
    @Value("${alipay.alipayPublicKey}")
    private String publicKey;

    //服务器异步通知页面路径
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;

    //页面跳转同步通知页面路径
    @Value("${alipay.returnUrl}")
    private String returnUrl;

    //签名方式
    @Value("${alipay.signType}")
    private String signType;

    //字符编码格式
    @Value("${alipay.charset}")
    private String charset;

    //支付宝网关
    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;

    private final String format = "json";

    private goodService goodservice;

    //PC网页段支付，返回的是支付宝账号的登录页面
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(AlipayBean alipayBean) throws AlipayApiException {
        //模拟数据
        alipayBean.setOut_trade_no(UUID.randomUUID().toString().replaceAll("-", ""));
        alipayBean.setSubject("PAY YOUR BILL"); // Order name需要改成真实的商品名称
        alipayBean.setTotal_amount(String.valueOf(new Random().nextInt(100))); // 这是需要改成真实的交易金额,且传递的是String类型
        alipayBean.setBody("good description"); // good description需要改成真实的商品描述

        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, format, charset, publicKey, signType);
        //PC网页支付使用AlipayTradePagePayRequest传参，下面调用的是pageExecute方法
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

        // 调用SDK生成表单
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }

    /**
     * 手机扫码支付
     *
     * @return
     * @throws Exception
     */
//    @RequestMapping("/pay2")
//    @ResponseBody
//    public Result pay2(AlipayBean alipayBean) throws Exception {
//        //接口模拟数据
//        alipayBean.setOut_trade_no("20210817010101003");
//        alipayBean.setSubject("订单名称");
//        alipayBean.setTotal_amount(String.valueOf(new Random().nextInt(100)));
//        alipayBean.setBody("商品描述");
//
//        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, format, charset, publicKey, signType);
//        //扫码支付使用AlipayTradePrecreateRequest传参，下面调用的是execute方法
//        AlipayTradePrecreateRequest precreateRequest = new AlipayTradePrecreateRequest();
//        precreateRequest.setReturnUrl(returnUrl);
//        precreateRequest.setNotifyUrl(notifyUrl);
//        precreateRequest.setBizContent(JSON.toJSONString(alipayBean));
//
//        AlipayTradePrecreateResponse response = null;
//        try {
//            response = alipayClient.execute(precreateRequest);
//        } catch (AlipayApiException e) {
//            throw new Exception(String.format("下单失败 错误代码:[%s], 错误信息:[%s]", e.getErrCode(), e.getErrMsg()));
//        }
//
//        /*
//        {
//        "code": "10000",
//        "msg": "Success",
//        "out_trade_no": "815259610498863104",
//        "qr_code": "https://qr.alipay.com/bax09455sq1umiufbxf4503e"
//        }
//        */
//        if (!response.isSuccess()) {
//            throw new Exception(String.format("下单失败 错误代码:[%s], 错误信息:[%s]", response.getCode(), response.getMsg()));
//        }
//        // TODO 下单记录保存入库
//        // 返回结果，主要是返回 qr_code，前端根据 qr_code 进行重定向或者生成二维码引导用户支付
//        JSONObject jsonObject = new JSONObject();
//        //支付宝响应的订单号
//        String outTradeNo = response.getOutTradeNo();
//        jsonObject.put("outTradeNo", outTradeNo);
//        //二维码地址，页面使用二维码工具显示出来就可以了
//        jsonObject.put("qrCode", response.getQrCode());
//        return Result.success(jsonObject);
//    }

    @RequestMapping("/success")
    @ResponseBody
    public String success() {
        return "交易成功！";
    }

    @RequestMapping(value = "/index")
    public String payCoin() {
        return "index.html";
    }
}

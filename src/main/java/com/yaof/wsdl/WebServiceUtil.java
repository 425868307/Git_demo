package com.yaof.wsdl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * webservice 接口调用示例
 *
 * @author Administrator
 */

public class WebServiceUtil {

    private static Logger logger = LoggerFactory.getLogger(WebServiceUtil.class);

    /**
     * webservice 当参数只有一个时的调用方法
     *
     * @param name        QName-wsdl文件相应接口name
     * @param typeQName   wsdl文件相应接口的入参,可为null
     * @param returnType  相应接口的返回类型的出参,可为null
     * @param paramClazz  入参类型
     * @param returnClazz 出参类型
     * @return OperationDesc对象
     * @author yaofang
     */
    public static OperationDesc getOperationDesc(QName name, QName typeQName,
                                                 QName returnType, Class<?> paramClazz, Class<?> returnClazz) {
        OperationDesc oper = new OperationDesc();

        ParameterDesc param = new ParameterDesc(name,
                ParameterDesc.IN,
                typeQName,
                paramClazz, false, false);
        oper.addParameter(param);
        oper.setReturnType(returnType);
        oper.setReturnClass(returnClazz);
        oper.setUse(Use.LITERAL);
        return oper;
    }

    /**
     * webservice 入参QName和出参QName缺省方法  webservice参数只有一个
     *
     * @param name
     * @param paramClazz
     * @param returnClazz
     * @return
     * @author yaofang
     */
    public static OperationDesc getOperationDesc(QName name, Class<?> paramClazz, Class<?> returnClazz) {
        return getOperationDesc(name, null, null, paramClazz, returnClazz);
    }

    /**
     * webservice 入参有多个的调用方法
     *
     * @param paramList   参数list 元素是Object数组,存一组入参数据
     * @param returnType  返回参数QName
     * @param returnClazz 返回类型
     * @return
     * @author yaofang
     */
    public static OperationDesc getOperationDesc(List<Object[]> paramList,
                                                 QName returnType, Class<?> returnClazz) {
        OperationDesc oper = new OperationDesc();

        ParameterDesc param = null;
        for (Object[] pars : paramList) {
            param = new ParameterDesc(
                    (QName) pars[0],
                    ParameterDesc.IN,
                    (QName) pars[1],
                    (Class<?>) pars[2], false, false);
            oper.addParameter(param);
        }
        oper.setReturnType(returnType);
        oper.setReturnClass(returnClazz);
        oper.setUse(Use.LITERAL);
        return oper;
    }

    /**
     * 取得客户端的Call对象
     *
     * @param uri           webservice的url
     * @param operationDesc
     * @param qName
     * @return Call
     */
    public static Call getClientCall(String uri, OperationDesc operationDesc, QName qName) {
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();

            call.setTargetEndpointAddress(uri);
            call.setOperation(operationDesc);
            call.setOperationName(qName);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return call;
    }

    /**
     * @param uri           webservice的url
     * @param operationDesc
     * @param operationName 接口方法
     * @Description 取得客户端的Call对象
     * @return Call
     */
    public static Call getClientCall(String uri, OperationDesc operationDesc, String operationName) {
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();

            call.setTargetEndpointAddress(uri);
            call.setOperation(operationDesc);
            call.setOperationName(operationName);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return call;
    }

    /**
     * 取得客户端的Call对象
     *
     * @param uri           webservice的url
     * @param operationDesc 参数对象
     * @param qName         接口方法QName对象
     * @param soapActionURI namespace+接口名
     * @return Call
     * @author yaofang
     */
    public static Call getClientCall(String uri, OperationDesc operationDesc,
                                     QName qName, String soapActionURI) {
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();

            call.setTargetEndpointAddress(uri);
            call.setOperation(operationDesc);
            call.setOperationName(qName);
            call.setSOAPActionURI(soapActionURI);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return call;
    }

    /**
     * @param url           webservice的wsdl地址url
     * @param operationName         接口方法QName对象
     * @param params namespace+接口名
     * @Description 取得客户端的Call对象
     * @return Call
     * @author yaofang
     */
    public static Call getClientCall(String url, String operationName,
                                     Map<String, QName> params, QName outQName) {
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();

            call.setTargetEndpointAddress(url);        //
            call.setOperationName(operationName);
            Set<Entry<String, QName>> paramSet = params.entrySet();
            for (Entry<String, QName> param : paramSet) {
                call.addParameter(param.getKey(),
                        param.getValue(),
                        javax.xml.rpc.ParameterMode.IN);// 接口的参数
            }
            call.setReturnType(outQName);// 设置返回类型
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return call;
    }

    /**
     * @param paramName 接口参数名
     * @param url       webservice的wsdl地址url
     * @param namespace 接口namespace
     * @param method    接口方法名
     * @Description 取得客户端的Call对象, 适用于1个参数且类型为String, 返回值为String
     * @return Call
     * @author yaofang
     */
    public static Call getClientCall(String paramName, String url, String namespace, String method) {
        OperationDesc oper = new OperationDesc();
        ParameterDesc param = new ParameterDesc(new QName("", paramName),
                ParameterDesc.IN,
                XMLType.XSD_STRING,
                String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(XMLType.XSD_STRING);
        oper.setReturnClass(String.class);
        oper.setUse(Use.LITERAL);

        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();

            call.setOperation(oper);
            call.setTargetEndpointAddress(url);        //
            call.setOperationName(new QName(namespace, method));
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return call;
    }

    /**
     * 调用webservice返回Object,适用于参数只有一个且参数类型为String
     *
     * @param paramName 参数名
     * @param url       webservice
     * @param namespace 命名空间
     * @param method    方法名
     * @param reqParam  请求此接口的参数
     * @return
     * @author yaofang
     * @date 2018年8月1日
     */
    public static Object sendRequest(String paramName,
                                     String url, String namespace, String method, String reqParam) {
        OperationDesc oper = new OperationDesc();
        ParameterDesc param = new ParameterDesc(new QName("", paramName),
                ParameterDesc.IN,
                XMLType.XSD_STRING,
                String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(XMLType.XSD_STRING);
        oper.setReturnClass(String.class);
        oper.setUse(Use.LITERAL);

        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();

            call.setOperation(oper);
            call.setTargetEndpointAddress(url);        //
            call.setOperationName(new QName(namespace, method));
            return call.invoke(new Object[]{reqParam});
        } catch (ServiceException e1) {
            logger.error(e1.getMessage(), e1);
        } catch (RemoteException e2) {
            logger.error(e2.getMessage(), e2);
        }
        return null;
    }

    /**
     * 调用webservice返回Object,适用于参数只有一个且参数类型为对象类型
     *
     * @param paramName 参数名
     * @param url       webservice
     * @param namespace 命名空间
     * @param method    方法名
     * @param paramClazz  请求此接口的参数
     * @return
     * @author yaofang
     * @date 2018年8月1日
     */
    public static Object sendRequest(String paramName, String url, String namespace,
                                     String method, Class<?> paramClazz, Class<?> returnClazz, Object obj) throws Exception {
        OperationDesc oper = new OperationDesc();
        ParameterDesc param = new ParameterDesc(new QName("", paramName),
                ParameterDesc.IN,
                null,
                paramClazz, false, false);
        oper.addParameter(param);
        oper.setReturnType(null);
        oper.setReturnClass(returnClazz);
        oper.setUse(Use.LITERAL);

        Service service = new Service();
        Call call = (Call) service.createCall();

        call.setOperation(oper);
        call.setEncodingStyle(null);
        call.setTargetEndpointAddress(url);
        call.setOperationName(new QName(namespace, method));
        return call.invoke(new Object[]{obj});
    }


    public static void main(String[] args) {

        Object result = sendRequest("trainsRequest",
                "http://10.3.14.215:7003/sale/services/LAAgentInfos",
                "http://10.3.14.215:7003/sale/services/LAAgentInfos",
                "trains", "{\"transRequest\":{\"requestDate\":\"2021-06-07 14:05:56\",\"Managecom\":\"\"," +
                        "\"ManagecomName\":\"\",\"Branchattr\":\"30030001003A\",\"Agentcode\":\"\",\"TransType\":\"activity\"}}");
        System.out.println(result);
        JSONObject resObj = JSONObject.parseObject(result.toString());
        JSONObject response = resObj.getJSONObject("transResponse");
        if (response != null) {
            String code = response.getString("code");
            if ("0".equals(code)) {
                JSONObject datas = response.getJSONObject("data");
                if(datas != null) {
                    String teamList = datas.getString("teamList");
                    System.out.println(teamList);
                }
            }
        }
    }
}

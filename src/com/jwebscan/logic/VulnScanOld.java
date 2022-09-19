package com.jwebscan.logic;

import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.ExecutionResults;

/**
 * @author Basil
 * @create 2022/4/27 7:35
 */
public class VulnScanOld {
    public void getResult(String param){
        //创建对象与nmap建立连接，相对路径
        Nmap4j nmap4j = new Nmap4j( "doc/nmap-7.92" ) ;
        //需要扫描的主机ip，可查询范围192.168.1.1-255
        nmap4j.includeHosts(param);
        //不需要扫描的主机范围
        //nmap4j.excludeHosts("192.168.1.1-246");
        //扫描规则
        nmap4j.addFlags( "-sV --script vuln") ;
        try {
            nmap4j.execute();
            ExecutionResults executionResults = nmap4j.getExecutionResults();
            if( !nmap4j.hasError() ) {
                //输出扫描结果
                System.out.println(executionResults.getOutput());
            } else {
                System.out.println( executionResults.getErrors() ) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//                //解析String类型的xml
//                String xmlContent ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><school><student><id>0001</id></student></school>";
//                Document xmldoc = DocumentHelper.parseText(returnData);
//                //得到根节点
//                Element rootEl = xmldoc.getRootElement();
//                if(rootEl != null){
//                    Element addrEl = rootEl.element("host").element("address");
//                    if(addrEl != null){
//                        //扫描到的ip
//                        String scanIp = addrEl.attributeValue("addr");
//                        if(scanIp.equals(ip)){
//                            log.info("ip匹配成功，开始解析...");
//                            //ip类型
//                            String ipType = StringUtils.isEmpty(addrEl.attributeValue("addrtype"))?"":"地址:"+addrEl.attributeValue("addrtype")+" ";
//                            //获取所有子节点的跟节点，xpath
//                            //对比环境之后发现，dom4j 的版本不一样
//                            List<Element> ports = rootEl.selectNodes("//ports/port").stream().filter(node->node instanceof Element).map(node->(Element)node).collect(Collectors.toList());
//                            if(ports.size()>0){
//                                for(Element el:ports){
//                                    Map<String,Object> portMap = new HashMap<>();
//                                    //协议
//                                    String agreement = el.attributeValue("protocol");
//                                    //端口号
//                                    String port = el.attributeValue("portid");
//
//                                    Element stateEl = el.element("state");
//                                    //状态
//                                    String state = StringUtils.isEmpty(stateEl.attributeValue("state"))?"":"状态:"+stateEl.attributeValue("state")+" ";
//                                    //reason
//                                    String reason = StringUtils.isEmpty(stateEl.attributeValue("reason"))?"":"reason:"+stateEl.attributeValue("reason")+" ";
//                                    //reason_ttl
//                                    String reasonTTL = StringUtils.isEmpty(stateEl.attributeValue("reason_ttl"))?"":"reason_ttl:"+stateEl.attributeValue("reason_ttl")+" ";
//
//                                    Element serviceEl = el.element("service");
//                                    //服务名
//                                    String servicename = StringUtils.isEmpty(serviceEl.attributeValue("name"))?"":"服务名:"+serviceEl.attributeValue("name")+" ";
//                                    //product
//                                    String product = StringUtils.isEmpty(serviceEl.attributeValue("product"))?"":"product:"+serviceEl.attributeValue("product")+" ";
//                                    //操作系统
//                                    String ostype = StringUtils.isEmpty(serviceEl.attributeValue("ostype"))?"":"操作系统:"+serviceEl.attributeValue("ostype")+" ";
//                                    //主机名
//                                    String hostname = StringUtils.isEmpty(serviceEl.attributeValue("hostname"))?"":"主机名:"+serviceEl.attributeValue("hostname")+" ";
//                                    //附加信息
//                                    String extrainfo = StringUtils.isEmpty(serviceEl.attributeValue("extrainfo"))?"":"附加信息:"+serviceEl.attributeValue("extrainfo")+" ";
//                                    portMap.put("id", JAXWSUtils.getUUID());
//                                    portMap.put("ip",ip);
//                                    portMap.put("wcode","12");
//                                    portMap.put("website","Nmap扫描工具");
//                                    portMap.put("port",port);
//                                    portMap.put("agreement",agreement);
//                                    String other = hostname + ostype + state + ipType + servicename + extrainfo + product + reason + reasonTTL;
//                                    portMap.put("other",other);
//                                    result.add(portMap);
//                                }
//                                log.info("解析数据成功...");
//                            }
//                        }
//                    }
//
//                }else {
//                    log.info("解析数据为空...");
//                }


//                        while (!returnIfo.get(i).contains("Service Info")) {
//                            if (returnIfo.get(i).contains("80/tcp")) {
//                                String[] s = returnIfo.get(i).split(" ");
//                                for (int j = 1; j < s.length; j++) {
//                                    if (!"".equals(s[j])) {
//                                        s[1] = s[j];
//                                        break;
//                                    }
//                                }
//                                tm.addRow(new Object[]{s[0], s[1], "SQL注入"});
//                            }
//                            i++;
//                        }
    }
}

package com.abs.wfs.workman.dao.query.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLManager {
    private static final String ROOT = "Record";


    public List<Map<String, String>> getXMLtoListMap(String xml) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
        NodeList nodeList = doc.getElementsByTagName(ROOT);

        System.out.println("#################### >> getXMLtoListMap");
        System.out.println("NODE List Size >> " + nodeList.getLength());

        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            Node recordNode = nodeList.item(i);
            Map<String, String> resultSetMap = new HashMap<String,String>();

            if(recordNode.getNodeName() != null) {
                NodeList resultSetItems = recordNode.getChildNodes();
                for(int j = 0; j < resultSetItems.getLength(); j++) {
                    Node resultSetItem = resultSetItems.item(j);
                    resultSetMap.put(resultSetItem.getNodeName(), resultSetItem.getTextContent());
                }
                resultList.add(resultSetMap);
            }
        }


        return resultList;

    }

    public List<Map<String, String>> getXMLtoListMap(String xml, String TagName) throws SAXException, IOException, ParserConfigurationException {
        System.out.println("#################### >> xml");
        System.out.println(xml);
        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
        NodeList nodeList = doc.getElementsByTagName(TagName);

        System.out.println("#################### >> getXMLtoListMap");
        System.out.println("NODE List Size >> " + nodeList.getLength());

        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            Node recordNode = nodeList.item(i);
            Map<String, String> resultSetMap = new HashMap<String,String>();

            if(recordNode.getNodeName() != null) {
                NodeList resultSetItems = recordNode.getChildNodes();
                for(int j = 0; j < resultSetItems.getLength(); j++) {
                    Node resultSetItem = resultSetItems.item(j);
                    resultSetMap.put(resultSetItem.getNodeName(), resultSetItem.getTextContent());
                }
                resultList.add(resultSetMap);
            }
        }


        return resultList;

    }

//	public List<Map<String, Object>> getWorkEndListByLotMap(String xml) throws SAXException, IOException, ParserConfigurationException {
//		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//
//		DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
//
//        NodeList nodeList = doc.getElementsByTagName("lot");
//
//
//        return resultList;
//	}

    public List<Map<String, Object>> getTrackOutDicingMap(String xml) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
        NodeList nodeList = doc.getElementsByTagName("procCellInfo");

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Map<String, Object> resultSetMap = new HashMap<String,Object>();
            List<Map<String, String>> unitList = new ArrayList<Map<String, String>>();

            if(node.getNodeName() != null) {
                NodeList childNodeList = node.getChildNodes();

                for(int j = 0; j < childNodeList.getLength(); j++) {
                    Node childNode = childNodeList.item(j);

                    if("unitList".equals(childNode.getNodeName())) {
                        Map<String, String> unitMap = new HashMap<String, String>();

                        NodeList unitNodeList = childNode.getChildNodes();

                        for(int k = 0; k < unitNodeList.getLength(); k++) {
                            Node unit = unitNodeList.item(k);
                            unitMap.put(unit.getNodeName(), unit.getTextContent());
                        }
                        unitList.add(unitMap);
                    } else {
                        resultSetMap.put(childNode.getNodeName(), childNode.getTextContent());
                    }
                }
            }

            if(unitList.size() > 0) {
                resultSetMap.put("unitList", unitList);
            }

            resultList.add(resultSetMap);
        }

        return resultList;
    }


    public List<Map<String, Object>> getDcolMap(String xml) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
        NodeList nodeList = doc.getElementsByTagName("dcollData");

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Map<String, Object> resultSetMap = new HashMap<String,Object>();
            List<Map<String, String>> paraNameList = new ArrayList<Map<String, String>>();

            if(node.getNodeName() != null) {
                NodeList childNodeList = node.getChildNodes();

                for(int j = 0; j < childNodeList.getLength(); j++) {
                    Node childNode = childNodeList.item(j);

                    if("paraName".equals(childNode.getNodeName())) {
                        Map<String, String> paraName = new HashMap<String, String>();

                        NodeList paraNameNodeList = childNode.getChildNodes();

                        for(int k = 0; k < paraNameNodeList.getLength(); k++) {
                            Node unit = paraNameNodeList.item(k);
                            paraName.put(unit.getNodeName(), unit.getTextContent());
                        }
                        paraNameList.add(paraName);
                    } else {
                        resultSetMap.put(childNode.getNodeName(), childNode.getTextContent());
                    }
                }
            }

            if(paraNameList.size() > 0) {
                resultSetMap.put("paraNameList", paraNameList);
            }

            resultList.add(resultSetMap);
        }

        return resultList;
    }

    public Map<String, Object> getWorkEndInfo(String xml) throws SAXException, IOException, ParserConfigurationException {

        Map<String, Object> result = new HashMap<String,Object>();

        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
        NodeList nodeList = doc.getElementsByTagName("lot");

        List<String> lotIdList = new ArrayList<String>();
        List<String> ppIdList = new ArrayList<String>();
        List<Map<String, String>> slotList = new ArrayList<Map<String,String>>();
        List<Map<String, String>> filteredSlotList = new ArrayList<Map<String,String>>();


        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            NodeList childNodeList = node.getChildNodes();
            System.out.println(childNodeList.getLength());
            for(int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);

                String childNodeName = childNode.getNodeName();
                String lotId = "";
                String ppId = "";
                switch (childNodeName) {
                    case "lotId":
                        lotId = childNode.getFirstChild().getTextContent();
                        lotIdList.add(lotId);
                        break;
                    case "ppId":
                        try {
                            ppId = childNode.getFirstChild().getTextContent();
                            ppIdList.add(ppId);
                            break;
                        } catch(Exception e) {
                            ppIdList.add("");
                            break;
                        }
                    case "slotMap":
                        Map<String,String> slotMap = new HashMap<String,String>();
                        NodeList slotMapNode = childNode.getChildNodes();
                        slotMap.put("lotId", lotIdList.get(i));
                        slotMap.put("ppId", ppIdList.get(i));
                        for(int nodeCnt = 0; nodeCnt < slotMapNode.getLength(); nodeCnt++) {
                            Node slot = slotMapNode.item(nodeCnt);
                            slotMap.put(slot.getNodeName(), slot.getTextContent());
                        }
                        slotList.add(slotMap);
                        break;
                }
            }
        }

        Set<String> lotSet = new HashSet<String>(lotIdList);
        List<String> distinctLotList =new ArrayList<String>(lotSet);

        Set<String> ppIdSet = new HashSet<String>(ppIdList);
        List<String> distinctPpIdList =new ArrayList<String>(ppIdSet);

        result.put("lotIdList", distinctLotList.toArray(new String[0]));
        result.put("ppIdList", distinctPpIdList.toArray(new String[0]));

        List<String> prodMtrlList = new ArrayList<String>();
        for(Map<String,String> slot : slotList) {
            String slotProdMtrlId = slot.get("prodMtrlId");

            if(!prodMtrlList.contains(slotProdMtrlId)) {
                prodMtrlList.add(slotProdMtrlId);
                filteredSlotList.add(slot);
            }
        }

        result.put("slotList", filteredSlotList);

        return result;
    }

    public Map<String, Object> getWorkEndInfoUnpacking(String xml) throws SAXException, IOException, ParserConfigurationException {

        Map<String, Object> result = new HashMap<String,Object>();

        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
        NodeList nodeList = doc.getElementsByTagName("lot");

        List<String> lotIdList = new ArrayList<String>();
        List<String> ppIdList = new ArrayList<String>();
        List<Map<String, String>> slotList = new ArrayList<Map<String,String>>();
        List<Map<String, String>> filteredSlotList = new ArrayList<Map<String,String>>();


        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            NodeList childNodeList = node.getChildNodes();
            System.out.println(childNodeList.getLength());
            for(int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);

                String childNodeName = childNode.getNodeName();
                String lotId = "";
                String ppId = "";
                switch (childNodeName) {
                    case "lotId":
                        lotId = childNode.getFirstChild().getTextContent();
                        lotIdList.add(lotId);
                        break;
                    case "ppId":
                        try {
                            ppId = childNode.getFirstChild().getTextContent();
                            ppIdList.add(ppId);
                            break;
                        } catch(Exception e) {
                            ppIdList.add("");
                            break;
                        }
                    case "slotMap":
                        Map<String,String> slotMap = new HashMap<String,String>();
                        NodeList slotMapNode = childNode.getChildNodes();
                        slotMap.put("lotId", lotIdList.get(i));
                        slotMap.put("ppId", ppIdList.get(i));
                        for(int nodeCnt = 0; nodeCnt < slotMapNode.getLength(); nodeCnt++) {
                            Node slot = slotMapNode.item(nodeCnt);
                            slotMap.put(slot.getNodeName(), slot.getTextContent());
                        }
                        slotList.add(slotMap);
                        break;
                }
            }
        }

        Set<String> lotSet = new HashSet<String>(lotIdList);
        List<String> distinctLotList =new ArrayList<String>(lotSet);

        Set<String> ppIdSet = new HashSet<String>(ppIdList);
        List<String> distinctPpIdList =new ArrayList<String>(ppIdSet);

        result.put("lotIdList", distinctLotList.toArray(new String[0]));
        result.put("ppIdList", distinctPpIdList.toArray(new String[0]));

//        List<String> prodMtrlList = new ArrayList<String>();
//        for(Map<String,String> slot : slotList) {
//            String slotProdMtrlId = slot.get("prodMtrlId");
//
//            if(!prodMtrlList.contains(slotProdMtrlId)) {
//                prodMtrlList.add(slotProdMtrlId);
//                filteredSlotList.add(slot);
//            }
//        }

        result.put("slotList", slotList);

        return result;
    }

}

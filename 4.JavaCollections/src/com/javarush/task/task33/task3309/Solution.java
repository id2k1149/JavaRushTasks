package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);

        return writer.toString().replaceAll("<" + tagName + ">", "<!--" + comment + "-->" + "\n" + "<" + tagName + ">").replaceAll("<" + tagName + "/>", "<!--" + comment + "-->" + "\n" + "<" + tagName + "/>");
    }

    public static void main(String[] args) throws JAXBException {
//        System.out.println(toXmlWithComment(new Shop(), "profit", "some comment"));
        System.out.println(toXmlWithComment(new First(), "second", "it's a comment"));
    }


    /*
    <?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of <second>]]></second>
    <!--it's a comment-->
    <second/>
    <forth>
        <!--it's a comment-->
        <second>some string</second>
        <!--it's a comment-->
        <second><![CDATA[need CDATA because of <second>]]></second>
    </forth>
    <fifth><![CDATA[need CDATA because of "]]></fifth>
</first>
     */
}

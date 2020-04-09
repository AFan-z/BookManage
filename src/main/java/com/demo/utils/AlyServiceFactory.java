package com.demo.utils;

import com.demo.mapper.analysis.bookAnalysis;
import com.demo.mapper.analysis.userAnalysis;
import org.yu.myorm.core.dynproxy.MapperInvoHander;

import java.lang.reflect.Proxy;

public class AlyServiceFactory {
    public static userAnalysis getUserAnalysisService() {
        return (userAnalysis) Proxy.newProxyInstance(userAnalysis.class.getClassLoader(), new Class[]{com.demo.mapper.analysis.userAnalysis.class},new MapperInvoHander());
    }

    public static bookAnalysis getBookAnalysisService() {
        return (bookAnalysis) Proxy.newProxyInstance(bookAnalysis.class.getClassLoader(), new Class[]{com.demo.mapper.analysis.bookAnalysis.class},new MapperInvoHander());
    }

}

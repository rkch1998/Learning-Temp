package com.connect.DB.entities;

public class Function {
    private final String schema;
    private final String functionName;
     public Function(String schema, String functionName){
         this.schema = schema;
         this.functionName = functionName;
     }

     public String getSchema(){
         return schema;
     }

     public String getFunctionName(){
         return functionName;
     }

}

package com.connect.DB.service;

import com.connect.DB.data.DatabaseOperations;
import com.connect.DB.util.FunctionWriter;

public class FunctionProcessor {
    private final DatabaseOperations databaseOperations;
    private final FunctionWriter functionWriter;

    public FunctionProcessor(DatabaseOperations databaseOperations, FunctionWriter functionWriter){
        this.databaseOperations = databaseOperations;
        this.functionWriter = functionWriter;
    }

    public void processFunction(String function){
        String[] result = parseFunction(function);
        if(result.length == 2){
            String schema = result[0];
            String functionName = result[1];
            String queryResult = databaseOperations.executeQuery(schema, functionName);

            if(queryResult != null){
                functionWriter.writeToFile(queryResult, "result.txt");
            }else{
                System.out.println("Error executing the query");
            }

        } else{
            System.out.println("Invalid function name format");
        }
    }

    private String[] parseFunction(String function){
        function = function.replace("\"", "");
        return function.split("\\.");
    }
}

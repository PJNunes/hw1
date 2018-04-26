package ua.tqs.hw1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Pedro Nunes, nmec 59542
 */

public class Converter implements Serializable{
    
    private final JSONObject info;

    public Converter() {
        info=obtainInfo();
    }
    
    public JSONObject getInfo(){
        return info;
    }
    
    public List<String> getCoins(){
        List<String> result= new ArrayList<>();
        
        JSONObject coins=(JSONObject) info.get("quotes");
        JSONArray array = coins.names();

        for (int j = 0; j < array.length(); j++  ) {
            result.add(array.getString(j).substring(3));
        }
        java.util.Collections.sort(result);
        return result;
    }
    
    public String convert(String entryValue, String entryCurrency, String outCurrency){
        entryValue=entryValue.replace(",", ".");
        if(isFloat(entryValue)){
            float in=getConversion(entryCurrency);
            float out=getConversion(outCurrency);
            float result=Float.parseFloat(entryValue)/in;
            result*=out;
            return String.valueOf(round(result));
        }
        return null;
    }
    
    public float getConversion(String currency){
        return info.getJSONObject("quotes").getFloat("USD"+currency);
    }
    
    public static JSONObject obtainInfo(){   
        HttpURLConnection urlConnection = null;
 
        String info = null;
 
        try {
            URL url = new URL("http://www.apilayer.net/api/live?access_key=4a61b8f9cbf465a7c1a69b102ac46a22");
 
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
 
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            try( BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }
                info = buffer.toString();
            }
        } catch (IOException e) {
            //Do nothing
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        
        if (info == null)
            info=readFile();
        else
            saveFile(info.trim());
        
        return new JSONObject(info);
    }
    
    public static String readFile(){
        String result;
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("cache.txt"));
            result=new String(encoded, "utf-8");
        } catch (IOException ex) {
            result="";
        }
        return result.trim();
    }
    
    public static void saveFile(String info){
       try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("cache.txt"), "utf-8"))) {
            writer.write(info);
        } catch (IOException ex) {
            //Do nothing
        }
    }
    
    public boolean isFloat(String text){
        try{
            Float.parseFloat(text);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    
    public float round(float value) {
        int scale = (int) Math.pow(10, 2);
        return (float) Math.round(value * scale) / scale;
    }
}

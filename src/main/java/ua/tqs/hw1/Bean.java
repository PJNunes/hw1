package ua.tqs.hw1;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Pedro Nunes, nmec 59542
 */

@ManagedBean
@ApplicationScoped
public class Bean implements Serializable {
    
    private String entryValue;
    private String entryCurrency;
    private String outValue;
    private String outCurrency;
    private Converter converter;

    public Bean() {
        entryValue="";
        outValue="";
        converter=new Converter();
    }
    
    public String getEntryValue() {
        return entryValue;
    }

    public void setEntryValue(String entryValue) {
        this.entryValue = entryValue;
    }

    public String getEntryCurrency() {
        return entryCurrency;
    }

    public void setEntryCurrency(String entryCurrency) {
        this.entryCurrency = entryCurrency;
    }

    public String getOutValue() {
        return outValue;
    }

    public void setOutValue(String outValue) {
        this.outValue = outValue;
    }
    
    public String getOutCurrency() {
        return outCurrency;
    }

    public void setOutCurrency(String outCurrency) {
        this.outCurrency = outCurrency;
    }
    
    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }
    
    public List<String> getCoins(){
        return converter.getCoins();
    }
    
    public String convert(){
        entryValue=entryValue.replace(",", ".");
        if(isFloat(entryValue)){
            String result=converter.convert(entryValue, entryCurrency, outCurrency);
            setOutValue(result);
        }
        else{
            setOutValue("ERROR");
        }
        return "main.xhtml";
    }
    
    public boolean isFloat(String text){
        try{
            Float.parseFloat(text);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}

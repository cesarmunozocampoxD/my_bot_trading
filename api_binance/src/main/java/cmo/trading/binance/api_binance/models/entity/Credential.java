package cmo.trading.binance.api_binance.models.entity;
import org.springframework.stereotype.Service;

@Service
public class Credential {
    
    private String api_key;
    private String api_secret;
    private Boolean login = false;

    public void createBitsoCredentials(String apiKey,String apiSecret){
        this.api_key = apiKey;
        this.api_secret = apiSecret;
        login = true;
    }
    
    public String API_KEY(){
        return api_key;
    }

    public String API_SECRET(){
        return api_secret;
    }

    public Boolean isLogin(){
        return login;
    }


}

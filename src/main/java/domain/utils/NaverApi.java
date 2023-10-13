package domain.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import domain.member.dto.MemberInfo;

public class NaverApi {
	
	public static MemberInfo naverInfoByAccesToken(String token) {
        // 토큰 받아서 memberInfo 저장해서 리턴 
		MemberInfo memberInfo = null;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            
            URL url = new URL(apiURL);
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            
            int responseCode = con.getResponseCode();
            
            BufferedReader br;
            
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            
            br.close();
            
            JSONParser parser = new JSONParser();
            
            JSONObject res = (JSONObject) parser.parse(response.toString());
            
            JSONObject object = (JSONObject) res.get("response");
            
            String email = (String) object.get("email");
            String name = (String) object.get("name");
            String nickName = (String) object.get("nickname");
            String tel = (String) object.get("mobile");
            
            memberInfo = new MemberInfo();
            memberInfo.setEmail(email);
            memberInfo.setName(name);
            memberInfo.setNickName(nickName);
            memberInfo.setTel(tel);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return memberInfo;
    }
}

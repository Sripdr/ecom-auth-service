package in.ecom.client;

import in.ecom.dto.userinfo.UserInfoRequest;
import in.ecom.dto.userinfo.UserInfoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("http://ECOM-USERINFO-SERVICE/api/ecom/user-info")
public interface UserInfoClient {
    @PostExchange
       void saveUserInfo(@RequestBody UserInfoRequest userInfoRequest);

    @GetExchange("/userid/{userId}")
    UserInfoResponse getUserInfoByUserId(@PathVariable String userId);

    @PatchExchange("/firstname/{userId}")
    UserInfoResponse updateFirstName(@PathVariable String userId, @RequestBody String firstName);

    @PatchExchange("/lastname/{userId}")
    UserInfoResponse updateLastName(@PathVariable String userId, @RequestBody String lastName);
}

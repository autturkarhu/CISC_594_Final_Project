package blog;

import com.blog.pojo.AccessToken;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface AccessTokensRepository extends CrudRepository<AccessToken, String> {
    List<AccessToken> findByRole(String role);
}